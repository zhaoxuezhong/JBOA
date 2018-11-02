package com.zxz.util;
import java.security.MessageDigest;
/**
 * ʹ�÷�����MD5.MD5Encode("����")
 */
public class MD5 {
	/**
	 * 16�����ַ�
	 */
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	/**
	 * ���ֽ�����ת��Ϊ16�����ַ�
	 */
	private static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	/**
	 * ���ֽ�ת16�����ַ�
	 * @param ��ת���ֽ�
	 * @return 16�����ַ�
	 */
	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}
	/**
	 * ʹ��MessageDigest���MD5ժҪ�㷨���Դ��Ϣ����ϢժҪ
	 * @return ��ɵ�32λ16�����ַ���ϢժҪ
	 */
	public static String MD5Encode(String origin) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(resultString
					.getBytes()));
		} catch (Exception ex) {}
		return resultString;
	}
	
	public static void main(String[] args) {
		String md5=MD5Encode("123");
		System.out.println(md5);//�����202cb962ac59075b964b07152d234b70

	}
}
