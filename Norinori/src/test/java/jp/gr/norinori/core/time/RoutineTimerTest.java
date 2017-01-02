package jp.gr.norinori.core.time;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import jp.gr.norinori.core.collection.TreeNode;
import jp.gr.norinori.utility.StringUtil;

/**
 * 処理時間計測テスト
 *
 * @author nori
 */
public class RoutineTimerTest {

	@Test
	public void testTimer() {
		RoutineTimer routineTimer = new RoutineTimer();
		try {
			for (int i = 0; i < 2000; i++) {
				routineTimer.start("test1");
				Thread.sleep(5);
				routineTimer.stop("test1");
			}
			routineTimer.start("test2");
			for (int i = 0; i < 2000; i++) {
				Thread.sleep(5);
			}
			routineTimer.stop("test2");
		} catch (InterruptedException e) {
			System.out.println(e);
		}

		System.out.println(toNumber(routineTimer, "test1"));
		System.out.println(toNumber(routineTimer, "test2"));
	}

	@Test
	public void testTimer2() {
		RoutineTimer routineTimer = new RoutineTimer();
		try {
			int timerid = -1;
			for (int i = 0; i < 2000; i++) {
				if (timerid >= 0) {
					routineTimer.start(timerid);
				}
				timerid = routineTimer.start("test");
				Thread.sleep(5);
				routineTimer.stop(timerid);
			}
			timerid = routineTimer.start("test2");
			for (int i = 0; i < 2000; i++) {
				Thread.sleep(5);
			}
			routineTimer.stop(timerid);
		} catch (InterruptedException e) {
			System.out.println(e);
		}

		System.out.println(toNumber(routineTimer, "test"));
		System.out.println(toNumber(routineTimer, "test2"));
	}

	@Test
	public void testTimer3() {
		int loop = 50000000;
		RoutineTimer routineTimer = new RoutineTimer();

		routineTimer.start("only timer");
		for (int i = 0; i < loop; i++) {
			long startTimer = System.currentTimeMillis();
			long endTimer = System.currentTimeMillis();
		}
		routineTimer.stop("only timer");

		routineTimer.start("string timerid");
		for (int i = 0; i < loop; i++) {
			routineTimer.start("test1");
			routineTimer.stop("test1");
		}
		routineTimer.stop("string timerid");

		routineTimer.start("int timerid");
		int timerid = -1;
		for (int i = 0; i < loop; i++) {
			if (timerid >= 0) {
				routineTimer.start(timerid);
			} else {
				timerid = routineTimer.start("test2");
			}
			routineTimer.stop(timerid);
		}
		routineTimer.stop("int timerid");

		routineTimer.start("int timerid2");
		timerid = 10;
		for (int i = 0; i < loop; i++) {
			routineTimer.start(timerid);
			routineTimer.stop(timerid);
		}
		routineTimer.stop("int timerid2");

		System.out.println(toNumber(routineTimer, "test1"));
		System.out.println(toNumber(routineTimer, "test2"));

		System.out.println(toNumber(routineTimer, "only timer"));
		System.out.println(toNumber(routineTimer, "string timerid"));
		System.out.println(toNumber(routineTimer, "int timerid"));
		System.out.println(toNumber(routineTimer, "int timerid2"));
	}

	@Test
	public void testGetTreeTimeids() throws Exception {
		int loopCount = 10000000;
		RoutineTimer timer = new RoutineTimer();

		timer.start("root");
		Thread.sleep(200);

		timer.start("node1", "root");
		Thread.sleep(200);
		timer.stop("node1");

		timer.start("node2", "root");
		Map<Integer, Integer> map = new HashMap<>();
		int count = 1;
		for (int i = 0; i < loopCount; i++) {
			int r = i & 1;
			timer.start("containsKey", "node2");
			if (map.containsKey(r)) {
				timer.start("get", "node2");
				count = map.get(r);
				timer.stop("get");
				count++;
			}
			timer.stop("containsKey");
			timer.start("put", "node2");
			map.put(r, count);
			timer.stop("put");

			timer.start("get_all", "node2");
			count = map.get(r);
			timer.stop("get_all");
		}
		timer.stop("node2");

		timer.start("node3", "root");
		for (int i = 0; i < loopCount; i++) {
			timer.start("loop3", "node3");
			timer.stop("loop3");
			// r = map.get(r);

			// if (map.containsKey(r)) {
			// r++;
			// }
		}
		timer.stop("node3");

		// Object o = null;
		// NumberingMap<String, Integer> numberMap = new NumberingHashMap<>();
		// timer.start("node4", "root");
		// for (int i = 0; i < loopCount; i++) {
		// o = numberMap.get("");
		//
		// // if (map.containsKey(r)) {
		// // r++;
		// // }
		// }
		// timer.stop("node4");
		// System.out.println(o);

		timer.start("node5", "root");
		for (int i = 0; i < loopCount; i++) {
		}
		timer.stop("node5");

		timer.stop("root");

		TreeNode<String> result;
		result = timer.getTreeTimeids();

		for (TreeNode<String> root : result.children()) {
			logTimer(timer, root, 0);
		}

	}

	@Test
	public void testTimer20() {
		RoutineTimer routineTimer = new RoutineTimer();
		try {
			for (int i = 0; i < 20; i++) {
				routineTimer.start("test" + i);
				routineTimer.stop("test" + i);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}


	private static void logTimer(RoutineTimer timer, TreeNode<String> node, int depth) {
		String pad = "";
		if (depth > 0) {
			pad = String.format("%" + (depth * 2) + "s", "");
		}

		System.out.println(pad + toNumber(timer, node.getValue()));

		for (TreeNode<String> child : node.children()) {
			logTimer(timer, child, depth + 1);
		}
	}

	private static String toNumber(RoutineTimer routineTimer, String timeid) {
		return timeid + " : " + StringUtil.formatNumber(routineTimer.getTotal(timeid), "#,###") + "ms ("
				+ StringUtil.formatNumber(routineTimer.getCount(timeid), "#,###") + ")";
	}
}
