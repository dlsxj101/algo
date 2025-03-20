import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, ans;
    static List<Integer> students[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        boolean problem[] = new boolean[N + 1];
        students = new List[M];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            students[i] = new ArrayList<>();

            int tmp = Integer.parseInt(st.nextToken());
            for (int j = 0; j < tmp; j++) {
                students[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        ans = Integer.MAX_VALUE;
        dfs(0, 0, problem);

        if(ans == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(ans);
    }

    private static void dfs(int cnt, int memberCnt, boolean problem[]) {
        if (cnt >= M) {
            boolean allSolved = true;
            for (int i = 1; i <= N; i++) {
                if (!problem[i]) {
                    allSolved = false;
                    break;
                }
            }
            if (allSolved) {
//                System.out.println(memberCnt);
                ans = Math.min(ans, memberCnt);
            }
            return;
        }

        // 모든 문제를 해결했는지 체크
        boolean allSolved = true;
        for (int i = 1; i <= N; i++) {
            if (!problem[i]) {
                allSolved = false;
                break;
            }
        }

        if (allSolved) {
            ans = Math.min(ans, memberCnt);
            return;
        }

        // 학생을 선택하지 않는 경우
        dfs(cnt + 1, memberCnt, problem);

        // 학생을 선택하는 경우: problem 배열을 복사한 후 수정
        boolean[] newProblem = problem.clone();
        for (int tmp : students[cnt]) {
            newProblem[tmp] = true;
        }
        dfs(cnt + 1, memberCnt + 1, newProblem);
    }

}