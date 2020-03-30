package _2020_笔试;

import java.util.*;

/**
 * 【小强的养鸡场】小强有n个养鸡场,第i个养鸡场初始时有a[i]只小鸡.与其他养鸡场不同的是,小强的每个养鸡场每天都会增加k只小鸡,为了维持养鸡场良好的生态环境,
 * 小强在每天结束的时候都会在数量最多的养鸡场里卖掉一半的小鸡,即若当前最多的养鸡场含有x只小鸡,经过贩卖后,当前养鸡场会剩下(x/2，取下界)只小鸡.
 * 现在问你经过m天后,小强的n个养鸡场里面一共有多少只小鸡.
 *
 * 1<=m<=100000
 * 1<=n<=100000
 * 1<=k<=100000
 * 1<=a[i]<=100000
 *
 * 解法
 * 1. 暴力解法，时间复杂度O(m * n/2)
 * 2. 写个最大堆，时间复杂度O(m * logn)
 */
public class 小强的养鸡场 {

    public static void main(String[] args) {
        // 925
        System.out.println(chicken(new int[]{100, 200, 400}, 3, 100));
    }

    static int chicken(int[] a, int m, int k) {
        Arrays.sort(a);
        LinkedList<Farm> fList = new LinkedList<>();
        Queue<Farm> q = fList;
        for (int i = a.length - 1; i >= 0; i--) {
            q.add(new Farm(a[i], 0));
        }
        for (int day = 1; day <= m; day++) {
            Farm f = q.poll();
            f.inc(day, k);
            f.sell();
            int i = 0;
            for(Farm f1: fList) {
                f1.inc(day, k);
                if(f.cnt >= f1.cnt) {
                    break;
                }
                i++;
            }
            fList.add(i, f);
        }
        int sum = 0;
        while (!q.isEmpty()) {
            sum += q.poll().cnt;
        }
        return sum;
    }

    static class Farm {
        int cnt;
        int day;

        public Farm(int cnt, int day) {
            this.cnt = cnt;
            this.day = day;
        }

        void inc(int day, int k) {
            this.cnt = this.cnt + (day - this.day) * k;
            this.day = day;
        }

        void sell() {
            this.cnt /= 2;
        }
    }

}
