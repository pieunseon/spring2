package kr.green.springtest.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kr.green.springtest.dao.BoardDao;
import kr.green.springtest.vo.AccountVo;
import kr.green.springtest.vo.BoardVo;

@Service
public class BoardServiceImp implements BoardService {
	@Autowired
	BoardDao boardDao;

	@Override
	public List<BoardVo> getBoards() {
		return boardDao.getBoards();
	}

	@Override
	public void registerBoard(BoardVo board) {
		boardDao.setBoard(board);	
	}

	@Override
	public BoardVo detailBoard(Integer id) {
		if(id == null)
			return null;
		return boardDao.getBoard(id);
	}


	@Override
	public void deleteBoard(Integer id, AccountVo user) { 
		BoardVo board = boardDao.getBoard(id);
		/*1. 해당 id를 가진 게시글이 이미 삭제된 경우이거나 
		 *2. 로그인한 유저가 게시판 작성자가 아닌경우에는 게시판 삭제 동작이 실행되지 않고 게시판 리스트페이지로 넘어간다 
		*/
		if(board == null 
			|| !board.getWriter().equals(user.getId())) return;      
		board.setState("D"); 
		
		boardDao.updateBoard(board);   //update: 수정, 삭제  같이 처리위해 (재사용이 가능한 메소드) 
	}
	//게시글 수정
	@Override
	public boolean modifyBoard(BoardVo board, AccountVo user) {
		BoardVo oriBoard = boardDao.getBoard(board.getId());
		if(oriBoard == null || !oriBoard.getWriter().contentEquals(user.getId()))
		return false;
		  board.setRegistered_date(oriBoard.getRegistered_date()); 
		  board.setState(oriBoard.getState()); 
		  boardDao.updateBoard(board); 
		 return true;
	}

}
