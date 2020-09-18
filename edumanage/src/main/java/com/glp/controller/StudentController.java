package com.glp.controller;


import com.glp.comment.StateSelect;
import com.glp.pojo.Student;
import com.glp.pojo.Teacher;
import com.glp.service.StudentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Value("${student.file.local-path}")
    private String localpath;

    @Resource
    StudentService studentService;

    //查看要修改的对象是否存在，如果存在就将该对象存入session中
    @PostMapping("/returnStudent")
    public Object findIfNeedUpdate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        Student student = (Student)req.getSession().getAttribute("studentUser");
        student.setState(StateSelect.valueOf(student.getStates()).getStr());
        return student;
    }

    @RequestMapping("/fileUpload")
    public Object fileUpload(HttpServletRequest req,HttpServletResponse resp,@RequestParam("file") MultipartFile file){

        if(file.isEmpty()){
             return false;
        }
        String fileName = file.getOriginalFilename();
        int size = (int) file.getSize();

        Student studentUser = (Student)req.getSession().getAttribute("studentUser");
        String stuid = studentUser.getStudent_id();
        String sname = studentUser.getSname();
        String path =localpath +"/" +  stuid+"/" + sname+".pdf";
        File dest = new File(path);

        studentUser.setMessage(path);

        if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest); //保存文件
            studentUser.setStates(1);
            studentService.updatePath(studentUser);
            resp.sendRedirect("/edumanage/studentAdmin.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @RequestMapping("/fileDownload")
    public String fileDownLoad(HttpServletRequest req,HttpServletResponse response){
        Student studentUser = (Student)req.getSession().getAttribute("studentUser");
        Student student = studentService.findStudent(studentUser.getId());

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
}
