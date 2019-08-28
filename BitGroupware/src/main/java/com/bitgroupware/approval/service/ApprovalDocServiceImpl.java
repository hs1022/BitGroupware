package com.bitgroupware.approval.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitgroupware.approval.beans.ApprovalDoucemtDto;
import com.bitgroupware.approval.beans.ApprovalFileDto;
import com.bitgroupware.approval.persistence.ApprovalDocumentDao;

@Service
public class ApprovalDocServiceImpl implements ApprovalDocService {

	@Autowired
    private ApprovalDocumentDao apDao;
	
	static final Logger LOGGER = LoggerFactory.getLogger(ApprovalDocServiceImpl.class);
	
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

	// 등록(insert+update)
	@Transactional
	@Override
	public void insertApprovalDoc(ApprovalDoucemtDto dto) {
		if(dto.getApdocNo() == null || "".equals(dto.getApdocNo())) {
			apDao.insertApprovalDoc(dto);
			LOGGER.error("insertApprovalDoc");
		}else if(dto.getApdocNo() != null){
			apDao.updateApprovalDoc(dto);
			LOGGER.error("updateApprovalDoc");
		}else {
			LOGGER.error("둘 다 해당사항 없음");
		}
	}

	// 삭제
	@Override
	public void deleteApprovalDoc(ApprovalDoucemtDto dto) {
		apDao.deleteApprovalDoc(dto);
	}

	@Override
	public void insertApprovalDocFile(ApprovalFileDto fileDto) {
		// TODO Auto-generated method stub
		apDao.insertApprovalDocFile(fileDto);
	}

	@Override
	public void updateApprovalDoc(ApprovalDoucemtDto dto) {
		// TODO Auto-generated method stub
		apDao.updateApprovalDoc(dto);
		
	}

	@Override
	public void updateApprovalDocFile(ApprovalFileDto fileDto) {
		// TODO Auto-generated method stub
		apDao.updateApprovalDocFile(fileDto);
	}

	@Override
	public void deleteApprovalDocFile(ApprovalFileDto fileDto) {
		// TODO Auto-generated method stub
		apDao.deleteApprovalDocFile(fileDto);
		
	}
}
