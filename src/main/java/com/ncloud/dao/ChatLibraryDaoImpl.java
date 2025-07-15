package com.ncloud.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ncloud.domain.ChatLibraryVO;

@Repository
public class ChatLibraryDaoImpl implements ChatLibraryDao{

	@Inject
	private SqlSession sql;
	
	@Override
	public int insertChatLibrary(ChatLibraryVO vo) throws Exception{
		return sql.insert("chatLibrary.insertChatLibrary", vo);
	}
}
