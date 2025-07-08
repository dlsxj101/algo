import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, M, ans;
    static List<Integer> adjList[];
    static HashSet<Integer> origin;
    static boolean visited[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        origin = new HashSet<>();    // 마약 원산지(탐색의 시작점)

        visited = new boolean[N];

        adjList = new List[N];
        for (int i = 0; i < N; i++) {
            adjList[i] = new ArrayList<>();
        }

        // 마약 공급망 연결
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int first =  st.nextToken().charAt(0) - 'A';
            int second = st.nextToken().charAt(0) - 'A';

            adjList[first].add(second);
            visited[second] = true; // 마약 원산지 확인용
        }

        // 마약 원산지 확인하기
        for(int i = 0; i < N; i++){
            if(visited[i]) continue;

            origin.add(i); // 마약 원산지 확정
        }

        visited = new boolean[N];   // 초기화(방문배열로 사용하기 위해)

        st = new StringTokenizer(br.readLine());
        int cnt = Integer.parseInt(st.nextToken());

        // 검거한 곳은 마약 공급이 중단되므로 차단시키기
        for(int i = 0; i < cnt; i++){
            int temp = st.nextToken().charAt(0) - 'A';
            visited[temp] = true;
        }

        // 여전히 마약을 공급 받는 마약 공급책 수 세기 시작
        ans = 0;
        for(int start : origin){
            if(visited[start]) continue;
            dfs(start);
        }

        System.out.println(ans);
    }

    static void dfs(int now){
        visited[now] = true;

        if(!origin.contains(now)) ans++;

        for(int next : adjList[now]){
            if(visited[next]) continue;

            dfs(next);
        }
    }
}
