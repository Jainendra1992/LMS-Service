package com.website.lms.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.website.lms.Repository.SkillRepository;
import com.website.lms.entity.Skill;

@Service
public class SkillService {

	@Autowired
	private SkillRepository skillRepository;
	
	public Skill save(Skill skill1)
	{
		return skillRepository.save(skill1);
	}
	
	public Iterable<Skill> get()
	{
		return skillRepository.findAll();
	}
	public String delete()
	{
		skillRepository.deleteAll();
		return "success";
	}
	public Skill getById(Integer id)
	{
		return skillRepository.findById(id).get();
	}
	public String deleteById(Integer id)
	{
		 skillRepository.findById(id);
		 return "success";
	}
	
}
