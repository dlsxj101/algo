import java.util.*;
import java.io.*;

class Solution {
    
    static int dist[][];
    
    public int solution(int n, int s, int a, int b, int[][] fares) {

        dist = new int[n + 1][n + 1];
        
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                if(i == j) continue;
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
        
        for(int i = 0; i < fares.length; i++){
            dist[fares[i][0]][fares[i][1]] = fares[i][2];
            dist[fares[i][1]][fares[i][0]] = fares[i][2];
        }
        
        for(int k = 1; k <= n; k++){
            for(int i = 1; i <= n; i++){
                if(dist[i][k] == Integer.MAX_VALUE) continue;
                for(int j = 1; j <= n; j++){
                    if(dist[k][j] == Integer.MAX_VALUE) continue;
                    if(dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
        
        int answer = Integer.MAX_VALUE;
        
        for(int i = 1; i <= n; i++){
            answer = Math.min(answer, dist[s][i] + dist[i][a] + dist[i][b]);
        }
        
        return answer;
    }
}