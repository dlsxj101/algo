import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine().trim());

        int arr[][] = new int[N][2];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());

            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, (o1, o2) -> o1[1] - o2[1]);

        int ans = -1;
        A :
        for(int i = 0; i <= 1_000_000; i++){
            int finishTime = i;
            for(int j = 0; j < N; j++){
                finishTime += arr[j][0];
                if(finishTime > arr[j][1]) continue A;
            }
            ans = i;
        }
        System.out.println(ans);
    }
}