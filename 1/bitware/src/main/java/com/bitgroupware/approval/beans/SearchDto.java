package com.bitgroupware.approval.beans;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchDto {
	private String searchKeyword = "";         // 검색 키워드
    private String searchType = "";            // 검색 필드: 제목, 내용  
    private String[] searchTypeArr;            // 검색 필드를 배열로 변환
    private String searchExt1 = "";            // 검색 확장 필드  
    private String memId;  
    
    public String[] getSearchTypeArr() {
        return searchType.split(",");
    }

}
