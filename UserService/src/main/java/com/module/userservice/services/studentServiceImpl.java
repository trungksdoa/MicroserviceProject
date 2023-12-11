package com.module.userservice.services;


import com.module.userservice.model.Student;
import com.module.userservice.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service("studentService")
@RequiredArgsConstructor
public class studentServiceImpl implements BaseMethodServices<Student,String> {


    private final StudentRepository studentRepository;
    @Override
    public List<Student> get() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> get(String id) {
        return studentRepository.findById(id);
    }

    @Override
    public Student save(Student value) {
        return studentRepository.saveAndFlush(value);
    }

    @Override
    public void delete(Student student) {
        studentRepository.delete(student);
    }

}
