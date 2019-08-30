package com.bitgroupware.approval.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bitgroupware.approval.beans.ApprovalDocumentDto;
import com.bitgroupware.approval.beans.ApprovalDto;
import com.bitgroupware.approval.persistence.ApprovalDocumentDao;
import com.bitgroupware.approval.service.ApprovalDocService;
import com.bitgroupware.approval.service.ApprovalService;

@Controller
public class ApprovalController {
	
	@Autowired
	private ApprovalService approvalService;
	
	@Autowired
	private ApprovalDocService approvalDocService;
	
	// 임시 MAIN
	@RequestMapping("/main")
	public String main() {
		return "approval/main";
	}
	
	// 문서 폼
	@RequestMapping("/selectApprovalDocList")
	public String selectApprovalDocList(Model model) {
		List<ApprovalDocumentDto> approvalDocList = approvalDocService.selectApprovalDocList();
		model.addAttribute("approvalDocList",approvalDocList);
		return "approval/approvalDocTypeList";
	}
	
	// 기안 폼
	@RequestMapping("/insertApprovalView")
	public String insertApprovalView(ApprovalDto apDto,Model model) {
		System.out.println(apDto.toString());
		List<?> signlist=null;
		if(apDto.getApdocNo()==null) { // 신규
			apDto.setApDocstatus("1"); // 신규일때는 상태를 대기로 저장(문서상태:1)
			ApprovalDocumentDto docType = approvalDocService.selectApprovalDoc(apDto.getApdocNo()); // 관리자 - 결재문서 번호 뽑아옴
			apDto.setApdocNo(docType.getApdocNo()); //결재문서 번호 저장
			apDto.setApContent(docType.getApdocForm());
			String memId="임시값";
			apDto.setMemId(memId);
		}else {
			apDto = approvalService.selectApproval(apDto); 
		}
		model.addAttribute("apdocDto",apDto);
		return "approval/approvalWrite";
	}
	
	// 등록
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
}
