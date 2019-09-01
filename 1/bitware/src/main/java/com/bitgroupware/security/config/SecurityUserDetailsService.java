package com.bitgroupware.security.config;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bitgroupware.member.persistence.MemberRepository;
import com.bitgroupware.member.vo.MemberVo;

@Service
public class SecurityUserDetailsService implements UserDetailsService{

	@Autowired
	private MemberRepository memberRepo;
//	@Autowired
//	private HttpSession session;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<MemberVo> optional = memberRepo.findById(username);
		if(!optional.isPresent()) {
			throw new UsernameNotFoundException(username+" 사용자 없음");
		}else {
			MemberVo member = optional.get();
			
//			session.setAttribute("member", member);
			
			return new SecurityUser(member);
		}
	}

}
