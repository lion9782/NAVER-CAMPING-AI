package com.ncloud.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ncloud.domain.ChatLibraryVO;
import com.ncloud.domain.UserChatHistoryVO;

@Repository
public class ChatHistoryDaoImpl implements ChatHistoryDao{

	@Inject
	private SqlSession sql;
	
	@Override
	public int insertChatHistory(UserChatHistoryVO vo) throws Exception{
		return sql.insert("chatHistory.insertChatHistory", vo);
	}
	
	@Override
	public int updateAimessage(UserChatHistoryVO vo) throws Exception{
		return sql.update("chatHistory.updateAimessage", vo);
	}
	
	@Override
	public List<UserChatHistoryVO> getChatMessagesByRoomId(int chatRoomId) throws Exception {
	    return sql.selectList("chatHistory.getChatMessagesByRoomId", chatRoomId);
	}
	

}
