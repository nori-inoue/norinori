package jp.gr.norinori.core.element;

import jp.gr.norinori.core.element.Pair;

/**
 * 開始・終了ペア
 *
 * @author nori
 * @param T 開始・終了のタイプ
 */
public class StartEndPair<T> implements Pair<T, T> {

    // メンバ===================================================================
    private T start;
    private T end;

    // コンストラクタ===========================================================
    /**
     * 開始・終了ペアのインスタンスを生成する
     *
     * @param start 開始
     * @param end 終了
     */
    public StartEndPair(T start, T end) {
        this.start = start;
        this.end = end;
    }

    // メソッド=================================================================
    /**
     * 開始の値を取得する
     *
     * @return 開始の値
     */
    public T getFirst() {
        return this.start;
    }

    /**
     * 開始の値を取得する
     *
     * @return 開始の値
     */
    public T getStart() {
        return this.start;
    }

    /**
     * 終了の値を取得する
     *
     * @return 終了の値
     */
    public T getSecond() {
        return this.end;
    }

    /**
     * 終了の値を取得する
     *
     * @return 終了の値
     */
    public T getEnd() {
        return this.end;
    }
}
