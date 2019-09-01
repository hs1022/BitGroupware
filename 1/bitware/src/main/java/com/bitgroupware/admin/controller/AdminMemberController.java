package com.bitgroupware.admin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitgroupware.company.vo.DepartmentVo;
import com.bitgroupware.company.vo.RanksVo;
import com.bitgroupware.company.vo.TeamVo;
import com.bitgroupware.member.service.MemberService;
import com.bitgroupware.member.utils.Role;
import com.bitgroupware.member.vo.MemberVo;

@Controller
@RequestMapping("/admin")
public class AdminMemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private PasswordEncoder encoder;
	
	// 사원 리스트
	@RequestMapping("/selectMemberList")
	public String selectMemberList(Model model, MemberVo memberVo) {
		List<MemberVo> memberList = memberService.selectMemberList(memberVo);
		
		model.addAttribute("memberList", memberList);
		return "admin/member/memberList";
	}
	
	// 사원 등록 페이지
	@RequestMapping("/insertMemberView")
	public String insertMemberView(Model model, DepartmentVo departmentVo, RanksVo ranksVo) {
		List<DepartmentVo> deptList = memberService.selectDeptList(departmentVo);
		List<RanksVo> rankList = memberService.selectRanksList(ranksVo);
		String curdate = memberService.selectCurdate();
		
		model.addAttribute("deptList", deptList);
		model.addAttribute("rankList", rankList);
		model.addAttribute("curdate", curdate);
		return "admin/member/memberInsert";
	}
	
	// 비동기로 Team명 가져오기
	@RequestMapping("/selectTeamByDept")
	@ResponseBody
	public List<String> selectTeamByDept(String deptName) {
		List<TeamVo> teamList = memberService.selectTeamList(deptName);
		List<String> teamName = new ArrayList<String>();
		for(int i = 0; i < teamList.size(); i++) {
			teamName.add(teamList.get(i).getTeamName());
		}
		return teamName;
	}
	
	// 사원 등록
	@RequestMapping("/insertMember")
	public String insertMember(MemberVo memberVo) {
		String curdate = memberService.selectCurdate();
		String memCount = memberService.selectCountMember();
		String memId = curdate.replace("-", "") + (String.format("%3s", memCount)).replace(" ", "0"); // 사원번호 부여
		
		memberVo.setMemId(memId);
		memberVo.setMemPw(encoder.encode(memId)); // 초기 비밀번호 = 사번
		switch (memberVo.getRanks().getRanksNo()) {
		case 1:
			memberVo.setRole(Role.ROLE_USER);
			break;
		case 2:
			memberVo.setRole(Role.ROLE_PL);
			break;
		case 3:
		case 4:
		case 5:
			memberVo.setRole(Role.ROLE_PM);
			break;
		default:
			memberVo.setRole(Role.ROLE_USER);
		}
		
		memberService.insertMember(memberVo);
		return "redirect:selectMemberList";
	}
	
	// 사원 수정 페이지
	@RequestMapping("/updateMemberView")
	public String updateMemberView(Model model, String memId, DepartmentVo departmentVo, RanksVo ranksVo) {
		MemberVo member = memberService.selectMember(memId);
		List<DepartmentVo> deptList = memberService.selectDeptList(departmentVo);
		List<RanksVo> rankList = memberService.selectRanksList(ranksVo);
		String curdate = memberService.selectCurdate();
		
		model.addAttribute("deptList", deptList);
		model.addAttribute("rankList", rankList);
		model.addAttribute("curdate", curdate);
		model.addAttribute("member", member);
		return "admin/member/memberUpdate";
	}

	// 사원 수정
	@RequestMapping("/updateMember")
	public String updateMember(MemberVo memberVo) {
		switch (memberVo.getRanks().getRanksNo()) {
		case 1:
			memberVo.setRole(Role.ROLE_USER);
			break;
		case 2:
			memberVo.setRole(Role.ROLE_PL);
			break;
		case 3:
		case 4:
		case 5:
			memberVo.setRole(Role.ROLE_PM);
			break;
		default:
			memberVo.setRole(Role.ROLE_USER);
		}
		
		memberService.updateMember(memberVo);
		return "redirect:selectMemberList";
	}
}
