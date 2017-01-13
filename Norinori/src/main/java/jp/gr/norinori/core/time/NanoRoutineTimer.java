package jp.gr.norinori.core.time;

/**
 * 処理時間計測(ns)
 *
 * @author nori
 */
public class NanoRoutineTimer extends RoutineTimerFrame {

	// コンストラクタ===========================================================
	/**
	 * 処理時間計測のインスタンスを生成する
	 */
	public NanoRoutineTimer() {
		this(10);
	}

	/**
	 * 処理時間計測のインスタンスを生成する
	 *
	 * @param capacity 処理対象の予想数
	 */
	public NanoRoutineTimer(int capacity) {
		super(capacity);
	}

	@Override
	protected long getTimer() {
		return System.nanoTime();
	}

}
