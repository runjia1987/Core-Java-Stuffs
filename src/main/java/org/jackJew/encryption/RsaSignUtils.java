package org.jackJew.encryption;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

import org.jackJew.algorithm.Base64Algorithm;
import org.springframework.util.Base64Utils;

/**
 * RSA signature utils
 * @author Jack
 *
 */
public class RsaSignUtils {
	
	private PublicKey pubKey;
	private PrivateKey privateKey;
	
	private final static String EncodingCharset = "UTF-8";
	
	private final static String algorithm = "RSA";
	
	private final static String signAlgorithm = "MD5WithRSA";
	
	private final static int secretKeyLength = 1024;
	
	/**
	 * create pub & private from KeyPairGenerator
	 */
	public RsaSignUtils() throws NoSuchAlgorithmException{
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
	 * create sign for input bytes, using private key
	 * @param data
	 * @return
	 */
	public byte[] getSign(byte[] data) throws Exception{
		
		Signature sig = Signature.getInstance(signAlgorithm);
		sig.initSign(privateKey);
		sig.update(data);
		byte[] bytes = sig.sign();
		
		// create the sign, base64 encoded
		return bytes;
	}
	
	/**
	 * verify if the previously generated sign is matched to the holding public key
	 * @param bytes
	 * @param data
	 * @return
	 */
	public boolean verify(byte[] bytes, byte[] data) throws Exception {
		
		Signature sig = Signature.getInstance(signAlgorithm);
		sig.initVerify(pubKey);
		sig.update(data);
		
		// start verify
		return sig.verify(bytes);
		
	}
	
	public static void main(String[] args) throws Exception {
		byte[] contentBytes = "these are the content, just for test".getBytes(EncodingCharset);
		RsaSignUtils utils = new RsaSignUtils();
		byte[] bytes = utils.getSign(contentBytes);
		
		String generatedSign = Base64Utils.encodeToString(bytes);
		System.out.println("base64 encoded signature: " + generatedSign);
		
		//start kto verify the signature
		boolean result = utils.verify(bytes, contentBytes);
		
		System.out.println("verification result: " + result);
	}

}
