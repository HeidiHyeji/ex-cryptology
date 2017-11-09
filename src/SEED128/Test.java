package SEED128;

import org.apache.commons.codec.CharEncoding;
/**
 * 
 * @author 인프라닉스
 *
 */
public class Test {
	private final static String KEY = "0000000000000000";
	private final static String DATA = "0123456789abcdef";

	public Test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		try {
			SEED_KISA seed_KISA = new SEED_KISA();

			// Round keys for encryption or decryption
			int pdwRoundKey[] = new int[32];
			// User secret key
			byte pbUserKey[] = KEY.getBytes(CharEncoding.UTF_8);
			// input plaintext to be encrypted
			byte pbData[] = DATA.getBytes(CharEncoding.UTF_8);
			byte pbCipher[] = new byte[16];
			byte pbPlain[] = new byte[16];

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
			
			System.out.print("Ciphertext\t: ");
			for (int i = 0; i < 16; i++)
				System.out.print(Integer.toHexString(0xff & pbCipher[i]));
			System.out.print("\n\n");

			// Decryption
			seed_KISA.SeedDecrypt(pbCipher, pdwRoundKey, pbPlain);

			System.out.println("[ Test Decrypt mode ]");
			System.out.print("Key\t\t: ");
			System.out.println(new String(pbUserKey,CharEncoding.UTF_8));
			
			System.out.print("Ciphertext\t: ");
			for (int i = 0; i < 16; i++)
				System.out.print(Integer.toHexString(0xff & pbCipher[i]));
			System.out.print("\n");
			System.out.print("Plaintext\t: ");
			System.out.println(new String(pbPlain,CharEncoding.UTF_8));
		
		} catch (Exception e) {
		}
	}
}
