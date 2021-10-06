package tictactoe;

import java.util.Scanner;

public class Table {


    private final Scanner scanner;
    private final String[] cells = {" ", " ", " ", " ", " ", " ", " ", " ", " "};

    public Table() {
        this.scanner = new Scanner(System.in);
    }



    private void print(String[] cell) {

        int num = 0;

        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                if (j == 0 || j == 4) {
                    System.out.print("| ");
                } else {
                    System.out.print(cell[num++] + " ");
                }
            }

            System.out.println();
        }

        System.out.println("---------");
    }


    public void run() {

        print(this.cells);

        boolean exit = false;
        while (!exit) {

            boolean isValid = false;
            int[] coordinates;

            System.out.println("Enter the coordinates: ");
            coordinates = chekNumber(cells);
            cells[indexNum(coordinates[0], coordinates[1])] = "X";

            print(cells);

            if (!whoWins(cells).equals("Game not finished")) {
                isValid = true;
                exit = true;

            }


            if (!isValid) {
                System.out.println("Enter the coordinates: ");
                coordinates = chekNumber(cells);
                cells[indexNum(coordinates[0], coordinates[1])] = "O";

                print(cells);

                if (!whoWins(cells).equals("Game not finished")) {
                    exit = true;
                }
            }

        }

        System.out.println(whoWins(cells));


    }


    private String whoWins(String[] cells) {

        String xWins = winningCombinations(cells, "X");
        String oWins = winningCombinations(cells, "O");
        int empty = emptySpaces(cells);

        if (!oWins.equals("0") && xWins.equals("0")) {
            return oWins;
        }
        if (!xWins.equals("0") && oWins.equals("0")) {
            return xWins;
        }
        if (empty == 0) {
            return "Draw";
        }


        return "Game not finished";


    }

    private String winningCombinations(String[] cells, String ticTacToe) {

        boolean firstRow = cells[0].equals(ticTacToe) && cells[1].equals(ticTacToe) && cells[2].equals(ticTacToe);
        boolean secondRow = cells[3].equals(ticTacToe) && cells[4].equals(ticTacToe) && cells[5].equals(ticTacToe);
        boolean thirdRow = cells[6].equals(ticTacToe) && cells[7].equals(ticTacToe) && cells[8].equals(ticTacToe);

        boolean firstColumn = cells[0].equals(ticTacToe) && cells[3].equals(ticTacToe) && cells[6].equals(ticTacToe);
        boolean secondColumn = cells[1].equals(ticTacToe) && cells[4].equals(ticTacToe) && cells[7].equals(ticTacToe);
        boolean thirdColumn = cells[2].equals(ticTacToe) && cells[5].equals(ticTacToe) && cells[8].equals(ticTacToe);

        boolean diagonalForLeft = cells[0].equals(ticTacToe) && cells[4].equals(ticTacToe) && cells[8].equals(ticTacToe);
        boolean diagonalForRight = cells[2].equals(ticTacToe) && cells[4].equals(ticTacToe) && cells[6].equals(ticTacToe);

        if (firstRow || secondRow || thirdRow || firstColumn || secondColumn || thirdColumn ||
                diagonalForLeft || diagonalForRight) {
            return ticTacToe + " wins";
        }

        return "0";

    }


    private int emptySpaces(String[] cells) {
        int count = 0;
        for (String cell : cells) {
            if (cell.equals(" ")) {
                count++;
            }
        }
        return count;
    }


    private int[] chekNumber(String[] cells) {
        int[] n = new int[2];
        boolean valid = false;
        String[] input;
        while (!valid) {

            input = scanner.nextLine().split(" ");

            try {

                n[0] = Integer.parseInt(input[0]);
                n[1] = Integer.parseInt(input[1]);

                if (n[0] > 3 || n[1] > 3 || n[0] < 1 || n[1] < 1) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else {
                    if (!isOccupy(n[0], n[1], cells)) {
                        System.out.println("This cell is occupied! Choose another one!");
                    } else {
                        valid = true;
                    }
                }

            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
            }

        }


        return n;
    }

    private boolean isOccupy(int first, int second, String[] cells) {
        int index = indexNum(first, second);
        boolean isEmpty = isEmpty(cells[index]);

        if (!isEmpty) {
            return false;
        } else {
            cells[index] = "X";
            return true;
        }

    }


    private int indexNum(int first, int second) {
        if (first == 1 && second == 1) {
            return 0;
        }
        if (first == 1 && second == 2) {
            return 1;
        }
        if (first == 1 && second == 3) {
            return 2;
        }
        if (first == 2 && second == 1) {
            return 3;
        }
        if (first == 2 && second == 2) {
            return 4;
        }
        if (first == 2 && second == 3) {
            return 5;
        }
        if (first == 3 && second == 1) {
            return 6;
        }
        if (first == 3 && second == 2) {
            return 7;
        }
        if (first == 3 && second == 3) {
            return 8;
        }

        return -1;

    }

    private boolean isEmpty(String cells) {
        return " ".equals(cells);

    }

}
