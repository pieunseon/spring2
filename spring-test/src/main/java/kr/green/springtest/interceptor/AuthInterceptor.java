package kr.green.springtest.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler)
			throws Exception {
		HttpSession session = request.getSession();		//session에 있는 유저 정보 가져온다.
		Object user = session.getAttribute("user");    //Object 로 가져오는 이유: 유저 정보가 있는지 없는지만 확인해서 가져오면되기때문에
		//AccountVo user = (AccountVo)session.getAttribute("user");  >> object 대신 사용가능
		
		if(user == null) {
			response.sendRedirect(request.getContextPath()+"/");
		}
		return true;
	}
}
