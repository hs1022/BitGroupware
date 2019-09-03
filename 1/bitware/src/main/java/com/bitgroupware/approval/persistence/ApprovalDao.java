package com.bitgroupware.approval.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.bitgroupware.approval.beans.ApprovalDto;

@Mapper
public interface ApprovalDao {
	// 결재 받을 문서 리스트
	@Select("select * from approval where mem_id = #{memId} and ap_deleteflag='N'")
	List<ApprovalDto> selectApprovalListToBeByTotal(String memId);

	@Select("select * from approval where mem_id = #{memId} and ap_deleteflag='N' and ap_docstatus = #{status}")
	List<ApprovalDto> selectApprovalListToBe(String memId, String status);

	// 결재 할 문서 리스트
	@Select("select * from approval where ap_signpath = #{memId} and ap_docstatus in (1,2) and ap_deleteflag = 'N'")
	List<ApprovalDto> selectApprovalListTo(String memId);

	@Select("select * from approval where ap_no = #{apNo} and ap_deleteflag = 'N'")
	ApprovalDto selectApproval(String apNo);

	// 기안
	@Insert("insert into approval(ap_title,ap_content,ap_deleteflag,ap_docstatus,apdoc_no,ap_insertdate,mem_id,ap_signpath,ap_sign_url1,ap_sign_url2,ap_sign_url3,ap_sign_url4,ap_sign_url5,ap_sign_name1,ap_sign_name2,ap_sign_name3,ap_sign_name4,ap_sign_name5,final_sign) values(#{apTitle},#{apContent},#{apDeleteflag},#{apDocstatus},#{apdocNo},now(),#{memId},#{apSignpath},#{apSignUrl1},#{apSignUrl2},#{apSignUrl3},#{apSignUrl4},#{apSignUrl5},#{apSignName1},#{apSignName2},#{apSignName3},#{apSignName4},#{apSignName5},#{finalSign})")
	void insertApproval(ApprovalDto approval);

	@Select("select mem_id from member where team_name=#{teamName} and ranks='팀장'")
	String selectTeamLeader(String teamName);

	@Select("select mem_id from member where dept_name=#{deptName} and ranks='부장'")
	String selectHeader(String deptName);

	@Select("select * from member join ranks on member.ranks = ranks.ranks where ranks_no = #{ranksNo}")
	String selectDirector(int ranksNo);

	@Select("select * from member join ranks on member.ranks = ranks.ranks where ranks_no = #{ranksNo}")
	String selectBoss(int ranksNo);

	@Select ("select ranks.ranks_no from member join ranks on member.ranks = ranks.ranks where member.mem_id = #{memId}")
	int selectRanksNo(String memId);

	@Update("update approval set ap_docstatus = #{apDocstatus}, ap_signpath = #{apSignpath}, ap_sign_url1 = #{apSignUrl1}, ap_sign_url2 = #{apSignUrl2}, ap_sign_url3 = #{apSignUrl3}, ap_sign_url4 = #{apSignUrl4}, ap_sign_url5 = #{apSignUrl5}, ap_sign_name1 = #{apSignName1}, ap_sign_name2 = #{apSignName2}, ap_sign_name3 = #{apSignName3}, ap_sign_name4 = #{apSignName4}, ap_sign_name5 = #{apSignName5} where ap_no = #{apNo}")
	void updateApprovalPath(ApprovalDto approval);

	@Update("update approval set ap_docstatus = #{apDocstatus}, ap_signpath = #{apSignpath}, ap_comment = #{apComment}, ap_sign_url1 = #{apSignUrl1}, ap_sign_url2 = #{apSignUrl2}, ap_sign_url3 = #{apSignUrl3}, ap_sign_url4 = #{apSignUrl4}, ap_sign_url5 = #{apSignUrl5}, ap_sign_name1 = #{apSignName1}, ap_sign_name2 = #{apSignName2}, ap_sign_name3 = #{apSignName3}, ap_sign_name4 = #{apSignName4}, ap_sign_name5 = #{apSignName5} where ap_no = #{apNo}")
	void updateApprovalCancel(ApprovalDto approval);

	@Update("update approval set ap_deleteflag ='Y' where ap_no = #{apNo}")
	void deleteApproval(ApprovalDto approval);
	
	@Update("update approval set ap_title = #{apTitle} ,ap_content = #{apContent} where ap_no = #{apNo}")
	void updateApproval(ApprovalDto approval);
}
