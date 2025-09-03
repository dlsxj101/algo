import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Node{
        String number;
        int mod;

        public Node(String number, int mod){
            this.number = number;
            this.mod = mod;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        A:
        for(int tc = 1; tc <= T; tc++){
            int target = Integer.parseInt(br.readLine());

            Queue<Node> q = new ArrayDeque<>();
            boolean visited[] = new boolean[target + 1];

            q.add(new Node("1", 1));
            visited[1] = true;

            int depth = 0;
            while(!q.isEmpty()){
                int size = q.size();

                depth++;
                if(depth > 100) break;
                for(int s = 0; s < size; s++){
                    Node now = q.poll();

                    if(now.mod == 0) {
                        sb.append(now.number).append("\n");
                        continue A;
                    }

                    int next0 = (now.mod * 10) % target;
                    if(!visited[next0]) {
                        q.add(new Node(now.number + "0", next0));
                        visited[next0] = true;
                    }

                    int next1 = ((now.mod * 10) + 1) % target;
                    if(!visited[next1]){
                        q.add(new Node(now.number + "1", next1));
                        visited[next1] = true;
                    }
                }
            }
            sb.append("BARK").append("\n");
        }
        System.out.println(sb);
    }
}