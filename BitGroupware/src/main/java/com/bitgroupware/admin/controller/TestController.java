package com.bitgroupware.admin.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitgroupware.community.persistence.MeetingroomRepository;
import com.bitgroupware.community.persistence.NoticeRepository;
import com.bitgroupware.community.vo.MeetingroomVo;
import com.bitgroupware.community.vo.NoticeVo;
import com.bitgroupware.company.persistence.DepartmentRepository;
import com.bitgroupware.company.persistence.RanksRepository;
import com.bitgroupware.company.persistence.TeamRepository;
import com.bitgroupware.company.vo.DepartmentVo;
import com.bitgroupware.company.vo.RanksVo;
import com.bitgroupware.company.vo.TeamVo;
import com.bitgroupware.member.persistence.MemberRepository;
import com.bitgroupware.member.utils.Role;
import com.bitgroupware.member.vo.MemberVo;

@Controller
public class TestController {
	
	@Autowired
	private MemberRepository memberRepo;
	@Autowired
	private NoticeRepository noticeRepo;
	@Autowired
	private MeetingroomRepository meetingroomRepo;
	@Autowired
	private DepartmentRepository departmentRepo;
	@Autowired
	private TeamRepository teamRepo;
	@Autowired
	private RanksRepository ranksRepo;
	@Autowired
	private PasswordEncoder encoder;

	@RequestMapping("/login")
	public void login() {
		
	}
	@RequestMapping("/loginSuccess")
	public void loginSuccess() {
		
	}
	
	@RequestMapping("/createNotice")
	@ResponseBody
	public String createNotice() {
		MemberVo admin = memberRepo.findById("admin").get();
		for(int i=1; i<=342; i++) {
			NoticeVo notice = new NoticeVo();
			notice.setNtCate("일반");
			notice.setNtTitle("글제목"+i);
			notice.setNtContent("글내용"+i);
			notice.setMember(admin);
			
			noticeRepo.save(notice);
		}
		return "공지사항 생성완료";
	}
	
	@RequestMapping("/createMeetingroom")
	@ResponseBody
	public String createMeetionroom() {
		for(int i=1; i<=4; i++) {
			MeetingroomVo mr = new MeetingroomVo();
			mr.setMrName("회의실"+i);
			mr.setMrMaxPerson(i*4);
			meetingroomRepo.save(mr);
		}
		return "회의실 생성완료";
	}
	
	@RequestMapping("/createCompany")
	@ResponseBody
	public String createCompany() {
		DepartmentVo department1 = new DepartmentVo();
		department1.setDeptName("지원부");
		departmentRepo.save(department1);
		DepartmentVo department2 = new DepartmentVo();
		department2.setDeptName("영업부");
		departmentRepo.save(department2);
		DepartmentVo department3 = new DepartmentVo();
		department3.setDeptName("개발부");
		departmentRepo.save(department3);
		
		DepartmentVo department4 = departmentRepo.findById(1).get();
		TeamVo team1 = new TeamVo();
		team1.setDepartment(department4);
		team1.setTeamName("경영지원팀");
		teamRepo.save(team1);
		TeamVo team2 = new TeamVo();
		team2.setDepartment(department4);
		team2.setTeamName("기술지원팀");
		teamRepo.save(team2);
		DepartmentVo department5 = departmentRepo.findById(2).get();
		TeamVo team3 = new TeamVo();
		team3.setDepartment(department5);
		team3.setTeamName("영업1팀");
		teamRepo.save(team3);
		TeamVo team4 = new TeamVo();
		team4.setDepartment(department5);
		team4.setTeamName("영업2팀");
		teamRepo.save(team4);
		DepartmentVo department6 = departmentRepo.findById(3).get();
		TeamVo team5 = new TeamVo();
		team5.setDepartment(department6);
		team5.setTeamName("개발1팀");
		teamRepo.save(team5);
		TeamVo team6 = new TeamVo();
		team6.setDepartment(department6);
		team6.setTeamName("개발2팀");
		teamRepo.save(team6);
		
		RanksVo ranks1 = new RanksVo();
		ranks1.setRanks("사장");
		ranksRepo.save(ranks1);
		RanksVo ranks2 = new RanksVo();
		ranks2.setRanks("이사");
		ranksRepo.save(ranks2);
		RanksVo ranks3 = new RanksVo();
		ranks3.setRanks("부장");
		ranksRepo.save(ranks3);
		RanksVo ranks4 = new RanksVo();
		ranks4.setRanks("팀장");
		ranksRepo.save(ranks4);
		RanksVo ranks5 = new RanksVo();
		ranks5.setRanks("사원");
		ranksRepo.save(ranks5);
		
		return "컴퍼니 생성완료";
	}
	
	@RequestMapping("/createAdmin")
	@ResponseBody
	public String createMember() {
		MemberVo member = new MemberVo();
		member.setMemId("admin");
		member.setMemPw(encoder.encode("admin"));
		member.setMemName("권재범");
		member.setRole(Role.ROLE_ADMIN);
		member.setEnabled(true);

		member.setMemJoinDate(new Date());

		member.setMemTel("010-2641-2684");
		member.setMemOfficeTel("02-1234-1234");

		member.setMemJumin("840415-1000000");
		member.setMemSignUrl("/images/840415/sign.png");
		member.setMemVacation(15);
		
		member.setMemAddrCode("07777");
		member.setMemAddr("서울시 강남구 강남대로 5");
		member.setMemAddrDetail("101호");
		
		DepartmentVo department3 = departmentRepo.findById(3).get();
		member.setDepartment(department3);
//		TeamVo team5 = teamRepo.findById(5).get();
//		member.setTeam(team5);
		RanksVo ranks = ranksRepo.findById("부장").get();
		member.setRanks(ranks);
		
		memberRepo.save(member);
		
		return "멤버 하나 생성완료";
		
	}
}
