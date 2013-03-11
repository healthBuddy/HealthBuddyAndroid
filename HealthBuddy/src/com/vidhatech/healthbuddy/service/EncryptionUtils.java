package com.vidhatech.healthbuddy.service;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Utility class that helps encrypt and decrypt requests.
 *
 */
public class EncryptionUtils {

	private final static String algorithm = "RSA";
	
	public final static String encrypt(String clearText, String secretKey) throws Exception
	{
		Key key = generateKey(secretKey);
		
		Cipher c = Cipher.getInstance(algorithm);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(clearText.getBytes());
        
        return new String(encVal);		
	}
	
	public final static String decrypt(String encryptedText, String secretKey) throws Exception
	{
		Key key = generateKey(secretKey);
        Cipher c = Cipher.getInstance(algorithm);
        c.init(Cipher.DECRYPT_MODE, key);
        byte [] decValue = c.doFinal(encryptedText.getBytes());
        /*byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
        byte[] decValue = c.doFinal(decordedValue);*/
                
        String decryptedValue = new String(decValue);
        return decryptedValue;
	}
	
	private final static Key generateKey(String secretKey)
	{
		Key key = new SecretKeySpec(secretKey.getBytes(), algorithm);
        return key;
	}
	
}
