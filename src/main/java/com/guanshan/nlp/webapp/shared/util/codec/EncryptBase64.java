package com.guanshan.nlp.webapp.shared.util.codec;

import java.io.UnsupportedEncodingException;

import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.codec.Base64;


public class EncryptBase64 extends MessageDigestPasswordEncoder {

	
	public EncryptBase64(String algorithm) {
		super(algorithm);
		// TODO Auto-generated constructor stub
	}

	@Override
	// 如果返回true，则验证通过。
	public boolean isPasswordValid(String savePass, String submitPass, Object salt){
		return savePass.equalsIgnoreCase(getBase64Code(submitPass));
	}

	// 全局数组
	private final static String[] strDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
			"e", "f" };

	// 返回形式为数字和字符串
	private static String byteToArrayString(byte bByte) {
		int iRet = bByte;
		if (iRet < 0) {
			iRet += 256;
		}
		int iD1 = iRet / 16;
		int iD2 = iRet % 16;
		return strDigits[iD1] + strDigits[iD2];
	}

	// 返回形式只为数字
	private static String byteToNum(byte bByte) {
		int iRet = bByte;
		if (iRet < 0) {
			iRet += 256;
		}
		return String.valueOf(iRet);
	}

	// 转换字节数组为16进制字串
	private static String byteToString(byte[] bByte) {
		StringBuffer sBuffer = new StringBuffer();
		for (int i = 0; i < bByte.length; i++) {
			sBuffer.append(byteToArrayString(bByte[i]));
		}
		return sBuffer.toString();
	}

	public static String getBase64Code(String sourceStr) {

		String resultString = null;
		try {
			byte[] sourceStrBytes = sourceStr.getBytes("UTF-8");
			byte[] result = Base64.encode(sourceStrBytes);
			// md.digest() 该函数返回值为存放哈希值结果的byte数组
			resultString = byteToString(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultString;
	}

//	public static void main(String[] args) throws UnsupportedEncodingException {
//		System.out.println(EncryptBase64.getBase64Code("123467896532"));
//	}

}
