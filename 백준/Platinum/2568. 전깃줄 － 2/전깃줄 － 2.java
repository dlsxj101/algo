import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int arr[], indexArr[];
	static List<Integer> lis;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine().trim());
		
		arr = new int[500001];
		indexArr = new int[500001];
		Arrays.fill(indexArr, -1);
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int A = Integer.parseInt(st.nextToken())-1;
			int B = Integer.parseInt(st.nextToken());
			
			arr[A] = B;
		}
		
		lis = new ArrayList<>();
		int start = 0;
		for(int i = 0; i < 500001; i++) {
			if(arr[i] == 0) continue;
			
			lis.add(arr[i]);
			start = i;
			break;
		}
		indexArr[0] = lis.size()-1;
		
		for(int i = start; i < 500001; i++) {
			if(arr[i] == 0) continue;
			int tmp = arr[i];
			
			if(lis.get(lis.size()-1) < tmp) {
				lis.add(tmp);
				indexArr[i] = lis.size()-1;
			}
			else {
				int index = binarySearch(tmp);
				
				lis.set(index, tmp);
				indexArr[i] = index;
			}
		}
//		System.out.println(Arrays.toString(indexArr));
		sb.append(N - lis.size()).append("\n");
		
		//역추적하여 LIS 복원
		List<Integer> ans = new ArrayList<>();
		int index = lis.size()-1;
		
		for(int i = 500000; i >= 0; i--) {
			if(indexArr[i] == -1) continue;
			if(indexArr[i] == index) {
				index--;
				ans.add(arr[i]);
			}
		}
		
//		for(int i = 0; i < ans.size(); i++) {
//			System.out.print(ans.get(i)+" ");
//		}
//		System.out.println();
		
		A : for(int i = 0; i < 500001; i++) {
			if(arr[i] == 0) continue;
			for(int j : ans) {
				if(arr[i] == j) continue A;
			}
			
			sb.append(i+1).append("\n");
		}
		
		System.out.println(sb);
	}

	static int binarySearch(int target) {
		int index = 0;
		
		int low = 0;
		int high = lis.size()-1;
		
		while(low <= high) {
			int mid = (low+high) / 2;
			
			if(lis.get(mid) < target) {
				low = mid + 1;
			}
			else {
				index = mid;
				high = mid - 1;
			}
		}
		return index;
	}
}