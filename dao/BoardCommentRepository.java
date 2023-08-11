package kr.ac.kopo.ctc.kopo36.board.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.ctc.kopo36.board.domain.BoardComment;

@Repository
public interface BoardCommentRepository extends JpaRepository<BoardComment, Long>, JpaSpecificationExecutor<BoardComment> {
	   

}
