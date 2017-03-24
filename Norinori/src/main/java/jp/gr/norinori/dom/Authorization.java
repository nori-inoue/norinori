package jp.gr.norinori.dom;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * 承認情報
 *
 * @author inoue
 */
public class Authorization {
	public String userAgent = "";
	public Map<String, String> cookies = new HashMap<>();
	public URL url;
}
