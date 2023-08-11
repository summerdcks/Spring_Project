package kr.ac.kopo.ctc.kopo36.board.web;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import kr.ac.kopo.ctc.kopo36.board.dao.BoardItemRepository;
import kr.ac.kopo.ctc.kopo36.board.domain.BoardComment;
import kr.ac.kopo.ctc.kopo36.board.domain.BoardItem;
import kr.ac.kopo.ctc.kopo36.board.service.BoardItemService;

@Controller
public class BoardItemController {
	
	@Autowired
	private BoardItemRepository boardItemRepository;
	
	@Autowired
	private BoardItemService boardItemServiceImpl;

	@RequestMapping(value="")
	@ResponseBody
	public String write() {
		return "hello";
	}	
	
	//메인화면에 리스트 출력, 페이지네이션 구현
	@GetMapping("/main") 
	public String getAllBoards(Model model, Pageable pageable, @RequestParam(value="page", defaultValue="0") Integer curPage) {
		int currentPage = curPage - 1;
		if(currentPage < 0) currentPage = 0;		
		PageRequest pageRequest = PageRequest.of(currentPage, 15, Sort.by(Sort.Direction.DESC, "bunho"));		
		Page<BoardItem> pages = boardItemRepository.findAll(pageRequest);
		model.addAttribute("pages", pages);
		return "main";
	}
	
	//글조회, 댓글조회
	@RequestMapping(value= "/view")
	public String getView(@RequestParam("id") Long id, Model model) throws Exception {
		BoardItem boardItem = boardItemRepository.findById(id).get();
		Collection<BoardComment> comments = boardItemRepository.findById(id).get().getBoardComment();	
		boardItem.setViewcnt(boardItem.getViewcnt() + 1);
		boardItemRepository.save(boardItem);				
		model.addAttribute("boardItem", boardItem);
		model.addAttribute("comments", comments);
		return "view";
	}
	
	//글쓰기 화면
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String write(Model model) throws Exception {
		return "write";
	}
	
	//글쓰기 적용
	@RequestMapping(value="/writeFinish")
	public String insertOne(HttpServletRequest req, Model model) throws Exception {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		if(req.getParameter("title") != null) {
			BoardItem boardItem = new BoardItem();
			boardItem.setIlja(formatter.format(date));
			boardItem.setJemok(req.getParameter("title"));
			boardItem.setNaeyong(req.getParameter("content"));
			boardItemRepository.save(boardItem);
		}	
		return "redirect:/main";
	}
	
	//글삭제
	@RequestMapping(value= "/delete")
	public String deleteOne(@RequestParam("id") Long id, Model model) throws Exception {	
		BoardItem boardItem = boardItemRepository.findById(id).get();
		boardItemRepository.deleteById(id);	
		return "redirect:/main";
	}
	
	
	//글수정 화면
	@RequestMapping(value= "/modify")
	public String modify(@RequestParam("id") Long id, Model model) throws Exception {
		BoardItem boardItem = boardItemRepository.findById(id).get();
		model.addAttribute("boardItem", boardItem);
		return "modify";
	}
	
	//글수정 적용
	@PostMapping(value= "/update")
	public String update(@ModelAttribute BoardItem board, Model model) {
		BoardItem boardItem = boardItemRepository.findById(board.getBunho()).get();		
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		boardItem.setIlja(formatter.format(date));
		boardItem.setJemok(board.getJemok());
		boardItem.setNaeyong(board.getNaeyong());
		boardItemRepository.save(boardItem);				
		model.addAttribute("boardItem", boardItem);				
		return "view";
	}
	
	//글제목 검색
	@RequestMapping(value= "/search")
	public String search(@RequestParam("str") String str, Model model, Pageable pageable, HttpServletRequest req) throws Exception {	
		int curPage = 0;
		if(req.getParameter("page") == null) {
			curPage = 0;
		} else {
			curPage = Integer.parseInt(req.getParameter("page")) - 1;
		}
		if(curPage < 0) curPage = 0;		
		Sort sort = Sort.by(Sort.Direction.DESC, "bunho");
		Pageable pageableWithSorting = PageRequest.of(curPage, pageable.getPageSize(), sort);
		Page<BoardItem> pages = boardItemRepository.findByJemokContaining(str, pageableWithSorting);
		model.addAttribute("pages", pages);
		model.addAttribute("str", str);
		System.out.println(str);
		return "search";
	}
	
}
