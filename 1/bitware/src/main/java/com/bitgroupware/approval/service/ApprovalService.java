package com.bitgroupware.approval.service;

import java.util.List;

import com.bitgroupware.approval.beans.ApprovalDocumentDto;
import com.bitgroupware.approval.beans.ApprovalDto;
import com.bitgroupware.approval.beans.ApprovalProgressDto;

public interface ApprovalService {
	
	// 결재 받을 문서 리스트
	List<?> selectApprovalListTobe();
	
	// 결재 할 문서 리스트
	List<?> selectApprovalListTo();
	
	// 읽기
	ApprovalDto selectApproval(ApprovalDto apDto);
	
	// 기안
	void insertApproval(ApprovalProgressDto aprDto,ApprovalDocumentDto dto);
	
	// 삭제
	void deleteApproval();
	
	// 결재 경로
	List<?> selectSign(String string);
	
	// 마지막 결재 경로
	List<?> selectLineLast();
	
	// 결재
	void updateApproval();
}
