package com.bitgroupware.approval.service;

import java.util.List;

import com.bitgroupware.approval.vo.ApprovalDoucemtDto;

public interface ApprovalService {
	
	// 모든 문서양식 불러오기
	List<ApprovalDoucemtDto> selectApprovalDocList();
	
	// 읽기
	ApprovalDoucemtDto selectApprovalDoc(String apdocNo);
	
	// 등록(insert+update)
	void insertApprovalDoc(ApprovalDoucemtDto dto);
	
	// 삭제
	void deleteApprovalDoc(ApprovalDoucemtDto dto);

}
