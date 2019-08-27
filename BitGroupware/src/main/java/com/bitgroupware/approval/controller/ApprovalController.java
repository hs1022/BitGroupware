package com.bitgroupware.approval.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.bitgroupware.approval.service.ApprovalService;

@Controller
public class ApprovalController {
	
	@Autowired
	private ApprovalService approvalService;
	
	// 결재 받을 문서 리스트
	public String selectApprovalListTobe(Model model) {
		
		List<?> approvalListTobe = approvalService.selectApprovalListTobe();
		model.addAttribute("approvalListTobe",approvalListTobe);
		return "approval/approvalTobeList";
		
	}
	
	// 결재 할 문서 리스트
	public String selectApprovalListTo() {
		return null;
	}
	
	// 기안View
	public String insertApprovalView() {
		return null;
	}
	
	// 기안
	public String insertApproval() {
		return null;
	}
	
	// 읽기
	public String approvalView() {
		return null;
	}
	
	// 삭제
	public String deleteApproval() {
		return null;
	}
	
	// 결재
	public String updateApproval() {
		return null;
	}
}
