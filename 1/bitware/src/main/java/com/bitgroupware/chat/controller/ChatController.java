package com.bitgroupware.chat.controller;

import static java.lang.String.format;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bitgroupware.chat.Beans.DepartmentDto;
import com.bitgroupware.chat.Beans.MemberDto;
import com.bitgroupware.chat.Beans.Message;
import com.bitgroupware.chat.Beans.Message.MessageType;
import com.bitgroupware.chat.service.ChatService;
import com.bitgroupware.security.config.SecurityUser;


@Controller
public class ChatController {
	private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);
	
	@Autowired
	private SimpMessageSendingOperations messagingTemplate;
	
	@Autowired
	ChatService chatservice;
	
	@MessageMapping("/chat/{roomId}/sendMessage")
	public void addUser(@DestinationVariable String roomId, @Payload Message chatMessage, SimpMessageHeaderAccessor headerAccessor) {
		String currentRoomId = (String) headerAccessor.getSessionAttributes().put("room_id", roomId);
		if(currentRoomId == null) {
			Message leaveMessage = new Message();
			leaveMessage.setType(MessageType.LEAVE);
			leaveMessage.setSender(chatMessage.getSender());
			messagingTemplate.convertAndSend(format("/channel/%s", currentRoomId), leaveMessage);
		}
		headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
		messagingTemplate.convertAndSend(format("/channel/%s", roomId), chatMessage);
	}
	
	
	
	/*chat 메인 페이지로 이동 */
	@RequestMapping("chat")
	public String chatView(String memId, @AuthenticationPrincipal SecurityUser principal, Model model) {
		String sessionId = principal.getMember().getMemId();
		model.addAttribute("sessionId", sessionId);
		model.addAttribute("memId", memId);
		System.out.println(sessionId);
		System.out.println(memId);
		String roomId = memId + sessionId;
		model.addAttribute("roomId", roomId);
		
		return "chat/chat";
	}
	
	@RequestMapping("chatList")
	public String chatList(Model model, MemberDto memberDto, DepartmentDto depDto) {
		List<MemberDto> memberList = chatservice.selectMemberList(memberDto);
		List<DepartmentDto> depList = chatservice.selectDeptList(depDto);
		model.addAttribute("memberList", memberList);
		model.addAttribute("depList", depList);
		return "chat/chatList";
	}
}
