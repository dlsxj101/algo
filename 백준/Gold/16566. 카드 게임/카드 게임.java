import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int parents[];

    static int findSet(int a){
        if(parents[a] == a) return a;
        return parents[a] = findSet(parents[a]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        List<Integer> minsu = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            minsu.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(minsu);

        parents = new int[M+1];
        for(int i = 0; i <= M; i++){
            parents[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        for (int k = 0; k < K; k++) {
            int target = Integer.parseInt(st.nextToken());
            int size = minsu.size();

            int low = 0;
            int high = size - 1;
            int index = -1;
            while (low <= high) {
                int mid = (low + high) / 2;
                int now = minsu.get(mid);

                if(now > target) {
                    index = mid;
                    high = mid - 1;
                }
                else {
                    low = mid + 1;
                }
            }
            int nowAns = findSet(index);
            sb.append(minsu.get(nowAns)).append("\n");
            parents[nowAns] = findSet(nowAns + 1);
        }

        System.out.println(sb);

    }
}