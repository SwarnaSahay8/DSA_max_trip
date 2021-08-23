package assignment;

import java.util.ArrayList;
import java.util.HashMap;

public class App {

    public static void main(String[] args) {
//        Scanner scanner =new Scanner(System.in);
//        int n = scanner.nextInt();

        /*
        Question : Given pair of trips from source to destination.For each trip, it can be done only once. Find the maximum no. of trips done.
         */

        char[][] graph = new char[][]{
                {'h', 'k'},
                {'d', 'h'},
                {'h', 'a'},
                {'j', 'd'},
                {'j', 'r'},
                {'m', 'd'},
                {'k', 'm'},
                {'x', 'z'},
                {'y', 'z'}
        };

        HashMap<Character, ArrayList<Character>> map = new HashMap<>();
        HashMap<String, Boolean> visited = new HashMap<>();
        for (int i = 0; i < graph.length; i++) {
            char key = graph[i][0], val = graph[i][1];
            if (map.get(key) == null) {
                ArrayList<Character> tmp2 = new ArrayList<>();
                tmp2.add(val);
                map.put(key, tmp2);
            } else {
                ArrayList<Character> tmp = map.get(key);
                tmp.add(val);
            }

            if (!map.containsKey(val)) {
                map.put(val, new ArrayList<>());
            }
            String tempString = Character.toString(key) + val;
            visited.put(tempString, false);
        }
        int maxPath = 0;
        for (char ch : map.keySet()) {
            maxPath = Math.max(processUtil(ch, map, visited), maxPath);
        }
        System.out.println(maxPath);
    }

    public static int processUtil(char start, HashMap<Character, ArrayList<Character>> map, HashMap<String, Boolean> visited) {
        ArrayList<Character> list = map.get(start);
        int path = 0;
        boolean inside = false;
        for (char ch : list) {
            String temp = Character.toString(start) + ch;
            if (visited.get(temp) == false) {
                inside = true;
                visited.put(temp, true);
                path = Math.max(path, processUtil(ch, map, visited));
                visited.put(temp, false);
            }
        }
        return inside ? path + 1 : path;
    }
}
