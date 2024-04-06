package elliot.project_4;

public class Move {
    int board[][] = new int[6][6];
    String move;
    
    Move(int[][] b, String c, int n, char d){
        board = b;
        move = c + " " + Integer.toString(n) + " " + d;
    }
    Move(int[][] b){
        board = b;
    }
}
