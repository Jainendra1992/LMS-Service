package com.website.lms.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.website.lms.Repository.StudentRepository;
import com.website.lms.entity.Student;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	public Student save(Student student)
	{
	 return	studentRepository.save(student);
	}
	public Iterable<Student> get()
	{
		return studentRepository.findAll();
	}
	public Student getById(@PathVariable Integer id)
	{
		return studentRepository.findById(id).get();
	}
	
	public Student update(@RequestBody Student student)
	{
		 return	studentRepository.save(student);
	}
	
}
