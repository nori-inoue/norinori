package jp.gr.norinori;

import java.math.BigInteger;

/**
 *
 * 数式クラス
 *
 * @author nori
 *
 */
public class Math {

	/**
	 *
	 * 入山徳夫氏アルゴリズム
	 *
	 * @param n
	 * @param r
	 * @return 組み合わせ数 nCr
	 */
	public static BigInteger combination(int n, int r) {
		if (n < 0 || r < 0 || r > n) {
			return BigInteger.ZERO;
		}

		if (n - r < r) {
			r = n - r;
		}
		if (r == 0) {
			return BigInteger.ONE;
		}
		if (r == 1) {
			return BigInteger.valueOf(n);
		}

		int[] numerator = new int[r];
		int[] denominator = new int[r];

		for (int k = 0; k < r; k++) {
			numerator[k] = n - r + k + 1;
			denominator[k] = k + 1;
		}

		for (int p = 2; p <= r; p++) {
			int pivot = denominator[p - 1];
			if (pivot > 1) {
				int offset = (n - r) % p;
				for (int k = p - 1; k < r; k += p) {
					numerator[k - offset] /= pivot;
					denominator[k] /= pivot;
				}
			}
		}

		BigInteger result = BigInteger.ONE;
		for (int k = 0; k < r; k++) {
			if (numerator[k] > 1) {
				result = result.multiply(BigInteger.valueOf(numerator[k]));
			}
		}

		return result;
	}
}
