package com.bitgroupware.approval.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ApprovalFileDto {
	private int		apFileNo,	// 파일번호
					apNo;		// 문서번호
	
	private String	apFileName,	// 파일명
					apFileUrl,	// 첨부파일URL
					apFileType;	// 파일 타입
}
