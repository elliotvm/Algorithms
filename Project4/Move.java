public class Move {
    int board[][] = new int[6][6];
    String move;
    
    Move(int[][] b, String c, int n, char d){
        move = c + " " + Integer.toString(n) + " " + d;
    }
}
