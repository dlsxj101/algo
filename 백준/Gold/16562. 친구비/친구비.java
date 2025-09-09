import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, k;
    static int parents[], friendCost[];

    static void make(){
        friendCost = new int[N + 1];

        parents = new int[N + 1];
        Arrays.fill(parents, -1);

    }

    static int findSet(int a){
        if(parents[a] < 0) return a;
        return parents[a] = findSet(parents[a]);
    }

    static boolean union(int a, int b){
        int rootA = findSet(a);
        int rootB = findSet(b);
        if(rootA == rootB) return false;

        if(friendCost[rootA] <= friendCost[rootB]){
            parents[rootA] += parents[rootB];
            parents[rootB] = rootA;
            return true;
        }
        else{
            parents[rootB] += parents[rootA];
            parents[rootA] = rootB;
            return true;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        make();

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            friendCost[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            union(a, b);
        }

        int ans = 0;
        for(int i = 1; i <= N; i++){
            if(parents[i] >= 0) continue;

            ans += friendCost[i];
        }

        if(ans > k) System.out.println("Oh no");
        else System.out.println(ans);
    }
}