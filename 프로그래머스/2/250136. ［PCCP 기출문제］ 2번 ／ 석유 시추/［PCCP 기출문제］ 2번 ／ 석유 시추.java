import java.util.*;
class Solution {
    
    static int N, M, size;
    static int map[][];
    static int visited[][];
    
    static int dr[] = {-1, 1, 0, 0};
    static int dc[] = {0, 0, -1, 1};
    
    public int solution(int[][] land) {
        N = land.length;
        M = land[0].length;
        
        map = new int[N][M];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                map[i][j] = land[i][j];
            }
        }
        
        int cnt = 0;
        Map<Integer, Integer> hashmap = new HashMap<>();
        visited = new int[N][M];
        
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(map[i][j] == 0) continue;
                if(visited[i][j] != 0) continue;
                
                Queue<int[]> q = new ArrayDeque<>();
                q.add(new int[] {i, j});
                cnt++;
                visited[i][j] = cnt;
                int size = 0;
                
                while(!q.isEmpty()){
                    int tmp[] = q.poll();
                    size++;
                    
                    int r = tmp[0];
                    int c = tmp[1];
                    
                    for(int d = 0; d < 4; d++){
                        int nr = r + dr[d];
                        int nc = c + dc[d];
                        
                        if( (nr >= N) || (nc >= M) || (nr < 0) || (nc < 0) ) continue;
                        if(map[nr][nc] == 0) continue;
                        
                        if(visited[nr][nc] != 0) continue;
                        q.add(new int[] {nr, nc});
                        visited[nr][nc] = cnt;
                    }
                    hashmap.put(cnt, size);
                }
            }
        }
        
        int answer = -1;
        
        for(int i = 0; i < M; i++){
            boolean check[] = new boolean[cnt+1];
            int temp = 0;
            for(int j = 0; j < N; j++){
                if(map[j][i] == 0) continue;
                if(check[visited[j][i]]) continue;
                
                check[visited[j][i]] = true;
                temp += hashmap.get(visited[j][i]);
            }
            answer = Math.max(answer, temp);
        }
        return answer;
    }
}