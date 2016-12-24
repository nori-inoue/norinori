package jp.gr.norinori.utility;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * 暗号化ユーティリティ
 *
 * @author inoue
 */
public class Encryption {
	/**
	 * Salt付きSHA256ハッシュ値を取得する。
	 *
	 * @param saltKey
	 * @param target 対象文字列
	 * @return Salt付きSHA256ハッシュ値
	 */
	public static String getSalteSha256(String saltKey, String target) {
		String salt = getSha256(saltKey);
		return getSha256(salt + target);
	}

	/**
	 * 文字列から SHA256 のハッシュ値を取得する。
	 *
	 * @param target 対象文字列
	 * @return SHA256ハッシュ値
	 */
	public static String getSha256(String target) {
		MessageDigest md = null;
		StringBuffer buf = new StringBuffer();
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(target.getBytes());
			byte[] digest = md.digest();

			for (int i = 0; i < digest.length; i++) {
				buf.append(String.format("%02x", digest[i]));
			}

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return buf.toString();
	}

	/**
	 * 文字列からBlowfishで暗号化した値を取得する。
	 *
	 * @param key 暗号化キー
	 * @param target 対象文字列
	 * @return 暗号化した値
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws IOException
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @see http://d.hatena.ne.jp/kenpoco/20080717/1216291915
	 */
	public static String encryptBlowfish(String key, String target) throws IllegalBlockSizeException,
			BadPaddingException, IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
		SecretKeySpec sksSpec = new SecretKeySpec(key.getBytes(), "Blowfish");

		Cipher cipher = Cipher.getInstance("Blowfish");
		cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, sksSpec);

		return encodeBase64(cipher.doFinal(target.getBytes()));
	}

	/**
	 * Blowfishで暗号化した文字列を復号化する。
	 *
	 * @param key 暗号化キー
	 * @param encryptedTarget 暗号化した文字列(BASE64)
	 * @return 復号化した文字列
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws IOException
	 */
	public static String decryptBlowfish(String key, String encryptedTarget) throws NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException {
		SecretKeySpec sksSpec = new SecretKeySpec(key.getBytes(), "Blowfish");

		Cipher cipher = Cipher.getInstance("Blowfish");
		cipher.init(Cipher.DECRYPT_MODE, sksSpec);

		return new String(cipher.doFinal(decodeBase64(encryptedTarget)));
	}

	/**
	 * BASE64で文字列化する。
	 *
	 * @param data
	 * @return BASE64の文字列
	 * @throws IOException
	 * @see http://d.hatena.ne.jp/kenpoco/20080717/1216291915
	 */
	private static String encodeBase64(byte[] data) throws IOException {

		return new String(Base64.encodeBase64(data), "iso-8859-1");
	}

	/**
	 * BASE64で文字列化した値を元の値に戻す。
	 *
	 * @param base64 BASE64の文字列
	 * @return 元の値
	 * @throws IOException
	 * @see http://d.hatena.ne.jp/kenpoco/20080717/1216291915
	 */
	private static byte[] decodeBase64(String base64) throws IOException {
		return Base64.decodeBase64(base64.getBytes());
	}
}
