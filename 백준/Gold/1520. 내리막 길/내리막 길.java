import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] memo, map;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        memo = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = dfs(0, 0);

        System.out.println(ans);

//        for(int i = 0; i < N; i++){
//            System.out.println();
//            for(int j = 0; j < M; j++){
//                System.out.print(memo[i][j] + " ");
//            }
//        }
    }

    static int dfs(int nowR, int nowC){
        if(nowR == N - 1 && nowC == M - 1) return 1;

        for(int d = 0; d < 4; d++){
            int nr = nowR + dr[d];
            int nc = nowC + dc[d];

            if( (nr >= N) || (nc >= M) || (nr < 0) || (nc < 0) ) continue;
            if(map[nr][nc] >= map[nowR][nowC]) continue;
            if(memo[nr][nc] != 0) {
                memo[nowR][nowC] += memo[nr][nc];
                continue;
            }

            memo[nowR][nowC] += dfs(nr, nc);
        }
        if(memo[nowR][nowC] == 0) map[nowR][nowC] = 10001;
        return memo[nowR][nowC];
    }
}
