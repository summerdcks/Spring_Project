package kr.ac.kopo.ctc.kopo36.board.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.ctc.kopo36.board.domain.BoardItem;

@Repository
public interface BoardItemRepository extends JpaRepository<BoardItem, Long>, JpaSpecificationExecutor<BoardItem>{
	Page<BoardItem> findAll(Pageable pageable);
	Page<BoardItem> findByJemokContaining(String JemokKeyword, Pageable pageable);
	

}
