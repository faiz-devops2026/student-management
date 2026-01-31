package com.example.student.service;

import java.util.List;

import com.example.student.model.Student;

public interface StudentService {
	Student saveStudent(Student student);
    List<Student> getAllStudents();
    Student getStudentById(Long id);
}
