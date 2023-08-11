package kr.ac.kopo.ctc.kopo36.board.domain;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;

@Entity
public class BoardItem { //공지사항 클래스
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long bunho; //글번호
	
	@Column(length= 500)
	private String jemok; //제목
		
	@Column(columnDefinition = "TEXT", nullable = false)
	private String naeyong; //내용
	
	@Column(nullable = false)
	private String ilja; //등록일
	
	@Column(columnDefinition = "integer default 0", nullable = false)
	private int viewcnt; //조회수
		
	public BoardItem() {
		
	}
	
	public Long getBunho() {
		return bunho;
	}

	public String getJemok() {
		return jemok;
	}

	public String getIlja() {
		return ilja;
	}

	public String getNaeyong() {
		return naeyong;
	}

	public int getViewcnt() {
		return viewcnt;
	}

	public void setBunho(Long bunho) {
		this.bunho = bunho;
	}

	public void setJemok(String jemok) {
		this.jemok = jemok;
	}

	public void setIlja(String ilja) {
		this.ilja = ilja;
	}

	public void setNaeyong(String naeyong) {
		this.naeyong = naeyong;
	}

	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}

	@OneToMany(cascade=CascadeType.ALL, mappedBy="boardItem", fetch = FetchType.LAZY)
	@OrderBy("bunho asc")
	private Collection<BoardComment> boardComments;
	
	public Collection<BoardComment> getBoardComment() {
		if(boardComments == null) {
			boardComments = new ArrayList<BoardComment>();
		}
		return boardComments;
	}
	
	public void setBoardComment(Collection<BoardComment> boardComments) {
		this.boardComments = boardComments;
	}
	
	public void addBoardComment(BoardComment bc) {
		Collection<BoardComment> boardComments = getBoardComment();
		bc.setBoardItem(this);
		boardComments.add(bc);
	}
	
}
