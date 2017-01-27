package jp.gr.norinori.utility;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jp.gr.norinori.test.NorinoriTestFrame;
import jp.gr.norinori.utility.ArrayUtil;

import org.junit.Test;

/**
 * 配列ユーティリティテスト
 *
 * @author nori
 */
public class ArrayUtilTest extends NorinoriTestFrame {
	@Test
	public void testArray() {
		String[] array = ArrayUtil.array("A", "B", "C");

		assertEquals(3, array.length);
		assertEquals("A", array[0]);
		assertEquals("B", array[1]);
		assertEquals("C", array[2]);

		BigDecimal[] arrayDecimal = ArrayUtil.array(BigDecimal.ZERO, BigDecimal.ONE);

		assertEquals(2, arrayDecimal.length);
		assertEquals(BigDecimal.ZERO, arrayDecimal[0]);
		assertEquals(BigDecimal.ONE, arrayDecimal[1]);
	}

	@Test
	public void testClone() {
		String[] orgArray = ArrayUtil.array("A", "B", "C");
		String[] newArray = ArrayUtil.clone(orgArray);

		assertEquals(3, newArray.length);
		assertEquals("A", newArray[0]);
		assertEquals("B", newArray[1]);
		assertEquals("C", newArray[2]);

		BigDecimal[] orgArrayDecimal = ArrayUtil.array(BigDecimal.ZERO, BigDecimal.ONE);
		BigDecimal[] newArrayDecimal = ArrayUtil.clone(orgArrayDecimal);

		assertEquals(2, newArrayDecimal.length);
		assertEquals(BigDecimal.ZERO, newArrayDecimal[0]);
		assertEquals(BigDecimal.ONE, newArrayDecimal[1]);

		int[] orgArrayInt = { 10, 20, 30 };
		int[] newArrayInt = ArrayUtil.clone(orgArrayInt);

		assertEquals(3, newArrayInt.length);
		assertEquals(10, newArrayInt[0]);
		assertEquals(20, newArrayInt[1]);
		assertEquals(30, newArrayInt[2]);
	}

	@Test
	public void testCloneOffset() {
		String[] orgArray = ArrayUtil.array("A", "B", "C");
		String[] newArray = ArrayUtil.clone(orgArray, 1, 2);

		assertEquals(2, newArray.length);
		assertEquals("B", newArray[0]);
		assertEquals("C", newArray[1]);

		BigDecimal[] orgArrayDecimal = ArrayUtil.array(BigDecimal.ZERO, BigDecimal.ONE);
		BigDecimal[] newArrayDecimal = ArrayUtil.clone(orgArrayDecimal, 0, 1);

		assertEquals(1, newArrayDecimal.length);
		assertEquals(BigDecimal.ZERO, newArrayDecimal[0]);

		int[] orgArrayInt = { 10, 20, 30 };
		int[] newArrayInt = ArrayUtil.clone(orgArrayInt, 1, 2);

		assertEquals(2, newArrayInt.length);
		assertEquals(20, newArrayInt[0]);
		assertEquals(30, newArrayInt[1]);
	}

	@Test
	public void testResize() {
		String[] orgArray = ArrayUtil.array("A", "B", "C");
		String[] newArray = ArrayUtil.resize(orgArray, 2);

		assertEquals(2, newArray.length);
		assertEquals("A", newArray[0]);
		assertEquals("B", newArray[1]);

		BigDecimal[] orgArrayDecimal = ArrayUtil.array(BigDecimal.ZERO, BigDecimal.ONE);
		BigDecimal[] newArrayDecimal = ArrayUtil.resize(orgArrayDecimal, 3);

		assertEquals(3, newArrayDecimal.length);
		assertEquals(BigDecimal.ZERO, newArrayDecimal[0]);
		assertEquals(BigDecimal.ONE, newArrayDecimal[1]);
		assertEquals(null, newArrayDecimal[2]);

		int[] orgArrayInt = { 10, 20, 30 };
		int[] newArrayInt = ArrayUtil.resize(orgArrayInt, 2);

		assertEquals(2, newArrayInt.length);
		assertEquals(10, newArrayInt[0]);
		assertEquals(20, newArrayInt[1]);
	}

	@Test
	public void testAdd() {
		String[] orgArray = ArrayUtil.array("A", "B", "C");
		String[] newArray = ArrayUtil.add(orgArray, "D");

		assertEquals(4, newArray.length);
		assertEquals("C", newArray[2]);
		assertEquals("D", newArray[3]);

		int[] orgArrayInt = { 10, 20, 30 };
		int[] newArrayInt = ArrayUtil.add(orgArrayInt, 40);

		assertEquals(4, newArrayInt.length);
		assertEquals(30, newArrayInt[2]);
		assertEquals(40, newArrayInt[3]);
	}

	@Test
	public void testAddArray() {
		String[] orgArray = ArrayUtil.array("A", "B", "C");
		String[] addArray = ArrayUtil.array("D", "E");
		String[] newArray = ArrayUtil.add(orgArray, addArray);

		assertEquals(5, newArray.length);
		assertEquals("C", newArray[2]);
		assertEquals("D", newArray[3]);
		assertEquals("E", newArray[4]);
	}

	@Test
	public void testToArray() {
		List<String> list = new ArrayList<String>();
		list.add("A");
		list.add("B");
		list.add("C");

		String[] array = ArrayUtil.toArray(list);

		assertEquals(3, array.length);
		assertEquals("A", array[0]);
		assertEquals("B", array[1]);
		assertEquals("C", array[2]);

		List<BigDecimal> listDecimal = new ArrayList<BigDecimal>();
		listDecimal.add(BigDecimal.ZERO);
		listDecimal.add(BigDecimal.ONE);
		BigDecimal[] arrayDecimal = ArrayUtil.toArray(listDecimal);

		assertEquals(2, arrayDecimal.length);
		assertEquals(BigDecimal.ZERO, arrayDecimal[0]);
		assertEquals(BigDecimal.ONE, arrayDecimal[1]);

		List<String> zeroList = new ArrayList<String>();
		String[] zeroArray = ArrayUtil.toArray(zeroList);

		assertNull(zeroArray);
	}

	@Test
	public void testReverseArray() {
		byte[] orgArray = new byte[] { (byte) 1, (byte) 2, (byte) 4, (byte) 6 };
		byte[] expectArray = new byte[] { (byte) 6, (byte) 4, (byte) 2, (byte) 1 };

		byte[] array = ArrayUtil.reverse(orgArray);

		for (int i = 0; i < expectArray.length; i++) {
			assertEquals(expectArray[i], array[i]);
		}
	}
}
