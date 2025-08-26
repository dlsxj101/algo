import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        List<Integer> A = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(A, Collections.reverseOrder());

        ArrayDeque<Integer> q = new ArrayDeque<>(A);

        int t = 0;
        while (true) {
            if (t > T) break;
            if (q.isEmpty()) {
                System.out.println("YES");
                return;
            }
            if(q.size() <= 1) break;

            int now = q.poll();
            int last = q.pollLast();

            if(now == 0) {
                System.out.println("YES");
                return;
            }

            now += last;
            t += last;

            if (now == K) continue;
            if (now > K) {
                q.addLast(now - K);
                t -= now - K;
                continue;
            }
            q.addFirst(now);
        }

        System.out.println("NO");
    }
}