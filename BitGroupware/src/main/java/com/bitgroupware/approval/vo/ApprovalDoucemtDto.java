package com.bitgroupware.approval.vo;

import lombok.Getter;
import lombok.ToString;
import lombok.Setter;

/**
 * @Author 	홍한솔
 * @Date	2019. 8. 20.
 */
@Getter
@Setter
@ToString
public class ApprovalDoucemtDto {
	private String 	apdocNo,	// 번호
					apdocName,	// 문서양식명
					apdocForm;	// 문서양식내용
}
