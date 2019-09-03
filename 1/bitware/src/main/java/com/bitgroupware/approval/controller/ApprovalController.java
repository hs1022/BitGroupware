package com.bitgroupware.approval.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bitgroupware.approval.beans.ApprovalDocumentDto;
import com.bitgroupware.approval.beans.ApprovalDto;
import com.bitgroupware.approval.service.ApprovalDocService;
import com.bitgroupware.approval.service.ApprovalService;
import com.bitgroupware.member.vo.MemberVo;
import com.bitgroupware.security.config.SecurityUser;

@Controller
@RequestMapping("/user")
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
	
	@RequestMapping("/insertApprovalView")
	public String insertApprovalView(Model model, int apdocNo) {
		ApprovalDocumentDto approvalDocument = approvalDocService.selectApprovalDoc(apdocNo);
		model.addAttribute("approvalDocument",approvalDocument);
		return "approval/approvalWrite";
	}
	
	// 등록
	@RequestMapping("/insertApproval")
	public String insertApproval(ApprovalDto approval, @AuthenticationPrincipal SecurityUser principal) {
		approval.setApDeleteflag("N");
		approval.setApDocstatus("1");
		approval.setMemId(principal.getMember().getMemId());
		MemberVo member = principal.getMember();
		String memSignUrl = member.getMemSignUrl();
		int ranks = member.getRanks().getRanksNo();
		
		switch(ranks) {
		case 1 : approval.setApSignUrl1(memSignUrl);
				 approval.setApSignName1(member.getMemName());
			break;
		case 2 : approval.setApSignUrl2(memSignUrl);
				 approval.setApSignName2(member.getMemName());
			break;
		case 3 : approval.setApSignUrl3(memSignUrl);
				 approval.setApSignName3(member.getMemName());
			break;
		case 4 : approval.setApSignUrl4(memSignUrl);
				 approval.setApSignName4(member.getMemName());
			break;
		case 5 : approval.setApSignUrl5(memSignUrl);
				 approval.setApSignName5(member.getMemName());
			break;
		}
		approvalService.insertApproval(approval, principal.getMember());
		System.out.println("aaa"+approval);
		return "redirect:/user/selectApprovalListToBe";
	}
	
	// 결재 받을 문서 리스트
	@RequestMapping("/selectApprovalListToBe")
	public String selectApprovalListToBe(Model model, @AuthenticationPrincipal SecurityUser principal, String status) {
		String memId = principal.getMember().getMemId();
		List<ApprovalDto> approvalListToBe;
		if(status==""||status==null) {
			approvalListToBe = approvalService.selectApprovalListToBeByTotal(memId);
		}else {
			approvalListToBe = approvalService.selectApprovalListToBe(memId, status);
		}
		model.addAttribute("approvalListToBe",approvalListToBe);
		
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String today = format.format(date);
		model.addAttribute("today",today);
		
		return "approval/approvalListToBe";
	}
	
	// 결재 할 문서 리스트
	@RequestMapping("/selectApprovalListTo")
	public String selectApprovalListTo(Model model, @AuthenticationPrincipal SecurityUser principal) {
		String memId = principal.getMember().getMemId();
		List<ApprovalDto> approvalList = approvalService.selectApprovalListTo(memId);
		model.addAttribute("approvalList",approvalList);
		return "approval/approvalListTo";
	}

	// 읽기
	@RequestMapping("/selectApprovalView")
	public String selectApprovalView(Model model, String apNo, @AuthenticationPrincipal SecurityUser principal) {
		ApprovalDto approval = approvalService.selectApproval(apNo);
		model.addAttribute("approval", approval);
		String memId = principal.getMember().getMemId();
		int ranksNo = approvalService.selectRanksNo(memId);
		model.addAttribute("ranksNo",ranksNo);
		System.out.println(approval);
		return "approval/approvalDetail";
	}
	
	// 삭제
	@RequestMapping("/deleteApproval")
	public String deleteApproval(ApprovalDto approval) {
		approvalService.deleteApproval(approval);
		return "redirect:/user/selectApprovalListToBe";
	}
	
	// 결재
	@RequestMapping("/updateApprovalPath")
	public String updateApprovalPath(String apNo, int finalSign, int ranksNo, @AuthenticationPrincipal SecurityUser principal) {
		ApprovalDto approval = approvalService.selectApproval(apNo);
		MemberVo member = principal.getMember();
		if(ranksNo==finalSign) {
			approval.setApDocstatus("4");
			approval.setApSignpath(null);
			String memSignUrl = member.getMemSignUrl();
			int ranks = member.getRanks().getRanksNo();
			switch(ranks) {
			case 1 : approval.setApSignUrl1(memSignUrl);
					 approval.setApSignName1(member.getMemName());
				break;
			case 2 : approval.setApSignUrl2(memSignUrl);
					 approval.setApSignName2(member.getMemName());
				break;
			case 3 : approval.setApSignUrl3(memSignUrl);
					 approval.setApSignName3(member.getMemName());
				break;
			case 4 : approval.setApSignUrl4(memSignUrl);
					 approval.setApSignName4(member.getMemName());
				break;
			case 5 : approval.setApSignUrl5(memSignUrl);
					 approval.setApSignName5(member.getMemName());
				break;
			}
		}else {
			approval.setApDocstatus("2");
			approval.setApSignpath(member.getMemName());
			String memSignUrl = member.getMemSignUrl();
			int ranks = member.getRanks().getRanksNo();
			switch(ranks) {
			case 1 : approval.setApSignUrl1(memSignUrl);
					 approval.setApSignName1(member.getMemName());
				break;
			case 2 : approval.setApSignUrl2(memSignUrl);
					 approval.setApSignName2(member.getMemName());
				break;
			case 3 : approval.setApSignUrl3(memSignUrl);
					 approval.setApSignName3(member.getMemName());
				break;
			case 4 : approval.setApSignUrl4(memSignUrl);
					 approval.setApSignName4(member.getMemName());
				break;
			case 5 : approval.setApSignUrl5(memSignUrl);
					 approval.setApSignName5(member.getMemName());
				break;
			}
		}
		approvalService.updateApprovalPath(approval, member);
		return "redirect:/user/selectApprovalListToBe";
	}
	
	// 반려
	@RequestMapping("/updateApprovalCancel")
	public String updateApprovalCancel(String apNo, String apComment, @AuthenticationPrincipal SecurityUser principal) {
		ApprovalDto approval = approvalService.selectApproval(apNo);
		approval.setApDocstatus("3");
		approval.setApSignpath(null);
		approval.setApComment(apComment);
		MemberVo member = principal.getMember();
		int ranks = member.getRanks().getRanksNo();
		String memSignUrl = "/images/no.png";

		switch (ranks) {
		case 1:
			approval.setApSignUrl1(memSignUrl);
			approval.setApSignName1(member.getMemName());
			break;
		case 2:
			approval.setApSignUrl2(memSignUrl);
			approval.setApSignName2(member.getMemName());
			break;
		case 3:
			approval.setApSignUrl3(memSignUrl);
			approval.setApSignName3(member.getMemName());
			break;
		case 4:
			approval.setApSignUrl4(memSignUrl);
			approval.setApSignName4(member.getMemName());
			break;
		case 5:
			approval.setApSignUrl5(memSignUrl);
			approval.setApSignName5(member.getMemName());
			break;
		}
		System.out.println("!!!!"+approval);
		approvalService.updateApprovalCancel(approval);
		return "redirect:/user/selectApprovalListToBe";
	}
	
	
	
	
	
	
	@RequestMapping("/updateApprovalView")
	public String updateApprovalView(Model model,String apNo) {
		ApprovalDto approval = approvalService.selectApproval(apNo);
		model.addAttribute("approval",approval);
		return "approval/approvalUpdate";
	}
	
	@RequestMapping("/updateApproval")
	public String updateApproval(ApprovalDto approval) {
		approvalService.updateApproval(approval);
		return "redirect:/user/selectApprovalListToBe";
	}

}
