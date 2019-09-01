package com.bitgroupware.member.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bitgroupware.member.vo.MemberVo;

public interface MemberRepository extends JpaRepository<MemberVo, String>{
	@Query(value = "select curdate()", nativeQuery = true)
	String selectCurdate();
	
	@Query(value = "select count(*) from member", nativeQuery = true)
	String selectCountMember();
}
