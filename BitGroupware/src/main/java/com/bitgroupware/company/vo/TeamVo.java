package com.bitgroupware.company.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity(name = "team")
public class TeamVo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int teamNo;
	private String teamName;
	
	@ManyToOne
	@JoinColumn(name = "dept_no")
	private DepartmentVo department;

}
