package com.bitgroupware.community.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitgroupware.community.service.MeetingroomReservationService;
import com.bitgroupware.community.vo.MeetingroomReservationVo;
import com.bitgroupware.community.vo.MeetingroomVo;
import com.bitgroupware.member.vo.MemberVo;
import com.bitgroupware.security.config.SecurityUser;

@Controller
@RequestMapping("/user")
public class MeetingroomController {

	@Autowired
	private MeetingroomReservationService meetingroomReservationService;
	
	@RequestMapping("/selectMeetingroomReservationView")
	public String insertMeetingroomView(Model model, String msg, @AuthenticationPrincipal SecurityUser principal) {
		MemberVo member = principal.getMember();
//		meetingroomReservationService.deleteCheck();
		List<MeetingroomReservationVo> mrrListByMemId = meetingroomReservationService.selectMeetingroomReservationList(member);
		model.addAttribute("mrrListByMemId", mrrListByMemId);
		model.addAttribute("msg",msg);
		return "community/meetingroom";
	}
	
	@RequestMapping("/insertMeetingroomReservation")
	public String insertMeetingroom(int mrNo, MeetingroomReservationVo mrr, @AuthenticationPrincipal SecurityUser principal) {
		try {
			String[] startArr = mrr.getMrResStart().split("T");
			String[] endArr = mrr.getMrResEnd().split("T");
			String start = startArr[0]+" "+startArr[1]+":00";
			String end = endArr[0]+" "+endArr[1]+":00";
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date startDate = format.parse(start);
			Date endDate = format.parse(end);
			Date date = new Date();
			int check = meetingroomReservationService.checkDuplicates(mrNo, start, end); 
			if(date.compareTo(startDate)==1||startDate.compareTo(endDate)==1||check==1) {
				String msg = "Invalid date entered. Please re-enter.";
				return "redirect:/user/selectMeetingroomReservationView?msg="+msg;
			}
		} catch (ParseException e) {
			System.out.println("날짜 선택에서 오류 났어요.");
			e.printStackTrace();
		}
		MeetingroomVo mr = meetingroomReservationService.selectMeetingroomByMrNo(mrNo);
		mrr.setMeetingroom(mr);
		mrr.setMember(principal.getMember());
		meetingroomReservationService.insertMeetingroomReservation(mrr);
		return "redirect:/user/selectMeetingroomReservationView";
	}

	@RequestMapping("/selectMeetingroomReservationListAjax")
	@ResponseBody
	public List<MeetingroomReservationVo> selectMeetingroomReservationList() {
		return meetingroomReservationService.selectMeetingroomReservationList();
	}
	
	@RequestMapping("/deleteMeetingroomReservation")
	public String deleteMeetingroomReservation(int mrResNo) {
		meetingroomReservationService.deleteMeetingroomReservation(mrResNo);
		return "redirect:/user/selectMeetingroomReservationView";
	}
}
