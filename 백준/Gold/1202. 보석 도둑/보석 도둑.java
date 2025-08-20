import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Jewel implements Comparable<Jewel>{
        int weight;
        int price;

        public Jewel(int weight, int price) {
            this.weight = weight;
            this.price = price;
        }

        @Override
        public int compareTo(Jewel o) {
            if(this.weight == o.weight) return o.price - this.price;
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Jewel jewels[] = new Jewel[N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());

            int M = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());

            jewels[i] = new Jewel(M, V);
        }
        Arrays.sort(jewels);

        int bags[] = new int[K];
        for(int i = 0; i < K; i++){
            bags[i] = Integer.parseInt(br.readLine().trim());
        }
        Arrays.sort(bags);

        PriorityQueue<Integer> candidate = new PriorityQueue<>(Collections.reverseOrder());

        long ans = 0;
        int index = 0;

        for(int bag : bags){
            while(true){
                if(index >= N) break;
                if(jewels[index].weight > bag) break;
                candidate.add(jewels[index].price);
                index++;
            }

            if(!candidate.isEmpty()){
                ans += candidate.poll();
            }
        }

        System.out.println(ans);
    }
}