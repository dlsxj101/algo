import java.util.*;

class Solution {
    
    static int N, M, result;
    static String tmp[], userId[], bannedId[];
    static boolean visited[];
    static List<HashSet<String>> check;
    
    public int solution(String[] user_id, String[] banned_id) {
        N = user_id.length;
        M = banned_id.length;
        
        userId = user_id;
        bannedId = banned_id;
        
        visited = new boolean[N];
        tmp = new String[N];
        check = new ArrayList<>();
        
        result = 0;
        
        dfs(0);
        
        return check.size();
    }
    
    static void dfs(int cnt){
        if(cnt == M){
            if(check.size() == 0){
                HashSet<String> tmpSet = new HashSet<>();
                
                for(String tmpStr : tmp){
                    tmpSet.add(tmpStr);
                }
                
                check.add(tmpSet);
                return;
            }
            
            B :
            for(HashSet temp : check){
                for(String tmpStr : tmp){
                    if(temp.contains(tmpStr)) continue;
                    
                    continue B;
                }
                return;
            }
            
            HashSet<String> set = new HashSet<>();
                
            for(String tmpStr : tmp){
                set.add(tmpStr);
            }
            check.add(set);
            return;
        }
        
        A :
        for(int i = 0; i < N; i++){
            if(visited[i]) continue;
            
            // 임시 배열의 아이디 길이와 불량 사용자의 아이디 길이가 다를 경우 넘어가기
            if(userId[i].length() != bannedId[cnt].length()) continue;
                
            // 임시 배열 검증
            for(int j = 0; j < userId[i].length(); j++){
                // 불량 사용자 아이디의 * 부분은 그냥 건너뛰기
                if(bannedId[cnt].charAt(j) == '*') continue;

                // 임시 배열 아이디와 불량 사용자 아이디가 다를 경우 다음 아이디로 넘어가기
                if(userId[i].charAt(j) != bannedId[cnt].charAt(j)) continue A;
            }
            
            tmp[cnt] = userId[i];
            visited[i] = true;
            dfs(cnt+1);
            visited[i] = false;
        }
    }
}