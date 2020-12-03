package com.example.demo.controller;


import com.example.demo.pojo.Student;
import com.example.demo.service.StudentService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lihu
 * @since 2020-12-03
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentService studentService;

    @RequestMapping("/save")
    public String save(){
        for (int i=0;i<10;i++){
            Student student=new Student();
            student.setId(i+10);
            student.setName("瓜田李下 "+i);
            student.setAge(i+10);

            studentService.save(student);
        }

        return "success";
    }
}

