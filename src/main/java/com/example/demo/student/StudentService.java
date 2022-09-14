package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @Autowired
    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
       Optional <Student> studentByEmail= studentRepository.finStudentByEmail(student.getEmail());
        if(studentByEmail.isPresent()){
            throw new IllegalStateException("email taken");
        }else{
            studentRepository.save(student);
        }



    }

    public void deleteStudent(Long studentId) {
        boolean exist=studentRepository.existsById(studentId);
        if(exist) {
            studentRepository.deleteById(studentId);
        }else{
            throw new IllegalStateException("Student with id: "+ studentId+ " not found to delete");
        }
    }

    @Transactional
    public void updateStudent(Long studentId,String name,String email){
        boolean exist=studentRepository.existsById(studentId);
        Student student=studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException("id not found"));

        if(name != null && !Objects.equals(student.getName(),name))
            student.setName(name);




        if( !studentRepository.finStudentByEmail(email).isPresent()){
            student.setEmail(email);
        }else{
            throw new IllegalStateException("email taken");
        }
        student.setEmail(email);





    }


}
