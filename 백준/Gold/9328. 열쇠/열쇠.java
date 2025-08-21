import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int dr[] = {-1, 1, 0, 0};
    static int dc[] = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());

            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            char map[][] = new char[h][w];
            for (int i = 0; i < h; i++) {
                String input = br.readLine().trim();
                for (int j = 0; j < w; j++) {
                    map[i] = input.toCharArray();
                }
            }

            // 열쇠 등록
            Set<Character> keys = new HashSet<>();
            String keyTmp = br.readLine();
            int keyCnt = keyTmp.length();
            if (!keyTmp.equals("0")) {
                for (int i = 0; i < keyCnt; i++) {
                    keys.add(keyTmp.charAt(i));
                }
            }

            int ans = 0;
            for (int roof = 0; roof < 40; roof++) {
                boolean visited[][] = new boolean[h][w];
                Queue<int[]> q = new ArrayDeque<>();


                // 테두리에서 시작
                for (int i = 0; i < h; i++) {
                    for (int j = 0; j < w; j++) {
                        if (i == 0 || i == h - 1 || j == 0 || j == w - 1) {
                            if (map[i][j] == '*') continue;

                            if (map[i][j] == '$') {
                                ans++;
                                map[i][j] = '.';
                            }
                            else if(map[i][j] == '.'){}

                            // 대문자라면
                            else if(map[i][j] - 'a' < 0){
                                if(keys.contains(Character.toLowerCase(map[i][j]))){
                                    map[i][j] = '.';
                                }
                                continue;
                            }
                            // 소문자라면
                            else {
                                keys.add(map[i][j]);
                                map[i][j] = '.';
                            }

                            visited[i][j] = true;
                            q.add(new int[]{i, j});
                        }
                    }
                }

                while (!q.isEmpty()) {
                    int tmp[] = q.poll();

                    int r = tmp[0];
                    int c = tmp[1];

                    for (int d = 0; d < 4; d++) {
                        int nr = r + dr[d];
                        int nc = c + dc[d];

                        if ((nr >= h) || (nc >= w) || (nr < 0) || (nc < 0)) continue;
                        if (map[nr][nc] == '*') continue;
                        if (visited[nr][nc]) continue;

                        if (map[nr][nc] == '$') {
                            ans++;
                            map[nr][nc] = '.';

                        }
                        else if(map[nr][nc] == '.'){}

                        // 대문자라면
                        else if(map[nr][nc] - 'a' < 0){
                            if(keys.contains(Character.toLowerCase(map[nr][nc]))){
                                map[nr][nc] = '.';
                            }
                            continue;
                        }
                        // 소문자라면
                        else {
                            keys.add(map[nr][nc]);
                            map[nr][nc] = '.';
                        }

                        visited[nr][nc] = true;
                        q.add(new int[]{nr, nc});
                    }
                }
            }

            sb.append(ans).append("\n");


//            System.out.println();
//            // map 확인용
//            for(int i = 0; i < h; i++){
//                System.out.println();
//                for(int j = 0; j < w; j++){
//                    System.out.print(map[i][j] + " ");
//                }
//            }
        }

        System.out.println(sb);
    }
}