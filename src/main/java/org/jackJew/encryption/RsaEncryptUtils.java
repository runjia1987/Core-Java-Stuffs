package org.jackJew.encryption;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.jackJew.algorithm.Base64Algorithm;

/**
 * RSA encrypt utils
 * @author zhurunjia
 *
 */
public class RsaEncryptUtils {
	
	private PublicKey pubKey;
	private PrivateKey privateKey;
	
	private final static String EncodingCharset = "UTF-8";
	
	private final static String algorithm = "RSA";
	
	private final static String transformationAlgorithm = "RSA/ECB/OAEPWithSHA-256AndMGF1Padding";
	
	private final static int secretKeyLength = 2048;
	
	/**
	 * create pub & private from KeyPairGenerator
	 */
	public RsaEncryptUtils() throws NoSuchAlgorithmException{
		KeyPairGenerator kpGenerator = KeyPairGenerator.getInstance(algorithm);
		kpGenerator.initialize(secretKeyLength);
		
		KeyPair keypair = kpGenerator.generateKeyPair();
		pubKey = keypair.getPublic();
		privateKey = keypair.getPrivate();
		
		String publicKeyStr = new String(Base64Algorithm.encode(pubKey.getEncoded()));
		String privateKeyStr = new String(Base64Algorithm.encode(privateKey.getEncoded()));
		
		System.out.println("created keyPair:\npublic: " + publicKeyStr
										+ "\nprivate: " + privateKeyStr);
	}
	
	/**
	 * encrypt, using public key
	 */
	public String encrypt(byte[] data) {
		try {
			Cipher cipher = Cipher.getInstance(transformationAlgorithm);
			cipher.init(Cipher.ENCRYPT_MODE, pubKey);
			
			byte[] resultBytes = cipher.doFinal(data);
			return new String(Base64Algorithm.encode(resultBytes));
			
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	/**
	 * decrypt, using private key
	 */
	public String decrypt(String content) {
		try {
			Cipher cipher = Cipher.getInstance(transformationAlgorithm);
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			
			byte[] base64DecodedBytes = Base64Algorithm.decode(content.toCharArray());
			byte[] resultBytes = cipher.doFinal(base64DecodedBytes);
			
			return new String(resultBytes, EncodingCharset);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException e) {
			e.printStackTrace();			
			return null;
		}		
	}
	
	public static void main(String[] args) throws Exception {
		RsaEncryptUtils rsaEncryptUtils = new RsaEncryptUtils();
		
		String message = "我是Jack";
		
		String encryptedBase64Msg = rsaEncryptUtils.encrypt(message.getBytes(EncodingCharset));
		System.out.println("encryptedBase64Msg:" + encryptedBase64Msg);
		
		String decryptedMessage = rsaEncryptUtils.decrypt(encryptedBase64Msg);
		System.out.println(decryptedMessage);
	}

}
