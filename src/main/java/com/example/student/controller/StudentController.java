package com.example.student.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.student.model.Student;
import com.example.student.service.StudentService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService service;

    // save without image
    @PostMapping
    public Student save(@RequestBody Student student) {
        return service.saveStudent(student);
    }

    // get all students
    @GetMapping
    public List<Student> getAll() {
        return service.getAllStudents();
    }

    // search by id
    @GetMapping("/by-id/{id}")
    public Student getById(@PathVariable Long id) {
        return service.getStudentById(id);
    }

    // save with image
    @PostMapping(value = "/save-with-image", consumes = "multipart/form-data")
    public Student saveStudentWithImage(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam Integer age,
            @RequestParam MultipartFile image
    ) throws IOException {

        // 🔑 ABSOLUTE path inside container
        String uploadDir = "/app/uploads/students/";

        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();   // MUST
        }

        String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
        File dest = new File(uploadDir + fileName);

        image.transferTo(dest);

        Student student = new Student();
        student.setName(name);
        student.setEmail(email);
        student.setAge(age);

        // 🔑 only filename in DB
        student.setImagePath(fileName);

        return service.saveStudent(student);
    }

}
