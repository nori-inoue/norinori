package jp.gr.norinori.utility;

import java.lang.reflect.Array;
import java.util.List;

/**
 * 配列ユーティリティ
 *
 * @author nori
 */
public final class ArrayUtil {
	// スタティックメソッド=====================================================
	/**
	 * 配列形式に変換する
	 *
	 * @param <T> 要素の型
	 * @param array 可変長引数
	 * @return 配列
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] array(T... array) {
		return array;
	}

	/**
	 * 配列を複製する
	 *
	 * @param array 配列
	 * @return 複製した配列
	 */
	public static boolean[] clone(boolean[] array) {
		return (boolean[]) cloneObject(array, 0, array.length);
	}

	/**
	 * 指定した配列の位置から指定した要素数分の配列を作成する
	 *
	 * @param array 配列
	 * @param offset 複製を開始する配列要素番号
	 * @param length 複製する要素数
	 * @return 複製した配列
	 */
	public static boolean[] clone(boolean[] array, int offset, int length) {
		return (boolean[]) cloneObject(array, offset, length);
	}

	/**
	 * 配列を複製する
	 *
	 * @param array 配列
	 * @return 複製した配列
	 */
	public static byte[] clone(byte[] array) {
		return (byte[]) cloneObject(array, 0, array.length);
	}

	/**
	 * 指定した配列の位置から指定した要素数分の配列を作成する
	 *
	 * @param array 配列
	 * @param offset 複製を開始する配列要素番号
	 * @param length 複製する要素数
	 * @return 複製した配列
	 */
	public static byte[] clone(byte[] array, int offset, int length) {
		return (byte[]) cloneObject(array, offset, length);
	}

	/**
	 * 配列を複製する
	 *
	 * @param array 配列
	 * @return 複製した配列
	 */
	public static char[] clone(char[] array) {
		return (char[]) cloneObject(array, 0, array.length);
	}

	/**
	 * 指定した配列の位置から指定した要素数分の配列を作成する
	 *
	 * @param array 配列
	 * @param offset 複製を開始する配列要素番号
	 * @param length 複製する要素数
	 * @return 複製した配列
	 */
	public static char[] clone(char[] array, int offset, int length) {
		return (char[]) cloneObject(array, offset, length);
	}

	/**
	 * 配列を複製する
	 *
	 * @param array 配列
	 * @return 複製した配列
	 */
	public static double[] clone(double[] array) {
		return (double[]) cloneObject(array, 0, array.length);
	}

	/**
	 * 指定した配列の位置から指定した要素数分の配列を作成する
	 *
	 * @param array 配列
	 * @param offset 複製を開始する配列要素番号
	 * @param length 複製する要素数
	 * @return 複製した配列
	 */
	public static double[] clone(double[] array, int offset, int length) {
		return (double[]) cloneObject(array, offset, length);
	}

	/**
	 * 配列を複製する
	 *
	 * @param array 配列
	 * @return 複製した配列
	 */
	public static float[] clone(float[] array) {
		return (float[]) cloneObject(array, 0, array.length);
	}

	/**
	 * 指定した配列の位置から指定した要素数分の配列を作成する
	 *
	 * @param array 配列
	 * @param offset 複製を開始する配列要素番号
	 * @param length 複製する要素数
	 * @return 複製した配列
	 */
	public static float[] clone(float[] array, int offset, int length) {
		return (float[]) cloneObject(array, offset, length);
	}

	/**
	 * 配列を複製する
	 *
	 * @param array 配列
	 * @return 複製した配列
	 */
	public static int[] clone(int[] array) {
		return (int[]) cloneObject(array, 0, array.length);
	}

	/**
	 * 指定した配列の位置から指定した要素数分の配列を作成する
	 *
	 * @param array 配列
	 * @param offset 複製を開始する配列要素番号
	 * @param length 複製する要素数
	 * @return 複製した配列
	 */
	public static int[] clone(int[] array, int offset, int length) {
		return (int[]) cloneObject(array, offset, length);
	}

	/**
	 * 配列を複製する
	 *
	 * @param array 配列
	 * @return 複製した配列
	 */
	public static long[] clone(long[] array) {
		return (long[]) cloneObject(array, 0, array.length);
	}

	/**
	 * 指定した配列の位置から指定した要素数分の配列を作成する
	 *
	 * @param array 配列
	 * @param offset 複製を開始する配列要素番号
	 * @param length 複製する要素数
	 * @return 複製した配列
	 */
	public static long[] clone(long[] array, int offset, int length) {
		return (long[]) cloneObject(array, offset, length);
	}

	/**
	 * 配列を複製する
	 *
	 * @param array 配列
	 * @return 複製した配列
	 */
	public static short[] clone(short[] array) {
		return (short[]) cloneObject(array, 0, array.length);
	}

	/**
	 * 指定した配列の位置から指定した要素数分の配列を作成する
	 *
	 * @param array 配列
	 * @param offset 複製を開始する配列要素番号
	 * @param length 複製する要素数
	 * @return 複製した配列
	 */
	public static short[] clone(short[] array, int offset, int length) {
		return (short[]) cloneObject(array, offset, length);
	}

	/**
	 * 配列を複製する
	 *
	 * @param <T> 要素の型
	 * @param array 配列
	 * @return 複製した配列
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] clone(T[] array) {
		return (T[]) cloneObject(array, 0, array.length);
	}

	/**
	 * 指定した配列の位置から指定した要素数分の配列を作成する
	 *
	 * @param <T> 要素の型
	 * @param array 配列
	 * @param offset 複製を開始する配列要素番号
	 * @param length 複製する要素数
	 * @return 複製した配列
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] clone(T[] array, int offset, int length) {
		return (T[]) cloneObject(array, offset, length);
	}

	/**
	 * 配列に追加する
	 *
	 * @param <T> 要素の型
	 * @param array 配列
	 * @param obj 新しい要素
	 * @return 新しい要素を追加した配列
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] add(T[] array, T obj) {
		return (T[]) addObject(array, obj);
	}

	/**
	 * 配列に追加する
	 *
	 * @param array 配列
	 * @param obj 新しい要素
	 * @return 新しい要素を追加した配列
	 */
	public static boolean[] add(boolean[] array, boolean obj) {
		return (boolean[]) addObject(array, obj);
	}

	/**
	 * 配列に追加する
	 *
	 * @param array 配列
	 * @param obj 新しい要素
	 * @return 新しい要素を追加した配列
	 */
	public static byte[] add(byte[] array, byte obj) {
		return (byte[]) addObject(array, obj);
	}

	/**
	 * 配列に追加する
	 *
	 * @param array 配列
	 * @param obj 新しい要素
	 * @return 新しい要素を追加した配列
	 */
	public static char[] add(char[] array, char obj) {
		return (char[]) addObject(array, obj);
	}

	/**
	 * 配列に追加する
	 *
	 * @param array 配列
	 * @param obj 新しい要素
	 * @return 新しい要素を追加した配列
	 */
	public static double[] add(double[] array, double obj) {
		return (double[]) addObject(array, obj);
	}

	/**
	 * 配列に追加する
	 *
	 * @param array 配列
	 * @param obj 新しい要素
	 * @return 新しい要素を追加した配列
	 */
	public static float[] add(float[] array, float obj) {
		return (float[]) addObject(array, obj);
	}

	/**
	 * 配列に追加する
	 *
	 * @param array 配列
	 * @param obj 新しい要素
	 * @return 新しい要素を追加した配列
	 */
	public static int[] add(int[] array, int obj) {
		return (int[]) addObject(array, obj);
	}

	/**
	 * 配列に追加する
	 *
	 * @param array 配列
	 * @param obj 新しい配列
	 * @return 新しい要素を追加した配列
	 */
	public static byte[] add(byte[] array, byte[] obj) {
		return (byte[]) addArray(array, obj);
	}

	/**
	 * 配列に追加する
	 *
	 * @param array 配列
	 * @param obj 新しい配列
	 * @return 新しい要素を追加した配列
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] add(T[] array, T[] obj) {
		return (T[]) addArray(array, obj);
	}

	/**
	 * 配列をリサイズする
	 *
	 * @param array 配列
	 * @param size 新しいサイズ
	 * @return リサイズした配列
	 */
	public static boolean[] resize(boolean[] array, int size) {
		return (boolean[]) resizeObject(array, size);
	}

	/**
	 * 配列をリサイズする
	 *
	 * @param array 配列
	 * @param size 新しいサイズ
	 * @return リサイズした配列
	 */
	public static byte[] resize(byte[] array, int size) {
		return (byte[]) resizeObject(array, size);
	}

	/**
	 * 配列をリサイズする
	 *
	 * @param array 配列
	 * @param size 新しいサイズ
	 * @return リサイズした配列
	 */
	public static char[] resize(char[] array, int size) {
		return (char[]) resizeObject(array, size);
	}

	/**
	 * 配列をリサイズする
	 *
	 * @param array 配列
	 * @param size 新しいサイズ
	 * @return リサイズした配列
	 */
	public static double[] resize(double[] array, int size) {
		return (double[]) resizeObject(array, size);
	}

	/**
	 * 配列をリサイズする
	 *
	 * @param array 配列
	 * @param size 新しいサイズ
	 * @return リサイズした配列
	 */
	public static float[] resize(float[] array, int size) {
		return (float[]) resizeObject(array, size);
	}

	/**
	 * 配列をリサイズする
	 *
	 * @param array 配列
	 * @param size 新しいサイズ
	 * @return リサイズした配列
	 */
	public static int[] resize(int[] array, int size) {
		return (int[]) resizeObject(array, size);
	}

	/**
	 * 配列をリサイズする
	 *
	 * @param array 配列
	 * @param size 新しいサイズ
	 * @return リサイズした配列
	 */
	public static long[] resize(long[] array, int size) {
		return (long[]) resizeObject(array, size);
	}

	/**
	 * 配列をリサイズする
	 *
	 * @param array 配列
	 * @param size 新しいサイズ
	 * @return リサイズした配列
	 */
	public static short[] resize(short[] array, int size) {
		return (short[]) resizeObject(array, size);
	}

	/**
	 * 配列をリサイズする
	 *
	 * @param <T> 要素の型
	 * @param array 配列
	 * @param size 新しいサイズ
	 * @return リサイズした配列
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] resize(T[] array, int size) {
		return (T[]) resizeObject(array, size);
	}

	/**
	 * 配列を反転する
	 *
	 * @param array 配列
	 * @return 反転した配列
	 */
	public static boolean[] reverse(boolean[] array) {
		return (boolean[]) reverseObject(array);
	}

	/**
	 * 配列を反転する
	 *
	 * @param array 配列
	 * @return 反転した配列
	 */
	public static byte[] reverse(byte[] array) {
		return (byte[]) reverseObject(array);
	}

	/**
	 * 配列を反転する
	 *
	 * @param array 配列
	 * @return 反転した配列
	 */
	public static char[] reverse(char[] array) {
		return (char[]) reverseObject(array);
	}

	/**
	 * 配列を反転する
	 *
	 * @param array 配列
	 * @return 反転した配列
	 */
	public static double[] reverse(double[] array) {
		return (double[]) reverseObject(array);
	}

	/**
	 * 配列を反転する
	 *
	 * @param array 配列
	 * @return 反転した配列
	 */
	public static float[] reverse(float[] array) {
		return (float[]) reverseObject(array);
	}

	/**
	 * 配列を反転する
	 *
	 * @param array 配列
	 * @return 反転した配列
	 */
	public static int[] reverse(int[] array) {
		return (int[]) reverseObject(array);
	}

	/**
	 * 配列を反転する
	 *
	 * @param array 配列
	 * @return 反転した配列
	 */
	public static long[] reverse(long[] array) {
		return (long[]) reverseObject(array);
	}

	/**
	 * 配列を反転する
	 *
	 * @param array 配列
	 * @return 反転した配列
	 */
	public static short[] reverse(short[] array) {
		return (short[]) reverseObject(array);
	}

	/**
	 * 配列を反転する
	 *
	 * @param array 配列
	 * @return 反転した配列
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] reverse(T[] array) {
		return (T[]) reverseObject(array);
	}

	/**
	 * リストを配列に変換する
	 *
	 * @param <T> 要素の型
	 * @param list リスト
	 * @return 配列
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] toArray(List<T> list) {
		if (list == null) {
			return null;
		}
		if (list.isEmpty()) {
			return (T[]) null;
		}
		T element = list.get(0);

		Object newArray = Array.newInstance(element.getClass(), 1);
		return (T[]) list.toArray((T[]) newArray);
	}

	// ローカルメソッド=========================================================
	/**
	 * 指定した配列の位置から指定した要素数分の配列を作成する
	 *
	 * @param <T> 要素の型
	 * @param array 配列
	 * @param offset 複製を開始する配列要素番号
	 * @param length 複製する要素数
	 * @return 複製した配列
	 */
	private static Object cloneObject(Object array, int offset, int length) {

		if (array == null) {
			return null;
		}
		int arrayLength = Array.getLength(array);
		int newLength = length;
		if (arrayLength < length) {
			newLength = arrayLength;
		}

		Object newArray = Array.newInstance(
				array.getClass().getComponentType(), newLength);
		if (newArray != null) {
			System.arraycopy(array, offset, newArray, 0, newLength);
		}
		return newArray;
	}

	/**
	 * 指定した配列の位置から指定した要素数分の配列を作成する
	 *
	 * @param <T> 要素の型
	 * @param array 配列
	 * @param length 複製する要素数
	 * @return 複製した配列
	 */
	private static Object resizeObject(Object array, int length) {

		if (array == null) {
			return null;
		}
		int arrayLength = Array.getLength(array);
		int newLength = length;
		if (arrayLength < length) {
			newLength = arrayLength;
		}

		Object newArray = Array.newInstance(
				array.getClass().getComponentType(), length);
		if (newArray != null) {
			System.arraycopy(array, 0, newArray, 0, newLength);
		}
		return newArray;
	}

	/**
	 * 指定した配列の位置から指定した要素数分の配列を作成する
	 *
	 * @param <T> 要素の型
	 * @param array 配列
	 * @param addObject 追加する要素
	 * @return 複製した配列
	 */
	private static Object addObject(Object array, Object addObject) {

		if (array == null) {
			return null;
		}
		int arrayLength = Array.getLength(array);
		int newLength = arrayLength + 1;

		Object newArray = Array.newInstance(
				array.getClass().getComponentType(), newLength);
		if (newArray != null) {
			System.arraycopy(array, 0, newArray, 0, arrayLength);
			Array.set(newArray, newLength - 1, addObject);
		}
		return newArray;
	}

	/**
	 * 指定した配列の位置から指定した配列の要素を追加する
	 *
	 * @param <T> 要素の型
	 * @param array 配列
	 * @param addArray 追加する配列
	 * @return 複製した配列
	 */
	private static Object addArray(Object array, Object addArray) {

		if (array == null) {
			return null;
		}
		if (addArray == null) {
			return array;
		}

		int arrayLength = Array.getLength(array);
		int addArrayLength = Array.getLength(addArray);
		int newLength = arrayLength + addArrayLength;

		Object newArray = Array.newInstance(array.getClass().getComponentType(), newLength);
		if (newArray != null) {
			System.arraycopy(array, 0, newArray, 0, arrayLength);
			for (int i = 0; i < addArrayLength; i++) {
				Object addObject = Array.get(addArray, i);
				Array.set(newArray, arrayLength + i, addObject);
			}
		}
		return newArray;
	}

	/**
	 * 指定した配列の各要素を反転した配列を作成する
	 *
	 * @param <T> 要素の型
	 * @param array 配列
	 * @return 反転した配列
	 */
	private static Object reverseObject(Object array) {
		if (array == null) {
			return null;
		}
		int arrayLength = Array.getLength(array);

		Object newArray = Array.newInstance(array.getClass().getComponentType(), arrayLength);
		if (newArray != null) {
			for (int i = 0; i < arrayLength; i++) {
				Array.set(newArray, i, Array.get(array, arrayLength - i - 1));
			}
		}
		return newArray;
	}
}
