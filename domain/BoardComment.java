package kr.ac.kopo.ctc.kopo36.board.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class BoardComment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int bunho; //글번호
	
	@Column
	private String jemok; //제목
	
	@Column
	private String ilja; //등록일
	
	@Column(columnDefinition = "TEXT")
	private String naeyong; //내용
		
	public BoardItem getBoardItem() {
		return boardItem;
	}

	public void setBoardItem(BoardItem boardItem) {
		this.boardItem = boardItem;
	}		
	
	public int getBunho() {
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

	public void setBunho(int bunho) {
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

	@ManyToOne(optional=false)
	@JoinColumn(name="rootid")
	private BoardItem boardItem;
	
	

}
