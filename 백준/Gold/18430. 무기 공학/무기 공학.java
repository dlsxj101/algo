import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, ans;
    static int map[][];
    static boolean visited[][];

    static int delta[][] = {
            {0, -1, 1, 0},
            {0, -1, -1, 0},
            {-1, 0, 0, 1},
            {1, 0, 0, 1}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N][M];

        ans = 0;
        dfs(0, 0);

        System.out.println(ans);
    }

    static void dfs(int idx, int score) {
        if(idx == N * M){
            ans = Math.max(ans, score);
            return;
        }

        dfs(idx + 1, score);

        int i = idx / M;
        int j = idx % M;

        if(visited[i][j]) return;

        for(int d = 0; d < 4; d++){
            if(!check(i + delta[d][0], j + delta[d][1])) continue;
            if(!check(i + delta[d][2], j + delta[d][3])) continue;

            if(!visited[i + delta[d][0]][j + delta[d][1]] && !visited[i + delta[d][2]][j + delta[d][3]]){
                visited[i][j] = true;
                visited[i + delta[d][0]][j + delta[d][1]] = true;
                visited[i + delta[d][2]][j + delta[d][3]] = true;

                dfs(idx + 1, score
                        + map[i][j] * 2
                        + map[i + delta[d][0]][j + delta[d][1]]
                        + map[i + delta[d][2]][j + delta[d][3]]
                );

                visited[i][j] = false;
                visited[i + delta[d][0]][j + delta[d][1]] = false;
                visited[i + delta[d][2]][j + delta[d][3]] = false;
            }
        }
    }

    static boolean check(int nr, int nc) {
        if ((nr >= N) || (nc >= M) || (nr < 0) || (nc < 0)) return false;
        return true;
    }
}