import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
 * 메모리 : kb
 * 시간 : ms
 * 전략
 */
public class Main {
	static class Node implements Comparable<Node>{
		int index;
		int cost;
		public Node(int index, int cost) {
			this.index = index;
			this.cost = cost;
		}
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.cost, o.cost);
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			List<Node> adjList[] = new List[n+1];
			for(int i = 1; i <= n; i++) {
				adjList[i] = new ArrayList<>();
			}
			for(int i = 1; i <= d; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				
				adjList[b].add(new Node(a, s));
			}
			
			int dist[] = new int[n+1];
			Arrays.fill(dist, Integer.MAX_VALUE);
			
			//다익스트라 시작
			PriorityQueue<Node> pq = new PriorityQueue<>();
			pq.add(new Node(c, 0));
			dist[c] = 0;
			
			while(!pq.isEmpty()) {
				Node node = pq.poll();
				int nowVertex = node.index;
				int nowCost = node.cost;
				
				if(dist[nowVertex] < nowCost) continue;
				
				for(Node next : adjList[nowVertex]) {
					if(dist[next.index] > dist[nowVertex] + next.cost) {
						dist[next.index] = dist[nowVertex] + next.cost;
						
						pq.add(new Node(next.index, dist[next.index]));
					}
				}
			}
			int ans = 0;
			int cnt = 0;
			for(int i : dist) {
				if(i == Integer.MAX_VALUE) continue;
				cnt++;
				ans = Math.max(ans, i);
			}
			sb.append(cnt).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}
}