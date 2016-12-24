package jp.gr.norinori.utility;

import static org.junit.Assert.assertEquals;
import jp.gr.norinori.test.NorinoriTestFrame;
import jp.gr.norinori.utility.FirstSkip;

import org.junit.Test;

/**
 * @author nori
 */
public class FirstSkipTest extends NorinoriTestFrame {

    /**
     * {@link jp.gr.norinori.utility.FirstSkip#toString()} のためのテスト・メソッド。
     */
    @Test
    public void testToString() {
        FirstSkip firstSkip = new FirstSkip("OK");
        assertEquals("", firstSkip.toString());
        assertEquals("OK", firstSkip.toString());
        assertEquals("OK", firstSkip.toString());

        FirstSkip firstSkip2 = new FirstSkip("FIRST", "SECOND");
        assertEquals("FIRST", firstSkip2.toString());
        assertEquals("SECOND", firstSkip2.toString());
        assertEquals("SECOND", firstSkip2.toString());
    }

}
