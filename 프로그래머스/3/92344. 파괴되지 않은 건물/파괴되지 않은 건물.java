import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[][] board, int[][] skill) {
        int N = board.length;
        int M = board[0].length;
        int answer = 0;
        
        int newBoard[][] = new int[N+1][M+1];
        
        for(int s = 0; s < skill.length; s++){
            int type = skill[s][0];
            int sign = (type == 1) ? -1 : 1;
            int r1 = skill[s][1];
            int c1 = skill[s][2];
            int r2 = skill[s][3];
            int c2 = skill[s][4];
            int degree = skill[s][5] * sign;
            
            newBoard[r1][c1] += degree;
            newBoard[r1][c2 + 1] += (-1) * degree;
            newBoard[r2+1][c1] += (-1) * degree;
            newBoard[r2+1][c2 + 1] += degree;
        }
        
        for(int i = 0; i < N; i++){
            for(int j = 1; j < M; j++){
                newBoard[i][j] += newBoard[i][j-1];
            }
        }
        
        for(int j = 0; j < M; j++){
            for(int i = 1; i < N; i++){
                newBoard[i][j] += newBoard[i-1][j];
            }
        }
        
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                board[i][j] += newBoard[i][j];
                if(board[i][j] > 0) answer++;
            }
        }
        
        return answer;
    }
}