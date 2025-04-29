import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int dr[] = {-1, 1, 0, 0};
    static int dc[] = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int map[][] = new int[R][C];

        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[R][C];

        int startR = 0;
        int startC = 0;

        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = input.charAt(j);

                if (map[i][j] == 'J') {
                    startR = i;
                    startC = j;
                }

                if (map[i][j] == 'F') {
                    q.add(new int[]{i, j, 1});
                }
            }
        }

        q.add(new int[]{startR, startC, 0});
        visited[startR][startC] = true;

        int depth = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            depth++;
            for (int s = 0; s < size; s++) {
                int[] tmp = q.poll();

                int r = tmp[0];
                int c = tmp[1];
                int type = tmp[2];  // 불:1, 사람:0

                if(type == 1) {
                    for(int d = 0; d < 4; d++){
                        int nr = r + dr[d];
                        int nc = c + dc[d];

                        if( (nr < 0) || (nr >= R) || (nc < 0) || (nc >= C) ) continue;
                        if(map[nr][nc] == 'F') continue;
                        if(map[nr][nc] == '#') continue;

                        map[nr][nc] = 'F';
                        q.add(new int[]{nr, nc, 1});
                    }
                }
                else if(type == 0) {
                    for (int d = 0; d < 4; d++) {
                        int nr = r + dr[d];
                        int nc = c + dc[d];

                        if (nr < 0 || nr >= R || nc < 0 || nc >= C) {
                            System.out.println(depth);
                            return;
                        }
                        if (map[nr][nc] == '#') continue;
                        if (map[nr][nc] == 'F') continue;
                        if (visited[nr][nc]) continue;

                        visited[nr][nc] = true;
                        q.add(new int[]{nr, nc, 0});
                    }
                }
            }
        }

        System.out.println("IMPOSSIBLE");
    }
}