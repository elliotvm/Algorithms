import java.util.*;

public class Project_4 {

    public static void main(String[] args) {
        int board[][] = new int[6][6];
        Queue<int[][]> Q = new LinkedList<>();
        HashMap<String, int[][]> HM = new HashMap<>();
        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        
        for(int i=0; i < n; i++){
            String type = in.nextLine();
            String color = in.nextLine();
            String orientation = in.nextLine();
            
            vehicles.add(new Vehicle(type, color, orientation));
            
           int coordY = Integer.parseInt(in.nextLine()) - 1;
            int coordX = Integer.parseInt(in.nextLine()) - 1;
            
            if("truck".equals(type)){
                if("h".equals(orientation)){
                    board[coordY][coordX] = i + 1;
                    board[coordY][coordX + 1] = i + 1;
                    board[coordY][coordX + 2] = i + 1;
                }else if("v".equals(orientation)){
                    board[coordY][coordX] = i + 1;
                    board[coordY + 1][coordX] = i + 1;
                    board[coordY + 2][coordX] = i + 1;
                }
            }
            else if("car".equals(type)){
                if("h".equals(orientation)){
                    board[coordY][coordX] = i + 1;
                    board[coordY][coordX + 1] = i + 1;
                }else if("v".equals(orientation)){
                    board[coordY][coordX] = i + 1;
                    board[coordY + 1][coordX] = i + 1;
                }
            }
        }
        
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++){
                System.out.print(board[i][j]);
            }
            System.out.print("\n");
        }
        
        
        Q.add(board);
        while(!Q.isEmpty()) {
            int[][] currBoard = Q.remove();
            // Get all legal moves. 
            Queue<int[][]> adjBoards = getAdj(currBoard, vehicles);
            while (!adjBoards.isEmpty()) {
                int[][] currAdj = adjBoards.remove();
                // Check if this board solves the puzzle. 
                
                // Generate strings from boards. 
                String strAdj = boardToStr(currAdj);
                // Put strings in hashmap if they aren't already there. 
                if (!HM.containsKey(strAdj)) {
                    HM.put(strAdj, currAdj);
                    // Enqueue new position. 
                    Q.add(currAdj);
                }
            }
        }
    }
    
    public static Queue<int[][]> getAdj(int[][] board, List<Vehicle> vehicles) {
        Queue<int[][]> adjBoards = new LinkedList<>();
        for (int i = 0; i < vehicles.size(); i++) {
            
        }
        
        return adjBoards;
    }
    
    public static String boardToStr(int[][] board) {
        String str = "";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                str = str.concat("" + board[i][j]);
            }
        }
        return str;
    }
}
