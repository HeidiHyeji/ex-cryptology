package AES256;

/**
 * 
 * @author http://blog.naver.com/PostView.nhn?blogId=slimcdp&logNo=220495115002
 *
 */
public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}
	public static void test() throws Exception{
		String key="aes256-test-key!!";
		AES256Util aes256 = new AES256Util(key);
		
		String text="test용 데이터";
		String encText = aes256.aesEncode(text);
		String decText = aes256.aesDecode(encText);
		
		System.out.println("암호화할 문자:" + text);
		System.out.println("암호화된 문자:" + encText);
		System.out.println("복호화된 문자:" + decText);
	}
	public static void main(String[] args) {
		try{
			test();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
