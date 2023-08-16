package com.website.lms.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.website.lms.Repository.TopicsRepository;
import com.website.lms.entity.Topics;

@Service
public class TopicsService {
	

	@Autowired
	private TopicsRepository topicsRepository;
	
	public Topics save(Topics topics)
	{
		return topicsRepository.save(topics);
	}
	
	public Iterable<Topics> get()
	{
		return topicsRepository.findAll();
	}
	public String delete()
	{
		topicsRepository.deleteAll();
		return "success";
	}
	public Topics getById(Integer id)
	{
		return topicsRepository.findById(id).get();
	}
	
	public String deleteByid(Integer id)
	{
		topicsRepository.findById(id);
		return "success";
	}
	
	
	
	
}
