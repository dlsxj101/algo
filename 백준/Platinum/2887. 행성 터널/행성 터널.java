import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int parents[];

	static void make() {
		parents = new int[N];
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

	static class Planet {
		int index;
		int x;
		int y;
		int z;

		public Planet(int index, int x, int y, int z) {
			this.index = index;
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}

	static class Edge implements Comparable<Edge> {
		int start;
		int end;
		int cost;

		public Edge(int start, int end, int cost) {
			this.start = start;
			this.end = end;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.cost, o.cost);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine().trim());
		make();

		Planet planetsX[] = new Planet[N];
		Planet planetsY[] = new Planet[N];
		Planet planetsZ[] = new Planet[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());

			planetsX[i] = new Planet(i, x, y, z);
			planetsY[i] = new Planet(i, x, y, z);
			planetsZ[i] = new Planet(i, x, y, z);
		}

		Arrays.sort(planetsX, (o1, o2) -> o1.x - o2.x);
		Arrays.sort(planetsY, (o1, o2) -> o1.y - o2.y);
		Arrays.sort(planetsZ, (o1, o2) -> o1.z - o2.z);

		PriorityQueue<Edge> pq = new PriorityQueue<>();

		for(int i = 0; i < N-1; i++) {
			Planet start = planetsX[i];
			Planet end = planetsX[i+1];

			int cost = Integer.MAX_VALUE;
			cost = Math.min(cost, Math.abs(start.x-end.x));
			cost = Math.min(cost, Math.abs(start.y-end.y));
			cost = Math.min(cost, Math.abs(start.z-end.z));

			pq.add(new Edge(start.index, end.index, cost));
		}

		for(int i = 0; i < N-1; i++) {
			Planet start = planetsY[i];
			Planet end = planetsY[i+1];

			int cost = Integer.MAX_VALUE;
			cost = Math.min(cost, Math.abs(start.x-end.x));
			cost = Math.min(cost, Math.abs(start.y-end.y));
			cost = Math.min(cost, Math.abs(start.z-end.z));

			pq.add(new Edge(start.index, end.index, cost));
		}

		for(int i = 0; i < N-1; i++) {
			Planet start = planetsZ[i];
			Planet end = planetsZ[i+1];

			int cost = Integer.MAX_VALUE;
			cost = Math.min(cost, Math.abs(start.x-end.x));
			cost = Math.min(cost, Math.abs(start.y-end.y));
			cost = Math.min(cost, Math.abs(start.z-end.z));

			pq.add(new Edge(start.index, end.index, cost));
		}

		int ans = 0;
		int cnt = 0;
		while(!pq.isEmpty()) {
			Edge e = pq.poll();
			if(union(e.start, e.end)) {
				//				System.out.println("start:"+e.start+"  end:"+e.end+"  cost:"+e.cost);
				ans += e.cost;

				cnt++;
				if(cnt >= N - 1) break;
			}
		}

		//		System.out.println(Arrays.toString(parents));
		System.out.println(ans);
	}
}