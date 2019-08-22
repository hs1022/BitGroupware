package com.bitgroupware.community.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitgroupware.community.persistence.MeetingroomRepository;
import com.bitgroupware.community.persistence.MeetingroomReservationRepository;
import com.bitgroupware.community.vo.MeetingroomReservationVo;
import com.bitgroupware.community.vo.MeetingroomVo;
import com.bitgroupware.member.vo.MemberVo;

@Service
public class MeetingroomReservationServiceImpl implements MeetingroomReservationService{

	@Autowired
	private MeetingroomReservationRepository meetingroomReservationRepo;
	@Autowired
	private MeetingroomRepository meetingroomRepo;
	
	public void insertMeetingroomReservation(MeetingroomReservationVo mrr) {
		meetingroomReservationRepo.save(mrr);
	}

	public MeetingroomVo selectMeetingroomByMrNo(int mrNo) {
		return meetingroomRepo.findById(mrNo).get();
	}

	public List<MeetingroomReservationVo> selectMeetingroomReservationList() {
		return meetingroomReservationRepo.findAll();
	}

	public List<MeetingroomReservationVo> selectMeetingroomReservationList(MemberVo member) {
		return meetingroomReservationRepo.findByMember(member);
	}

	public void deleteMeetingroomReservation(int mrResNo) {
		meetingroomReservationRepo.deleteById(mrResNo);
	}

	public void deleteCheck() {
		List<MeetingroomReservationVo> mrrList = meetingroomReservationRepo.findAll();
		for(MeetingroomReservationVo mrr:mrrList) {
			try {
				String[] endArr = mrr.getMrResEnd().split("T");
				String end = endArr[0]+" "+endArr[1]+":00";
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date endDate = format.parse(end);
				Date date = new Date();
				if(date.compareTo(endDate)==1) {
					meetingroomReservationRepo.delete(mrr);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}

	public int checkDuplicates(int mrNo, String start, String end) {
		return meetingroomReservationRepo.checkDuplicates(mrNo, start, end);
	}

}
