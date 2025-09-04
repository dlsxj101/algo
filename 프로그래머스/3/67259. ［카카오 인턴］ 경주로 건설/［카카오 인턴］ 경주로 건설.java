import java.util.*;
import java.io.*;

class Solution {
    
    static int dr[] = {-1, 0, 1, 0};    // 상(0) 좌(1) 하(2) 우(3)
    static int dc[] = {0, -1, 0, 1};    // 상(0) 좌(1) 하(2) 우(3)
    
    static class Node implements Comparable<Node>{
        int nowR;
        int nowC;
        int cost;
        int dir;
        
        public Node(int nowR, int nowC, int cost, int dir){
            this.nowR = nowR;
            this.nowC = nowC;
            this.cost = cost;
            this.dir = dir;
        }
        
        @Override
        public int compareTo(Node o){
            return Integer.compare(this.cost, o.cost);
        }
    }
    
    public int solution(int[][] board) {
        int N = board.length;
        
        int dist[][][] = new int[N][N][4];
        
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                for(int l = 0; l < 4; l++){
                    dist[i][j][l] = Integer.MAX_VALUE;
                }
            }
        }
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, 0, 0, 2));
        pq.add(new Node(0, 0, 0, 3));
        dist[0][0][2] = 0;
        dist[0][0][3] = 0;
        
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            
            if(dist[cur.nowR][cur.nowC][cur.dir] < cur.cost) continue;
            
            for(int d = 0; d < 4; d++){
                if(d == (cur.dir + 2) % 4) continue;    // 진행방향의 역방향은 X
                
                int nr = cur.nowR + dr[d];
                int nc = cur.nowC + dc[d];
                
                if( (nr >= N) || (nc >= N) || (nr < 0) || (nc < 0) ) continue;
                if(board[nr][nc] == 1) continue;
                
                int cost = 600;
                if(d == cur.dir) cost = 100;
                
                if(dist[nr][nc][d] >= dist[cur.nowR][cur.nowC][cur.dir] + cost){
                    dist[nr][nc][d] = dist[cur.nowR][cur.nowC][cur.dir] + cost;
                    pq.add(new Node(nr, nc, dist[nr][nc][d], d));
                }    
            }
        }
        
        // for(int i = 0; i < N; i++){
        //     System.out.println();
        //     for(int j = 0; j < N; j++){
        //         if(dist[i][j] == Integer.MAX_VALUE) System.out.print("I ");
        //         else System.out.print(dist[i][j] + " ");
        //     }
        // }
        
        int answer = Integer.MAX_VALUE;
        for(int i = 0; i < 4; i++){
            answer = Math.min(answer, dist[N-1][N-1][i]);
        }
        
        return answer;
    }
}