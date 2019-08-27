package com.bitgroupware.approval.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ApprovalFileDto {
	private int		apFileNo;		// 파일번호
	private String	apdocNo;		// 문서양식번호
	
	private String	apFileName;		// 파일명
	private String	apFileUrl;		// 첨부파일URL
	private String	apFileType;		// 파일 타입
	
	private MultipartFile[] files;	// 업로드 파일들
}
