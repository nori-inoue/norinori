package jp.gr.norinori.application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertArrayEquals;
import jp.gr.norinori.application.Arguments;
import jp.gr.norinori.application.CommandLineArgument;
import jp.gr.norinori.test.NorinoriTestFrame;

import org.junit.Test;

/**
 * コマンドライン引数テスト
 *
 * @author nori
 */
public class CommandLineArgumentTest extends NorinoriTestFrame {

    // メソッド=================================================================
	@Test
	public void testOptionValue() {
		String arg = "-hoge ABC DEF -foo XYZ";
		Arguments arguments = new CommandLineArgument(arg.split(" "));

        assertEquals("ABC", arguments.getOptionValue("-hoge"));
        assertEquals("-hoge", arguments.getOptionValue(0));
        assertEquals("ABC", arguments.getOptionValue(1));
	}

	@Test
	public void testOptionValues() {
		String arg = "-hoge ABC DEF -foo XYZ";
		Arguments arguments = new CommandLineArgument(arg.split(" "));

		assertArrayEquals((new String[]{"ABC","DEF"}), arguments.getOptionValues("-hoge"));
	}

	@Test
	public void testHasOption() {
		String arg = "-hoge ABC DEF -foo XYZ";
		Arguments arguments = new CommandLineArgument(arg.split(" "));

        assertTrue(arguments.hasOption("-hoge"));
	}
}
