package com.bitgroupware.approval.service;

import java.util.List;

import com.bitgroupware.approval.beans.ApprovalDto;
import com.bitgroupware.member.vo.MemberVo;

public interface ApprovalService {
	
	// 결재 받을 문서 리스트
	List<ApprovalDto> selectApprovalListToBeByTotal(String memId);
	List<ApprovalDto> selectApprovalListToBe(String memId, String status);
	
	// 결재 할 문서 리스트
	List<ApprovalDto> selectApprovalListTo(String memId);
	
	// 읽기
	ApprovalDto selectApproval(String apNo);
	
	// 기안
	void insertApproval(ApprovalDto approval, MemberVo member);
	
	int selectRanksNo(String memId);
	
	void updateApproval(ApprovalDto approval, MemberVo member);
	
	void updateApprovalCancel(String apNo, String apComment);
	
	


	
}
