package _2020_third;

import java.util.Scanner;

public class A {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int q = in.nextInt();
        long[][] a = new long[n + 1][];
        for (int i = 1; i <= n; i++) {
            a[i] = new long[m + 1];
            for (int j = 1; j <= m; j++) {
                a[i][j] = in.nextInt();
            }
        }
        for (int i = 0; i < q; i++) {
            int row = in.nextInt();
            int col = in.nextInt();
            long rv = rowVal(a,n,m, row);
            long cv = rowVal(a,n,m, col);
            if (rv == -1 || cv == -1 || rv != cv) {
                System.out.println("Unknown");
            } else {
                System.out.println(rv);
            }
        }
    }

    public static long rowVal(long[][] a, int n, int m, int row) {
        long[] b = new long[m];
        for (int i = 1; i <= m; i++) {
            b[i] = a[row][i];
        }
        double g = gap(b, m);
        long n1 = (long) g;
        long n2 = (long) g + 1;
        if (n1 < g || g < n2) {
            return -1;
        }
        return intVal(b,m,n1,row);
    }

    public static long colVal(long[][] a, int n, int m, int col) {
        long[] b = new long[n];
        for (int i = 1; i <= n; i++) {
            b[i] = a[i][col];
        }
        double g = gap(b, m);
        long n1 = (long) g;
        long n2 = (long) g + 1;
        if (n1 < g || g < n2) {
            return -1;
        }
        return intVal(b,n,n1,col);
    }

    public static double gap(long[] b, int n) {
        int i1 = -1;
        int i2 = -1;
        for (int i = 1; i <= n; i++) {
            if (i1 == -1 && b[i] != 0) {
                i1 = i;
            } else if (i1 != -1 && i2 == -1 && b[i] != 0) {
                i2 = i;
            }
        }
        if (i1 == -1 || i2 == -1) {
            return -1;
        }
        return (b[i2] - b[i1]) / (i2 - i1);
    }

    public static long intVal(long[]b, int n, long g, int idx) {
        for(int i=2;i<=n;i++) {
            if(b[i-1]!=0) {
                b[i] = b[i-1];
            }
        }
        for(int i=n-1;i>=1;i--) {
            if(b[i+1]!=0) {
                b[i] = b[i+1] - g;
            }
        }
        return b[idx];
    }
}
