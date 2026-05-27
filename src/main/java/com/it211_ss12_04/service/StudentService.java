package com.it211_ss12_04.service;

import com.it211_ss12_04.exception.StudentNotFoundException;
import com.it211_ss12_04.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private final List<Student> students = new ArrayList<>();
    private Long currentId = 1L;

    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    public Student getStudentById(Long id) {
        return students.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));
    }

    public Student addStudent(Student student) {
        student.setId(currentId++);
        students.add(student);
        return student;
    }

    public Student updateStudent(Long id, Student updatedStudent) {
        Student existing = getStudentById(id);
        existing.setStudentCode(updatedStudent.getStudentCode());
        existing.setFullName(updatedStudent.getFullName());
        existing.setMajor(updatedStudent.getMajor());
        existing.setGpa(updatedStudent.getGpa());
        return existing;
    }

    public void deleteStudent(Long id) {
        students.remove(getStudentById(id));
    }
}