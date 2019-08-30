package com.bitgroupware.approval.service;

import java.util.List;

import com.bitgroupware.approval.beans.ApprovalDocumentDto;
import com.bitgroupware.approval.beans.ApprovalFileDto;

public interface ApprovalDocService {
	
	// 모든 문서양식 불러오기
	List<ApprovalDocumentDto> selectApprovalDocList();
	
	// 읽기
	ApprovalDocumentDto selectApprovalDoc(String apdocNo);
	
	// 등록(insert+update)
	void insertApprovalDoc(ApprovalDocumentDto dto);
	
	void insertApprovalDocFile(ApprovalFileDto fileDto);
	
	// 수정
	void updateApprovalDoc(ApprovalDocumentDto dto);
	
	void updateApprovalDocFile(ApprovalFileDto fileDto);
	
	// 삭제
	void deleteApprovalDoc(ApprovalDocumentDto dto);
	
	void deleteApprovalDocFile(ApprovalFileDto fileDto);

}
