package com.lcx.mysdk.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
	public final static String MD5(String s,int w) {
		StringBuffer buf = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(s.getBytes());
			byte b[] = md.digest();

			int i;

			buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			switch (w) {
			case 16:
				return buf.toString().substring(8, 24).toUpperCase();// 16位的加密
			case 32:
				return buf.toString().toUpperCase();// 32位的加密
			}


		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		return "";
	}

	public static void main(String[] args) {
		System.out.println(MD5Util.MD5("1234567890abcDEF",16));
		System.out.println(MD5Util.MD5("1234567890abcDEF",32));
	}
}
