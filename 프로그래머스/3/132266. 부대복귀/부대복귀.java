import java.util.*;
import java.io.*;
class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        List<Integer> adjList[] = new List[n + 1];
        for(int i = 1; i <= n; i++){
            adjList[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < roads.length; i++){
            adjList[roads[i][0]].add(roads[i][1]);
            adjList[roads[i][1]].add(roads[i][0]);
        }
        
        int dist[] = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        Queue<Integer> q = new ArrayDeque<>();
        dist[destination] = 0;
        q.add(destination);
        
        while(!q.isEmpty()){
            int now = q.poll();
            
            for(int next : adjList[now]){
                if(dist[next] > dist[now] + 1){
                    dist[next] = dist[now] + 1;
                    q.add(next);
                }
            }
        }
        
        int[] answer = new int[sources.length];
        
        for(int i = 0; i < sources.length; i++){
            if(dist[sources[i]] == Integer.MAX_VALUE) {
                answer[i] = -1;
            }
            else answer[i] = dist[sources[i]];
        }
        return answer;
    }
}