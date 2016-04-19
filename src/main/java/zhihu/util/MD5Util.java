package zhihu.util;


import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by ZJ on 2016/4/13.
 */
public class MD5Util implements PasswordEncoder {

	@Override
	public String encode(CharSequence charSequence) {
		String result = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(charSequence.toString().getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			result = buf.toString();
		} catch (NoSuchAlgorithmException e) {
			System.out.println(e);
		}
		return result;
	}

	@Override
	public boolean matches(CharSequence charSequence, String s) {
		return encode(charSequence).equals(s);
	}
}
