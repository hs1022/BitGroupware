package com.bitgroupware.approval.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitgroupware.approval.beans.ApprovalDocumentDto;
import com.bitgroupware.approval.beans.ApprovalDto;
import com.bitgroupware.approval.beans.ApprovalProgressDto;
import com.bitgroupware.approval.persistence.ApprovalDao;

@Service
public class ApprovalServiceImpl implements ApprovalService {
	
	@Autowired
	private ApprovalDao apDao;
	
	static final Logger LOGGER = LoggerFactory.getLogger(ApprovalServiceImpl.class);

	// 결재 받을 문서 리스트
	@Override
	public List<?> selectApprovalListTobe() {
		// TODO Auto-generated method stub
		return null;
	}

	// 결재 할 문서 리스트
	@Override
	public List<?> selectApprovalListTo() {
		// TODO Auto-generated method stub
		return null;
	}

	// 읽기
	@Override
	public ApprovalDto selectApproval(ApprovalDto apDto){
		return apDao.selectApproval(apDto) ;
	}
	

	// 기안
	@Override
	public void insertApproval(ApprovalProgressDto aprDto,ApprovalDocumentDto dto) {
		
		if(dto.getApdocNo() == null || "".equals(dto.getApdocNo())) {
			LOGGER.error("insertApprovalDoc");
		}else if(dto.getApdocNo() != null){
			LOGGER.error("updateApprovalDoc");
		}else {
			LOGGER.error("둘 다 해당사항 없음");
		}
		// TODO Auto-generated method stub
		
	}

	// 삭제
	@Override
	public void deleteApproval() {
		// TODO Auto-generated method stub
		
	}
	
	// 결재 경로
	@Override
	public List<?> selectSign(String string) {
		return apDao.selectSign();
	}
	
	// 마지막 결재 경로
	@Override
	public List<?> selectLineLast() {
		// TODO Auto-generated method stub
		return null;
	}

	// 결재
	@Override
	public void updateApproval() {
		// TODO Auto-generated method stub
		
	}

	

}
