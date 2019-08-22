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
	
	/*
	 * // 상세
	 * 
	 * @RequestMapping("/selectDoc") public String
	 * selectApprovalDoc(HttpServletRequest request,ModelMap model,int apdocNo) {
	 * 
	 * 
	 * return "admin/approval/approvalDoc"; }
	 */
	
	// 등록
	@RequestMapping("/insertApprovalDocView")
	public String insertApprovalDoc(HttpServletRequest request,ModelMap model,ApprovalDoucemtDto apdocVo) {
		
		if(apdocVo.getApdocNo() != null) {
			apdocVo = approvalService.selectApprovalDoc(apdocVo.getApdocNo());
//			model.addAttribute( )
		}
		return "admin/approval/approvalDocWrite";
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
