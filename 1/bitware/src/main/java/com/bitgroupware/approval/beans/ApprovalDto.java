package com.bitgroupware.approval.beans;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ApprovalDto {
	private String apNo;			// 문서번호
	private String apTitle;			// 제목
	private String apContent;		// 문서내용
	private String apDeleteflag;	// 삭제여부
	private String apDocstatus;		// 문서상태
	private String apDocstep;		// 결재단계
	private String apdocNo;			// 문서양식번호
	private String apInsertdate;	// 작성일자
	private String apUpdatedate;	// 수정일자
	private String memId;			// 사용자번호
	private String apSignpath;		// 결재경로문자열
	
	private String finalSign;
}
