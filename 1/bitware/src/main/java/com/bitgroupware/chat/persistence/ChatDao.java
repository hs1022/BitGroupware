package com.bitgroupware.chat.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.bitgroupware.chat.Beans.DepartmentDto;
import com.bitgroupware.chat.Beans.MemberDto;


@Mapper
public interface ChatDao {
	
	//직원불러오기
	@Select("SELECT M.MEM_ID, M.MEM_NAME, D.DEPT_NAME FROM MEMBER AS M JOIN DEPARTMENT AS D ON M.DEPT_NAME = D.DEPT_NAME")
	List<MemberDto> selectMemberList();
	
	//부서명불러오기
	@Select("SELECT * FROM DEPARTMENT")
	List<DepartmentDto> selectDepList();
	
	/*
	 * <select id="listCart" resultType="com.five.mall.model.dto.CartDto"> select
	 * cartNo, m.userId, name, p.productNo, productName, amount, productPrice,
	 * nvl(productPrice*amount,0) money from member m, cart c, product p where
	 * m.userId=c.userId and p.productNo=c.productNo and m.userId=#{userId}
	 * </select>
	 */
}
