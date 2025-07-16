package com.ncloud.service;

import com.ncloud.domain.ChatLibraryVO;
import com.ncloud.domain.UserChatHistoryVO;

public interface ChatHistoryService {

	public int insertChatHistory(UserChatHistoryVO vo) throws Exception;
	
	public int updateAimessage(UserChatHistoryVO vo) throws Exception;
}
