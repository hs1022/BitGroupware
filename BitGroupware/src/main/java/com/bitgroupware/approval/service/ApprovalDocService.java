package com.bitgroupware.approval.service;

import java.util.List;

import com.bitgroupware.approval.beans.ApprovalDoucemtDto;
import com.bitgroupware.approval.beans.ApprovalFileDto;

public interface ApprovalDocService {
	
	// 모든 문서양식 불러오기
	List<ApprovalDoucemtDto> selectApprovalDocList();
	
	// 읽기
	ApprovalDoucemtDto selectApprovalDoc(String apdocNo);
	
	// 등록(insert+update)
	void insertApprovalDoc(ApprovalDoucemtDto dto);
	
	void insertApprovalDocFile(ApprovalFileDto fileDto);
	
	// 수정
	void updateApprovalDoc(ApprovalDoucemtDto dto);
	
	void updateApprovalDocFile(ApprovalFileDto fileDto);
	
	// 삭제
	void deleteApprovalDoc(ApprovalDoucemtDto dto);
	
	void deleteApprovalDocFile(ApprovalFileDto fileDto);

}
