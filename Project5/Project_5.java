import java.util.*;

public class Project_5 {

    public static void setIntersection(int x, int y, double d, double[][] matrix){
        matrix[x][y] = d;
        matrix[y][x] = d;
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        List<City> cities = new ArrayList<>();
        List<Sign> signs = new ArrayList<>();
        
        int num_intersections = in.nextInt();
        int num_roads = in.nextInt();
        int num_cities = in.nextInt();
        in.nextLine();
        //set matrix of intersections
        double matrix[][] = new double[num_intersections][num_intersections];
        
        //set roads between intersections
        for(int i = 0; i < num_roads; i++){
            int x = in.nextInt();
            int y = in.nextInt();
            double d = in.nextDouble();
            setIntersection(x, y, d, matrix);
        }
        
        //print matrix for debugging:
        for(int i = 0; i < num_intersections; i++){

            for(int j = 0; j < num_intersections; j++){
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.print("\n");
        }
        
        for(int i = 0; i < num_cities; i++){
            int num = in.nextInt();
            String name = in.next();
            City c = new City(num, name);
            cities.add(c);
        }
        
        int num_signs = in.nextInt();
        
        for(int i = 0; i < num_signs; i++){
            int s = in.nextInt();
            int e = in.nextInt();
            double d = in.nextDouble();
            Sign sign = new Sign(s, e, d);
            signs.add(sign);
        }
        
                
        //print cities for debugging
        for(City c : cities){
            System.out.println(c.getName() + " " + c.getIntersection());
        }
        
        //print out signs for debugging
        for(Sign s : signs){
            System.out.println(s.start + " " + s.end + " " + s.distance);
        }

        int n = matrix.length;

        double[][] best = matrix;
        int pred[][] = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                pred[i][j] = i;
            }
        }
        
        // Floyd-Warshall Algorithm
        for (int k = 0; k < n; k++) {
            for (int u = 0; u < n; u++) {
                for (int v = 0; v < n; v++) {
                    if ((best[u][k] + best[k][v]) < best[u][v]) {
                        best[u][v] = best[u][k] + best[k][v];
                        pred[u][v] = pred[k][v];
                    }
                }
            }
        }
        
       
        int predecessor;
        for (Sign s : signs) {
            SortedMap<Integer, String> map = new TreeMap<>();
            for (City c : cities) {
                boolean done = false;
                boolean roadUsed = false;
                predecessor = c.intersection;
                while (!done) {
                    predecessor = pred[s.start][predecessor];
                    if (predecessor == s.start) {
                        done = true;
                    }
                    if (predecessor == s.end) {
                        roadUsed = true;
                    }
                }
                if (roadUsed) {
                    s.addCity(c);
                }
            }
            
            for (City c : s.cities) {
                int distance = (int) Math.round(matrix[s.start][c.intersection] - s.distance);
                map.put(distance, c.name);
            }
            
            for (Integer key : map.keySet()) {
                System.out.println(map.get(key) + " " + key);
            }
            
        }
        
    }
}
