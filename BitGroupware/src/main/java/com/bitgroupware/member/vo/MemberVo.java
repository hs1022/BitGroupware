package com.bitgroupware.member.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.bitgroupware.company.vo.DepartmentVo;
import com.bitgroupware.company.vo.RanksVo;
import com.bitgroupware.company.vo.TeamVo;
import com.bitgroupware.member.utils.Role;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity(name = "member")
public class MemberVo {

	@Id
	@Column(columnDefinition = "varchar(100)")
	private String memId;
	private String memPw;
	private String memName;
	@Enumerated(EnumType.STRING)
	private Role role;
	private boolean enabled;
	
	private Date memJoinDate;
	@Column(insertable = false)
	private Date memQuitDate;
	@Column(insertable = false)
	private String memQuitReason;
	@Column(insertable = false, columnDefinition = "varchar(100) default 'work'")
	private String memStatus;
	
	private String memTel;
	private String memOfficeTel;
	
	private String memJumin;
	private String memSignUrl;
	private int memVacation;
	
	private String memAddrCode;
	private String memAddr;
	private String memAddrDetail;
	
	@ManyToOne
	@JoinColumn(name = "dept_name")
	private DepartmentVo department;
	@ManyToOne
	@JoinColumn(name = "team_name")
	private TeamVo team;
	@ManyToOne
	@JoinColumn(name = "ranks")
	private RanksVo ranks;
}
