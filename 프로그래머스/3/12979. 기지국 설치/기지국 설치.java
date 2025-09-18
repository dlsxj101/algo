import java.util.*;
import java.io.*;

class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        
        int m = stations.length;
        int zoneArea = (w*2) + 1;
        
        int start = 1;
        int end = 0;
        
        for(int i = 0; i <= m; i++){
            if(i == m) end = n + 1;
            else end = stations[i] - w;
            
            int noSignalZone = end - start;
            
            if(noSignalZone <= 0) {
                if(i != m) start = stations[i] + w + 1;
                continue;
            }
            
            int needCnt = noSignalZone / zoneArea;
            
            if(needCnt * zoneArea != noSignalZone) needCnt++;
            
            answer += needCnt;
            // System.out.println(needCnt);
            
            if(i==m) break;
            start = stations[i] + w + 1;
        }
        
        return answer;
    }
}