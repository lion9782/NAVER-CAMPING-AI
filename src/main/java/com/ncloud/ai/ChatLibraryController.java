package com.ncloud.ai;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ncloud.common.JsonHndr;
import com.ncloud.domain.ChatLibraryVO;
import com.ncloud.domain.UserChatHistoryVO;
import com.ncloud.service.ChatHistoryService;
import com.ncloud.service.ChatLibraryService;

import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/ChatLibrary")
public class ChatLibraryController {

	@Inject
	ChatLibraryService service;
	
	@Inject
	ChatHistoryService hservice;
	
	@RequestMapping(value = "/sendMessage")
	public void insertChatLibrary(@ModelAttribute("ChatLibraryVO") ChatLibraryVO vo ,HttpServletResponse response, HttpServletRequest request) throws Exception {
		String user_id = request.getParameter("savedUser");
		String create_at = request.getParameter("currentTime");
		String message = request.getParameter("message");
		String selectedChatRoomId = request.getParameter("selectedChatRoomId");
		String newChatRoomId = request.getParameter("newChatRoomId");
		int result = 0;
		int uResult = 0;
		
		UserChatHistoryVO uvo = new UserChatHistoryVO();
		if (newChatRoomId != null && !newChatRoomId.trim().isEmpty()) {
	        try {
	            int nChatId = Integer.parseInt(newChatRoomId);
	            vo.setUser_chat_library_id(nChatId);
	            vo.setUser_id(user_id);
	            vo.setCreate_at(create_at);
	            result = service.insertChatLibrary(vo);
	        } catch (NumberFormatException e) {
	            System.err.println("newChatRoomId가 숫자가 아님: " + newChatRoomId);
	        }
	    }

	    // 기존 채팅방 메시지 저장 처리
	    if (selectedChatRoomId != null && !selectedChatRoomId.trim().isEmpty()) {
	        try {
	            int sChatId = Integer.parseInt(selectedChatRoomId);
	            uvo.setUser_chat_library_id(sChatId);
	            uvo.setUser_id(user_id);
	            uvo.setAsk_value(message);
	            uResult = hservice.insertChatHistory(uvo);
	        } catch (NumberFormatException e) {
	            System.err.println("selectedChatRoomId가 숫자가 아님: " + selectedChatRoomId);
	        }
	    }

		JSONObject json = new JSONObject();
		if(uResult == 1) {
			json.put("msg", "사용자 메시지 저장 성공");		
		}else {
			json.put("msg", "사용자 메시지 저장 실패");
		}
		
		JsonHndr.print(json, response);
	}
}
