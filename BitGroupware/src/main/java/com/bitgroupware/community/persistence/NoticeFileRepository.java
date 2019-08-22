package com.bitgroupware.community.persistence;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.bitgroupware.community.vo.NoticeFileVo;
import com.bitgroupware.community.vo.NoticeVo;

public interface NoticeFileRepository extends JpaRepository<NoticeFileVo, Integer>{

	@Query(value = "select * from notice_file where nt_no = ?", nativeQuery = true)
	List<NoticeFileVo> selectNoticeFileListByNtNo(int ntNo);

	List<NoticeFileVo> findByNotice(NoticeVo notice);
	
	@Query(value = "select count(*) from notice_file where nt_file_url = ?", nativeQuery = true)
	int countByNtFileUrl(String fileUrl);

	@Query(value = "select nt_file_no from notice_file where nt_file_url = ?", nativeQuery = true)
	int NtFileNoByNtFileUrl(String fileUrl);

	@Modifying
	@Transactional
	@Query(value = "delete from notice_file where nt_file_no = ?", nativeQuery = true)
	void deleteByNtFileNo(int ntFileNo);


}
