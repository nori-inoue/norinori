package jp.gr.norinori.application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import jp.gr.norinori.application.SimpleResourceFile;
import jp.gr.norinori.core.file.ResourceFile;
import jp.gr.norinori.define.Encoding;
import jp.gr.norinori.test.NorinoriTestFrame;

import org.junit.Test;

/**
 * コマンドライン引数テスト
 *
 * @author nori
 */
public class SimpleResourceFileTest extends NorinoriTestFrame {

	// メソッド=================================================================
	@Test
	public void testExists() {
		ResourceFile resourceFile = new SimpleResourceFile(this.getClass(), "resource.txt");

		assertTrue(resourceFile.exists());
	}

	@Test
	public void testCreateInputStream() {
		ResourceFile resourceFile = new SimpleResourceFile(this.getClass(), "resource.txt");

		List<String> result = new ArrayList<String>();
		try {
			InputStream is = resourceFile.createInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is, Encoding.UTF8.getCode()));
			String str;
			while ((str = br.readLine()) != null) {
				result.add(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		assertEquals("テスト", result.get(0));
		assertEquals("２行目", result.get(1));
	}

}
