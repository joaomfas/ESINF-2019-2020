package pl1_recursion;

/**
 *
 * @author joaoferreira
 */
public class PL1_Recursion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Ex.1 a) " + ex1_a("João"));
        System.out.println("Ex.1 b) " + ex1_b("João"));
        System.out.println("Ex.  2) " + ex2(1221));
        System.out.println("Ex.  3) " + ex3(48, 30));

        // Criar labirinto
        int[][] mapa = {
            {1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1},
            {1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1},
            {1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };
        int[][] caminhoLabirinto = ex4(mapa, 0, 0);
        System.out.println("Ex. 4)");
        printMatrix(caminhoLabirinto);
    }

    /*
     * 1. Write a recursive method that receives a string and returns another
     * string: 
     * a) With its characters with the same order of the original
     * string.
     */
    public static String ex1_a(String s) {
        if (s.length() == 0) {
            return "";
        } else {
            String letter = String.valueOf(s.charAt(0));
            return letter + ex1_a(s.substring(1));
        }
    }

    /*
     * b) With its characters with reverse order of the original string.
     */
    public static String ex1_b(String s) {
        if (s.length() == 0) {
            return "";
        } else {
            String letter = String.valueOf(s.charAt(s.length() - 1));
            return letter + ex1_b(s.substring(0, s.length() - 1));
        }
    }

    /*
    2. Make a recursive method to see if a number is palindrome that is, the number is the same when written
    forwards or backwards (examples: 99, 101, 111, 121, 1221, 21112, 10001, … ).
     */
    public static boolean ex2(int num) {
        return ex2_aux(num, 0) == num;
    }

    public static int ex2_aux(int num1, int num2) {
        if (num1 == 0) {
            return num2;
        } else {
            num2 = num2 * 10;
            num2 = num2 + (num1 % 10);
            num1 = num1 / 10;
            return ex2_aux(num1, num2);
        }
    }

    /*
    3. State a recursive method that calculates the greatest common divisor of two positive integers using the
    algorithm of successive divisions. For example, m.d.c (48,30)=6.
     */
    public static int ex3(int num1, int num2) {
        if (num1 > num2) {
            return ex3(num2, num1);
        }

        if (num2 > num1 && num2 % num1 == 0) {
            return num1;
        } else {
            return ex3(num1, num2 % num1);
        }
    }

    /*
    4. Develop a recursive method to demonstrate the backtracking through the search of a path in a labyrinth.
    Consider that only horizontal and vertical movements are allowed (diagonal are prohibited) and
    movements obey the following order: north↑, east→, south↓ and west ←. Represent the labyrinth by a
    matrix of zeros and ones, in which the walls represent zeros and ones halls. For example, for the following
    labyrinth 7x13:
    {1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1},
    {1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1},
    {1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
    {1, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1},
    {1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0},
    {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    A recursive method that seeks a path between the source (0, 0) and the destination (6, 12) should mark
    to 9 the labyrinth path cells and cells accessed but which have not led to this solution with 2. So the
    return positions (backtracking) can be viewed with 2.
     */
    public static int[][] ex4(int[][] mapa, int x, int y) {
        // Marcar caminho para a saída na matriz com o #9
        mapa[x][y] = 9;

        //printMatrix(mapa);

        // Condição de paragem quando a saída do labirinto é alcançada
        if (y == mapa[x].length - 1 && x == mapa.length - 1) {
            return mapa;
        }

        // Norte
        if (x - 1 >= 0 && mapa[x - 1][y] == 1) {
            if (ex4(mapa, x - 1, y) != null) {
                return mapa;
            }
        }

        // Este
        if (y + 1 < mapa[x].length && mapa[x][y + 1] == 1) {
            if (ex4(mapa, x, y + 1) != null) {
                return mapa;
            }
        }

        // Sul
        if (x + 1 < mapa.length && mapa[x + 1][y] == 1) {
            if (ex4(mapa, x + 1, y) != null) {
                return mapa;
            }
        }

        // Oeste
        if (y - 1 >= 0 && mapa[x][y - 1] == 1) {
            if (ex4(mapa, x, y - 1) != null) {
                return mapa;
            }
        }

        // Marcar caminho anulado
        mapa[x][y] = 2;
        return null;
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
