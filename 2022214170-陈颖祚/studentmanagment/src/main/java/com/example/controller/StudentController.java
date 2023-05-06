package com.example.controller;

import com.example.dao.StudentDAO;
import com.example.entity.SearchData;
import com.example.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private StudentDAO studentDAO = new StudentDAO();

    @GetMapping("/getall")
    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable("id") int id) {
        return studentDAO.getStudentById(id);
    }

    @PostMapping("/search")
    public List<Student> searchStudent(@RequestBody SearchData searchData) {
        return studentDAO.searchStudent(searchData);
    }

    @PostMapping("/add")
    public void addStudent(@RequestBody Student student) {
        studentDAO.addStudent(student);
    }

    @PutMapping("/{id}")
    public void updateStudent(@PathVariable("id") int id, @RequestBody Student student) {
        var stu = studentDAO.getStudentById(id);
        if (stu == null) {
            return;
        }
        studentDAO.updateStudent(student);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable("id") int id) {
        var stu = studentDAO.getStudentById(id);
        studentDAO.deleteStudent(stu);
    }
}