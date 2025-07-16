package com.ncloud.dao;

import com.ncloud.domain.ChatLibraryVO;
import com.ncloud.domain.UserChatHistoryVO;

public interface ChatHistoryDao {

	public int insertChatHistory(UserChatHistoryVO vo) throws Exception;
	
	public int updateAimessage(UserChatHistoryVO vo) throws Exception;
	
}
