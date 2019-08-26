package com.bitgroupware.community.service;

import java.util.List;

import com.bitgroupware.community.vo.NoticeFileVo;
import com.bitgroupware.community.vo.NoticeVo;
import com.bitgroupware.utils.Search;

public interface NoticeService {

	//Page<NoticeVo> selectNoticeList(Search search);
	
	List<NoticeVo> selectNoticeList(int begin, Search search);

	void insertNotice(NoticeVo notice);

	NoticeVo selectNoticeByNtNo(int ntNo);

	void updateNotice(NoticeVo notice);

	int countNotice(Search search);

	List<NoticeFileVo> selectNoticeFileListByNtNo(int ntNo);

	void deleteNoticeFile(String fileUrl);

}
