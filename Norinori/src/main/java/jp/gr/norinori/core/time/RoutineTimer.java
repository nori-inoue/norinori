package jp.gr.norinori.core.time;

/**
 * 処理時間計測(ms)
 *
 * @author nori
 */
public class RoutineTimer extends RoutineTimerFrame {

	// コンストラクタ===========================================================
	/**
	 * 処理時間計測のインスタンスを生成する
	 */
	public RoutineTimer() {
		this(10);
	}

	/**
	 * 処理時間計測のインスタンスを生成する
	 *
	 * @param capacity 処理対象の予想数
	 */
	public RoutineTimer(int capacity) {
		super(capacity);
	}

	@Override
	protected long getTimer() {
		return System.currentTimeMillis();
	}

}
