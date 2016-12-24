package jp.gr.norinori.utility;

/**
 * 初回スキップ
 *
 * @author nori
 */
public class FirstSkip {

    // メンバ===================================================================
    private String first;
    private String noFirst;

    private boolean isFirst;

    // コンストラクタ===========================================================
    /**
     * 初回スキップのインスタンスを生成する
     *
     * @param noFirst 初回以降の文字列
     */
    public FirstSkip(String noFirst) {
        this("", noFirst);
    }

    /**
     * 初回スキップのインスタンスを生成する
     *
     * @param first 初回文字列
     * @param noFirst 初回以降の文字列
     */
    public FirstSkip(String first, String noFirst) {
        this.first = first;
        this.noFirst = noFirst;
        this.isFirst = true;
    }

    // メソッド=================================================================
    /**
     * 文字列を取得する<br/>
     * 初回アクセス時は「初回文字列」、それ以降は「初回以降の文字列」
     *
     * @return 文字列
     */
    public String toString() {
        if (this.isFirst) {
            this.isFirst = false;
            return this.first;
        }
        return this.noFirst;
    }
}
