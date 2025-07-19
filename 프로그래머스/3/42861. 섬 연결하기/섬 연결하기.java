import java.util.*;
import java.io.*;

class Solution {
    
    static int N, parents[];
    
    static class Edge implements Comparable<Edge> {
        int start;
        int end;
        int cost;
        
        public Edge(int start, int end, int cost){
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Edge o){
            return Integer.compare(this.cost, o.cost);
        }
    }
    
    static void make(){
        parents = new int[N];
        Arrays.fill(parents, -1);
    }
    
    static int findSet(int a){
        if(parents[a] < 0) return a;
        return parents[a] = findSet(parents[a]);
    }
    
    static boolean union(int a, int b){
        int rootA = findSet(a);
        int rootB = findSet(b);
        if(rootA == rootB) return false;
        
        parents[rootA] += parents[rootB];
        parents[rootB] = rootA;
        return true;
    }
    
    public int solution(int n, int[][] costs) {
        N = n;
        make();
        
        List<Edge> edges = new ArrayList<>();
        for(int i = 0; i < costs.length; i++){
            edges.add(new Edge(costs[i][0], costs[i][1], costs[i][2]));
        }
        
        Collections.sort(edges);
        
        int answer = 0;
        int cnt = 0;
        for(Edge edge : edges){
            if(cnt >= N - 1) break;
            if(union(edge.start, edge.end)){
                cnt++;
                answer += edge.cost;
            }
        }
        
        return answer;
    }
}