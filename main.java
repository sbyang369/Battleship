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

        // player 1 input
        for (int i=0;i<5;i++) {
            boolean valid = false;
            while (!valid) {
                System.out.printf("Coordinate #%d ",i+1);
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
                        if (row == input1[x][0]) {
                            if (col == input1[x][1]) {
                                duplicate = true;
                                break;
                            }
                        }
                    }

                    if (duplicate) {
                        System.out.println("You already have a ship there. Choose different coordinates.");
                        continue;
                    }

                    input1[i][0] = row;
                    input1[i][1] = col;
                    valid = true;

                } catch (ArithmeticException e) {
                    System.out.println("Invalid coordinates. Choose diffferent coordinates.");
                }
            }
         }

        for (int j=0;j<input1.length;j++) {
            System.out.println(input1[j][0] + " " + input1[j][1]);
        }

        // print board
        System.out.println();
        for (int row=0;row<5;row++) {
            for (int col=0;col<5;col++) {
                board1[row][col] = '-';
            }
        }

        for (int row=0;row<5;row++) {
            for (int col=0;col<2;col++) {
                board1[input1[row][0]][input1[row][1]] = '@';
            }
        }

        printBattleShip(board1);

        // player 2 input
        System.out.println("PLAYER 2, ENTER YOUR SHIPS' COORDINATES.");
        for (int i=0;i<5;i++) {
            boolean valid = false;
            while (!valid) {
                System.out.printf("Coordinate #%d ",i+1);
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
                        if (row == input2[x][0]) {
                            if (col == input2[x][1]) {
                                duplicate = true;
                                break;
                            }
                        }
                    }

                    if (duplicate) {
                        System.out.println("You already have a ship there. Choose different coordinates.");
                        continue;
                    }

                    input2[i][0] = row;
                    input2[i][1] = col;
                    valid = true;

                } catch (ArithmeticException e) {
                    System.out.println("Invalid coordinates. Choose diffferent coordinates.");
                }
            }
        }

        for (int j=0;j<input2.length;j++) {
            System.out.println(input2[j][0] + " " + input2[j][1]);
        }

        // print board
        System.out.println();
        for (int row=0;row<5;row++) {
            for (int col=0;col<5;col++) {
                board2[row][col] = '-';
            }
        }

        for (int row=0;row<5;row++) {
            for (int col=0;col<2;col++) {
                board2[input2[row][0]][input2[row][1]] = '@';
            }
        }

        printBattleShip(board2);

        //history boards
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

        for (int count =0;count<5;count++) {
            System.out.println();
        }

        // Hit sequence
        do {

            //repeat for player 1's turn
            int targetRow2;
            int targetCol2;
            System.out.println("Player 1, hit: ");
            targetRow2 = input.nextInt();
            targetCol2 = input.nextInt();

            int targetCol;
            if (targetRow2 < 0 || targetRow2 > 4 || targetCol2 < 0 || targetCol2 > 4) {
                System.out.println("Invalid coordinates. Choose different coordinates,");
                continue;
            }

            // check if board has an 'O' / already hit. then update history + location
            if (board2[targetRow2][targetCol2] == 'O') {
                System.out.println("You already fired on this spot. Choose different coordinates.");
            } else if (board2[targetRow2][targetCol2] == '-') {
                System.out.println("PLAYER 1 MISSED!");
                history1[targetRow2][targetCol2] = 'O';
                board2[targetRow2][targetCol2] = 'O';
                printBattleShip(history1);
            } else {
                System.out.println("PLAYER 1 HIT PLAYER 2's SHIP!");
                history1[targetRow2][targetCol2] = 'X';
                board2[targetRow2][targetCol2] = 'X';
                printBattleShip(history1);
            }

            // PLAYER 2'S TURN
            int targetRow1;
            int targetCol1;
            System.out.println("Player 2, hit: ");
            targetRow1 = input.nextInt();
            targetCol1 = input.nextInt();

            if (targetRow1 < 0 || targetRow1 > 4 || targetCol1 < 0 || targetCol1 > 4) {
                System.out.println("Invalid coordinates. Choose different coordinates,");
                continue;
            }

            // check if board has an 'O' / already hit. then update history + location
            if (board1[targetRow1][targetCol1] == 'O') {
                System.out.println("You already fired on this spot. Choose different coordinates.");
            } else if (board1[targetRow1][targetCol1] == '-') {
                System.out.println("PLAYER 2 MISSED!");
                history2[targetRow1][targetCol1] = 'O';
                board1[targetRow1][targetCol1] = 'O';
                printBattleShip(history2);
            } else {
                System.out.println("PLAYER 2 HIT PLAYER 1's SHIP!");
                history2[targetRow1][targetCol1] = 'X';
                board1[targetRow1][targetCol1] = 'X';
                printBattleShip(history2);
            }

            //include checker for if loop is done; if so, end
            int count=0;
            for (int row=0;row<5;row++) {
                if (board1[input1[row][0]][input1[row][1]] == 'X') {
                    count++;
                    System.out.println("Player 2 hits: " + count);

                }
            }
            if (count >= 5) {
                System.out.println("PLAYER 2 WINS! YOU SUNK ALL OF YOUR OPPONENT'S SHIPS!");
                running = false;
            }

            int count2=0;
            for (int row=0;row<5;row++) {
                if (board2[input2[row][0]][input2[row][1]] == 'X') {
                    count2++;
                    System.out.println("Player 1 hits: " + count2);
                }
            }

            if (count2 >= 5) {
                System.out.println("PLAYER 1 WINS! YOU SUNK ALL OF YOUR OPPONENT'S SHIPS!");
                running = false;
            }

        } while (running == true);

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

