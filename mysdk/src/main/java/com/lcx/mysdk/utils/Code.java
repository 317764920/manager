package com.lcx.mysdk.utils;

import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


public class Code {
	/**
	 *
	 * @Description(功能描述)    : 加密
	 * @author(作者)         ：liuchunxu
	 * @date (开发日期)      ：2016-04-01 17:12
	 *
	 */
	public static String Encrypt(String sSrc, String sKey) {
		String encodeToString = "";
		try {
			if (sKey == null) {
				System.out.print("Key为空null");
				return null;
			}
			// 判断Key是否为16位
			if (sKey.length() != 16) {
				System.out.print("Key长度不是16位");
				return null;
			}
			String md5 = MD5Util.MD5(sKey, 16);
			byte[] raw = md5.getBytes("utf-8");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");// "算法/模式/补码方式"
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
			encodeToString = Base64.encodeToString(encrypted, Base64.DEFAULT);// 此处使用BASE64做转码功能，同时能起到2次加密的作用。
		} catch (Exception e) {

		}
		return encodeToString;
	}

	/**
	 *
	 * @Description(功能描述)    : 解密
	 * @author(作者)         ：liuchunxu
	 * @date (开发日期)      ：2016-04-01 17:13
	 *
	 */
	public static String Decrypt(String sSrc, String sKey) {
		try {
			// 判断Key是否正确
			if (sKey == null) {
				System.out.print("Key为空null");
				return null;
			}
			// 判断Key是否为16位
			if (sKey.length() != 16) {
				System.out.print("Key长度不是16位");
				return null;
			}
			String md5 = MD5Util.MD5(sKey, 16);
			byte[] raw = md5.getBytes("utf-8");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);
			byte[] encrypted1 = Base64.decode(sSrc,Base64.DEFAULT);// 先用base64解密
			try {
				byte[] original = cipher.doFinal(encrypted1);
				String originalString = new String(original, "utf-8");
				return originalString;
			} catch (Exception e) {
				System.out.println(e.toString());
				return null;
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
			return null;
		}
	}

	public static void main(String[] args) throws Exception {
		/*
		 * 此处使用AES-128-ECB加密模式，key需要为16位。
		 */
		String cKey = "1234567890abcDEF";
		// 需要加密的字串
		String cSrc = "lltech";
		System.out.println(cSrc);
		// 加密
		String enString = Code.Encrypt(cSrc, cKey);
		System.out.println("加密后的字串是：" + enString);

		// 解密
		String DeString = Code.Decrypt(enString, cKey);
		System.out.println("解密后的字串是：" + DeString);
	}
}