package jp.gr.norinori.utility;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import jp.gr.norinori.utility.Encryption;

import org.junit.Test;

public class EncryptionTest {
	@Test
	public void testGetSalteSha256() {
		String result = Encryption.getSalteSha256("1", "test");
		System.out.println(result);
        assertEquals(64, result.length());
	}

	@Test
	public void testGetSha256() {
		String result = Encryption.getSha256("test");
		System.out.println(result);
        assertEquals(64, result.length());
	}

	@Test
	public void testBlowfish() {
		try {
			String encrypt = Encryption.encryptBlowfish("abc", "test");
			System.out.println(encrypt);

			String decrypt = Encryption.decryptBlowfish("abc", encrypt);
			System.out.println(decrypt);

	        assertEquals("test", decrypt);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}
