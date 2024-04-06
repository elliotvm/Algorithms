package elliot.project_4;

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
                if(isWinner(currAdj)){
                    //run Win subroutine
                    System.out.println("winner");
                }
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

    public static boolean isWinner(int[][] b){
        return b[2][5] == 1;
    }
    
    public static Queue<int[][]> getAdj(int[][] board, List<Vehicle> vehicles) {
        Queue<int[][]> adjBoards = new LinkedList<>();
        for (int v = 1; v <= vehicles.size(); v++) {
            Vehicle currV = vehicles.get(v - 1);
            int x = -1;
            int y = -1;
            for (int j = 0; j < board.length; j++) {
                for (int i = 0; i < board.length; i++) {
                    if (x == -1 && board[j][i] == v) {
                        x = i;
                        y = j;
                    }
                }
            }
            if (currV.orientation.equals("h")) {
                if (currV.type.equals("car")) {
                    // Move car left.
                    for (int n = 1; x - n >= 0 && board[y][x-n] == 0; n++) {
                        int[][] newBoard = board.clone();
                        newBoard[y][x] = 0;
                        newBoard[y][x+1] = 0;
                        newBoard[y][x-n] = v;
                        newBoard[y][x-n+1] = v;
                        adjBoards.add(newBoard);
                  
                    }
                    // Move car right.
                    for (int n = 1; x + 1 + n < 6 && board[y][x+1+n] == 0; n++) {
                        int[][] newBoard = board.clone();
                        newBoard[y][x] = 0;
                        newBoard[y][x+1] = 0;
                        newBoard[y][x+n] = v;
                        newBoard[y][x+n+1] = v;
                        adjBoards.add(newBoard);
                    }
                }
                else if (currV.type.equals("truck")) {
                    // Move truck left.
                    for (int n = 1; x - n >= 0 && board[y][x-n] == 0; n++) {
                        int[][] newBoard = board.clone();
                        newBoard[y][x] = 0;
                        newBoard[y][x+1] = 0;
                        newBoard[y][x+2] = 0;
                        newBoard[y][x-n] = v;
                        newBoard[y][x-n+1] = v;
                        newBoard[y][x-n+2] = v;
                        adjBoards.add(newBoard);
                    }
                    // Move truck right.
                    for (int n = 1; x + 2 + n < 6 && board[y][x+2+n] == 0; n++) {
                        int[][] newBoard = board.clone();
                        newBoard[y][x] = 0;
                        newBoard[y][x+1] = 0;
                        newBoard[y][x+2] = 0;
                        newBoard[y][x+n] = v;
                        newBoard[y][x+n+1] = v;
                        newBoard[y][x+n+2] = v;
                        adjBoards.add(newBoard);
                    }
                }
            }
            if (currV.orientation.equals("v")) {
                if (currV.type.equals("car")) {
                    // Move car up.
                    for (int n = 1; y - n >= 0 && board[y - n][x] == 0; n++) {
                        int[][] newBoard = board.clone();
                        newBoard[y][x] = 0;
                        newBoard[y+1][x] = 0;
                        newBoard[y-n][x] = v;
                        newBoard[y-n+1][x] = v;
                        adjBoards.add(newBoard);
                    }
                    // Move car down.
                    for (int n = 1; y + 1 + n < 6 && board[y + 1 + n][x] == 0; n++) {
                        int[][] newBoard = board.clone();
                        newBoard[y][x] = 0;
                        newBoard[y+1][x] = 0;
                        newBoard[y+n][x] = v;
                        newBoard[y+n+1][x] = v;
                        adjBoards.add(newBoard);
                    }
                } else if (currV.type.equals("truck")) {
                    // Move truck up.
                    for (int n = 1; y - n >= 0 && board[y - n][x] == 0; n++) {
                        int[][] newBoard = board.clone();
                        newBoard[y][x] = 0;
                        newBoard[y+1][x] = 0;
                        newBoard[y+2][x] = 0;
                        newBoard[y-n][x] = v;
                        newBoard[y-n+1][x] = v;
                        newBoard[y-n+2][x] = v;
                        adjBoards.add(newBoard);
                    }
                    // Move truck down.
                    for (int n = 1; y + 2 + n < 6 && board[y + 2 + n][x] == 0; n++) {
                        int[][] newBoard = board.clone();
                        newBoard[y][x] = 0;
                        newBoard[y+1][x] = 0;
                        newBoard[y+2][x] = 0;
                        newBoard[y+n][x] = v;
                        newBoard[y+n+1][x] = v;
                        newBoard[y+n+2][x] = v;
                        adjBoards.add(newBoard);
                    }
                }
            }
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
