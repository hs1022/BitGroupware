package com.bitgroupware.approval.beans;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ApprovalProgressDto {
	private String aprNo;			// 결재번호
	private String apNo;			// 문서번호
	private String aprStep;			// 결재단계
	private String aprType;			// 결재종류
	private String aprResult;		// 결재결과
	private String aprComment;		// 코멘트
	private String aprReceiveDate;	// 받은일자
	private String aprSignDate;		// 결재일자
	private String memId;			// 사용자번호
	private String rank;			// 직위
}
