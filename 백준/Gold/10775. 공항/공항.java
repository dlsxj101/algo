import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int G, P;
    static int parents[];

    static void make(){
        parents = new int[G+1];
        for(int i = 1; i <= G; i++){
            parents[i] = i;
        }
    }

    static int findSet(int a){
        if(parents[a] == a) return a;
        return parents[a] = findSet(parents[a]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        G = Integer.parseInt(br.readLine().trim());
        P = Integer.parseInt(br.readLine().trim());

        make();

        int answer = 0;

        for(int i = 0; i < P; i++){
            int now = Integer.parseInt(br.readLine().trim());

            if(findSet(now) == 0){
                System.out.println(answer);
                return;
            }

            answer++;
            parents[findSet(now)] = parents[findSet(now) - 1];

        }
        System.out.println(answer);
    }
}