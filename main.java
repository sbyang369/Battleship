import java.util.Scanner;
public class Battleship {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int [][] input1 = new int[5][2];
        int [][] input2 = new int[5][2];
        char [][] board1 = new char[5][5];
        char [][] board2 = new char[5][5];
        char [][] history1 = new char[5][5];
        char [][] history2 = new char[5][5];

        boolean running = true;

        System.out.println("Welcome to Battleship!");

        // create p1 board
        getUserInput(input, input1, "Player 1");
        createBoard(board1,input1);
        printBattleShip(board1);

        // create p2 board
        getUserInput(input, input2, "Player 2");
        createBoard(board2,input2);
        printBattleShip(board2);

        //history boards
        createHistoryBoards(history1,history2);

        space(5);

        // Hit sequence
        do {
            //player 1's turn
            int targetRow2;
            int targetCol2;
            System.out.println("Player 1, hit: ");
            input.nextLine();
            String[] parts = input.nextLine().split(" ")
            targetRow2 = Integer.parseInt(parts[0]);
            targetCol2 = Integer.parseInt(parts[1]);

            if (targetRow2 < 0 || targetRow2 > 4 || targetCol2 < 0 || targetCol2 > 4) {
                System.out.println("Invalid coordinates. Choose different coordinates,");
                continue;
            }

            turn("Player 1", "Player 2", board2, history1, targetRow2, targetCol2);

            // player 2's turn
            int targetRow1;
            int targetCol1;
            System.out.println("Player 2, hit: ");
            targetRow1 = input.nextInt();
            targetCol1 = input.nextInt();

            if (targetRow1 < 0 || targetRow1 > 4 || targetCol1 < 0 || targetCol1 > 4) {
                System.out.println("Invalid coordinates. Choose different coordinates,");
                continue;
            }

            turn("Player 2", "Player 1", board1, history2, targetRow1, targetCol1);

            //checker
            int count1 = check(board1,input1, "Player 1");
            if (count1 >= 5) {
                System.out.println("PLAYER 2 WINS! YOU SUNK ALL OF YOUR OPPONENT'S SHIPS!");
                running = false;
            }

            int count2= check(board2,input2,"Player 2");

            if (count2 >= 5) {
                System.out.println("PLAYER 1 WINS! YOU SUNK ALL OF YOUR OPPONENT'S SHIPS!");
                running = false;
            }

        } while (running == true);

    }

    public static void createBoard(char[][] board,int[][] ships) {
        for (int row=0;row<5;row++) {
            for (int col=0;col<5;col++) {
                board[row][col] = '-';
            }
        }

        for (int i=0; i < ships.length; i++) {
            board[ships[i][0]][ships[i][1]] = '@';
        }
    }

    public static void getUserInput(Scanner input, int[][] ships, String playerName) {
        for (int i=0;i<5;i++) {
            boolean valid = false;
            while (!valid) {
                System.out.printf("%s | Coordinate #%d:  ",playerName, i+1);
                String line = input.nextLine();
                String[] parts = line.split(" ");

                if (parts.length != 2) {
                    System.out.println("Invalid coordinates. Choose different coordinates0");
                    continue;
                }

                try {
                    int row = Integer.parseInt(parts[0]);
                    int col = Integer.parseInt(parts[1]);

                    if (row < 0 || row > 4 || col < 0 || col > 4) {
                        System.out.println("Invalid coordinates. Choose different coordinates,");
                        continue;
                    }

                    boolean duplicate = false;
                    for (int x=0;x<i;x++) {
                        if (row == ships[x][0]) {
                            if (col == ships[x][1]) {
                                duplicate = true;
                                break;
                            }
                        }
                    }

                    if (duplicate) {
                        System.out.println("You already have a ship there. Choose different coordinates.");
                        continue;
                    }

                    ships[i][0] = row;
                    ships[i][1] = col;
                    valid = true;

                } catch (ArithmeticException e) {
                    System.out.println("Invalid coordinates. Choose diffferent coordinates.");
                }
            }
        }
    }

    public static void createHistoryBoards(char[][] history1,char[][] history2) {
        for (int row=0;row<5;row++) {
            for (int col=0;col<5;col++) {
                history2[row][col] = '-';
            }
        }

        for (int row=0;row<5;row++) {
            for (int col=0;col<5;col++) {
                history1[row][col] = '-';
            }
        }

    }

    public static void space(int num) {
        for (int count =0;count<num;count++) {
            System.out.println();
        }
    }

    public static void turn(String player, String opp, char[][] board, char[][] history, int targetRow, int targetCol) {

        // check if board has an 'O' / already hit. then update history + location
        if (board[targetRow][targetCol] == 'O') {
            System.out.println("You already fired on this spot. Choose different coordinates.");
        } else if (board[targetRow][targetCol] == '-') {
            System.out.printf("%s MISSED!%n", player);
            history[targetRow][targetCol] = 'O';
            board[targetRow][targetCol] = 'O';
            printBattleShip(history);
        } else {
            System.out.printf("%s HIT %s's SHIP!%n", player, opp);
            history[targetRow][targetCol] = 'X';
            board[targetRow][targetCol] = 'X';
            printBattleShip(history);
        }
    }

    public static int check(char [][] board, int[][] input, String player) {
        int count=0;
        for (int row=0;row<5;row++) {
            if (board[input[row][0]][input[row][1]] == 'X') {
                count++;
                System.out.printf("%s hits: %d %n", player,count);
            }
        }
        return count;
    }

    public static void printBattleShip(char[][] board) {
        System.out.println("  0 1 2 3 4");
        for (int i = 0; i < board.length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}

