package com.bitgroupware.approval.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bitgroupware.approval.beans.ApprovalDto;

@Service
public class ApprovalServiceImpl implements ApprovalService {

	// 결재 받을 문서 리스트
	@Override
	public List<?> selectApprovalListTobe() {
		// TODO Auto-generated method stub
		return null;
	}

	// 결재 할 문서 리스트
	@Override
	public List<?> selectApprovalListTo() {
		// TODO Auto-generated method stub
		return null;
	}

	// 읽기
	@Override
	public ApprovalDto selectApproval() {
		// TODO Auto-generated method stub
		return null;
	}

	// 기안
	@Override
	public void insertApproval() {
		// TODO Auto-generated method stub
		
	}

	// 삭제
	@Override
	public void deleteApproval() {
		// TODO Auto-generated method stub
		
	}
	
	// 결재 경로
	@Override
	public List<?> selectLine() {
		// TODO Auto-generated method stub
		return null;
	}
	
	// 마지막 결재 경로
	@Override
	public List<?> selectLineLast() {
		// TODO Auto-generated method stub
		return null;
	}

	// 결재
	@Override
	public void updateApproval() {
		// TODO Auto-generated method stub
		
	}

}
