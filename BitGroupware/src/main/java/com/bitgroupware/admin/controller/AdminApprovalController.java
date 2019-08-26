package com.bitgroupware.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bitgroupware.approval.service.ApprovalService;
import com.bitgroupware.approval.vo.ApprovalDoucemtDto;

/**
 * @Title	ApprovalController
 * @Author 	홍한솔
 * @Date	2019. 8. 20.
 */

@Controller
@RequestMapping("/admin")
public class AdminApprovalController {
	
	@Autowired
	private ApprovalService approvalService;
	
	// 문서리스트
	@RequestMapping("/selectApprovalDocList")
	public String selectApprovalDocList(Model model) {
		
		List<ApprovalDoucemtDto> approvalDocList = approvalService.selectApprovalDocList();
		model.addAttribute("approvalDocList",approvalDocList);
		
		return "admin/approval/approvalDocList";
	}
	
	// 등록페이지(insert+update)
	@RequestMapping("/insertApprovalDocView")
	public String insertApprovalDocView(Model model,ApprovalDoucemtDto apdocDto) {
		
		if(apdocDto.getApdocNo() != null) { // 수정할 때 필요 해서  Dto 가져감
			apdocDto = approvalService.selectApprovalDoc(apdocDto.getApdocNo());
			model.addAttribute("apdocDto",apdocDto);
		}
		return "admin/approval/approvalDocWrite";
	}
	
	
	// 등록(insert+update)
	@RequestMapping("/insertApprovalDoc")
	public String insertApprovalDoc(Model model,ApprovalDoucemtDto apdocDto) {
		approvalService.insertApprovalDoc(apdocDto);
		return "redirect:/admin/selectApprovalDocList";
	}
	
	
	// 삭제
	@RequestMapping("/deleteApprovalDoc")
	public String deleteApprovalDocList(Model model,ApprovalDoucemtDto apdocDto) {
		approvalService.deleteApprovalDoc(apdocDto);
		return "redirect:/admin/selectApprovalDocList";
	}
}
