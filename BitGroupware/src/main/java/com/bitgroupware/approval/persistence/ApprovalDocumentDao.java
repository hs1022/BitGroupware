package com.bitgroupware.approval.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.bitgroupware.approval.vo.ApprovalDoucemtDto;

@Mapper
public interface ApprovalDocumentDao {

	// 모든 문서양식 불러오기
	@Select("SELECT * FROM APPROVAL_DOCUMENT")
	List<ApprovalDoucemtDto> selectApprovalDocList();
	
	// 읽기
	@Select("SELECT * FROM APPROVAL_DOCUMENT WHERE APDOC_NO = #{apdocNo}")
	ApprovalDoucemtDto selectApprovalDoc(String apdocNo);

	// 등록
	@Insert("INSERT INTO APPROVAL_DOCUMENT (APDOC_NAME,APDOC_FORM) VALUES (#{apdocName}, #{apdocForm})")
	void insertApprovalDoc(ApprovalDoucemtDto dto);

	// 수정
	@Update("UPDATE APPROVAL_DOCUMENT SET APDOC_NAME=#{apdocName}, APDOC_FORM=#{apdocForm} WHERE APDOC_NO=#{apdocNo}")
	void updateApprovalDoc(ApprovalDoucemtDto dto);
	
	// 삭제
	@Delete("DELETE from APPROVAL_DOCUMENT WHERE APDOC_NO = #{apdocNo}")
	void deleteApprovalDoc(ApprovalDoucemtDto dto);

}
