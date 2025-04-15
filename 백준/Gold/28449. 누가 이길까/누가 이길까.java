import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int HI[], ARC[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        HI = new int[N];
        ARC = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            HI[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(HI);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            ARC[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(ARC);

        long winHICnt = 0;
        long winARCCnt = 0;
        long drawCnt = 0;

        for (int i = 0; i < M; i++) {
            int tmp[] = binarySearch(ARC[i]);

            winHICnt += tmp[0];
            winARCCnt += tmp[1];
            drawCnt += tmp[2];

//            System.out.println(Arrays.toString(tmp));
        }

        System.out.println(winHICnt + " " + winARCCnt + " " + drawCnt);
    }

    private static int[] binarySearch(int value) {
        int lose = lowerBound(HI, value); // HI < value
        int draw = upperBound(HI, value) - lose; // HI == value
        int win = N - lose - draw; // HI > value

        return new int[]{win, lose, draw};
    }

    private static int lowerBound(int[] arr, int target) {
        int low = 0, high = arr.length;
        while (low < high) {
            int mid = (low + high) / 2;
            if (arr[mid] < target) low = mid + 1;
            else high = mid;
        }
        return low;
    }

    private static int upperBound(int[] arr, int target) {
        int low = 0, high = arr.length;
        while (low < high) {
            int mid = (low + high) / 2;
            if (arr[mid] <= target) low = mid + 1;
            else high = mid;
        }
        return low;
    }

}