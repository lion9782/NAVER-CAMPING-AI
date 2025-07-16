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
@RequestMapping("/ChatHistory")
public class ChatHistoryController {

	@Inject
	ChatHistoryService service;
	
	@RequestMapping(value = "/sendAiMessage")
	public void insertChatLibrary(@ModelAttribute("UserChatHistoryVO") UserChatHistoryVO vo ,HttpServletResponse response, HttpServletRequest request) throws Exception {
		String user_id = request.getParameter("userId");
		String selectedChatRoomId = request.getParameter("selectedChatRoomId");
		String newChatRoomId = request.getParameter("newChatRoomId");
		String answer_value = request.getParameter("aiResponse");
		String currentTime = request.getParameter("currentTime");
				
		String chatId = "";
		
		if(selectedChatRoomId != null) {
			chatId = selectedChatRoomId;
		}else if(newChatRoomId != null) {
			chatId = newChatRoomId;
		}
		
		vo.setAnswer_create_at(currentTime);
		vo.setAnswer_value(answer_value);
		vo.setUser_chat_library_id(Integer.parseInt(chatId));
		vo.setUser_id(user_id);
		
		int result = service.updateAimessage(vo);
		
		JSONObject json = new JSONObject();
		if(result == 1) {
			json.put("msg", "AI 응답성공");		
		}else {
			json.put("msg", "AI 응답실패");
		}
		
		JsonHndr.print(json, response);
	}
}
