package com.zxz.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.zxz.entity.BizClaimVoucher;
import com.zxz.entity.condition.ClaimVoucherStatisticsCondition;
import com.zxz.entity.page.PageInfo;
import com.zxz.service.BizClaimVoucherStatisticsService;
import com.zxz.service.impl.BizClaimVoucherStatisticsServiceImpl;
import com.zxz.util.DateUtil;
import com.zxz.util.ExportDataExcel;

/**
 * @author Administrator
 * 
 */
public class ExportDataExcelAction extends ActionSupport {
	private static final long serialVersionUID = -2012449644557613950L;
	private BizClaimVoucherStatisticsService bcvss = new BizClaimVoucherStatisticsServiceImpl();
	private ClaimVoucherStatisticsCondition condition = new ClaimVoucherStatisticsCondition();
	private InputStream inputStream;
	
	@SuppressWarnings({ "deprecation", "unchecked", "rawtypes" })
	public String exportExcel() throws Exception {
		try {
			List<String> heads = new ArrayList<String>();
			heads.add("编号");
			heads.add("报销人");
			heads.add("报销总额");
			heads.add("年份");
			if(condition.getMonth()!=null) heads.add("月份");
			heads.add("部门");
			PageInfo<BizClaimVoucher> pageInfo = bcvss.findClaimVoucherByMonth(
					condition, -1, -1);
			List bodys=new ArrayList();
			List item=null;
			double sum=0;
			for(BizClaimVoucher bcv:pageInfo.getList()){
				item=new ArrayList();
				item.add(bcv.getId());
				item.add(bcv.getSysEmployeeByCreateSn().getName());
				item.add(bcv.getTotalAccount());
				item.add(DateUtil.getYear(bcv.getModifyTime()));
				if(condition.getMonth()!=null)item.add(DateUtil.getMonth(bcv.getModifyTime()));
				item.add(bcv.getSysEmployeeByCreateSn().getSysDepartment().getName());
				bodys.add(item);
				sum+=bcv.getTotalAccount();
			}
			item=new ArrayList();
			item.add("");
			item.add("总计");
			item.add(sum);
			bodys.add(item);
			String excelName=condition.getMonth()+"月度报销单统计详情表";
			if(condition.getMonth()==null) excelName=condition.getYear()+"年度报销单统计详情表";
			HSSFWorkbook workbook=ExportDataExcel.expExcel(heads,bodys,excelName);
			ExportDataExcel.outFile(workbook, ServletActionContext.getRequest().
					getRealPath("/file/"+excelName+System.currentTimeMillis()+".xls"));
			inputStream=new ByteArrayInputStream("导出成功".getBytes("utf-8"));
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			inputStream=new ByteArrayInputStream("导出失败".getBytes("utf-8"));
			return ERROR;
		}
	}
	public BizClaimVoucherStatisticsService getBcvss() {
		return bcvss;
	}
	public void setBcvss(BizClaimVoucherStatisticsService bcvss) {
		this.bcvss = bcvss;
	}
	public ClaimVoucherStatisticsCondition getCondition() {
		return condition;
	}
	public void setCondition(ClaimVoucherStatisticsCondition condition) {
		this.condition = condition;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
}
