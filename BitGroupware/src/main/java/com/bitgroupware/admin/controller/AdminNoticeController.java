package com.bitgroupware.admin.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitgroupware.community.service.NoticeService;
import com.bitgroupware.community.utils.Pager;
import com.bitgroupware.community.utils.Search;
import com.bitgroupware.community.utils.TemporaryFileUrl;
import com.bitgroupware.community.vo.NoticeFileVo;
import com.bitgroupware.community.vo.NoticeVo;
import com.bitgroupware.security.config.SecurityUser;

@Controller
@RequestMapping("/admin")
public class AdminNoticeController {

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
		
		return "admin/community/noticeList";
	}
	
	@RequestMapping("/insertNoticeView")
	public String insertNoticeView() {
		TemporaryFileUrl.fileUrl.clear();
		return "admin/community/noticeWrite";
	}
	
	@RequestMapping("/insertNotice")
	public String insertNotice(NoticeVo notice, @AuthenticationPrincipal SecurityUser principal) {
		notice.setMember(principal.getMember());
		noticeService.insertNotice(notice);
		return "redirect:/admin/selectNoticeList";
	}
	
	@RequestMapping("/updateNoticeView")
	public String updateNoticeView(Model model, int ntNo) {
		TemporaryFileUrl.fileUrl.clear();
		NoticeVo notice = noticeService.selectNoticeByNtNo(ntNo);
		List<NoticeFileVo> noticeFileList = noticeService.selectNoticeFileListByNtNo(ntNo);
		model.addAttribute("notice", notice);
		model.addAttribute("noticeFileList", noticeFileList);
		return "admin/community/noticeUpdate";
	}
	
	@RequestMapping("/updateNotice")
	public String updateNotice(NoticeVo notice) {
		noticeService.updateNotice(notice);
		return "redirect:/admin/selectNoticeList";
	}
	
	@RequestMapping("/deleteNotice")
	public String deleteNotice(int ntNo) {
		NoticeVo notice = noticeService.selectNoticeByNtNo(ntNo);
		notice.setNtDelCheck("Y");
		noticeService.updateNotice(notice);
		return "redirect:/admin/selectNoticeList";
	}
	
	@RequestMapping(value="/deleteNoticeCheckBox",method=RequestMethod.POST)
	@ResponseBody
	public String deleteNotice(@RequestParam(value="checkBoxArr[]") List<String> checkBoxArr) {
		for(String checkBox: checkBoxArr) {
			NoticeVo notice = noticeService.selectNoticeByNtNo(Integer.parseInt(checkBox));
			notice.setNtDelCheck("Y");
			noticeService.updateNotice(notice);
		}
		return "삭제완료";
	}
	
}
