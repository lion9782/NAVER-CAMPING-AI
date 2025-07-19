package com.ncloud.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncloud.dao.ChatHistoryDao;
import com.ncloud.dao.ChatLibraryDao;
import com.ncloud.domain.ChatLibraryVO;
import com.ncloud.domain.UserChatHistoryVO;

@Service
public class ChatHistoryServiceImpl implements ChatHistoryService{

	@Inject
	private ChatHistoryDao dao;
		
	@Override
	public int insertChatHistory(UserChatHistoryVO vo) throws Exception{
		return dao.insertChatHistory(vo);
	}
	
	@Override
	public int updateAimessage(UserChatHistoryVO vo) throws Exception{
		return dao.updateAimessage(vo);
	}
	
	@Override
	public List<UserChatHistoryVO> getChatMessagesByRoomId(int chatRoomId) throws Exception {
	    return dao.getChatMessagesByRoomId(chatRoomId);
	}
	
}
