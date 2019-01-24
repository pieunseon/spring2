package kr.green.springtest.dao;

import org.apache.ibatis.annotations.Param;

import kr.green.springtest.vo.AccountVo;
import kr.green.springtest.vo.BoardVo;

public interface AccountDao {
	public AccountVo getAccount(@Param("id")String id);
	public void setAccount(@Param("user")AccountVo userInfo);
	public BoardVo getBoard(@Param("id")Integer id);
}
 