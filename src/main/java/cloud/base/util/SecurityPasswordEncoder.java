package cloud.base.util;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

public class SecurityPasswordEncoder{
	/**
	 * @param password
	 * @param salt
	 *  * 		md5加密密码  如果不需要盐值加密，则第二个参数传入null
	 * @return
	 */
	public static String encodeMd5HashAsBase64(String password,String salt){
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		md5.setEncodeHashAsBase64(false);//encodeHashAsBase64 版本  如果为true 生成24位的Base64版  默认为false
		password = md5.encodePassword(password, salt);//如果需要盐值加密，在此方法的第二个参数中传入盐值
		return password;
	}
	
	/**
	 * @param password
	 * @param salt
	 *    256的哈希算法(SHA)加密 ,
	 * @return
	 */
	public static String encodeSha_256(String password,String salt){
		ShaPasswordEncoder en = new ShaPasswordEncoder();
		en.setEncodeHashAsBase64(false);//默认为false，如果为true，则表示使用SHA-256的哈希算法(SHA)加密
		password = en.encodePassword(password, salt);//如果需要盐值加密，在此方法的第二个参数中传入盐值
		return password;
	}
	
	public static void main(String args[]){
		System.out.println(encodeMd5HashAsBase64("123",null));
	}
}
