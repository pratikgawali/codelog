// https://codeforces.com/contest/2117/problem/E

import java.util.*;

public class LostSoul {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            
            int n = sc.nextInt();
            
            int[] a = new int[n];
            int[] b = new int[n];

            for (int i=0; i<n; i++) {
                a[i] = sc.nextInt();
            }

            for (int i=0; i<n; i++) {
                b[i] = sc.nextInt();
            }

            int ans = matches(a, b);
            System.out.println(ans);
        }

        sc.close();
    }

    private static int matches(int[] a, int[] b) {

        int n = a.length;

        if (a[n-1] == b[n-1]) {
            return n;
        }

        if (a[n-2] == b[n-2] || a[n-2] == a[n-1] || b[n-2] == b[n-1]) {
            return n-1;
        }

        Set<Integer> seen = new HashSet<>(n);
        seen.add(a[n-1]);
        seen.add(b[n-1]);

        int prevA = a[n-2], prevB = b[n-2];

        int r = n-3;
        while (r >= 0) {
            
            if (a[r] == b[r]) { 
                break;
            } else if (a[r] == prevA || b[r] == prevB) {
                break;
            } else if (seen.contains(a[r]) || seen.contains(b[r])) {
                break;
            }
            else {
                seen.add(prevA);
                seen.add(prevB);
                
                prevA = a[r];
                prevB = b[r];
            }

            r--;
        }

        return r+1;
    }
}

