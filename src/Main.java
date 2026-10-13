import java.util.Scanner;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        scan.useLocale(Locale.US);
        
        Matrix A = null;
        Matrix B = null;

        System.out.println("Операции над матрицами с комплексными числами");

        while (true) {
            System.out.println("\nВыберите операцию:");
            System.out.println("1  - Ввести матрицу A");
            System.out.println("2  - Ввести матрицу B");
            System.out.println("3  - Показать матрицы");
            System.out.println("4  - Сложение матриц (A + B)");
            System.out.println("5  - Вычитание матриц (A - B)");
            System.out.println("6  - Умножение матриц (A * B)");
            System.out.println("7  - Деление матриц (A / B)");
            System.out.println("8  - Транспонирование матрицы A");
            System.out.println("9  - Определитель матрицы A");
            System.out.println("10 - Обратная матрица A");
            System.out.println("0  - Выход");
            System.out.print("Ваш выбор: ");

            int choice = scan.nextInt();

            if (choice == 0) {
                System.out.println("Программа завершена");
                break;
            }

            if (choice < 1 || choice > 10) {
                System.out.println("Неверный выбор! Попробуйте снова.");
                continue;
            }

            try {
                switch (choice) {
                    case 1:
                        A = readMatrix(scan, "A");
                        break;
                    case 2:
                        B = readMatrix(scan, "B");
                        break;
                    case 3:
                        showMatrices(A, B);
                        break;
                    case 4:
                        System.out.println("\nA + B:");
                        System.out.println(A.add(B));
                        break;
                    case 5:
                        System.out.println("\nA - B:");
                        System.out.println(A.subtract(B));
                        break;
                    case 6:
                        System.out.println("\nA * B:");
                        System.out.println(A.multiply(B));
                        break;
                    case 7:
                        System.out.println("\nA / B:");
                        System.out.println(A.divide(B));
                        break;
                    case 8:
                        System.out.println("\nA^T:");
                        System.out.println(A.transpose());
                        break;
                    case 9:
                        System.out.println("\nОпределитель A: " + A.determinant());
                        break;
                    case 10:
                        System.out.println("\nA^(-1):");
                        System.out.println(A.inverse());
                        break;
                }
            }
            catch (IllegalArgumentException aex) {
                System.out.println("\nОшибка: " + aex.getMessage());
            }
            catch (ArithmeticException aex) {
                System.out.println("\nОшибка: " + aex.getMessage());
            }
            catch (NullPointerException e) {
                System.out.println("\nОшибка: сначала введите матрицы A и B!");
            }
        }
        scan.close();
    }

    private static Matrix readMatrix(Scanner scan, String name) {
        System.out.println("\nВвод матрицы " + name + ":");
        System.out.print("Введите число строк: ");
        int rows = scan.nextInt();
        System.out.print("Введите число столбцов: ");
        int cols = scan.nextInt();

        Matrix m = new Matrix(rows, cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print("Элемент [" + i + "][" + j + "] (действительная и мнимая части через пробел): ");
                double re = scan.nextDouble();
                double im = scan.nextDouble();
                scan.nextLine();
                m.set(i, j, new Complex(re, im));
            }
        }
        return m;
    }

    private static void showMatrices(Matrix A, Matrix B) {
        System.out.println("\nМатрица A:");
        if (A == null) {
            System.out.println("Матрица A не задана");
        } else {
            System.out.println(A);
        }

        System.out.println("Матрица B:");
        if (B == null) {
            System.out.println("Матрица B не задана");
        } else {
            System.out.println(B);
        }
    }
}
