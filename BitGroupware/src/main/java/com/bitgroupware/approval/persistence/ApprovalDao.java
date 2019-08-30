package com.bitgroupware.approval.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.bitgroupware.approval.beans.ApprovalDto;

@Mapper
public interface ApprovalDao {
	// 결재 받을 문서 리스트
		List<?> selectApprovalListTobe();
		
		// 결재 할 문서 리스트
		List<?> selectApprovalListTo();
		
		// 읽기
		@Select("SELECT * FROM APPROVAL_DOCUMENT WHERE APDOC_NO = #{apdocNo}")
		ApprovalDto selectApproval(ApprovalDto apDto);
		
		// 기안
		void insertApproval();
		
		// 삭제
		void deleteApproval();
		
		// 결재 경로
		List<?> selectLine();
		
		// 마지막 결재 경로
		List<?> selectLineLast();
		
		// 결재
		void updateApproval();
}
