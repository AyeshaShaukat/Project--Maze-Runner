/**
 * This is the Maze class. It has all of the methods and information needed
 * to build a specific maze and have a MazeRunner get from the start to the end.
 * There are pits and walls that block the path of the user. If the user attempts to
 * move through a wall or a pit Maze throws an exception and terminates the program.
 * The MazeRunner class should never throw an exception.
 */

public class Maze {
    private char[][] myMap;
    private char[][] solution;
    private int row;
    private int col;

    /**
     * Instantiate a new Maze object.
     */
    public Maze() {
        row = 1;
        col = 0;
        myMap = new char[20][20];
        solution = new char[20][20];
        fillMap(myMap);
        fillMap(solution);
        fillSolution();
    }

    private void fillMap(char[][] map) {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                map[i][j] = '.';
            }
        }
        myMap[row][col] = 'x';
    }

    /**
     * Display the maze. Dots represent unexplored spaces, x is your current position,
     * - and | are walls, 0 are pits, and * are explored spaces.
     */
    public void printMap() {
        printMap(myMap);
    }

    private void printMap(char[][] map) {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private boolean isThereAPit(int rowMove, int colMove) {
        if (col + colMove > 20 || col + colMove < 0 ||
                row + rowMove > 20 || row + rowMove < 0  ) {
            return false;
        } else if (solution[row + rowMove][col + colMove] == '0') {
            myMap[row+rowMove][col+colMove] = '0';
            return true;
        } else {
            return false;
        }
    }

    /**
     * Determines if there is a pit in the direction given.
     * @param dir the direction given ("R", "L", "U", or "D").
     * @return true if there is a pit, false otherwise.
     */
    public boolean isThereAPit(String dir) {
        if(dir.equals("R")) {
            return isThereAPit(0, 1);
        } else if (dir.equals("L")) {
            return isThereAPit(0,-1);
        } else if (dir.equals("U")) {
            return isThereAPit(-1, 0);
        } else if(dir.equals("D")) {
            return isThereAPit(1, 0);
        } else {
            throw new IllegalArgumentException("I didn't understand the direction you entered");
        }
    }

    /**
     * Jumps over a pit in the direction given. Moves your character two spaces.
     * Does nothing if there is no pit in that direction.
     * @param dir the directions given ("R", "L", "U", or "D").
     */
    public void jumpOverPit(String dir) {
        if(isThereAPit(dir)) {
            if(dir.equals("R")) {
                move(0, 2);
            } else if (dir.equals("L")) {
                move(0, -2);
            } else if (dir.equals("U")) {
                move(-2, 0);
            } else if(dir.equals("D")) {
                move(2, 0);
            }
        }
    }

    private boolean canMove(int rowMove, int colMove) {
        if (col + colMove > 20 || col + colMove < 0 ||
                row + rowMove > 20 || row + rowMove < 0) {
            return false;
        }
        else if (solution[row + rowMove][col + colMove] == '*') {
            myMap[row+rowMove][col+colMove] = '*';
            return true;
        } else if (solution[row + rowMove][col + colMove] == '0') {
            myMap[row+rowMove][col+colMove] = '*';
            return false;
        } else {
            myMap[row+rowMove][col+colMove] = '-';
            return false;
        }
    }

    /**
     * Determines if your character can move right.
     * @return true if there are no obstacles to the right.
     */
    public boolean canIMoveRight() {
        return canMove(0,1);
    }

    /**
     * Determines if your character can move left.
     * @return true if there are no obstacles to the left.
     */
    public boolean canIMoveLeft() {
        return canMove(0,-1);
    }

    /**
     * Determines if your character can move up.
     * @return true if there are no obstacles above.
     */
    public boolean canIMoveUp() {
        return canMove(-1,0);
    }

    /**
     * Determines if your character can move down.
     * @return true if there are no obstacles below.
     */
    public boolean canIMoveDown() {
        return canMove(1,0);
    }

    private void move(int rowMove, int colMove) {
        if(canMove(rowMove, colMove)) {
            myMap[row][col] = '*';
            row += rowMove;
            col += colMove;
            if(solution[row][col] == '0') {
                throw new IllegalArgumentException("Oh NOOOOO you fell into a pit!");
            }
            myMap[row][col] = 'x';
        } else {
            throw new IllegalArgumentException("ERROR: You cannot move that way");
        }
        //printMap(myMap);
    }

    /**
     * Moves your character one space right.
     */
    public void moveRight() {
        move(0, 1);
    }

    /**
     * Moves your character one space left.
     */
    public void moveLeft() {
        move(0, -1);
    }

    /**
     * Moves your character one space down.
     */
    public void moveUp() {
        move(-1, 0);
    }

    /**
     * Moves your character one space up.
     */
    public void moveDown() {
        move(1, 0);
    }

    private void fillSolution() {
        for (int i = 0; i < 6; i++) {
            solution[0][i] = '-';
        }
        for (int i = 0; i < 5; i++) {
            solution[1][i] = '*';
        }
        for (int i = 2; i < 20; i++) {
            solution[i][4] = '*';
        }
        solution[19][4] = '-';
        solution[1][5] = '|';
        solution[2][5] = '|';
        for (int i = 0; i < 14; i++) {
            solution[2][i] = '-';
        }
        solution[2][4] = '*';
        for (int i = 3; i < 20; i++) {
            solution[i][3] = '|';
        }
        for (int i = 4; i < 15; i++) {
            solution[3][i] = '*';
        }
        solution[2][14] = '|';
        solution[1][14] = '|';
        solution[0][14] = '-';
        solution[0][15] = '-';
        solution[0][16] = '-';
        for (int i = 1; i < 7; i++) {
            solution[i][16] = '|';
        }
        solution[7][14] = '-';
        solution[7][15] = '-';
        solution[7][16] = '-';
        for (int i = 5; i < 15; i++) {
            solution[4][i] = '-';
        }
        solution[5][14] = '|';
        solution[6][14] = '|';
        for (int i = 1; i < 7; i++) {
            solution[i][15] = '*';
        }
        for (int i = 5; i < 20; i++) {
            solution[i][5] = '|';
        }
        for (int i = 5; i < 17; i++) {
            solution[12][i] = '-';
            solution[13][i] = '*';
            solution[14][i] = '-';
        }
        solution[11][16] = '|';
        solution[10][16] = '|';
        solution[14][18] = '|';
        solution[13][18] = '|';
        solution[12][18] = '|';
        solution[11][18] = '-';
        solution[11][19] = '-';
        solution[9][16] = '-';
        solution[9][17] = '-';
        solution[9][18] = '-';
        solution[9][19] = '-';
        solution[14][17] = '-';
        for (int i = 10; i < 14; i++) {
            solution[i][17] = '*';
        }
        solution[10][18] = '*';
        solution[10][19] = '*';
        for(int i = 5; i < 13; i++) {
            solution[8][i] = '-';
            solution[10][i] = '-';
            solution[9][i] = '*';
        }
        for (int i = 8; i < 11; i++) {
            solution[i][13] = '|';
        }
        for(int i = 5; i < 19; i++) {
            solution[17][i] = '-';
            solution[19][i] = '-';
            solution[18][i] = '*';
        }
        for (int i = 17; i < 20; i++) {
            solution[i][19] = '|';
        }
        for(int i = 3; i >= 0; i--) {
            solution[14][i] = '-';
            solution[16][i] = '-';
            solution[15][i] = '*';
        }
        for (int i = 14; i < 17; i++) {
            solution[i][0] = '|';
        }
        addPits();
        //printMap(solution);
    }
    private void addPits(){
        solution[1][2] = '0';
        solution[3][7] = '0';
        solution[3][12] = '0';
        solution[6][4] = '0';
        solution[15][4] = '0';
        solution[9][10] = '0';
        solution[13][17] = '0';
        solution[13][15] = '0';
        solution[18][10] = '0';
    }

    /**
     * Determines if the user reached the end of the maze.
     * @return true if the user is at the end, false otherwise.
     */
    public boolean didIWin() {
        if (row == 10 && col == 19) {
            return true;
        } else {
            return false;
        }
    }
}
