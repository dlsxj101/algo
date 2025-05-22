import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int dr[] = {-1, 0, 1, 0}; // 상, 좌, 하, 우
    static int dc[] = {0, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int C = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        char map[][] = new char[R][C];

        int startR = -1, startC = -1;
        int endR = -1, endC = -1;

        for (int i = 0; i < R; i++) {
            String input = br.readLine().trim();
            for (int j = 0; j < C; j++) {
                map[i][j] = input.charAt(j);
                if (map[i][j] == 'C') {
                    if (startR == -1) {
                        startR = i; // 첫 번째 C
                        startC = j;
                    } else {
                        endR = i;   // 두 번째 C
                        endC = j;
                    }
                }
            }
        }

        // ★ dist를 3차원 배열로 변경: dist[r][c][dir]
        //   dir: 0=상, 1=좌, 2=하, 3=우
        int dist[][][] = new int[R][C][4];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                Arrays.fill(dist[i][j], Integer.MAX_VALUE);
            }
        }

        // 우선순위 큐: (r, c, 방향, 거울 설치 횟수)
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[3] - o2[3]);

        // visited[r][c][dir]
        boolean visited[][][] = new boolean[R][C][4];

        // 시작점: 아직 방향이 없으므로 인접한 칸으로 첫 이동 시에 비용 = 0
        // ★ 시작점에서 4방향을 모두 시도해볼 수도 있으나,
        //   문제 조건에 따라 인접칸으로 이동 가능한 방향만 세팅
        for (int d = 0; d < 4; d++) {
            int nr = startR + dr[d];
            int nc = startC + dc[d];
            if (nr < 0 || nc < 0 || nr >= R || nc >= C) continue;
            if (map[nr][nc] == '*') continue;

            dist[nr][nc][d] = 0; // 거울 설치 없이 진행
            pq.add(new int[]{nr, nc, d, 0});
        }

        while (!pq.isEmpty()) {
            int[] tmp = pq.poll();
            int r = tmp[0];
            int c = tmp[1];
            int dir = tmp[2];
            int cost = tmp[3]; // 현재까지 설치한 거울 수

            // 이미 방문 처리된 (r, c, dir)이면 스킵
            if (visited[r][c][dir]) continue;
            visited[r][c][dir] = true;

            // 현재 우선순위 큐에서 꺼낸 cost가 dist배열과 불일치하면, 더 낮은 비용으로 갱신된 상태이므로 스킵
            if (cost > dist[r][c][dir]) continue;

            // 4방향 탐색
            for (int nd = 0; nd < 4; nd++) {
                // 뒤로 가는 것(180도 회전)은 굳이 안 쓰겠다면 아래처럼 continue
                if ((dir + 2) % 4 == nd) continue;

                int nr = r + dr[nd];
                int nc = c + dc[nd];
                if (nr < 0 || nc < 0 || nr >= R || nc >= C) continue;
                if (map[nr][nc] == '*') continue;

                // 다음 방향으로 가는 데 필요한 거울 설치 수
                int nextCost = cost + ((dir == nd) ? 0 : 1);

                // ★ dist를 3차원으로 접근
                if (dist[nr][nc][nd] > nextCost) {
                    dist[nr][nc][nd] = nextCost;
                    pq.add(new int[]{nr, nc, nd, nextCost});
                }
            }
        }

        // 최종 결과: 도착점 (endR, endC) 에 대한 4방향 중 최솟값
        int answer = Integer.MAX_VALUE;
        for (int d = 0; d < 4; d++) {
            answer = Math.min(answer, dist[endR][endC][d]);
        }

        // 디버깅 겸, dist 최솟값을 전체 좌표에 대해 찍어보기(원하면)
        /*
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                int minVal = Integer.MAX_VALUE;
                for (int d = 0; d < 4; d++) {
                    minVal = Math.min(minVal, dist[i][j][d]);
                }
                if (minVal == Integer.MAX_VALUE) System.out.print("I ");
                else System.out.print(minVal + " ");
            }
            System.out.println();
        }
        */

        System.out.println(answer);
    }
}
