package com.bitgroupware.approval.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitgroupware.approval.beans.ApprovalDto;
import com.bitgroupware.approval.persistence.ApprovalDao;
import com.bitgroupware.member.vo.MemberVo;

@Service
public class ApprovalServiceImpl implements ApprovalService {

	@Autowired
	private ApprovalDao apDao;

	static final Logger LOGGER = LoggerFactory.getLogger(ApprovalServiceImpl.class);

	// 결재 받을 문서 리스트
	public List<ApprovalDto> selectApprovalListToBeByTotal(String memId) {
		return apDao.selectApprovalListToBeByTotal(memId);
	}

	public List<ApprovalDto> selectApprovalListToBe(String memId, String status) {
		return apDao.selectApprovalListToBe(memId, status);
	}

	// 결재 할 문서 리스트
	public List<ApprovalDto> selectApprovalListTo(String memId) {
		return apDao.selectApprovalListTo(memId);
	}

	// 읽기
	@Override
	public ApprovalDto selectApproval(String apNo) {
		return apDao.selectApproval(apNo);
	}

	// 기안
	@Override
	public void insertApproval(ApprovalDto approval, MemberVo member) {

		int ranks = member.getRanks().getRanksNo();

		switch (ranks) {
		case 1:
			String teamName = member.getTeam().getTeamName();
			String teamLeaderId = apDao.selectTeamLeader(teamName);
			approval.setApSignpath(teamLeaderId);
			break;
		case 2:
			String deptName = member.getDepartment().getDeptName();
			String headerId = apDao.selectHeader(deptName);
			approval.setApSignpath(headerId);
			break;
		case 3:
			String directorId = apDao.selectDirector(ranks + 1);
			approval.setApSignpath(directorId);
			break;
		case 4:
			String bossId = apDao.selectBoss(ranks + 1);
			approval.setApSignpath(bossId);
			break;
		}
		apDao.insertApproval(approval);
	}

	public int selectRanksNo(String memId) {
		return apDao.selectRanksNo(memId);
	}

	public void updateApprovalPath(ApprovalDto approval, MemberVo member) {
		
		int ranks = member.getRanks().getRanksNo();

		switch (ranks) {
		case 1:
			String teamName = member.getTeam().getTeamName();
			String teamLeaderId = apDao.selectTeamLeader(teamName);
			approval.setApSignpath(teamLeaderId);
			break;
		case 2:
			String deptName = member.getDepartment().getDeptName();
			String headerId = apDao.selectHeader(deptName);
			approval.setApSignpath(headerId);
			break;
		case 3:
			String directorId = apDao.selectDirector(ranks + 1);
			approval.setApSignpath(directorId);
			break;
		case 4:
			String bossId = apDao.selectBoss(ranks + 1);
			approval.setApSignpath(bossId);
			break;
		}
		apDao.updateApprovalPath(approval);
	}

	public void updateApprovalCancel(ApprovalDto approval) {
		apDao.updateApprovalCancel(approval);
	}

	@Override
	public void deleteApproval(ApprovalDto approval) {
		apDao.deleteApproval(approval);
	}
	
	@Override
	public void updateApproval(ApprovalDto approval) {
		apDao.updateApproval(approval);
	}

	


}
