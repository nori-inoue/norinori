package jp.gr.norinori.application;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import jp.gr.norinori.test.NorinoriTestFrame;

/**
 * 引数テスト
 *
 * @author nori
 */
public class SimpleArgumentTest extends NorinoriTestFrame {

	// メソッド=================================================================
	@Test
	public void testOptionValue() {
		Map<String, String[]> argumentsMap = new HashMap<>();
		argumentsMap.put("-hoge", new String[] { "ABC", "DEF" });
		argumentsMap.put("-foo", new String[] { "XYZ" });

		Arguments arguments = new SimpleArgument(argumentsMap);

		assertEquals("ABC", arguments.getOptionValue("-hoge"));
		assertEquals("-hoge", arguments.getOptionValue(0));
		assertEquals("ABC", arguments.getOptionValue(1));
	}

	@Test
	public void testOptionValues() {
		Map<String, String[]> argumentsMap = new HashMap<>();
		argumentsMap.put("-hoge", new String[] { "ABC", "DEF" });
		argumentsMap.put("-foo", new String[] { "XYZ" });

		Arguments arguments = new SimpleArgument(argumentsMap);

		assertArrayEquals((new String[] { "ABC", "DEF" }), arguments.getOptionValues("-hoge"));
	}

	@Test
	public void testHasOption() {
		Map<String, String[]> argumentsMap = new HashMap<>();
		argumentsMap.put("-hoge", new String[] { "ABC", "DEF" });
		argumentsMap.put("-foo", new String[] { "XYZ" });

		Arguments arguments = new SimpleArgument(argumentsMap);

		assertTrue(arguments.hasOption("-hoge"));
	}
}
