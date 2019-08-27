package com.bitgroupware.admin.controller;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bitgroupware.approval.service.ApprovalDocService;
import com.bitgroupware.approval.vo.ApprovalDoucemtDto;
import com.bitgroupware.approval.vo.ApprovalFileDto;

/**
 * @Title	ApprovalController
 * @Author 	홍한솔
 * @Date	2019. 8. 20.
 */

@Controller
@RequestMapping("/admin")
public class AdminApprovalController {
	
	@Autowired
	private ApprovalDocService approvalService;
	
	private static String UPLOAD_DIR = System.getProperty("user.dir") + "/src/main/resources/static/images/";
	
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
	public String insertApprovalDoc(Model model, ApprovalDoucemtDto apdocDto,ApprovalFileDto apfileDto) {
		
		
//		if (!file.getOriginalFilename().isEmpty()) {
//			String fileName = apdocFileDto.getFile().getOriginalFilename();
//			String path = request.getServletContext().getRealPath("upload");
//			apdocFileDto.getFile().transferTo(new File(path+fileName));
//			apdocFileDto.setApFileUrl(fileName);
//		}
		String apFileName = "Empty";
		
		if (!apfileDto.getFile().isEmpty()) {
			apFileName = apfileDto.getFile().getOriginalFilename();
			try {
				String path = UPLOAD_DIR;
				System.out.println("path"+path);
				apfileDto.getFile().transferTo(new File(path + apFileName));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		apfileDto.setApFileUrl(apFileName);
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
