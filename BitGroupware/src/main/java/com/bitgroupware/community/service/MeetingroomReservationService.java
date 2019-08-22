package com.bitgroupware.community.service;

import java.util.List;

import com.bitgroupware.community.vo.MeetingroomReservationVo;
import com.bitgroupware.community.vo.MeetingroomVo;
import com.bitgroupware.member.vo.MemberVo;

public interface MeetingroomReservationService {

	void insertMeetingroomReservation(MeetingroomReservationVo mrr);

	MeetingroomVo selectMeetingroomByMrNo(int mrNo);

	List<MeetingroomReservationVo> selectMeetingroomReservationList();

	List<MeetingroomReservationVo> selectMeetingroomReservationList(MemberVo member);

	void deleteMeetingroomReservation(int mrResNo);

	void deleteCheck();

	int checkDuplicates(int mrNo, String start, String end);

}
