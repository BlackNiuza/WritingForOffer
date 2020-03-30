package _2020_笔试;

import java.util.HashMap;
import java.util.Map;

/**
 * 小强得到了一个长度为n的序列，但他只对非常大的数字感兴趣，因此他想随机选择这个序列的一个连续子序列，并求出这个序列的最大值，请你告诉他这个
 * 最大值的期望值是多少。
 * 对于连续子序列的定义：例如长度为3的序列2 5 6
 * 他的连续子序列有 2, 5, 6, 2 5, 5 6, 5 6, 2 5 6
 *
 * 解法
 * 动态规划全枚举，注意越界
 */
public class 序列 {

    public static void main(String[] args) {
        // 2.333333
        System.out.println(String.format("%.6f", e(new int[]{1, 2, 3})));
    }

    static double e(int[] seq) {
        int len = seq.length;
        double diffSeqCnt = len * (len + 1) / 2;
        Map<Long, Integer> maxVal = new HashMap<>();
        int[] valCnt = new int[1001];
        for (int i = 0; i < valCnt.length; i++) {
            valCnt[i] = 0;
        }
        findMaxAncCnt(seq, 0, len - 1, valCnt, maxVal);
        long res = 0;
        for (int i = 0; i < seq.length; i++) {
            res += seq[i] * valCnt[seq[i]];
        }
        return res / diffSeqCnt;
    }

    private static int findMaxAncCnt(int[] seq, int i, int j, int[] valCnt, Map<Long, Integer> maxVal) {
        int m = Integer.MIN_VALUE;
        if (i == j) {
            m = seq[i];
        } else {
            int len = j - i;
            for (int nI = i; nI <= j; nI++) {
                int nJ = nI + len - 1;
                if (nJ >= seq.length) {
                    break;
                }
                Long idx = idx(nI, nJ, seq.length);
                if (!maxVal.containsKey(idx)) {
                    maxVal.put(idx, findMaxAncCnt(seq, nI, nJ, valCnt, maxVal));
                }
                m = Math.max(m, maxVal.get(idx));
            }
        }
        valCnt[m]++;
        return m;
    }

    private static long idx(int i, int j, int n) {
        return i * n + j;
    }

}
