package com.bitgroupware.community.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitgroupware.community.persistence.NoticeFileRepository;
import com.bitgroupware.community.persistence.NoticeRepository;
import com.bitgroupware.community.vo.NoticeFileVo;
import com.bitgroupware.community.vo.NoticeVo;
import com.bitgroupware.utils.Search;
import com.bitgroupware.utils.TemporaryFileUrl;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeRepository noticeRepo;

	@Autowired
	private NoticeFileRepository noticeFileRepo;

	public List<NoticeVo> selectNoticeList(int begin, Search search) {
		if (search.getSearchCondition() == null)
			search.setSearchCondition("nt_title");
		if (search.getSearchKeyword() == null)
			search.setSearchKeyword("");

		String searchCondition = search.getSearchCondition();
		String searchKeyword = "%" + search.getSearchKeyword().trim() + "%";
		List<NoticeVo> noticeList = null;
		switch (searchCondition) {
		case "nt_title":
			noticeList = noticeRepo.findAllByPagingAndNtTitle(begin, searchKeyword);
			break;
		case "nt_cate":
			noticeList = noticeRepo.findAllByPagingAndNtCate(begin, searchKeyword);
			break;
		case "nt_content":
			noticeList = noticeRepo.findAllByPagingAndNtContent(begin, searchKeyword);
			break;
		}
		return noticeList;
	}

	@Transactional
	public void insertNotice(NoticeVo notice) {
		Map<String, String> map = TemporaryFileUrl.fileUrl;
		if (!map.isEmpty()) {
			notice.setNtFileCheck("Y");
			noticeRepo.save(notice);
			int ntNo = noticeRepo.findByMaxNtNo();
			NoticeVo temporaryNotice = noticeRepo.findById(ntNo).get();
			Set<String> keys = map.keySet();
			Iterator<String> iterator = keys.iterator();
			int idx = 0;
			while (iterator.hasNext()) {
				String key = iterator.next();
				String fileUrl = map.get(key);
				idx = fileUrl.indexOf("_") + 1;
				String fileName = fileUrl.substring(idx);
				NoticeFileVo noticeFile = new NoticeFileVo();
				noticeFile.setNotice(temporaryNotice);
				noticeFile.setNtFileName(fileName);
				noticeFile.setNtFileUrl(fileUrl);
				noticeFileRepo.save(noticeFile);
			}
			TemporaryFileUrl.fileUrl.clear();
		} else {
			notice.setNtFileCheck("N");
			noticeRepo.save(notice);
		}
	}

	public NoticeVo selectNoticeByNtNo(int ntNo) {
		return noticeRepo.findById(ntNo).get();
	}

	@Transactional
	public void updateNotice(NoticeVo notice) {
		Map<String, String> map = TemporaryFileUrl.fileUrl;
		if (!map.isEmpty()) {
			notice.setNtFileCheck("Y");
			noticeRepo.save(notice);
			Set<String> keys = map.keySet();
			Iterator<String> iterator = keys.iterator();
			int idx = 0;
			while (iterator.hasNext()) {
				String key = iterator.next();
				String fileUrl = map.get(key);
				idx = fileUrl.indexOf("_") + 1;
				String fileName = fileUrl.substring(idx);
				NoticeFileVo noticeFile = new NoticeFileVo();
				noticeFile.setNotice(notice);
				noticeFile.setNtFileName(fileName);
				noticeFile.setNtFileUrl(fileUrl);
				noticeFileRepo.save(noticeFile);
			}
			TemporaryFileUrl.fileUrl.clear();
		} else {
			List<NoticeFileVo> noticeFileList = noticeFileRepo.findByNotice(notice);
			if (noticeFileList.size() == 0) {
				notice.setNtFileCheck("N");
			} else {
				notice.setNtFileCheck("Y");
			}
			noticeRepo.save(notice);
		}
	}

	public int countNotice(Search search) {
		if (search.getSearchCondition() == null)
			search.setSearchCondition("nt_title");
		if (search.getSearchKeyword() == null)
			search.setSearchKeyword("");

		String searchCondition = search.getSearchCondition();
		String searchKeyword = "%" + search.getSearchKeyword().trim() + "%";
		int count = 0;
		switch (searchCondition) {
		case "nt_title":
			count = noticeRepo.countByNtTitle(searchKeyword);
			break;
		case "nt_cate":
			count = noticeRepo.countByNtCate(searchKeyword);
			break;
		case "nt_content":
			count = noticeRepo.countByNtContent(searchKeyword);
			break;
		}
		return count;
	}

	public List<NoticeFileVo> selectNoticeFileListByNtNo(int ntNo) {
		return noticeFileRepo.selectNoticeFileListByNtNo(ntNo);
	}

	public void deleteNoticeFile(String fileUrl) {
		int count = noticeFileRepo.countByNtFileUrl(fileUrl);
		if (count != 0) {
			int ntFileNo = noticeFileRepo.NtFileNoByNtFileUrl(fileUrl);
			noticeFileRepo.deleteByNtFileNo(ntFileNo);
		}
	}
}