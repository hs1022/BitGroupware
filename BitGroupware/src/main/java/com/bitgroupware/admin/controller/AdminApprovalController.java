package com.bitgroupware.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bitgroupware.approval.service.ApprovalService;
import com.bitgroupware.approval.vo.ApprovalDoucemtDto;
import com.bitgroupware.community.service.NoticeService;

/**
 * @Title	ApprovalController
 * @Author 	홍한솔
 * @Date	2019. 8. 20.
 */

@Controller
public class AdminApprovalController {
	
	@Autowired
	private ApprovalService approvalService;
	
	// 문서리스트
	@RequestMapping("/selectApprovalDocList")
	public String selectApprovalDocList(HttpServletRequest request,ModelMap model) {
		
		List<ApprovalDoucemtDto> approvalDocList = approvalService.selectApprovalDocList();
		model.addAttribute("approvalDocList",approvalDocList);
		
		return "admin/approval/approvalDocList";
	}
	
	// 등록페이지
	@RequestMapping("/insertApprovalDocView")
	public String insertApprovalDocView(HttpServletRequest request,ModelMap model,ApprovalDoucemtDto apdocDto) {
		
		if(apdocDto.getApdocNo() != null) {
			apdocDto = approvalService.selectApprovalDoc(apdocDto.getApdocNo());
			model.addAttribute("apdocDto",apdocDto);
		}
		return "admin/approval/approvalDocWrite";
	}
	
	
	// 등록
	public String insertApprovalDoc(HttpServletRequest request,ModelMap model,ApprovalDoucemtDto apdocDto) {
		approvalService.insertApprovalDoc(apdocDto);
		return "redirect:/admin/approval/approvalDocList";
	}
	
	
	// 수정
	@RequestMapping("/updateApprovalDoc")
	public String updateApprovalDocList(HttpServletRequest request,ModelMap model,ApprovalDoucemtDto apdocVo) {
		
		return "admin/approval/approvalDocUpdate";
	}
	
	// 삭제
	@RequestMapping("/deleteApprovalDoc")
	public String deleteApprovalDocList(HttpServletRequest request,ModelMap model,ApprovalDoucemtDto apdocVo) {
		
		return "admin/approval/approvalDocDelete";
	}
}
