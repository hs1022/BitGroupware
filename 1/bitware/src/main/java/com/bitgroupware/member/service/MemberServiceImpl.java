package com.bitgroupware.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitgroupware.company.persistence.DepartmentRepository;
import com.bitgroupware.company.persistence.RanksRepository;
import com.bitgroupware.company.persistence.TeamRepository;
import com.bitgroupware.company.vo.DepartmentVo;
import com.bitgroupware.company.vo.RanksVo;
import com.bitgroupware.company.vo.TeamVo;
import com.bitgroupware.member.persistence.MemberRepository;
import com.bitgroupware.member.vo.MemberVo;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberRepository memberRepository;

	@Autowired
	DepartmentRepository departmentRepository;
	
	@Autowired
	TeamRepository teamRepository;
	
	@Autowired
	RanksRepository ranksRepository;

	// 사원 리스트
	@Override
	public List<MemberVo> selectMemberList(MemberVo memberVo) {
		return (List<MemberVo>) memberRepository.findAll();
	}

	// 부서 리스트
	@Override
	public List<DepartmentVo> selectDeptList(DepartmentVo departmentVo) {
		return (List<DepartmentVo>) departmentRepository.findAll();
	}

	// 팀 리스트
	@Override
	public List<TeamVo> selectTeamList(String deptName) {
		DepartmentVo departmentVo = departmentRepository.findById(deptName).get();
		return teamRepository.findByDepartment(departmentVo);
	}
	
	
	// 직급 리스트
	@Override
	public List<RanksVo> selectRanksList(RanksVo ranksVo) {
		return (List<RanksVo>) ranksRepository.findAllByOrderByRanksNoAsc();
	}

	// 사원 생성
	@Override
	public void insertMember(MemberVo memberVo) {
		memberRepository.save(memberVo);
	}

	
	// DB 서버의 날짜, MemId 생성시 필요
	@Override
	public String selectCurdate() {
		return memberRepository.selectCurdate();
	}

	// DB의 Member수 Count, MemId 생성시 필요
	@Override
	public String selectCountMember() {
		return memberRepository.selectCountMember();
	}

	// 사원 수정을 위한 사원 정보
	@Override
	public MemberVo selectMember(String memId) {
		return memberRepository.findById(memId).get();
	}

	// 사원 수정
	@Override
	public void updateMember(MemberVo memberVo) {
		MemberVo updateMember = memberRepository.findById(memberVo.getMemId()).get();
		
		updateMember.setMemJoinDate(memberVo.getMemJoinDate());
		updateMember.setMemOfficeTel(memberVo.getMemOfficeTel());
		updateMember.setMemTel(memberVo.getMemTel());
		updateMember.setMemAddrCode(memberVo.getMemAddrCode());
		updateMember.setMemAddr(memberVo.getMemAddr());
		updateMember.setMemAddrDetail(memberVo.getMemAddrDetail());
		updateMember.setMemAddrExtra(memberVo.getMemAddrExtra());
		updateMember.setMemSignUrl(memberVo.getMemSignUrl());
		updateMember.setMemStatus(memberVo.getMemStatus());
		updateMember.setMemQuitDate(memberVo.getMemQuitDate());
		updateMember.setMemQuitReason(memberVo.getMemQuitReason());
		
		memberRepository.save(updateMember);
	}

}
