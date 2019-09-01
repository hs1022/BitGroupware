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
		@Select("SELECT * FROM APPROVAL AP " + 
				"INNER JOIN MEMBER MEM ON AP.MEM_ID=MEM.MEM_ID " + 
				"WHERE AP.AP_DELETEFLAG='N' AND AP_NO=#{apNo}")
		
//		@Select("SELECT * FROM APPROVAL_DOCUMENT WHERE APDOC_NO = #{apdocNo}")
		ApprovalDto selectApproval(ApprovalDto apDto);
		
		// 기안
		void insertApproval();
		
		// 삭제
		void deleteApproval();
		
		// 결재 경로
		@Select("SELECT apr_no, ap_no, apr_step, apr_type, apr_result, ap.mem_id, mem_name, ap.ranks, apr_signdate, apr_comment " + 
				"FROM approval_progress ap " + 
				"INNER JOIN member mem ON ap.mem_id=mem.mem_id "+ 
				"WHERE ap_no=#{ap_no} "+ 
				"ORDER BY apr_step")
		List<?> selectSign();
		
		// 마지막 결재 경로
		List<?> selectLineLast();
		
		// 결재
		void updateApproval();
}
