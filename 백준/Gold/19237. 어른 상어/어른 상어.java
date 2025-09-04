import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int dr[] = {0, -1, 1, 0, 0};
    static int dc[] = {0, 0, 0, -1, 1};

    static class Smell {
        int sharkNum;
        int remainSmell;

        public Smell(int sharkNum, int remainSmell) {
            this.sharkNum = sharkNum;
            this.remainSmell = remainSmell;
        }
    }

    static class Shark {
        int num;
        int nowR;
        int nowC;
        int curDir;

        public Shark(int num, int nowR, int nowC, int curDir) {
            this.num = num;
            this.nowR = nowR;
            this.nowC = nowC;
            this.curDir = curDir;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Smell smell[][] = new Smell[N][N];
        Shark sharks[] = new Shark[M + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int tmp = Integer.parseInt(st.nextToken());

                if(tmp == 0) continue;
                sharks[tmp] = new Shark(tmp, i, j, 0);  // 상어 기록(방향 제외)
                smell[i][j] = new Smell(tmp, k);              // 냄새 기록
            }
        }

        // 상어 방향 결정
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= M; i++){
            sharks[i].curDir = Integer.parseInt(st.nextToken());
        }

        // 상어 별, 방향 별 우선순위 결정
        int priDir[][][] = new int[M + 1][5][4];
        for(int i = 1; i <= M; i++){
            for(int j = 1; j <= 4; j++){
                st = new StringTokenizer(br.readLine());
                for(int l = 0; l < 4; l++){
                    priDir[i][j][l] = Integer.parseInt(st.nextToken());
                }
            }
        }

        A:
        for(int time = 1; time <= 1000; time++){
            // 냄새 감소시키기
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    if(smell[i][j] == null) continue;
                    
                    smell[i][j].remainSmell--;
                    
                    // 냄새가 사라졌으면 냄새 배열에서 제거
                    if(smell[i][j].remainSmell < 0){
                        smell[i][j] = null;
                    }
                }
            }

            Queue<Shark> q = new ArrayDeque<>();
            
            // 상어 이동하기
            B:
            for(int i = 1; i <= M; i++){
                if(sharks[i] == null) continue;
                Shark curShark = sharks[i];
                sharks[i] = null;
                int r = curShark.nowR;
                int c = curShark.nowC;

                // 이동 시 우선순위 순서로 탐색
                for(int d = 0; d < 4; d++){
                    int dir = priDir[i][curShark.curDir][d];

                    int nr = r + dr[dir];
                    int nc = c + dc[dir];

                    if( (nr >= N) || (nc>= N) || (nr < 0) || (nc < 0) ) continue;

                    // 빈 공간이 있으면 이동 후 바로 종료
                    if(smell[nr][nc] == null) {
                        q.add(new Shark(curShark.num, nr, nc, dir));
                        continue B;
                    }

                }

                // 빈 공간이 없으면 다시 우선순위 순서로 돌면서 자신의 냄새가 나는 곳으로 이동
//                System.out.println(time);
//                System.out.println(i);
                for(int d = 0; d < 4; d++){
                    int dir = priDir[i][curShark.curDir][d];

                    int nr = r + dr[dir];
                    int nc = c + dc[dir];

                    if( (nr >= N) || (nc>= N) || (nr < 0) || (nc < 0) ) continue;

                    if(smell[nr][nc].sharkNum == curShark.num){
//                        System.out.println("nr: " + nr + " nc: " + nc);
                        q.add(new Shark(curShark.num, nr, nc, dir));
                        break;
                    }
                }
            }
            sharks = new Shark[M + 1];
            while (!q.isEmpty()){
                Shark cur = q.poll();

                if(smell[cur.nowR][cur.nowC] == null || smell[cur.nowR][cur.nowC].remainSmell != k){
                    sharks[cur.num] = cur;
                    smell[cur.nowR][cur.nowC] = new Smell(cur.num, k);
                }
            }

//            System.out.println("t: " + time);
//            for(int i = 0; i < N; i++){
//                for(int j = 0; j < N; j++){
//                    if(smell[i][j] == null) {
//                        System.out.print("0 ");
//                        continue;
//                    }
//
//                    System.out.print(smell[i][j].remainSmell + " ");
//                }
//                System.out.println();
//            }

            // 1번 상어만 남았는지 확인
            for(int i = 2; i <= M; i++){
                if(sharks[i] != null) continue A;
            }
//            System.out.println(Arrays.toString(sharks));
            System.out.println(time);
            return;
        }
        System.out.println(-1);
    }
}