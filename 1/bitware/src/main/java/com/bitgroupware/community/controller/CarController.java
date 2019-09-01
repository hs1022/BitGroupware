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

import com.bitgroupware.community.service.CarReservationService;
import com.bitgroupware.community.vo.CarReservationVo;
import com.bitgroupware.community.vo.CarVo;
import com.bitgroupware.community.vo.MeetingroomVo;
import com.bitgroupware.member.vo.MemberVo;
import com.bitgroupware.security.config.SecurityUser;

@Controller
@RequestMapping("/user")
public class CarController {

	@Autowired
	private CarReservationService carReservationService;
	
	@RequestMapping("/selectCarReservationList")
	public String selectCarReservationList(Model model, String msg, @AuthenticationPrincipal SecurityUser principal) {
//		meetingroomReservationService.deleteCheck();
		List<CarVo> carList = carReservationService.selectCarList();
		MemberVo member = principal.getMember();
		List<CarReservationVo> carReservationList = carReservationService.selectCarReservationList(member);
		model.addAttribute("carList", carList);
		model.addAttribute("carReservationList", carReservationList);
		model.addAttribute("msg",msg);
		return "community/carReservationList";
	}
	
	@RequestMapping("/insertCarReservation")
	public String insertCarReservation(int carNo, CarReservationVo carReservation, @AuthenticationPrincipal SecurityUser principal) {
		try {
			String start = carReservation.getCarResStart()+":00";
			String end = carReservation.getCarResEnd()+":00";
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date startDate = format.parse(start);
			Date endDate = format.parse(end);
			Date date = new Date();
			int check = carReservationService.checkDuplicates(carNo, start, end); 
			if(date.compareTo(startDate)==1||startDate.compareTo(endDate)==1||check==1) {
				String msg = "Invalid date entered. Please re-enter.";
				return "redirect:/user/selectCarReservationList?msg="+msg;
			}
		} catch (ParseException e) {
			System.out.println("날짜 선택에서 오류 났어요.");
			e.printStackTrace();
		}
		CarVo car = carReservationService.selectCarByCarNo(carNo);
		carReservation.setCar(car);
		carReservation.setMember(principal.getMember());
		carReservationService.insertCarReservation(carReservation);
		return "redirect:/user/selectCarReservationList";
	}

	@RequestMapping("/selectCarReservationListAjax")
	@ResponseBody
	public List<CarReservationVo> selectCarReservationListAjax() {
		return carReservationService.selectCarReservationListAjax();
	}
	
	@RequestMapping("/deleteCarReservation")
	public String deleteCarReservation(int carResNo) {
		carReservationService.deleteCarReservation(carResNo);
		return "redirect:/user/selectCarReservationList";
	}
}
