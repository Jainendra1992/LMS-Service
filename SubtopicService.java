package com.website.lms.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.website.lms.Repository.SubtopicRepository;
import com.website.lms.entity.Subtopic;

@Service
public class SubtopicService {

	@Autowired
	private SubtopicRepository subtopicRepository;
	
	public Subtopic save (Subtopic subtopic)
	{
		return subtopicRepository.save(subtopic);
	}
	public List<Subtopic> get()
	{
		return (List<Subtopic>) subtopicRepository.findAll();
	}
	
	public String delete()
	{
		subtopicRepository.deleteAll();
		return "success";
	}
	
	public Subtopic getById(Integer id)
	{
		return  subtopicRepository.findById(id).get();
		
	}
	public String deleteById(Integer id)
	{
		 subtopicRepository.findById(id);
		 return"success";
	}
}
