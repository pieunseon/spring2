package kr.green.springtest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import kr.green.springtest.dao.AccountDao;
import kr.green.springtest.vo.AccountVo;

@Service
public class AccountServiceImp implements AccountService{

	@Autowired
	AccountDao accountDao;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public AccountVo signin(AccountVo loginInfo) {
		AccountVo user = accountDao.getAccount(loginInfo.getId());  //암호화된 정보이기 때문에.먼저 Id 정보를 넣어서 객체정보를 확인 후에 암호화 작업 처리 위해서 
		if(user != null && passwordEncoder.matches(loginInfo.getPw(),user.getPw()))
			return user;
		return null;
	}

	@Override
	public boolean signup(AccountVo userInfo) {
		if(accountDao.getAccount(userInfo.getId()) != null)
		return false;
		String encPw = passwordEncoder.encode(userInfo.getPw());
		userInfo.setPw(encPw);
		accountDao.setAccount(userInfo);
		return true;
	}

	@Override
	public boolean isDuplicated(String id) {
		if(accountDao.getAccount(id) !=null)
		return true; 	//중복 되면
		return false;   //중복이 안되면
	}                  //[ajax] 사용 처 :  아이디 중복검사, pagination, 댓글
}
