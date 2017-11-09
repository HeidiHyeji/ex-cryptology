package SEED128;

import java.math.BigInteger;

import org.apache.commons.codec.CharEncoding;

public class Cryptology {
	private final static String KEY = "0000000000000000";
//	//private static String DATA = "0123456789abcdef";
//
//	public void getData(String str){
//		this.DATA=str;
//	}
//	public Cryptology() {
//		// TODO Auto-generated constructor stub
//	}
	/*일반 텍스트를 암호화하는 메소드*/
	@SuppressWarnings("static-access")
	public String encryption(String plaintext){
		
		SEED_KISA seed_KISA = new SEED_KISA();
		String ciphertext="";//new String();
		
		try {

			// Round keys for encryption
			int pdwRoundKey[] = new int[32];
			// User secret key
			byte pbUserKey[] = KEY.getBytes(CharEncoding.UTF_8);
			// input plaintext to be encrypted
			byte pbData[] = plaintext.getBytes(CharEncoding.UTF_8);
			byte pbCipher[] = new byte[16];
		//	byte pbPlain[] = new byte[16];

			System.out.println("[ Test SEED reference code ]");
			System.out.print("\n\n");

			// Derive roundkeys from user secret key
			seed_KISA.SeedRoundKey(pdwRoundKey, pbUserKey);

			// Encryption
			seed_KISA.SeedEncrypt(pbData, pdwRoundKey, pbCipher);

		
			System.out.println("[ Test Encrypt mode ]");
			System.out.print("Key\t\t: ");
			System.out.println(new String(pbUserKey,CharEncoding.UTF_8));
			
			
			System.out.print("Plaintext\t: ");
			System.out.println(new String(pbData,CharEncoding.UTF_8));
			for(int i=0;i<pbData.length;i++){
				System.out.println(pbData[i]);
			}
			System.out.print("Ciphertext\t: ");
			for (int i = 0; i < 16; i++){
				ciphertext+=(Integer.toHexString(0xff & pbCipher[i]));
				System.out.print(Integer.toHexString(0xff & pbCipher[i]));
			}
			System.out.print("\n\n");
		
		} catch (Exception e) {
		}
		
		return ciphertext;
	}
	/*암호화된 텍스트를 일반텍스트로 복호화하는 메소드*/
	@SuppressWarnings("static-access")
	public   String decryption(String ciphertext){
		SEED_KISA seed_KISA = new SEED_KISA();
		String plaintext="";
		
		try {

			// Round keys for encryption
			int pdwRoundKey[] = new int[32];
			// User secret key
			byte pbUserKey[] = KEY.getBytes(CharEncoding.UTF_8);
			// input plaintext to be encrypted
		//	byte pbData[] = new byte[16];
			byte pbCipher[] =new BigInteger(ciphertext,16).toByteArray();//파라미터로 받은 암호를 바이트로 변경
			byte pbPlain[] = new byte[16];

			System.out.println("[ Test SEED reference code ]");
			System.out.print("\n\n");

			// Derive roundkeys from user secret key
			seed_KISA.SeedRoundKey(pdwRoundKey, pbUserKey);

			// Decryption
			seed_KISA.SeedDecrypt(pbCipher, pdwRoundKey, pbPlain);

						System.out.println("[ Test Decrypt mode ]");
						System.out.print("Key\t\t: ");
						System.out.println(new String(pbUserKey,CharEncoding.UTF_8));
						
						System.out.print("Ciphertext\t: ");
						for (int i = 0; i < 16; i++){
							//ciphertext+=Integer.toHexString(0xff & pbCipher[i]);
							System.out.print(Integer.toHexString(0xff & pbCipher[i]));
						}
						System.out.print("\n");
						System.out.print("Plaintext\t: ");
						for(int i=0;i<pbPlain.length;i++){
							System.out.println(pbPlain[i]);
						}
						//System.out.println(new String(pbPlain,CharEncoding.UTF_8));
						plaintext=new String(pbPlain,CharEncoding.UTF_8);
						System.out.println(plaintext);
		
		} catch (Exception e) {
		}
		
		return plaintext;
	}
	public static void main(String[] args){
		Cryptology c=new Cryptology();
		String e=c.encryption("1234506789abcdef");
	
		String st=c.decryption(e);
		System.out.println(st);
	}
}
