package com.glp.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.glp.comment.GenderSelect;
import com.glp.comment.TitleSelect;
import com.glp.pojo.Administrator;
import com.glp.pojo.PageBean;
import com.glp.pojo.Student;
import com.glp.pojo.Teacher;
import com.glp.service.AdminService;
import com.glp.service.StudentService;
import com.glp.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Resource
    AdminService adminService;

    @Resource
    TeacherService teacherService;

    @Resource
    StudentService studentService;

    //注册用户
    @PostMapping("/registerServlet")
    public void register(HttpServletRequest req, HttpServletResponse resp, Administrator u) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");
        Writer writer = resp.getWriter();
        int ret =adminService.register(u);
        if(ret == 0) {
            System.out.println("注册失败！");
            writer.write("<h2> 注册失败 </h2>" );
        }else {
            System.out.println("注册成功!");
            resp.sendRedirect("/edumanage/login.html");
        }
    }

    //登录用户
    @PostMapping("/loginServlet")
    public Object login(HttpServletRequest req, HttpServletResponse resp,Administrator admin) throws UnsupportedEncodingException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");

        String userid = req.getParameter("admin_id");
        String password = req.getParameter("password");

        System.out.println("=====================");
        System.out.println(userid);
        System.out.println(password);
        System.out.println("=====================");
        //管理员
        Administrator administrator = new Administrator();
        administrator.setAdmin_id(userid);
        administrator.setPassword(password);

        //导师
        Teacher teacher = new Teacher();
        teacher.setTeacher_id(userid);
        teacher.setPassword(password);

        //学生
        Student student = new Student();
        student.setStudent_id(userid);
        student.setPassword(password);

        Administrator admin1 = adminService.login(administrator);
        Teacher tea = teacherService.login(teacher);
        Student stu = studentService.login(student);
        //还有一个学生的


        Map<String ,Integer> return_map = new HashMap<>();

        if (admin1!=null){
            //说明数据库中有，显示登录成功，把登录信息存入session
            req.getSession().setAttribute("user",admin);
            //返回给登录页面json数据,0跳转到管理员界面
            return_map.put("msg",0);
        }else if(tea!=null){
            req.getSession().setAttribute("teacherUser",tea);
            //返回给登录页面json数据,1跳转到导师主页
            return_map.put("msg",1);
        }else if(stu!=null){
            req.getSession().setAttribute("studentUser",stu);
            //返回给登录页面json数据，跳转到学生主页
            return_map.put("msg",2);
        }else{
            //账号或密码错误
            return_map.put("msg",4);
        }
        return return_map;
    }

//====================导师管理模块=============================

    //分页+模糊查询
    @PostMapping("/findByPageServlet")
    public Object findUserByPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");

        //rows,currentPage,name,teacher_id
        String rows = req.getParameter("rows");
        String currentPage = req.getParameter("currentPage");
        String name = req.getParameter("name");
        String teacher_id = req.getParameter("teacher_id");

        Integer rowsInt = Integer.valueOf(rows);
        Integer currentPageInt = Integer.valueOf(currentPage);


        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("rows",rowsInt);
        map.put("currentPage",currentPageInt);
        map.put("tname",name);
        map.put("teacher_id",teacher_id);

        PageBean<Teacher> allByPage = adminService.findAllByPage(map);


        return allByPage;
    }

    //添加导师
    @PostMapping("/addServlet")
    public Object addServlet(HttpServletRequest req,HttpServletResponse resp) throws IOException, ParseException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        String teacher_id = req.getParameter("teacher_id");
        String tname = req.getParameter("tname");
        String age = req.getParameter("age");
        String password = req.getParameter("password");
        String gender = req.getParameter("gender");
        String title = req.getParameter("title");
        String from_date = req.getParameter("from_date");

        Teacher u = new Teacher();
        u.setPassword(password);;
        u.setTeacher_id(teacher_id);
        u.setAge(Integer.valueOf(age));
        u.setTname(tname);
        u.setGender(Integer.valueOf(gender));
        u.setTitle(Integer.valueOf(title));
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //需要捕获异常
        u.setFrom_date(dateFormat.parse(from_date));

        int ret = adminService.add(u);

        Map<String,Object> returnMap = new HashMap<>();
        if(ret == 1) {
            returnMap.put("msg",true);
        }else {
            returnMap.put("msg",false);
        }
        return returnMap;
    }

    //删除一个导师
    @GetMapping("/deleteServlet")
    public void deleteUser(HttpServletRequest req,HttpServletResponse resp,int id) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        int result = adminService.delete(id);
        if(result==1){
            System.out.println("删除成功！！！");
            resp.sendRedirect("/edumanage/teacher.html");
        }else{
            resp.sendRedirect("/edumanage/teacher.html");
        }

    }

    //查看要修改的对象是否存在，如果存在就将该对象存入session中
    @GetMapping("/findUserServlet")
    public void findIfNeedUpdate(HttpServletRequest req,HttpServletResponse resp,int id) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        Teacher user = adminService.find(id);

        if(user == null) {
            System.out.println("没有要修改的对象！");
        }else {
            //将要修改的对象存入session中
            req.getSession().setAttribute("updateTeacher",user);
            resp.sendRedirect("/edumanage/updateTeacher.html");
        }
    }

    //在更新用户的时候，通过访问returnServlet拿到要修改的user对象，并将该对象填入前端界面中
    @PostMapping("/returnServlet")
    public Object returnFront(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        Teacher updateUser = (Teacher)req.getSession().getAttribute("updateTeacher");

        return updateUser;
    }

    //如果该用户存在，就可以通过update.html,进入/updateServlet
    @PostMapping("/updateServlet")
    public Object updateUser(HttpServletRequest req,HttpServletResponse resp) throws IOException, ParseException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        String teacher_id = req.getParameter("teacher_id");
        String tname = req.getParameter("tname");
        String age = req.getParameter("age");
        String password = req.getParameter("password");
        String gender = req.getParameter("gender");
        String title = req.getParameter("title");
        String from_date = req.getParameter("from_date");



        Teacher u = new Teacher();
        u.setPassword(password);;
        u.setTeacher_id(teacher_id);
        u.setAge(Integer.valueOf(age));
        u.setTname(tname);
        u.setGender(Integer.valueOf(gender));
        u.setTitle(Integer.valueOf(title));
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //需要捕获异常
        u.setFrom_date(dateFormat.parse(from_date));

        //还要从session中拿到要修改的id
        Teacher sessionUser = (Teacher)req.getSession().getAttribute("updateTeacher");
        u.setId(sessionUser.getId());

        int ret = adminService.update(u);
        Map<String,Object> return_map=new HashMap<>();
        if (ret==1){
            return_map.put("msg",true);
        }else{
            return_map.put("msg",false);
        }
        return return_map;
    }

    //通过controller直接写，删除选中的所有用户，也是通过service中的delete方法，会传过来的id数组,逐个删除
    @PostMapping("/deleteSelectedServlet")
    public Object deleteAllSelect(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        //获取数组id
        String[] values = req.getParameterValues("id[]");

        int sum=0;
        Map<String,Object> map=new HashMap<>();
        for(int i=0;i<values.length;i++){
            int j = Integer.parseInt(values[i]);
            //调用Service层方法删除
            int delete = adminService.delete(j);
            sum=sum+delete;
        }
        if(sum==values.length){
            //证明删除成功
            map.put("msg",true);
        }else {
            map.put("msg",false);
        }
        return map;
    }


    //====================学生管理模块=============================
    //分页+模糊查询
    @PostMapping("/findByPageStudent")
    public Object findUserByPageStudent(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");

        //rows,currentPage,student_id,sname,tname,states
        String rows = req.getParameter("rows");
        String currentPage = req.getParameter("currentPage");
        String student_id = req.getParameter("student_id");
        String sname = req.getParameter("sname");
        String tname = req.getParameter("tname");
        String states = req.getParameter("states");

        Integer rowsInt = Integer.valueOf(rows);
        Integer currentPageInt = Integer.valueOf(currentPage);


        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("rows",rowsInt);
        map.put("currentPage",currentPageInt);
        map.put("student_id",student_id);
        map.put("sname",sname);
        map.put("tname",tname);
        map.put("states",states);

        System.out.println("=====================");
        System.out.println(map);
        System.out.println("=====================");

        PageBean<Student> allByPage = adminService.findAllByPageStudent(map);


        return allByPage;
    }

    //添加学生
    @PostMapping("/addStudent")
    public Object addServletStudent(HttpServletRequest req,HttpServletResponse resp) throws IOException, ParseException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        String student_id = req.getParameter("student_id");
        String sname = req.getParameter("sname");
        String teacher_id = req.getParameter("teacher_id");

        Teacher teacher = adminService.find(Integer.valueOf(teacher_id));

        Student u = new Student();
        u.setStudent_id(student_id);
        u.setPassword("123");
        u.setSname(sname);
        u.setTeacher_id(Integer.valueOf(teacher_id));
        u.setTname(teacher.getTname());
        u.setStates(0);
        u.setMessage("");
        System.out.println("+++++++++++++++++++");
        System.out.println(u);
        System.out.println("+++++++++++++++++++");

        int ret = adminService.addStudent(u);

        Map<String,Object> returnMap = new HashMap<>();
        if(ret == 1) {
            returnMap.put("msg",true);
        }else {
            returnMap.put("msg",false);
        }
        return returnMap;
    }

    //删除一个导师
    @GetMapping("/deleteStudent")
    public void deleteStudent(HttpServletRequest req,HttpServletResponse resp,int id) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        int result = adminService.deleteStudent(id);
        if(result==1){
            System.out.println("删除成功！！！");
            resp.sendRedirect("/edumanage/student.html");
        }else{
            resp.sendRedirect("/edumanage/student.html");
        }

    }

    //查看要修改的对象是否存在，如果存在就将该对象存入session中
    @GetMapping("/findUserStudent")
    public void findIfNeedUpdateStudent(HttpServletRequest req,HttpServletResponse resp,int id) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        Student user = adminService.findStudent(id);

        if(user == null) {
            System.out.println("没有要修改的对象！");
        }else {
            //将要修改的对象存入session中
            req.getSession().setAttribute("updateStudent",user);
            resp.sendRedirect("/edumanage/updateStudent.html");
        }
    }

    //在更新用户的时候，通过访问returnServlet拿到要修改的user对象，并将该对象填入前端界面中
    @PostMapping("/returnStudent")
    public Object returnFrontStudent(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        Student updateUser = (Student)req.getSession().getAttribute("updateStudent");

        return updateUser;
    }

    //如果该用户存在，就可以通过update.html,进入/updateServlet
    @PostMapping("/updateStudent")
    public Object updateStudent(HttpServletRequest req,HttpServletResponse resp) throws IOException, ParseException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        String student_id = req.getParameter("student_id");
        String password = req.getParameter("password");
        String sname = req.getParameter("sname");
        String teacher_id = req.getParameter("teacher_id");
        String tname = req.getParameter("tname");



        Student u = new Student();
        u.setStudent_id(student_id);
        u.setPassword(password);
        u.setSname(sname);
        u.setTeacher_id(Integer.valueOf(teacher_id));
        u.setTname(tname);

        //还要从session中拿到要修改的id
        Student sessionUser = (Student)req.getSession().getAttribute("updateStudent");
        u.setId(sessionUser.getId());

        int ret = adminService.updateStudent(u);
        Map<String,Object> return_map=new HashMap<>();
        if (ret==1){
            return_map.put("msg",true);
        }else{
            return_map.put("msg",false);
        }
        return return_map;
    }

    //通过controller直接写，删除选中的所有用户，也是通过service中的delete方法，会传过来的id数组,逐个删除
    @PostMapping("/deleteSelectedStudent")
    public Object deleteAllSelectStudent(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        //获取数组id
        String[] values = req.getParameterValues("id[]");

        int sum=0;
        Map<String,Object> map=new HashMap<>();
        for(int i=0;i<values.length;i++){
            int j = Integer.parseInt(values[i]);
            //调用Service层方法删除
            int delete = adminService.deleteStudent(j);
            sum=sum+delete;
        }
        if(sum==values.length){
            //证明删除成功
            map.put("msg",true);
        }else {
            map.put("msg",false);
        }
        return map;
    }

    //文件下载
    @RequestMapping("/downStudent")
    public String fileDownLoad(HttpServletRequest req,HttpServletResponse response){
        String id = req.getParameter("id");
        Student student = studentService.findStudent(Integer.valueOf(id));

        File file = new File(student.getMessage());

        if(!file.exists()){
            return "下载文件不存在";
        }
        response.reset();
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment;filename="+student.getSname()+".pdf");

        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));) {
            byte[] buff = new byte[1024];
            OutputStream os  = response.getOutputStream();
            int i = 0;
            while ((i = bis.read(buff)) != -1) {
                os.write(buff, 0, i);
                os.flush();
            }
        } catch (IOException e) {
            return "下载失败";
        }
        return "下载成功";
    }

    //答辩结果审核
    @RequestMapping("/saveStudent")
    public void resultCheck(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        Student student = studentService.findStudent(Integer.valueOf(id));
        req.getSession().setAttribute("checkStudent",student);
        resp.sendRedirect("/edumanage/selectState.html");
    }

    //答辩状态变更
    @RequestMapping("/determinState")
    public Object determinResult(HttpServletRequest req,HttpServletResponse resp){
        String states= req.getParameter("states");
        Student stu = (Student)req.getSession().getAttribute("checkStudent");

        stu.setStates(Integer.valueOf(states));
        int ret = studentService.updateStates(stu);
        HashMap<String,Object> map = new HashMap<>();
        if(ret==1){
            map.put("msg",true);
        }else{
            map.put("msg",false);
        }
        return map;
    }

}
