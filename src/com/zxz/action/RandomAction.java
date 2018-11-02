package com.zxz.action;

import java.io.InputStream;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.zxz.util.RandomNumUtil;

public class RandomAction extends ActionSupport{
	private static final long serialVersionUID = -516526476175822029L;
	private InputStream inputStream;
	
	public String random()throws Exception{
		RandomNumUtil rnu=RandomNumUtil.Instance();
		inputStream=rnu.getImage();
		ActionContext.getContext().getSession().put("random", rnu.getString());
		return SUCCESS;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
}
