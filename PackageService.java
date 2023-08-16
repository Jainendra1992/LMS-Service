package com.website.lms.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;



@Service
public class PackageService {
	@Autowired
	private com.website.lms.Repository.PackageRepository packageRepository;
	
	public com.website.lms.entity.Package save(com.website.lms.entity.Package package1) {
		 System.err.println("came to service");
		return packageRepository.save(package1);
	}
	public Iterable<com.website.lms.entity.Package> getByName()
	{
		System.err.println("getting data from H2-consloe");
		
		return packageRepository.findAll();
	}
	
	public String delete()
	{
		packageRepository.deleteAll();
		return "success";
	}
	
	//Read Particular Data
	public com.website.lms.entity.Package getById(Integer id)
	{
		return packageRepository.findById(id).get();
	}
	//Delete Particular Data
	public String deleteById(Integer id)
	{
		packageRepository.deleteById(id);
		return "success";
	}

}