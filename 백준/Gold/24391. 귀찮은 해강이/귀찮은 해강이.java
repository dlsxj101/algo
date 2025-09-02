import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int parents[];

    static class TimeTable implements Comparable<TimeTable> {
        int code;
        int time;

        public TimeTable(int code, int time) {
            this.code = code;
            this.time = time;
        }

        @Override
        public int compareTo(TimeTable o) {
            return Integer.compare(this.time, o.time);
        }
    }

    static void make() {
        parents = new int[N + 1];
        Arrays.fill(parents, -1);
    }

    static int findSet(int a) {
        if (parents[a] < 0) return a;
        return parents[a] = findSet(parents[a]);
    }

    static boolean union(int a, int b) {
        int rootA = findSet(a);
        int rootB = findSet(b);
        if (rootA == rootB) return false;

        parents[rootA] += parents[rootB];
        parents[rootB] = rootA;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        make();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            union(a, b);
        }

        TimeTable tt[] = new TimeTable[N];
        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++){
            int tmp = Integer.parseInt(st.nextToken());
            tt[i] = new TimeTable(i + 1, tmp);
        }
//        Arrays.sort(tt);

//        for(int i = 0; i < N; i++){
//            System.out.println(tt[i].code);
//        }

        int cnt = 0;
        for(int i = 1; i < N; i++){
            if(findSet(tt[i].time) == findSet(tt[i-1].time)) continue;
            cnt++;
        }
        System.out.println(cnt);
    }
}






































