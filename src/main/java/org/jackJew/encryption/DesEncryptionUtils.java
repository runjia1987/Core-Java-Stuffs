package org.jackJew.encryption;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.jackJew.algorithm.Base64Algorithm;

/**
 * DES encryption utils
 * @author Jack
 *
 */
public class DesEncryptionUtils {

	/**
	 * DES key size must be >= 8, <br/>
	 * actually only the first 8-bytes is used, tailing bytes are ignored
	 */
	/**
	 * DESede key size must be >= 24, <br/>
	 * actually only the first 24-bytes is used, tailing bytes are ignored
	 */
	private final static String sourceKey = "hd7js791s3kowmsl03malvqf";
	
	private final static String Algorithm = "DESede";  // DESede
	
	private final static String EncryptMode_CBC = "CBC";
	
	private final static String EncryptMode_ECB = "ECB";
	
	private final static String PaddingMode = "PKCS5Padding";
	
	private final static String Encoding = "UTF-8";
	
	private IvParameterSpec ivSpec = new IvParameterSpec(new byte[]{ 18, 20, 31, 5, 127, 90, 65, 33 }); // initialization vector
	
	private final SecretKey secretKey;
	
	/**
	 * create secret key
	 */
	public DesEncryptionUtils() {
		try {
			DESedeKeySpec spec = new DESedeKeySpec(sourceKey.getBytes(Encoding));
			SecretKeyFactory skf = SecretKeyFactory.getInstance(Algorithm);
			secretKey = skf.generateSecret(spec);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * encrypt
	 * @param content
	 * @param encryptMode CBC(preferrable) or ECB(not preferrable regarding safety)
	 * @return encoded string(base64 transformed)
	 */
	public String encrypt(String content, String encryptMode){	
		
		try {
			byte[] contentBytes = content.getBytes(Encoding);
			
			// like DES/CBC/PKCS5Padding
			Cipher cipher = Cipher.getInstance(Algorithm + "/" + encryptMode + "/" + PaddingMode);
			
			if (EncryptMode_CBC.equals(encryptMode) ) {
				// require IvSpec if use CBC mode, 8 byte				
				cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
			} else if(EncryptMode_ECB.equals(encryptMode)) {
				// ECB
				cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			} else {
				throw new IllegalArgumentException("wrong encryptMode.");
			}
			byte[] results = cipher.doFinal(contentBytes);
			// base64 transform
			String encodedString = new String(Base64Algorithm.encode(results));
			System.out.println("encrypted result :" + encodedString);
			
			return encodedString;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * encrypt
	 * @param content
	 * @param decryptMode CBC(preferred) or ECB(less safe)
	 * @return source string
	 */
	public String decrypt(String content, String decryptMode){
		byte[] decodedBytes = Base64Algorithm.decode(content.toCharArray());
		
		try {
			Cipher cipher = Cipher.getInstance(Algorithm + "/" + decryptMode + "/" + PaddingMode);
			if (EncryptMode_CBC.equals(decryptMode)) {
				// require IvSpec if use CBC mode, 8 byte	
				cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
			} else if(EncryptMode_ECB.equals(decryptMode)) {
				// ECB
				cipher.init(Cipher.DECRYPT_MODE, secretKey);
			} else {
				throw new IllegalArgumentException("wrong decryptMode.");
			}
			byte[] resultBytes = cipher.doFinal(decodedBytes);
			String sourceString = new String(resultBytes, Encoding);
			
			System.out.println("decrypted result :" + sourceString);
			
			return sourceString;
		} catch(Exception e){
			throw new RuntimeException(e);
		}	
	}
	
	/**
	 * test case
	 * @param args
	 */
	public static void main(String[] args){
		DesEncryptionUtils deu = new DesEncryptionUtils();
		String content = "我是Jack";
		
		String encodedString = deu.encrypt(content, EncryptMode_CBC);
		deu.decrypt(encodedString, EncryptMode_CBC);
		
		encodedString = deu.encrypt(content, EncryptMode_ECB);
		deu.decrypt(encodedString, EncryptMode_ECB);
	}
	
}
