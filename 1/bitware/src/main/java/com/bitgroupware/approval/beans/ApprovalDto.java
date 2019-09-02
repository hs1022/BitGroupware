package com.bitgroupware.approval.beans;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ApprovalDto {
	private String 	apNo;			// 문서번호
	private String	apTitle;		// 제목
	private String 	apContent;		// 문서내용
	private String 	apDeleteflag;	// 삭제여부
	private String 	apDocstatus;	// 문서상태
	private String 	apdocNo;		// 문서양식번호
	private Date 	apInsertdate;	// 작성일자
	private String	memId;			// 사용자번호
	private String 	apSignpath;		// 다음결제자
    private String 	apSignUrl1;		// 사인URL 1
    private String 	apSignUrl2;		// 사인URL 2
    private String 	apSignUrl3;		// 사인URL 3
    private String 	apSignUrl4;		// 사인URL 4
    private String 	apSignUrl5;		// 사인URL 5
    private String 	apSignName1;	// 사인 이름 1
    private String 	apSignName2;	// 사인 이름 2
    private String 	apSignName3;	// 사인 이름 3 
    private String 	apSignName4;	// 사인 이름 4
    private String 	apSignName5;	// 사인 이름 5
	private int 	finalSign;		// 최종결재넘버
}
