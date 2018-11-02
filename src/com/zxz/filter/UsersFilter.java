/**
 * 
 */
package com.zxz.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zxz.entity.SysEmployee;

/**
 * @author zhaoxuezhong
 *
 */
public class UsersFilter implements Filter {

	@Override
	public void destroy() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest)arg0;
		SysEmployee users=(SysEmployee) request.getSession().getAttribute("employee");
		String url=request.getRequestURI();
		if((users!=null&&!users.getName().equals(""))||url.indexOf("login.jsp")!=-1||url.indexOf("error.jsp")!=-1){
			arg2.doFilter(arg0, arg1);
		}
		else if(url.equals("/JBOA/")){
			((HttpServletResponse)arg1).sendRedirect("http://"+request.getHeader("Host")+"/JBOA/login.jsp");
		}
		else{
			request.getSession().setAttribute("message", "请先登录你的账号！");
			((HttpServletResponse)arg1).sendRedirect("http://"+request.getHeader("Host")+"/JBOA/login.jsp");
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO 自动生成的方法存根
		
	}


}
