package com.zxz.util;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	private static final String DEFAULTPATTERN = "yyyy-MM-dd";
	private static final String NORMALPATTERN = "yyyy-MM-dd hh:mm:ss";
	/**
	 * �������ַ�תΪjava.util.Date����
	 */
	public static Date strToDate(String dateStr,String parttern) throws Exception{
		if (parttern == null || parttern.equals("")){
			parttern = DEFAULTPATTERN;
		}
		SimpleDateFormat sdf=new SimpleDateFormat(parttern);
		return sdf.parse(dateStr);
	}
	
	/**
     * ��ȡ�ϸ��µ�һ��
     * @return
     */
    public static Date getFirstDayOfLastMonth(){
    	Calendar c = Calendar.getInstance();
    	c.add(Calendar.MONTH, -1);
    	c.set(Calendar.DAY_OF_MONTH,1);
    	c.set(Calendar.HOUR_OF_DAY,	0);
    	c.set(Calendar.MINUTE, 0);
    	c.set(Calendar.SECOND, 0);
    	return c.getTime();
    }
    
    /**
     * ��ȡ�ϸ������һ��
     * @return
     */
    public static Date getLastDayOfLastMonth(){
    	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    	Calendar c = Calendar.getInstance();
    	c.add(Calendar.MONTH, -1);
    	c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
    	
    	String dateStr = format.format(c.getTime());
    	try{
    		return strToDate(dateStr+" 23:59:59","yyyy-MM-dd hh:mm:ss");
    	}catch(Exception e){
    		return null;
    	}
    	
    	/*c.set(Calendar.HOUR_OF_DAY, 23);
    	c.set(Calendar.MINUTE, 59);
    	c.set(Calendar.SECOND, 59);
    	return c.getTime();*/
    }
    
    /**
     * ��ȡ���ڵ����
     * @param date
     * @return
     */
    public static int getYear(Date date){
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);
    	return c.get(Calendar.YEAR);
    }
    
    /**
     * ��ȡ���ڵ��·�
     * @param date
     * @return
     */
    public static int getMonth(Date date){
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);
    	return c.get(Calendar.MONTH)+1;	
    }
    
    /**
     * ��ȡ��һ������
     * @return
     */
    public static int getLastYear(){
    	Calendar c = Calendar.getInstance();
    	c.add(Calendar.YEAR, -1);
    	return c.get(Calendar.YEAR);
    }
    
    /**
     * ��ȡĳ��ĳ�µ����һ��
     * @param year
     * @param month
     * @return
     */
    public static String getLastDayOfMonth(int year, int month){
    	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    	Calendar c = Calendar.getInstance();
    	c.set(Calendar.YEAR, year);
    	c.set(Calendar.MONTH, month-1);
    	c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DATE));
    	String dateStr = format.format(c.getTime());
    	return dateStr;
    }
}
