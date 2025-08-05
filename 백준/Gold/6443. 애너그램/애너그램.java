import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, size;
    static char arr[], tmp[];
    static boolean visited[];
    static Set<String> set;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        N = Integer.parseInt(br.readLine().trim());
        for (int i = 0; i < N; i++) {
            set = new HashSet<>();
            String input = br.readLine().trim();

            arr = input.toCharArray();
            Arrays.sort(arr);

            size = arr.length;

            tmp = new char[size];
            visited = new boolean[size];
            dfs(0);

        }

        System.out.println(sb.toString());
    }

    static void dfs(int cnt) {
        if (cnt >= size) {
            
            // 조합된 단어들 문자열로 합치기
            StringBuilder now = new StringBuilder();
            for (char c : tmp) {
                now.append(c);
            }

            // 이미 포함된 경우
            if (set.contains(now.toString())) return;

            // 포함되지 않을 경우
            sb.append(now).append("\n");
            return;
        }

        for (int i = 0; i < size; i++) {
            if (visited[i]) continue;

            if (i > 0 && arr[i] == arr[i - 1] && !visited[i - 1]) continue;

            tmp[cnt] = arr[i];

            visited[i] = true;
            dfs(cnt + 1);
            visited[i] = false;
        }
    }
}