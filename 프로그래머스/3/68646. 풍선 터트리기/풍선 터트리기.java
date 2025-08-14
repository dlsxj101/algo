import java.util.*;
import java.io.*;
class Solution {
    public int solution(int[] a) {
        int N = a.length;
        int leftMin[] = new int[N];
        int rightMin[] = new int[N];
        
        leftMin[0] = a[0];
        rightMin[N - 1] = a[N - 1];
        for(int i = 1; i < N; i++){
            leftMin[i] = Math.min(leftMin[i-1], a[i]);
            rightMin[N - 1 - i] = Math.min(rightMin[N - i], a[N - 1 - i]);
        }
        
        int answer = N;
        for(int i = 0; i < N; i++){
            if(a[i] == leftMin[i] || a[i] == rightMin[i]) continue;
            answer--;
        }        
        
        return answer;
    }
}