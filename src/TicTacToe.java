import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    private static final char DOT_X = 'X';
    private static final char DOT_O = 'O';
    private static final char DOT_EMPTY = '_';

    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();

    private static char[][] field;
    private static char dotHuman;
    private static char dotAi;
    private static int fieldSizeX;
    private static int fieldSizeY;
    private static int scoreHuman;
    private static int scoreAi;
    private static int roundCounter;
    private static int winLength;

    public static void main(String[] args) {
        initField(5, 5);
        printField();
        humanTurn();
        printField();
        aiTurn();
        printField();
        startNewGame();
    }

    private static void startNewGame() {
        while (true){
            chooseDot();
            playRound();

            System.out.printf("SCORE:   HUMAN     AI\n" +
                    "            %d       %d\n", scoreHuman, scoreAi);
            System.out.print("Want to play again? (Y or N) >>> ");

            if (!scanner.next().toLowerCase(Locale.ROOT).equals("y")){
                System.out.println("BYE");
                break;
            }
        }
    }

    private static void playRound() {
        humanFirst();
        System.out.printf("Round %d start\n", ++ roundCounter);
        initField(3,3);
        printField();
        if (dotHuman == DOT_X){
            humanFirst();
        }else {
            aiFirst();
        }
    }

    private static void humanFirst(){
        while (true){
            humanTurn();
            printField();
            if (gameCheck(dotHuman)){
                break;
            }
            aiTurn();
            printField();
            if (gameCheck(dotAi)){
                break;
            }
        }
    }

    private static void aiFirst(){
        while (true){
            aiTurn();
            printField();
            if (gameCheck(dotAi)){
                break;
            }
            humanTurn();
            printField();
            if (gameCheck(dotHuman)){
                break;
            }
        }
}

    private static boolean gameCheck(char dot){
        if (checkWin(dot) && dot == dotHuman){
            System.out.println("Human WIN");
            scoreHuman++;
            return true;
        }else if (checkWin(dot) && dot == dotAi){
            System.out.println("Ai WIN");
            return true;
        }

        return checkDraw();
}

    private static void chooseDot() {
    System.out.print("Type 'X' to play with X and for 0 type anything >>>");

    if (scanner.next().toLowerCase(Locale.ROOT).equals("X")) {
        dotHuman = DOT_X;
        dotAi = DOT_O;
    } else {
        dotHuman = DOT_O;
        dotAi = DOT_X;
    }
}
    private static void  aiTurn() {
        int x;
        int y;

        do {
            x = random.nextInt(fieldSizeX) ;
            y = random.nextInt(fieldSizeY) ;
        } while (!isCellValid(y , x));

        field[y][x] = dotAi;
    }


    private static void  humanTurn() {
        int x;
        int y;

        do {
            System.out.print("Please enter coordinates x & y >>>");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(y , x));

        field[y][x] = dotHuman;
    }

    private static boolean checkWin(char dot) {
        if (field[0][0] == dot && field[0][1] == dot && field[0][2] == dot) return true;
        if (field[1][0] == dot && field[1][1] == dot && field[1][2] == dot) return true;
        if (field[2][0] == dot && field[2][1] == dot && field[2][2] == dot) return true;
        if (field[2][0] == dot && field[2][1] == dot && field[2][2] == dot) return true;
        if (field[2][0] == dot && field[2][1] == dot && field[2][2] == dot) return true;

        if (field[0][0] == dot && field[1][0] == dot && field[2][0] == dot) return true;
        if (field[0][1] == dot && field[1][1] == dot && field[2][1] == dot) return true;
        if (field[0][2] == dot && field[1][2] == dot && field[2][2] == dot) return true;
        if (field[0][0] == dot && field[1][0] == dot && field[2][0] == dot) return true;
        if (field[0][0] == dot && field[1][0] == dot && field[2][0] == dot) return true;

        if (field[0][0] == dot && field[1][0] == dot && field[2][0] == dot) return true;
        if (field[0][2] == dot && field[1][1] == dot && field[2][0] == dot) return true;
        return false;
    }

    private static boolean checkDraw(){
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (field[y][x] == DOT_EMPTY){
                    return false;
                }
            }
        }
        System.out.println("DRAW");
        return true;
    }

    public static boolean isCellValid(int y, int x) {
    return x >= 0 && y >= 0 && x < fieldSizeX && y < fieldSizeY && field[y][x] == DOT_EMPTY;
}

    private static void initField(int sizeX, int sizeY){
        fieldSizeY = sizeY;
        fieldSizeX = sizeX;
        field = new char[fieldSizeY][fieldSizeX];

        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                field[y][x] = DOT_EMPTY;
            }
        }

    }

    private static void printField(){
        System.out.println(" + ");

        for (int i = 0; i < fieldSizeX * 2 + 1; i++) {
            System.out.print(i % 2 == 0 ? "-": i/2 + 1);
        }
        System.out.println();
        for (int i = 0; i < fieldSizeY; i++) {
            System.out.print(i + 1 + "|");

            for (int j = 0; j < fieldSizeX; j++) {
                System.out.print(field[i][j] + "|");
            }
            System.out.println();
        }
        for (int i = 0; i < fieldSizeX * 2 + 1; i++) {
            System.out.print("_");
        }
        System.out.println();
    }
    
}
