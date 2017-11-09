package AES256;


import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.CharEncoding;
import org.apache.commons.codec.binary.Base64;
/**
 * 
 * @author http://aircook.tistory.com/entry/AES-SHA-%EC%95%94%ED%98%B8%ED%99%94-1-JAVA
 *
 */
public class Crypto {
	private final static String KEY = "01234567890123456789012345678901";
	private final static String KEY_128 = KEY.substring(0, 128 / 8);
	private final static String KEY_256 = KEY.substring(0, 256 / 8);

	public Crypto() {
		// TODO Auto-generated constructor stub
	}

	
	public static String encryptAES256(String string) {/*암호화*/
		try {
			byte[] key256Data = KEY_256.getBytes(CharEncoding.UTF_8);
			byte[] key128Data = KEY_128.getBytes(CharEncoding.UTF_8);

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

			cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key256Data, "AES"), new IvParameterSpec(key128Data));

			byte[] encrypted = cipher.doFinal(string.getBytes(CharEncoding.UTF_8));// AES
																					// 암호화
			byte[] base64Encoded = Base64.encodeBase64(encrypted);
			
			String result = new String(base64Encoded, CharEncoding.UTF_8);// 결과
			return result;
		} catch (Exception e) {
			return null;
		}
	}
	public static String decryptAES236(String string){/*복호화*/
		try{
			byte[] key256Data = KEY_256.getBytes(CharEncoding.UTF_8);
			byte[] key128Data = KEY_128.getBytes(CharEncoding.UTF_8);
			
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			
			cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key256Data,"AES"),new IvParameterSpec(key128Data));
			
			byte[] base64Decoded = Base64.decodeBase64(string.getBytes(CharEncoding.UTF_8)); //base64 디코딩
			
			byte[]decrypted=cipher.doFinal(base64Decoded); //AES복호화
			String result=new String(decrypted,CharEncoding.UTF_8); //결과
			
			return result;
		}catch(Exception e){
			return null;
		}
	}



	public static void main(String[] args) {
		String str1 = "김혜지abc";
		String str4 = encryptAES256(str1);
		System.out.println(str1);
		System.out.println("암호화된 문자:"+str4);
		
		String str5 = decryptAES236(str4);
		System.out.println(str1);
		System.out.println("암호화 풀린 문자:"+str5);

	}
}
