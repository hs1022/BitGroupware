package com.bitgroupware.community.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bitgroupware.community.service.NoticeService;
import com.bitgroupware.community.vo.NoticeVo;
import com.bitgroupware.utils.Pager;
import com.bitgroupware.utils.Search;

@Controller
@RequestMapping("/user")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	@RequestMapping("/selectNoticeList")
	public String selectNoticeList(Model model, @RequestParam(defaultValue = "1") int curPage, Search search) {
		
		int count = noticeService.countNotice(search);
		
		Pager page = new Pager(count, curPage);
		int blockBegin = page.getBlockBegin();
		int blockEnd = page.getBlockEnd();
		
		List<Integer> block= new ArrayList<Integer>();
		for( int i = blockBegin; i<=blockEnd; i++) {
			block.add(i);
		}
		
		int begin = page.getPageBegin()-1;
		
		List<NoticeVo> noticeList = noticeService.selectNoticeList(begin, search);
		
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String today = format.format(date);
		
		model.addAttribute("noticeList", noticeList);
		model.addAttribute("today",today);
		model.addAttribute("page",page);
		model.addAttribute("block",block);
		model.addAttribute("search",search);
		
		return "community/noticeList";
	}
	
	@RequestMapping("/selectNotice")
	public String selectNotice(Model model, int ntNo) {
		NoticeVo notice = noticeService.selectNoticeByNtNo(ntNo);
		model.addAttribute("notice", notice);
		return "community/noticeDetail";
	}
}
