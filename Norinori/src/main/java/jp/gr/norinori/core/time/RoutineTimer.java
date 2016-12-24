package jp.gr.norinori.core.time;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import jp.gr.norinori.core.collection.NumberingHashMap;
import jp.gr.norinori.core.collection.NumberingMap;
import jp.gr.norinori.core.collection.TreeNode;
import jp.gr.norinori.utility.ArrayUtil;

/**
 * 処理時間計測
 *
 * @author nori
 */
public class RoutineTimer {
	// メンバ===================================================================
	private NumberingMap<String, Integer> timeridMap;
	private Long[] timers;
	private TimerPoint[] totalTimes;
	private int lastTimerid = 0;
	private Map<String, String> timeidTreeMap;

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
		this.timeridMap = new NumberingHashMap<String, Integer>(capacity);
		this.timeidTreeMap = new HashMap<String, String>(capacity);
		this.timers = new Long[capacity];
		this.totalTimes = new TimerPoint[capacity];
	}

	/**
	 * 時間計測を開始する
	 *
	 * @param timeid 計測対象のタイムＩＤ
	 * @param parentTimeid 計測対象の親タイムＩＤ
	 * @return int timerid
	 */
	public int start(String timeid, String parentTimeid) {
		int timerid = this.lastTimerid;
		Long startTimer = System.currentTimeMillis();
		if (this.timeridMap.containsKey(timeid)) {
			timerid = this.timeridMap.get(timeid);
		} else {
			this.timeridMap.put(timeid, timerid);
			this.totalTimes[timerid] = new TimerPoint();
			this.lastTimerid++;
			this.timeidTreeMap.put(timeid, parentTimeid);
		}
		this.timers[timerid] = startTimer;

		return timerid;
	}

	/**
	 * 時間計測を開始する
	 *
	 * @param timeid 計測対象のタイムＩＤ
	 * @return int timerid
	 */
	public int start(String timeid) {
		return start(timeid, null);
	}

	/**
	 * 時間計測を開始する
	 *
	 * @param timerid 計測対象のタイマＩＤ
	 * @return int timerid
	 */
	public int start(int timerid) {
		long startTimer = System.currentTimeMillis();
		try {
			this.timers[timerid] = startTimer;
		} catch (Exception e) {
			this.timers = ArrayUtil.resize(this.timers, timerid + 1);
			this.totalTimes = ArrayUtil.resize(this.totalTimes, timerid + 1);
			this.timers[timerid] = startTimer;
			this.totalTimes[timerid] = new TimerPoint();
			this.lastTimerid = timerid + 1;
		}

		return timerid;
	}

	/**
	 * 時間計測を停止する
	 *
	 * @param timeid 計測対象のタイムＩＤ
	 */
	public void stop(String timeid) {
		if (!this.timeridMap.containsKey(timeid)) {
			return;
		}
		int timerid = this.timeridMap.get(timeid);
		long startTimer = this.timers[timerid];
		long endTimer = System.currentTimeMillis();

		this.totalTimes[timerid].count++;
		this.totalTimes[timerid].total += endTimer - startTimer;
	}

	/**
	 * 時間計測を停止する
	 *
	 * @param timerid 計測対象のタイマＩＤ
	 */
	public void stop(int timerid) {
		long startTimer;
		try {
			startTimer = this.timers[timerid];
		} catch (Exception e) {
			return;
		}
		long endTimer = System.currentTimeMillis();

		this.totalTimes[timerid].count++;
		this.totalTimes[timerid].total += endTimer - startTimer;
	}

	/**
	 * 時間計測結果を取得する
	 *
	 * @param timeid 計測対象のタイムＩＤ
	 * @return 時間計測結果(ms)
	 */
	public long getTotal(String timeid) {
		if (!this.timeridMap.containsKey(timeid)) {
			return -1;
		}
		int timerid = this.timeridMap.get(timeid);
		return this.totalTimes[timerid].total;
	}

	/**
	 * 時間計測結果を取得する
	 *
	 * @param timerid 計測対象のタイマＩＤ
	 * @return 時間計測結果(ms)
	 */
	public long getTotal(int timerid) {
		try {
			return this.totalTimes[timerid].total;
		} catch (Exception e) {
			return -1;
		}
	}

	/**
	 * 時間計測回数を取得する
	 *
	 * @param timeid 計測対象のタイムＩＤ
	 * @return 時間計測回数
	 */
	public long getCount(String timeid) {
		if (!this.timeridMap.containsKey(timeid)) {
			return 0;
		}
		int timerid = this.timeridMap.get(timeid);
		return this.totalTimes[timerid].count;
	}

	/**
	 * 時間計測回数を取得する
	 *
	 * @param timerid 計測対象のタイマＩＤ
	 * @return 時間計測回数
	 */
	public long getCount(int timerid) {
		try {
			return this.totalTimes[timerid].count;
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 計測対象のタイムＩＤの配列を取得する
	 *
	 * @return 計測対象のタイムＩＤの配列
	 */
	public String[] getTimeids() {
		String[] s = {};
		return (String[]) this.timeridMap.keySet().toArray(s);
	}

	/**
	 * 計測対象のタイムＩＤの配列をツリーを取得する
	 *
	 * @return 計測対象のタイムＩＤの配列
	 */
	public TreeNode<String> getTreeTimeids() {
		TreeNode<String> root = new TreeNode<String>();
		Map<String, TreeNode<String>> nodeMap = new HashMap<String, TreeNode<String>>();

		for (Entry<String, String> en : this.timeidTreeMap.entrySet()) {
			TreeNode<String> node = null;
			if (nodeMap.containsKey(en.getKey())) {
				node = nodeMap.get(en.getKey());
			} else {
				node = new TreeNode<String>(en.getKey());
				nodeMap.put(en.getKey(), node);
			}

			TreeNode<String> parentNode = null;
			if (en.getValue() == null) {
				parentNode = root;
			} else {
				if (nodeMap.containsKey(en.getValue())) {
					parentNode = nodeMap.get(en.getValue());
				} else {
					parentNode = new TreeNode<String>(en.getValue());
					nodeMap.put(en.getValue(), parentNode);
				}
			}
			parentNode.addNode(node);
		}
		return root;
	}

	/**
	 * Timer Point
	 *
	 * @author nori
	 */
	private class TimerPoint {
		public long total = 0L;
		public long count = 0L;
	}
}
