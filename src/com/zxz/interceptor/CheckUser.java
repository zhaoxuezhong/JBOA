package com.zxz.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.zxz.entity.SysEmployee;

/**
 * @author zhaoxuezhong
 *
 */
public class CheckUser extends AbstractInterceptor {
	private static final long serialVersionUID = -3117582792803376064L;

	@Override
	public String intercept(ActionInvocation ai) throws Exception {
		String actionName=ai.getInvocationContext().getName();
		SysEmployee emp=(SysEmployee) ai.getInvocationContext().getSession().get("employee");
		if(emp==null&&!"login".equals(actionName)){
			return "login";
		}
		return ai.invoke();
	}

}
