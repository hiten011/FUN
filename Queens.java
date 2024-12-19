import java.util.Scanner;

public class Queens {
    public static void main(String args[]) {
        System.out.println("Queens Solver!\n");
        Scanner sc = new Scanner(System.in);

        // entering the length of game board
        System.out.print("Enter the length of board: ");
        int n = sc.nextInt();
        System.out.println();

        // variables
        int[][] board = new int[n][n];
        int[][] ans = new int[n][n];
        int[] color_mask = new int[n];

        System.out.println("Enter the Game board: ");
        // taking input from user
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                board[i][j] = sc.nextInt();
            }
        }

        // // print the board - for confirmation
        // System.out.println();
        // print(board, n);

        // calling the function
        queens(board, ans, color_mask, 0, n);

        // print the ans board
        System.out.println();
        System.out.println("Ans: ");
        print(ans, n);
        
    }

    static boolean queens(int[][] board, int[][] ans, int[] color_mask, int row, int n){

        // base class
        if (row == n){
            return true;
        }

        // work
        for (int i = 0; i < n; i++){

            if (is_place(board, ans, color_mask, row, i, n)){
                ans[row][i] = 1; // update value
                color_mask[board[row][i]] = 1;

                // if found a solution
                if (queens(board, ans, color_mask, row + 1, n)){
                    return true;
                }

                ans[row][i] = 0; // backtrack
                color_mask[board[row][i]] = 0;
            }
        }

        return false;
    }

    static boolean is_place(int[][] board, int[][] ans, int[] color_mask, int row, int col, int n){

        // check column
        for(int i = 0; i < n; i++){

            if (ans[i][col] == 1){
                return false;
            }
        }

        // check diagonals - 1 block above
        if (row - 1 > -1 && col - 1 > -1 && ans[row - 1][col - 1] == 1){
            return false;
        }

        if (row - 1 > -1 && col + 1 < n && ans[row - 1][col + 1] == 1){
            return false;
        }

        // check color blocks
        if (color_mask[board[row][col]] == 1){
            return false;
        }


        return true; // default value
    }

    static void print(int[][] array, int n){

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                System.out.print(array[i][j] + " ");
            }

            System.out.println();
        }
    }
}
