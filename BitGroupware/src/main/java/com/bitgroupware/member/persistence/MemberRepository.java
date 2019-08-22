package com.bitgroupware.member.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bitgroupware.member.vo.MemberVo;

public interface MemberRepository extends JpaRepository<MemberVo, String>{

}
