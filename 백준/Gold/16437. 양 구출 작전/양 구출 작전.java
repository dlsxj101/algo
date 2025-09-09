import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static long ans;
    static long cnt[];
    static List<Integer> adjList[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine().trim());

        cnt = new long[N + 1];

        adjList = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 2; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            String animalName = st.nextToken();
            int animalCnt = Integer.parseInt(st.nextToken());
            int connect = Integer.parseInt(st.nextToken());

            adjList[connect].add(i);

            if (animalName.equals("W")) {
                cnt[i] = -animalCnt;
            } else {
                cnt[i] = animalCnt;
            }
        }

        ans = dfs(1);

        System.out.println(ans);
    }

    static long dfs(int now) {
        long sum = 0;
        for(int next : adjList[now]){
            sum += dfs(next);
        }

        if(cnt[now] >= 0){  // 현재 노드가 양인 경우
            return sum + cnt[now];
        } else {            // 현재 노드가 늑대인 경우
            long remain = sum + cnt[now];
            if(remain > 0){
                return remain;
            } else return 0;
        }

    }
}