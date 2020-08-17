package com.glp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.glp.pojo.PageBean;
import com.glp.pojo.User;

import com.glp.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
    @Resource
    private UserServiceImpl userService;

    //注册用户
    @RequestMapping("/registerServlet")
    public void register(HttpServletRequest req, HttpServletResponse resp,User u) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");
        Writer writer = resp.getWriter();
        int ret =userService.register(u);
        if(ret == 0) {
            System.out.println("注册失败！");
            writer.write("<h2> 注册失败 </h2>" );
        }else {
            System.out.println("注册成功!");
            resp.sendRedirect("/usermanagers/login.html");
        }
    }

    //登录用户
    @RequestMapping("/loginServlet")
    public void login(HttpServletRequest req, HttpServletResponse resp,User u) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");

        System.out.println("---------------------------"+req.getContextPath());

        User user = userService.login(u);

        Map<String ,Object> return_map = new HashMap<>();
        if (user!=null){
            //说明数据库中有，显示登录成功，把登录信息存入session
            req.getSession().setAttribute("user",user);
            //返回给登录页面json数据
            return_map.put("msg",true);
        }else{
            System.out.println("账号或密码错误");
            //账号或密码错误
            return_map.put("msg",false);
        }
        ObjectMapper mapper = new ObjectMapper(); //利用Jackson将map转化为json对象
        mapper.writeValue(resp.getWriter(),return_map);
    }

    //分页+模糊查询
    @RequestMapping("/findByPageServlet")
    public void findUserByPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");

        //rows,currentPage,name,address,email
        String rows = req.getParameter("rows");
        String currentPage = req.getParameter("currentPage");
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        String email = req.getParameter("email");

        Integer rowsInt = Integer.valueOf(rows);
        Integer currentPageInt = Integer.valueOf(currentPage);

        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("rows",rowsInt);
        map.put("currentPage",currentPageInt);
        map.put("name",name);
        map.put("address",address);
        map.put("email",email);
        System.out.println("===================*********************************8888888888888888================================");
        PageBean<User> allByPage = userService.findAllByPage(map);

        //传给前端
        ObjectMapper mapper=new ObjectMapper();
        mapper.writeValue(resp.getWriter(),allByPage);
    }

    //添加一个用户
    @RequestMapping("/addServlet")
    public void addServlet(HttpServletRequest req,HttpServletResponse resp,User u) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        int ret = userService.add(u);

        Map<String,Object> returnMap = new HashMap<>();
        if(ret == 1) {
            returnMap.put("msg",true);
        }else {
            returnMap.put("msg",false);
        }
        //把登录成功的map返回给前端。json      : 便于前端进行处理。
        ObjectMapper objectMapper = new ObjectMapper();
        //就是将returnMap，转换为json字符串
        objectMapper.writeValue(resp.getWriter(),returnMap);
    }

    //删除一个用户
    @RequestMapping("/deleteServlet")
    public String deleteUser(HttpServletRequest req,HttpServletResponse resp,int id) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        int result = userService.delete(id);
        if(result==1){
            System.out.println("删除成功！！！");
        }else{
            resp.getWriter().write("<h2 删除失败！>" + "</h2>");
        }
        return "redirect:/list.html";
    }

    //查看要修改的对象是否存在，如果存在就将该对象存入session中
    @RequestMapping("/findUserServlet")
    public String findIfNeedUpdate(HttpServletRequest req,HttpServletResponse resp,int id) throws UnsupportedEncodingException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        User user = userService.find(id);
        if(user == null) {
            System.out.println("没有要修改的对象！");
        }else {
            //将要修改的对象存入session中
            req.getSession().setAttribute("updateUser",user);
            return "redirect:/update.html"; //通过update.html调用updateServlet
        }
        return "redirect:/list.html";
    }

    //在更新用户的时候，通过访问returnServlet拿到要修改的user对象，并将该对象填入前端界面中
    @RequestMapping("/returnServlet")
    public void returnFront(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        Object updateUser = req.getSession().getAttribute("updateUser");

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(resp.getWriter(),updateUser);
    }

    //如果该用户存在，就可以通过update.html,进入/updateServlet
    @RequestMapping("/updateServlet")
    public void updateUser(HttpServletRequest req,HttpServletResponse resp,User user) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        //还要从session中拿到要修改的id
        User sessionUser = (User)req.getSession().getAttribute("updateUser");
        int id = sessionUser.getId();
        user.setId(id);

        int ret = userService.update(user);
        Map<String,Object> return_map=new HashMap<>();
        if (ret==1){
            return_map.put("msg",true);
        }else{
            return_map.put("msg",false);
        }
        ObjectMapper mapper=new ObjectMapper();
        mapper.writeValue(resp.getWriter(),return_map);
    }

    //通过controller直接写，删除选中的所有用户，也是通过service中的delete方法，会传过来的id数组,逐个删除
    @RequestMapping("/deleteSelectedServlet")
    public void deleteAllSelect(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        //获取数组id
        String[] values = req.getParameterValues("id[]");

        int sum=0;
        Map<String,Object> map=new HashMap<>();
        for(int i=0;i<values.length;i++){
            int j = Integer.parseInt(values[i]);
            //调用Service层方法删除
            int delete = userService.delete(j);
            sum=sum+delete;
        }
        if(sum==values.length){
            //证明删除成功
            map.put("msg",true);
        }else {
            map.put("msg",false);
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(resp.getWriter(),map);
    }

}
