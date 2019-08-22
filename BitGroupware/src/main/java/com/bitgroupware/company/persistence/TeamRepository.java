package com.bitgroupware.company.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bitgroupware.company.vo.TeamVo;

public interface TeamRepository extends JpaRepository<TeamVo, Integer>{

}
