package com.bitgroupware.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminMemberController {
	
	@RequestMapping("/selectMemberList")
	public String selectMemberList() {
		return "admin/member/memberList";
	}
	
	@RequestMapping("/insertMemberView")
	public String insertMemberView() {
		return "admin/member/memberInsert";
	}
	
	@RequestMapping("/insertMember")
	public String insertMember() {
		return "redirect:selectMemberList";
	}

}
