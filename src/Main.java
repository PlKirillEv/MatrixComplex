import java.util.Scanner;
import java.util.Locale;
import java.util.InputMismatchException;

public class Main {

    private static Matrix A = null;
    private static Matrix B = null;

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        scan.useLocale(Locale.US);

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

            try {
                int choice = scan.nextInt();
                scan.nextLine();

                if (choice == 0) {
                    System.out.println("Программа завершена");
                    break;
                }

                if (choice < 1 || choice > 10) {
                    System.out.println("Неверный выбор! Попробуйте снова.");
                    continue;
                }

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
                        checkNull(A, "A");
                        checkNull(B, "B");
                        resSave(scan, A.add(B), "A + B");
                        break;
                    case 5:
                        checkNull(A, "A");
                        checkNull(B, "B");
                        resSave(scan, A.subtract(B), "A - B");
                        break;
                    case 6:
                        checkNull(A, "A");
                        checkNull(B, "B");
                        resSave(scan, A.multiply(B), "A * B");
                        break;
                    case 7:
                        checkNull(A, "A");
                        checkNull(B, "B");
                        resSave(scan, A.divide(B), "A / B");
                        break;
                    case 8:
                        checkNull(A, "A");
                        resSave(scan, A.transpose(), "A^T");
                        break;
                    case 9:
                        checkNull(A, "A");
                        System.out.println("\nОпределитель A: " + A.determinant());
                        break;
                    case 10:
                        checkNull(A, "A");
                        resSave(scan, A.inverse(), "A^(-1)");
                        break;
                }
            } catch (IllegalArgumentException aex) {
                System.out.println("\nОшибка: " + aex.getMessage());
            } catch (ArithmeticException aex) {
                System.out.println("\nОшибка: " + aex.getMessage());
            } catch (NullPointerException e) {
                System.out.println("\nОшибка: сначала введите матрицы A и B!");
            } catch (InputMismatchException e) {
                scan.nextLine();
                System.out.println("\nОшибка ввода: нужно вводить числа!");
            }
        }
        scan.close();
    }

    private static void resSave(Scanner scan, Matrix result, String title) {
        System.out.println("\n" + title + ":");
        System.out.println(result);

        System.out.print("Сохранить результат в A или B? (A/B/Enter — пропустить): ");
        String answer = scan.nextLine().trim().toUpperCase();

        if (answer.equals("A")) {
            A = result;
            System.out.println("Результат сохранён в A");
        } else if (answer.equals("B")) {
            B = result;
            System.out.println("Результат сохранён в B");
        } else {
            System.out.println("Результат не сохранён");
        }
    }

    private static void checkNull(Matrix m, String name) {
        if (m == null) {
            throw new NullPointerException("Матрица " + name + " не задана!");
        }
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
