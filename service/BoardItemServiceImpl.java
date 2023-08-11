package kr.ac.kopo.ctc.kopo36.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.kopo.ctc.kopo36.board.dao.BoardItemRepository;

@Service
public class BoardItemServiceImpl implements BoardItemService{
	@Autowired
	private BoardItemRepository boardItemRepository;



}
