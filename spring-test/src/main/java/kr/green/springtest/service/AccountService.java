package kr.green.springtest.service;

import kr.green.springtest.vo.AccountVo;

public interface AccountService {

	AccountVo signin(AccountVo loginInfo);

	boolean signup(AccountVo userInfo);
	boolean isDuplicated(String id);

}
