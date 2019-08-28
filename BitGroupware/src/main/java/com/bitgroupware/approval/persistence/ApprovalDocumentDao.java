package com.bitgroupware.approval.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.bitgroupware.approval.beans.ApprovalDoucemtDto;
import com.bitgroupware.approval.beans.ApprovalFileDto;

@Mapper
public interface ApprovalDocumentDao {

	// 모든 문서양식 불러오기
	@Select("SELECT * FROM APPROVAL_DOCUMENT")
	List<ApprovalDoucemtDto> selectApprovalDocList();
	
	// 읽기
	@Select("SELECT * FROM APPROVAL_DOCUMENT WHERE APDOC_NO = #{apdocNo}")
	ApprovalDoucemtDto selectApprovalDoc(String apdocNo);

	// 등록
	@Insert("INSERT INTO APPROVAL_DOCUMENT (APDOC_NAME,APDOC_FORM,FiNAL_SIGN) VALUES (#{apdocName}, #{apdocForm}, #{finalSign})")
	void insertApprovalDoc(ApprovalDoucemtDto dto);
	
	@Insert("INSERT INTO APPROVAL_FILE (APDOC_NO,AP_FILENAME,AP_FILEURL) VALUES (#{apdocNo}, #{apFilename}, #{apFileurl})")
	void insertApprovalDocFile(ApprovalFileDto fileDto);

	// 수정
	@Update("UPDATE APPROVAL_DOCUMENT SET APDOC_NAME=#{apdocName}, APDOC_FORM=#{apdocForm}, FiNAL_SIGN=#{finalSign} WHERE APDOC_NO=#{apdocNo}")
	void updateApprovalDoc(ApprovalDoucemtDto dto);
	
	@Update("UPDATE APPROVAL_FILE SET AP_FILENAME=#{apFilename}, AP_FILEURL=#{apFileurl} WHERE APDOC_NO=#{apdocNo}")
	void updateApprovalDocFile(ApprovalFileDto fileDto);
	
	// 삭제
	@Delete("DELETE from APPROVAL_DOCUMENT WHERE APDOC_NO = #{apdocNo}")
	void deleteApprovalDoc(ApprovalDoucemtDto dto);

	@Delete("DELETE from APPROVAL_FILE WHERE APDOC_NO = #{apdocNo}")
	void deleteApprovalDocFile(ApprovalFileDto fileDto);
	
	@Select("SELECT MAX(APDOC_NO) FROM APPROVAL_DOCUMENT")
	String selectMaxApNo();

}
