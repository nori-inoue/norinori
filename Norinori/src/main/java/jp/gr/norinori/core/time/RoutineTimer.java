package jp.gr.norinori.core.time;

import java.util.HashMap;
import java.util.Map;

import jp.gr.norinori.core.collection.NumberingHashMap;

/**
 * 処理時間計測
 *
 * @author nori
 */
public class RoutineTimer {
	// メンバ===================================================================
	private Map<String, Long> timerMap;
	private Map<String, Long> totalTimeMap;

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
		this.timerMap = new HashMap<String, Long>(capacity);
		this.totalTimeMap = new NumberingHashMap<String, Long>(capacity);
	}

	/**
	 * 時間計測を開始する
	 *
	 * @param timeid 計測対象のタイムＩＤ
	 */
	public void start(String timeid) {
		long startTimer = System.currentTimeMillis();
		this.timerMap.put(timeid, startTimer);
	}

	/**
	 * 時間計測を停止する
	 *
	 * @param timeid 計測対象のタイムＩＤ
	 */
	public void stop(String timeid) {
		if (!this.timerMap.containsKey(timeid)) {
			return;
		}
		long startTimer = this.timerMap.get(timeid);
		long endTimer = System.currentTimeMillis();

		long totalTime = 0;
		if (this.totalTimeMap.containsKey(timeid)) {
			totalTime = this.totalTimeMap.get(timeid);
		}
		this.totalTimeMap.put(timeid, totalTime + endTimer - startTimer);
	}

	/**
	 * 時間計測結果を取得する
	 *
	 * @param timeid 計測対象のタイムＩＤ
	 * @return 時間計測結果(ms)
	 */
	public long getTotal(String timeid) {
		if (this.totalTimeMap.containsKey(timeid)) {
			return this.totalTimeMap.get(timeid);
		}
		return -1;
	}

	/**
	 * 計測対象のタイムＩＤの配列を取得する
	 *
	 * @return 計測対象のタイムＩＤの配列
	 */
	public String[] getTimeids() {
		String[] s = {};
		return (String[]) this.totalTimeMap.keySet().toArray(s);
	}
}
