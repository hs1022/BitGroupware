package com.bitgroupware.chat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitgroupware.chat.Beans.DepartmentDto;
import com.bitgroupware.chat.Beans.MemberDto;
import com.bitgroupware.chat.persistence.ChatDao;

@Service
public class ChatServiceImpl implements ChatService {

	@Autowired
	private ChatDao chatDao;

	@Override
	public List<MemberDto> selectMemberList(MemberDto memberDto) {
		// TODO Auto-generated method stub
		return chatDao.selectMemberList();
	}

	@Override
	public List<DepartmentDto> selectDeptList(DepartmentDto depDto) {
		// TODO Auto-generated method stub
		return chatDao.selectDepList();
	}
}
