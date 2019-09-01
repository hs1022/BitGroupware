package com.bitgroupware.chat.service;

import java.util.List;

import com.bitgroupware.chat.Beans.DepartmentDto;
import com.bitgroupware.chat.Beans.MemberDto;

public interface ChatService {
	
	List<MemberDto> selectMemberList(MemberDto memberDto);
	
	List<DepartmentDto> selectDeptList(DepartmentDto depDto);
}
