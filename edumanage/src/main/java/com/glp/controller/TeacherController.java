package com.glp.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.glp.comment.StateSelect;
import com.glp.pojo.PageBean;
import com.glp.pojo.Student;
import com.glp.pojo.Teacher;
import com.glp.service.StudentService;
import com.glp.service.TeacherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Resource
    TeacherService teacherService;

    @Resource
    StudentService studentService;

    @RequestMapping("/findStudents")
    public Object findStudents(HttpServletRequest req,HttpServletResponse resp){
        Teacher teacher =(Teacher)req.getSession().getAttribute("teacherUser");
        List<Student> studentList = teacherService.getStudents(teacher);

        System.out.println("================");
        System.out.println(studentList);
        System.out.println("================");

        for (Student stu:studentList) {
            stu.setState(StateSelect.valueOf(stu.getStates()).getStr());
        }
        return studentList;
    }

    //导师欢迎页面
    @RequestMapping("/findTeacher")
    public Object getTeacher(HttpServletRequest req,HttpServletResponse resp){
        Teacher teacher =(Teacher)req.getSession().getAttribute("teacherUser");
        return teacher;
    }


    //文件下载
    @RequestMapping("/fileDownload")
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

//    确认审核
    @RequestMapping("/checkServlet")
    public void checkResult(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        Student student = new Student();
        student.setId(Integer.valueOf(id));
        student.setStates(2);
        int ret = studentService.updateStates(student);
        if(ret==1){
            resp.sendRedirect("/edumanage/teacherAdmin.html");
        }else{
            resp.getWriter().write("更新失败");
        }

    }

//    撤销审核
    @RequestMapping("/uncheckServlet")
    public void uncheckResult(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        Student student = new Student();
        student.setId(Integer.valueOf(id));
        student.setStates(0);
        int ret = studentService.updateStates(student);
        if(ret==1){
            resp.sendRedirect("/edumanage/teacherAdmin.html");
        }else{
            resp.getWriter().write("更新失败");
        }
    }

}
