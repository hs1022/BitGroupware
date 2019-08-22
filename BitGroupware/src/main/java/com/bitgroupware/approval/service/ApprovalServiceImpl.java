package com.bitgroupware.approval.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitgroupware.approval.persistence.ApprovalDocumentDao;
import com.bitgroupware.approval.vo.ApprovalDoucemtDto;

@Service
public class ApprovalServiceImpl implements ApprovalService {

	@Autowired
    private SqlSessionTemplate sqlSession;
	@Autowired
    private ApprovalDocumentDao apDao;
	
	// 모든 문서양식 불러오기
	@Override
	public List<ApprovalDoucemtDto> selectApprovalDocList() {
		return apDao.selectApprovalDocList();
	}
	
	// 읽기
	@Override
	public ApprovalDoucemtDto selectApprovalDoc(String apdocNo) {
		return apDao.selectApprovalDoc(apdocNo);
	}
	 
	public ApprovalDoucemtDto selectApprovalDocOne(String param) {
        return sqlSession.selectOne("selectSignDocTypeOne", param);
    }
	
	// 등록
	@Override
	public void insertApprovalDoc(ApprovalDoucemtDto dto) {
		apDao.insertApprovalDoc(dto);
	}

	// 수정
	@Override
	public void updateApprovalDoc(ApprovalDoucemtDto dto) {
		// TODO Auto-generated method stub
		
	}

	
	
}
