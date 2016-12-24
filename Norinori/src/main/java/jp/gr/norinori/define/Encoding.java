package jp.gr.norinori.define;

/**
 * エンコーディング
 *
 * @author nori
 */
public enum Encoding {

    /** Unicode (UTF-8) */
    UTF8("UTF-8"),
    /** 日本語 (EUC) */
    EUC_JP("EUC-JP"),
    /** 日本語 (シフト JIS) */
    SHIFT_JIS("Shift_JIS");

    // メンバ===================================================================
    private String encoding;

    // コンストラクタ===========================================================
    private Encoding(String encoding) {
        this.encoding = encoding;
    }

    // メソッド=================================================================
    /**
     * Javaエンコーディング文字を返す
     *
     * @return Javaエンコーディング文字
     */
    public String getCode() {
        return this.encoding;
    }

    /**
     * Javaエンコーディング文字を返す
     *
     * @return Javaエンコーディング文字
     */
    public String toString() {
        return this.encoding;
    }
}
