package com.bitgroupware.security.config;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.bitgroupware.member.vo.MemberVo;

public class SecurityUser extends User{
	
	private static final long serialVersionUID = 1L;
	
	private MemberVo member;
	
	public SecurityUser(MemberVo member) {
		super(member.getMemId(), member.getMemPw(), AuthorityUtils.createAuthorityList(member.getRole().toString()));

		this.member = member;
	}

	public MemberVo getMember() {
		return member;
	}

}
