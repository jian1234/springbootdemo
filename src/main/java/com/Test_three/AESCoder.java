package com.Test_three;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang3.RandomStringUtils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.Base64;

import io.swagger.annotations.Api;

@Api(tags = "AES加密组件，使用CBC模式，需要一个向量iv，可增加加密算法的强度，IV的生成采用RandomStringUtils随机生成，16bytes长度")
public class AESCoder {
	private static final String KEY_ALGORITHM = "AES";
	private static final String DEFAULT_CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";// 默认的加密算法

	public static byte[] initSecretKey() {
		// 返回生成指定算法密钥生成器的 KeyGenerator 对象
		KeyGenerator kg = null;
		try {
			kg = KeyGenerator.getInstance(KEY_ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return new byte[0];
		}
		// 初始化此密钥生成器，使其具有确定的密钥大小
		// AES 要求密钥长度为 128
		kg.init(128);
		// 生成一个密钥
		SecretKey secretKey = kg.generateKey();
		return secretKey.getEncoded();
	}

	private static Key toKey(byte[] key) {
		// 生成密钥
		return new SecretKeySpec(key, KEY_ALGORITHM);
	}

	public static byte[] encrypt(byte[] data, Key key, String iv) throws Exception {
		return encrypt(data, key, DEFAULT_CIPHER_ALGORITHM, iv);
	}

	public static byte[] encrypt(byte[] data, byte[] key, String iv) throws Exception {
		return encrypt(data, key, DEFAULT_CIPHER_ALGORITHM, iv);
	}

	public static byte[] encrypt(byte[] data, byte[] key, String cipherAlgorithm, String iv)
			throws Exception {
		// 还原密钥
		Key k = toKey(key);
		return encrypt(data, k, cipherAlgorithm, iv);
	}

	/**
	 * AES 加密操作
	 *
	 * @param data
	 *                            待加密内容
	 * @param key
	 *                            加密密码
	 * @param cipherAlgorithm
	 *                            加密算法
	 * @param iv
	 *                            使用CBC模式，需要一个向量iv，可增加加密算法的强度
	 * @return 加密数据
	 */
	public static byte[] encrypt(byte[] data, Key key, String cipherAlgorithm, String iv)
			throws Exception {
		// 创建密码器
		Cipher cipher = Cipher.getInstance(cipherAlgorithm);
		// 向量iv
		IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes("utf-8"));
		// 使用密钥初始化，设置为加密模式
		cipher.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec);
		// 执行操作
		return cipher.doFinal(data);
	}

	public static byte[] decrypt(byte[] data, byte[] key, String iv) throws Exception {
		return decrypt(data, key, DEFAULT_CIPHER_ALGORITHM, iv);
	}

	public static byte[] decrypt(byte[] data, Key key, String iv) throws Exception {
		return decrypt(data, key, DEFAULT_CIPHER_ALGORITHM, iv);
	}

	public static byte[] decrypt(byte[] data, byte[] key, String cipherAlgorithm, String iv)
			throws Exception {
		// 还原密钥
		Key k = toKey(key);
		return decrypt(data, k, cipherAlgorithm, iv);
	}

	/**
	 * AES 解密操作
	 *
	 * @param data
	 *                            密文
	 * @param key
	 *                            密码
	 * @param cipherAlgorithm
	 *                            加密算法
	 * @param iv
	 *                            使用CBC模式，需要一个向量iv，可增加加密算法的强度
	 * @return 明文
	 */
	public static byte[] decrypt(byte[] data, Key key, String cipherAlgorithm, String iv)
			throws Exception {
		// 创建密码器
		Cipher cipher = Cipher.getInstance(cipherAlgorithm);
		// 向量iv
		IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes("utf-8"));
		// 使用密钥初始化，设置为解密模式
		cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);
		// 执行操作
		return cipher.doFinal(data);
	}

	/**
	 * 将二进制转换成16进制
	 * 
	 * @param buf
	 * @return
	 */
	public static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 将16进制转换为二进制
	 * 
	 * @param hexStr
	 * @return
	 */
	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}
	
	public byte[] encryption(String message) throws UnsupportedEncodingException, Exception {
		byte[] key = initSecretKey();
		Key k = toKey(key); // 生成秘钥
		String content = message;
		String iv = RandomStringUtils.randomAlphanumeric(16);
		byte[] md5Encrypt = Coder.encryptMD5(content.getBytes("utf-8"));
		byte[] encryptData = encrypt(content.getBytes("utf-8"), k,iv);// 数据加密
		return encryptData;
	}
	
	public static byte[] MD5(String data) throws  Exception {
		byte[] key = initSecretKey();
		Key k = toKey(key); // 生成秘钥
		String iv = RandomStringUtils.randomAlphanumeric(16);//获取偏移量
		byte[] md5Encrypt = Coder.encryptMD5(data.getBytes("utf-8"));
		String sEncrypt = parseByte2HexStr(md5Encrypt);//
		byte[] encryptData = encrypt(data.getBytes("utf-8"), k,iv);
		return encryptData;
	}
	
	
	public static Object getAlldata(Map<String, Object> map) throws UnsupportedEncodingException, Exception {
		String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQClh4jKqfhn9Gd4CGmA78Dm+GkiPM/0p2YPymt0Odq9DjWA6ycbVZK3H6W5tOgqiLncwMKH0ao5BEp0TFjKtyR9pvs4qKDx+F+XiGyKnkpln8bEFypNN5kwNBqeFysxT6MRvCkH+DHj0JwqFCmPVmAEdZbUS86+ag3zLkKebz1OTwIDAQAB";
		JSONObject datajson = new JSONObject(map);
		String sourceData = datajson.toString();
		byte[] key = initSecretKey();
		Key k = toKey(key); // 生成秘钥
		String iv = RandomStringUtils.randomAlphanumeric(16);//生成IV
		byte[] md5Encrypt = Coder.encryptMD5(sourceData.getBytes("utf-8"));
		byte[] encryptData = encrypt(sourceData.getBytes("utf-8"), k,iv);// 数据加密
		Map<String, Object> sourceKey = new HashMap<String, Object>();
		sourceKey.put("AesKey", parseByte2HexStr(key));
		sourceKey.put("IV", iv);
		JSONObject keyjson = new JSONObject(sourceKey);
		RSACoder rsaCoder = new RSACoder();
		byte[] bytes = keyjson.toString().trim().getBytes("utf-8");
		byte[] encryptKey = rsaCoder.encryptByPublicKey(bytes, publicKey);
		Map<String, Object> mapfinal = new HashMap<String, Object>();
		mapfinal.put("head",transByte(encryptKey));
		mapfinal.put("body",transByte(encryptData));
		return mapfinal;
	}	
	
	public static String transByte(byte[] arr) {
		String rtn = "[";
		for (int i = 0; i < arr.length; i++) {
			rtn = rtn + arr[i]+",";
		}
		rtn = rtn.substring(0, rtn.length()-1) +"]";
		return rtn;
		
	}
	public static void main(String[] args) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("1", "value");
		byte[] decodeFast = Base64.decodeFast(map.toString());
		System.out.println(decodeFast);
		
		byte[] key = initSecretKey();
		Key k = toKey(key); // 生成秘钥
		String content = "整个数据传输过程中，为了确保数据的安全通讯，需要对于传输数据进行加密。加密模式采用非对称加密+对称加密，如下：";
		String iv = RandomStringUtils.randomAlphanumeric(16);
		System.out.println("key.lenght:" + key.length);
		System.out.println("key:" + parseByte2HexStr(key));
		System.out.println("iv:" + iv);
		byte[] md5Encrypt = Coder.encryptMD5(content.getBytes("utf-8"));
		String sEncrypt = parseByte2HexStr(md5Encrypt);//
		System.out.println("原文:" + content);
		System.out.println("加密前数据MD5:" + sEncrypt);
		byte[] encryptData = encrypt(content.getBytes("utf-8"), k,iv);// 数据加密
//		Client client = new Client();
//		client.SendMsg(encryptData);
		// 数据解密
		byte[] decryptData = decrypt(encryptData, k,iv);
		byte[] md5Dncrypt = Coder.encryptMD5(decryptData);
		String sDncrypt = parseByte2HexStr(md5Dncrypt);
		String ds = new String(decryptData,"utf-8");
		System.out.println("解密后数据MD5:" + sDncrypt);
		System.out.println("解密后数据:" + ds);
	}
}
