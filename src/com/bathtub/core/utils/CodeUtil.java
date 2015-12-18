package com.bathtub.core.utils;

import java.security.Key;
import java.security.MessageDigest;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Hex;

import com.sun.crypto.provider.SunJCE;

/**
 * һ����ܽ��ܹ���<br>
 */
public class CodeUtil {

	static {
		Security.addProvider(new SunJCE());
	}

	/** MD5���� */
	public static final String MD5 = "MD5";
	/** hash���� */
	public static final String DigestAlgorithm = "SHA1";
	/** 3des���� */
	public static final String KeyAlgorithm = "DESede";
	/** 3des����(������) */
	public static final String CryptAlgorithm = "DESede/CBC/PKCS5Padding";
	/** ��Կ */
	private static final String keyString = "3497C134D60D7F9E404916BA3252F8C85E8AEA1315626EC1";
	/** ���� */
	private static final byte[] IV = { 1, 2, 3, 4, 5, 6, 7, 8 };
	/** ���� */
	private static final String encoding = "UTF-8";

	/**
	 * BASE64����
	 */
	public static byte[] base64Encode(byte[] b) {
		return Base64.encode(b);
	}

	/**
	 * BASE64����
	 */
	public static byte[] base64Decode(byte[] b) {
		return Base64.decode(b);
	}

	/**
	 * base64����
	 */
	public static byte[] base64Decode(String s) {
		return Base64.decode(s);
	}

	/**
	 * SHA����
	 */
	public static String generateDigest(String strTobeDigest) throws Exception {
		byte input[] = strTobeDigest.getBytes(encoding);
		byte output[] = null;
		MessageDigest DigestGenerator = MessageDigest
				.getInstance(DigestAlgorithm);
		DigestGenerator.update(input);
		output = DigestGenerator.digest();
		return new String(base64Encode(output));
	}

	/**
	 * ������Կ
	 */
	private static Key keyGenerator(String KeyStr) throws Exception {
		byte input[] = Hex.decode(KeyStr);
		DESedeKeySpec KeySpec = new DESedeKeySpec(input);
		SecretKeyFactory KeyFactory = SecretKeyFactory
				.getInstance(KeyAlgorithm);
		return KeyFactory.generateSecret(KeySpec);
	}

	/**
	 * iv����
	 */
	private static IvParameterSpec IvGenerator(byte b[]) throws Exception {
		IvParameterSpec IV = new IvParameterSpec(b);
		return IV;
	}

	/**
	 * 3des����
	 */
	public static String encrypt(String strTobeEnCrypted) throws Exception {
		byte input[] = strTobeEnCrypted.getBytes(encoding);
		Key k = keyGenerator(keyString);
		IvParameterSpec IVSpec = IvGenerator(IV);
		Cipher c = Cipher.getInstance(CryptAlgorithm);
		c.init(1, k, IVSpec);
		byte output[] = c.doFinal(input);
		return new String(base64Encode(output), encoding);
	}

	/**
	 * 3des����
	 */
	public static String decrypt(String strTobeDeCrypted) throws Exception {
		byte input[] = base64Decode(strTobeDeCrypted);
		Key k = keyGenerator(keyString);
		IvParameterSpec IVSpec = IvGenerator(IV);
		Cipher c = Cipher.getInstance(CryptAlgorithm);
		c.init(2, k, IVSpec);
		byte output[] = c.doFinal(input);
		return new String(output, encoding);
	}

	/**
	 * ʹ�ò�ͬ���㷨���ַ�������
	 * @param password �������ַ���
	 * @param algorithm ѡ���㷨
	 * @return String �ѽ����ַ���
	 */
	public static String encode(String password, String algorithm) {
		byte[] unencodedPassword = password.getBytes();
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance(algorithm);
		} catch (Exception e) {
			return password;
		}
		md.reset();
		md.update(unencodedPassword);
		byte[] encodedPassword = md.digest();
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < encodedPassword.length; i++) {
			if ((encodedPassword[i] & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Long.toString(encodedPassword[i] & 0xff, 16));
		}
		return buf.toString();
	}

}