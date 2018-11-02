package com.zxz.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class Util {

	public static SimpleDateFormat DATE_TIME_MS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
	public static SimpleDateFormat DATE_TIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat DATE = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat YEAR = new SimpleDateFormat("yyyy");
	public static SimpleDateFormat MONTH = new SimpleDateFormat("MM");
	/**
	 * �õ���ǰϵͳ��ʱ��
	 * 
	 * @return �����ַ��� ��ʽ yyyy-MM-dd HH:mm:ss SSS
	 */
	public static String getTableTS() {
		return getDateTimeMs(new Date(System.currentTimeMillis()), Util.DATE_TIME_MS);
	}

	/**
	 * ���ش����������ʱ���ַ���
	 * 
	 * @param date
	 * @param sdf
	 *            SimpleDateFormat �о�̬���� ��ʾ��ʽ
	 * @return
	 */
	public static String getDateTimeMs(Date date, SimpleDateFormat sdf) {
		if (date == null) {
			return "";
		}
		return sdf.format(date);
	}

	/**
	 * ���ַ���ת��ΪDate
	 * 
	 * @param date
	 *            ����Date��ʽ yyyy-MM-dd
	 * @return
	 */
	public static Date doConvertToDate(String date) {
		Date result = null;
		try {
			result = DATE.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * ���ش�����ʱ���ַ��� ��ʽyyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateTime(Date date) {

		return DATE_TIME.format(date);
	}

	/**
	 * ���ص�ǰ���yyyy
	 * @return
	 */
	public static String getCurrentYear() {
		Date date = new Date();
		return YEAR.format(date);
	}	/**
	 * ���ص�ǰ�·�MM
	 * @return
	 */
	public static String getCurrentMonth() {
		Date date = new Date();
		return MONTH.format(date);
	}
	
	/**
	 * ���ش������ַ��� ��ʽyyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static String getDate(Date date) {
		if (date == null) {
			return "";
		}
		return DATE.format(date);
	}

	/**
	 * ���ش����ں�ʱ����ַ���
	 * 
	 * @param date
	 * @param sdf
	 *            SimpleDateFormat �о�̬���� ��ʾ��ʽ
	 * @return
	 */
	public static String getOpTime(Date date, SimpleDateFormat sdf) {
		if (date == null) {
			return "";
		}
		return sdf.format(date);
	}

	/**
	 * ���ַ���ת��Ϊ��������
	 * 
	 * @param date
	 *            �����ַ���yyyy-MM-dd
	 * @return
	 */
	public static Date parseDate(String date) {
		Date d = null;
		try {
			d = DATE.parse(date);
		} catch (ParseException e) {
		}
		return d;
	}

	/**
	 * ���ַ���ת��Ϊ����ʱ������
	 * 
	 * @param date
	 *            ���������ַ���yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static Date parseDateTime(String date) {
		if (date == null || "".equals(date))
			return null;
		Date destDate = null;
		try {
			destDate = DATE_TIME.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return destDate;
	}

	/**
	 * ����������ȡָ����ݺ��·�
	 * 
	 * @author �к���
	 * @version 2010-10-18
	 * @param monthNumber
	 *            Ҫ���ӻ���ٵ��·���
	 * @return
	 */
	public static String getDefaultDate(int monthNumber) {
		DecimalFormat df = new DecimalFormat("00");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, monthNumber); // �õ���ǰ�·�+monthNumberge����

		int year = calendar.get(Calendar.YEAR);// ��ȡ��ǰ���
		int month = calendar.get(Calendar.MONTH) + 1;// ��ȡ��ǰ�·�

		return year + "-" + df.format(month);
	}

	/**
	 * ����������ȡָ������
	 * 
	 * @author �к���
	 * @version 2010-10-18
	 * @param monthNumber
	 *            Ҫ���ӻ���ٵ��·���
	 * @param dayNumber
	 *            Ҫ���ӻ���ٵ�����
	 * @return
	 */
	public static String getDefaultDate(int monthNumber, int dayNumber) {
		DecimalFormat df = new DecimalFormat("00");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, monthNumber); // �õ���ǰ�·�+monthNumberge����
		calendar.add(Calendar.DATE, dayNumber); // �õ���ǰ����+monthNumber��
		int year = calendar.get(Calendar.YEAR);// ��ȡ��ǰ���
		int month = calendar.get(Calendar.MONTH) + 1;// ��ȡ��ǰ�·�
		int day = calendar.get(Calendar.DATE);// ��ȡ��ǰ����
		
		return year + "-" + df.format(month) + "-" + df.format(day);
	}

	public static void main(String[] args) {
		System.out.println(formatFloat(12345623432.123456));
	}



	/**
	 * ��NULL�ַ���תΪ""
	 * 
	 * @param str
	 * @return
	 */
	public static String nvl(Object str) {
		return str == null ? "" : str.toString();
	}

	/**
	 * java��ʽ��ȡ32λUUID
	 * 
	 * @author �к���
	 * @version 2010-9-16
	 * @return
	 */
	public static String getUuid() {
		UUID uuid = UUID.randomUUID();

		return uuid.toString().replaceAll("-", "");
	}

	/**
	 * ֵ�Ƿ�Ϊ��
	 * 
	 * @author �к���
	 * @version 2010-9-19
	 * @param value
	 * @return
	 */
	public static boolean isNotEmpty(String value) {

		return null != value && !value.trim().equals("") && !value.equals("undefined") && !value.trim().equals("null");
	}

	/**
	 * ��ʽ��������
	 * @param f
	 * @return
	 */
	public static String formatFloat(double f) {
		java.text.DecimalFormat df = new java.text.DecimalFormat("#0.00");
		String sj = df.format(f);
		return sj;
	}
	
	/**
	 * �Ƚ������ַ���ʱ��Ĵ�С
	 * @param t1     ʱ��1
	 * @param t2     ʱ��2
	 * @param parrten ʱ���ʽ :yyyy-MM-dd
	 * @return ����long =0��ȣ�>0 t1>t2��<0 t1<t2
	 */
	public static long compareStringTime(String t1, String t2, String parrten) {
		SimpleDateFormat formatter = new SimpleDateFormat(parrten);
		ParsePosition pos = new ParsePosition(0);
		ParsePosition pos1 = new ParsePosition(0);
		Date dt1 = formatter.parse(t1, pos);
		Date dt2 = formatter.parse(t2, pos1);
		long l = dt1.getTime() - dt2.getTime();
		return l;
	}
	
	/**
	 * ����MD5����
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String createMD5(String str) throws Exception {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		byte[] srcByte = str.getBytes();
		md5.update(srcByte);
		byte[] resultByte = md5.digest();
		String result = new String(resultByte);

		result = "";
		for (int i = 0; i < resultByte.length; i++) {
			result += Integer.toHexString(
					(0x000000ff & resultByte[i]) | 0xffffff00).substring(6);
		}

		return result;
	}

	/**
	 * ������ƴ�ӳ�IN���(�ַ���)
	* @author GZZ
	 * @param arr
	 * @return
	 */
	public static String ArrayToIn(String[] arr) {// �ַ�IN�ַ���
		StringBuffer sb = new StringBuffer("IN (");
		for (int i = 0; i < arr.length; i++) {
			if (i < arr.length - 1) {
				sb.append("'" + arr[i] + "',");
			} else {
				sb.append("'" + arr[i]+"'");
			}
		}
		sb.append(")");
		return sb.toString();
	}

	/**
	 * ������ƴ�ӳ�IN���(��ֵ��)
	 * @author GZZ
	 * @param arr
	 * @return
	 */
	public static String ArrayToInNum(String[] arr) {// �ַ�IN�ַ���
		StringBuffer sb = new StringBuffer("IN (");
		for (int i = 0; i < arr.length; i++) {
			if (i < arr.length - 1) {
				sb.append(arr[i] + ",");
			} else {
				sb.append(arr[i]);
			}
		}
		sb.append(")");
		return sb.toString();
	}
	
	/**
	 * ��������Ŀ¼
	 * @param path
	 */
	public static void mkdir(String path) {
		File dir = new File(path);
		
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}
	}
	
	/**
	 * ��ȡȫ���ļ��� �ļ���+��չ��
	 * @param filePath
	 */
	public static String getFullFileName(String filePath) {
		filePath.replace("\\", "/");
		String[] pathArray = filePath.split("\\/");
		
		return pathArray[pathArray.length - 1];
	}
	
	/**
	 * ��ȡ�ļ���
	 * @param filePath
	 * @return
	 */
	public static String getFileName(String filePath) {
		String fileName = getFullFileName(filePath);
		
		String[] fileArray = fileName.split("\\.");
		return fileArray[0];
	}
	
	/**
	 * ��ȡ�����ļ�·��
	 * @param paramMap
	 * @param exportTemplatePath
	 * @return
	 */
	public static String getExportFileName(Map<String, Object> paramMap, ServletContext context, String exportTemplatePath) {
		String resultFileName = null;
		Configuration freemarkerCfg = new Configuration();  
		
		//����ģ��  
		freemarkerCfg.setServletContextForTemplateLoading(context, "/");  
		freemarkerCfg.setEncoding(Locale.getDefault(), "UTF-8"); 
		
		try {  
		    //ָ��ģ��·��  
		    Template template = freemarkerCfg.getTemplate(exportTemplatePath, "UTF-8");  
		    template.setEncoding("UTF-8");
		    
		    //���ɾ�̬�ļ�Ŀ¼
		    String year = getCurrentYear();
		    String month = getCurrentMonth();
		    
		    String filePath = context.getRealPath("/report/export" ) + "/" + year + "/" + month + "/";;
		    String fileName =  getFileName(exportTemplatePath) + ".xls";
		    mkdir(filePath);
		    
		    resultFileName = filePath + fileName;
		    File exportFile = new File(resultFileName);  
		    Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(exportFile), "UTF-8"));  
            
		    template.process(paramMap, out); 
            
            out.flush();  
            out.close();  
		} catch (Exception e) {  
		    e.printStackTrace();  
		}

		return resultFileName;
	}
	
	
	
	public static String getWeek(int week){
		String w = "";
		switch (week) {
		case 0:
			w="������";
			break;
		case 1:
			w="����һ";
			break;
		case 2:
			w="���ڶ�";
			break;
		case 3:
			w="������";
			break;
		case 4:
			w="������";
			break;
		case 5:
			w="������";
			break;
		case 6:
			w="������";
			break;			
		default:
			break;
		}
		return w;
	}
	
	/**
	 * ����˰��
	 * @param salary
	 */
	public static double salaryTax(double salary){
		double tax = 0;
		double moneyTax = salary - 2000; //����2000�Ž�˰
		if(moneyTax > 0){
			if(moneyTax < 500){
				tax = moneyTax * 0.05;
			}else if(moneyTax < 2000){
				tax = moneyTax * 0.1 - 25;
			}else if(moneyTax < 2000){
				tax = moneyTax * 0.1 - 25;
			}else if(moneyTax < 5000){
				tax = moneyTax * 0.15 - 125;
			}else if(moneyTax < 20000){
				tax = moneyTax * 0.2 - 375;
			}else if(moneyTax < 40000){
				tax = moneyTax * 0.25 - 1375;
			}else if(moneyTax < 60000){
				tax = moneyTax * 0.30 - 3375;
			}else if(moneyTax < 80000){
				tax = moneyTax * 0.35 - 6375;
			}else if(moneyTax < 100000){
				tax = moneyTax * 0.40 - 10375;
			}else{
				tax = moneyTax * 0.45 - 15375;
			}
		}
		return tax;
	}
	
}
