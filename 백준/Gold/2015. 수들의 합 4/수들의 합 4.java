import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long K = Long.parseLong(st.nextToken());

        long sum[] = new long[N+1];
        sum[0] = 0;

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            sum[i] = sum[i-1] + Long.parseLong(st.nextToken());
        }
//        System.out.println(Arrays.toString(sum));

        Map<Long, Integer> map = new HashMap<>();
        map.put(sum[0], 1);
        long cnt = 0;
        for(int i = 1; i <= N; i++){
            long target = sum[i] - K;
            if(map.containsKey(target)){
                cnt += map.get(target);
            }

            if(map.containsKey(sum[i])){
                map.replace(sum[i], map.get(sum[i]) + 1);
            }else{
                map.put(sum[i], 1);
            }
        }

        System.out.println(cnt);
    }
}






































