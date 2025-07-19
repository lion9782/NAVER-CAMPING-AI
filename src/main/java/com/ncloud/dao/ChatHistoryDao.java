package com.ncloud.dao;

import java.util.List;

import com.ncloud.domain.ChatLibraryVO;
import com.ncloud.domain.UserChatHistoryVO;

public interface ChatHistoryDao {

	public int insertChatHistory(UserChatHistoryVO vo) throws Exception;
	
	public int updateAimessage(UserChatHistoryVO vo) throws Exception;
	
	public List<UserChatHistoryVO> getChatMessagesByRoomId(int chatRoomId) throws Exception;
}
