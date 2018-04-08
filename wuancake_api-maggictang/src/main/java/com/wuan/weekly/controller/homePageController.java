package com.wuan.weekly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wuan.weekly.entity.Leave;
import com.wuan.weekly.entity.Main;
import com.wuan.weekly.service.homePageService;

@RestController
public class homePageController {

	@Autowired
	homePageService hps;
	
	@GetMapping("/")
	public Main home(
			@RequestParam(
					required=false, 
					name="id", 
					defaultValue="2") int user_id) {
		
		return hps.m(user_id);
	}


	@PostMapping("/leave")
	public Leave leave(Leave li) {
		//
		System.out.println(li);
		
		li.setStatus(200);
		li.setInfoText("请假成功");
		
		return li;
	}

}
