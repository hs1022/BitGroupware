package com.bitgroupware.company.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity(name = "department")
public class DepartmentVo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int deptNo;
	private String deptName;
}
