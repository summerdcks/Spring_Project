package kr.ac.kopo.ctc.kopo36.board.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.kopo.ctc.kopo36.board.dao.BoardCommentRepository;
import kr.ac.kopo.ctc.kopo36.board.dao.BoardItemRepository;
import kr.ac.kopo.ctc.kopo36.board.domain.BoardComment;
import kr.ac.kopo.ctc.kopo36.board.domain.BoardItem;
import kr.ac.kopo.ctc.kopo36.board.service.BoardItemService;

@Controller
public class BoardCommentController {
	
	@Autowired
	private BoardItemRepository boardItemRepository;
	
	@Autowired
	private BoardCommentRepository boardCommentRepository;
	
	@Autowired
	private BoardItemService boardItemServiceImpl;
	
		//댓글쓰기
		@RequestMapping(value= "/insertComment")
		public String insertComment(@RequestParam("id") Long id, @RequestParam("commentContent") String content) {
			BoardComment boardComment = new BoardComment();
			BoardItem board = new BoardItem();
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");			
			boardComment.setNaeyong(content);
			boardComment.setIlja(formatter.format(date));
			board.setBunho(id);
			boardComment.setBoardItem(board);
			boardCommentRepository.save(boardComment);	
			return "redirect:/view?id=" + id;
		}
		
		//댓글삭제
		@RequestMapping(value= "/deleteComment")
		public String deleteComment(@RequestParam("id") Long id) throws Exception {
			BoardItem boardItem = boardCommentRepository.findById(id).get().getBoardItem();
			boardCommentRepository.deleteById(id);			
			Long boardItemId = boardItem.getBunho();
			return "redirect:/view?id=" + boardItemId;
		}
		
		//댓글수정 화면
		@RequestMapping(value= "/modifyComment")
		public String modifyComment(@RequestParam("id") Long id, Model model) throws Exception {
			BoardComment boardComment = boardCommentRepository.findById(id).get();
			String Naeyong = boardComment.getNaeyong();
			model.addAttribute("commentId", id);
			model.addAttribute("Naeyong", Naeyong);
			return "modifyComment";
		}
			
		//댓글수정, 팝업창 종료
		@RequestMapping(value= "/close")
		public String updateComment(@RequestParam("naeyong") String naeyong, @RequestParam("commentId") int commentId) throws Exception {
			BoardItem boardItem = boardCommentRepository.findById((long) commentId).get().getBoardItem();
			BoardComment boardComment = new BoardComment();
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			boardComment.setBunho(commentId);
			boardComment.setNaeyong(naeyong);
			boardComment.setIlja(formatter.format(date));
			boardComment.setBoardItem(boardItem);
			boardCommentRepository.save(boardComment);	
			return "close";
		}
		
}

