// https://codeforces.com/problemset/problem/2103/C

import java.util.*;

public class MedianSplit {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            
            int n = sc.nextInt();
            int k = sc.nextInt();

            int[] arr = new int[n];
            for (int i=0; i<n; i++) {
                arr[i] = sc.nextInt();
            }

            String ans = canSplit(arr, k) ? "YES" : "NO";
            System.out.println(ans);
        }

        sc.close();
    }

    private static boolean canSplit(int[] arr, int k) {

        int n = arr.length;

        for (int i=0; i<n; i++) {
            arr[i] = arr[i] <= k ? 1 : -1; 
        }

        // case 1: left and right
        int l = n;
        int prefix = 0;
        for (int i=0; i<n-2; i++) {
            prefix += arr[i];
            if (prefix >= 0) {
                l = i;
                break;
            }
        }

        int r = -1;
        int suffix = 0;
        for (int i=n-1; i>1; i--) {
            suffix += arr[i];
            if (suffix >= 0) {
                r = i;
                break;
            }
        }

        if (l+1 < r) {
            return true;
        }

        // case 2: left and middle
        if (check(arr, k)) {
            return true;
        }

        // case 3: middle and right
        int[] rarr = new int[n];
        for (int i=0; i<n; i++) {
            rarr[i] = arr[n-1-i];
        }

        if (check(rarr, k)) {
            return true;
        }

        return false;
    }

    private static boolean check(int[] arr, int k) {

        int n = arr.length;

        int[] prefix = new int[n];
        prefix[0] = arr[0];
        for (int i=1; i<n; i++) {
            prefix[i] = prefix[i-1] + arr[i];
        }

        int[] msp = new int[n-1];
        msp[n-2] = prefix[n-2];
        for (int i=n-3; i>=0; i--) {
            msp[i] = Math.max(prefix[i], msp[i+1]);
        }

        for (int i=0; i<n-2; i++) {
            if (prefix[i] >= 0 && msp[i+1] >= prefix[i]) {
                return true;
            }
        }

        return false;
    }
}
