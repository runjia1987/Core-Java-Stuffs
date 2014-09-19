package org.jackJew.encryption;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.jackJew.algorithm.Base64Algorithm;

public class RSA_Sign_Utils {
	
	private String publicKeyStr;
	private String privateKeyStr;
	
	private final static String EncodingCharset = "UTF-8";
	
	private final static String algorithm = "RSA";
	
	private final static String signAlgorithm = "MD5WithRSA";
	
	private final static int secretKeyLength = 1024;
	
	/**
	 * create pub & private from KeyPairGenerator
	 */
	public RSA_Sign_Utils() throws NoSuchAlgorithmException{
		KeyPairGenerator kpGenerator = KeyPairGenerator.getInstance(algorithm);
		kpGenerator.initialize(secretKeyLength);
		
		KeyPair keypair = kpGenerator.generateKeyPair();
		PublicKey pubKey = keypair.getPublic();
		PrivateKey privateKey = keypair.getPrivate();
		
		publicKeyStr = new String(Base64Algorithm.encode(pubKey.getEncoded()));
		privateKeyStr = new String(Base64Algorithm.encode(privateKey.getEncoded()));
		
		System.out.println("created keyPair: public @ " + publicKeyStr
										+ "\nprivate@" + privateKeyStr);
	}
	
	/**
	 * create sign for input bytes, use private key
	 * @param data
	 * @return
	 */
	public String getSign(byte[] data) throws Exception{
		byte[] privateKeySpecBytes = Base64Algorithm.decode(privateKeyStr.toCharArray());
		
		// use PKCS8EncodedKeySpec to generate private key for signature
		PKCS8EncodedKeySpec encodedKeySpec = new PKCS8EncodedKeySpec(privateKeySpecBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
		PrivateKey _private = keyFactory.generatePrivate(encodedKeySpec);
		
		Signature sig = Signature.getInstance(signAlgorithm);
		sig.initSign(_private);
		sig.update(data);
		byte[] bytes = sig.sign();
		
		// create the sign, base64 encoded
		return new String(Base64Algorithm.encode(bytes));
	}
	
	/**
	 * verify if the previously generated sign is matched to the holding public key
	 * @param sign
	 * @param data
	 * @return
	 */
	public boolean verify(String sign, byte[] data) throws Exception {
		byte[] publicKeySpecBytes = Base64Algorithm.decode(publicKeyStr.toCharArray());
		
		// use X509EncodedKeySpec to generate public key for decryption
		X509EncodedKeySpec encodedKeySpec = new X509EncodedKeySpec(publicKeySpecBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
		PublicKey _public = keyFactory.generatePublic(encodedKeySpec);
		
		Signature sig = Signature.getInstance(signAlgorithm);
		sig.initVerify(_public);
		sig.update(data);
		
		// start verify
		return sig.verify(Base64Algorithm.decode(sign.toCharArray()));
		
	}
	
	public static void main(String[] args) throws Exception {
		byte[] contentBytes = "these are the content,just for test".getBytes(EncodingCharset);
		RSA_Sign_Utils utils = new RSA_Sign_Utils();
		String generatedSign = utils.getSign(contentBytes);
		
		System.out.println("base64 encoded signature: " + generatedSign);
		
		//start kto verify the signature
		boolean result = utils.verify(generatedSign, contentBytes);
		
		System.out.println("verification result: " + result);
	}

}
