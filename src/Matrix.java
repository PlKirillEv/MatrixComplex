public class Matrix {
    private final Complex[][] data;
    private final int rows;
    private final int cols;

    public Matrix(int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("Размеры матрицы должны быть положительными!");
        }
        this.rows = rows;
        this.cols = cols;
        this.data = new Complex[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] = new Complex(0,0);
            }
        }
    }

    public int getRows() {
        return rows;
    }
    public int getCols() {
        return cols;
    }

    public Complex get(int i, int j) {
        return data[i][j];
    }

    public void set(int i, int j, Complex value) {
        data[i][j] = value;
    }

    public Matrix add(Matrix other) {
        if (this.rows != other.rows || this.cols != other.cols) {
            throw new IllegalArgumentException("Размеры матрицы должны совпадать!");
        }
        Matrix result = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Complex sum = this.data[i][j].add(other.data[i][j]);
                result.set(i, j, sum);
            }
        }
        return result;
    }

    public Matrix subtract(Matrix other) {
        if (this.rows != other.rows || this.cols != other.cols) {
            throw new IllegalArgumentException("Размеры матрицы должны совпадать!");
        }
        Matrix result = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Complex diff = this.data[i][j].subtract(other.data[i][j]);
                result.set(i, j, diff);
            }
        }
        return result;
    }

    public Matrix multiply(Matrix other) {
        if (this.cols != other.rows) {
            throw new IllegalArgumentException("Для перемножения матриц число столбцов первой матрицы должно совпадать с числом строк второй!");
        }
        Matrix result = new Matrix(this.rows, other.cols);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < other.cols; j++) {
                Complex sum = new Complex(0,0);
                for (int k = 0; k < this.cols; k++) {
                    sum = sum.add(this.data[i][k].multiply(other.data[k][j]));
                }
                result.set(i, j, sum);
            }
        }
        return result;
    }

    public Matrix transpose() {
        Matrix result = new Matrix(cols, rows);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.set(j, i, this.data[i][j]);
            }
        }
        return result;
    }

    private Matrix minor(int row, int col) {
        Matrix result = new Matrix(rows-1, cols - 1);
        int newI = 0;
        for (int i = 0; i < rows; i++) {
            if (i != row) {
                int newJ = 0;
                for (int j = 0; j < cols; j++) {
                    if (j != col) {
                        result.set(newI, newJ, this.data[i][j]);
                        newJ++;
                    }
                }
                newI++;
            }
        }
        return result;
    }

    public Complex determinant() {
        if (rows != cols) {
            throw new IllegalArgumentException("Для нахождения определителя матрица должна быть квадратной!");
        }
        if (rows == 1) {
            return data[0][0];
        }
        if (rows == 2) {
            return data[0][0].multiply(data[1][1]).subtract(data[0][1].multiply(data[1][0]));
        }

        Complex result = new Complex(0, 0);
        for (int j = 0; j < cols; j++) {
            Complex part = data[0][j].multiply(minor(0, j).determinant());
            if (j % 2 == 0) {
                result = result.add(part);
            }
            else {
                result = result.subtract(part);
            }
        }
        return result;
    }

    public Matrix inverse() {
        if (rows != cols) {
            throw new IllegalArgumentException("Обратную матрицу можно найти только для квадратной матрицы!");
        }

        Complex det = this.determinant();

        if (det.getRe() == 0 && det.getIm() == 0) {
            throw new ArithmeticException("Матрица вырождена, обратной не может существовать!");
        }

        if (rows == 1) {
            Matrix result = new Matrix(1, 1);
            result.set(0, 0, new Complex(1, 0).divide(det));
            return result;
        }

        Matrix adj = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Complex minDet = this.minor(j, i).determinant();
                if ((i+j) % 2 != 0) {
                    minDet = new Complex(0, 0).subtract(minDet);
                }
                adj.set(i, j, minDet);
            }
        }

        Matrix result = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Complex val = adj.get(i, j).divide(det);
                result.set(i, j, val);
            }
        }
        return result;
    }

    public Matrix divide(Matrix other) {
        if (other.rows != other.cols) {
            throw new IllegalArgumentException("Делить можно только на квадратную матрицу!");
        }
        if (this.cols != other.rows) {
            throw new IllegalArgumentException("Для деления число столбцов первой матрицы должно совпадать с числом строк второй!");
        }
        return this.multiply(other.inverse());
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result += data[i][j] + "\t";
            }
            result += "\n";
        }
        return result;
    }
}
