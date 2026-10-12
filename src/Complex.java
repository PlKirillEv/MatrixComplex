public class Complex {
    private final double re;
    private final double im;

    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public double getRe() {
        return re;
    }
    public double getIm() {
        return im;
    }

    public Complex add(Complex other) {
        return new Complex(this.re + other.re, this.im + other.im);
    }

    public Complex subtract(Complex other) {
        return new Complex(this.re - other.re, this.im - other.im);
    }

    public Complex multiply(Complex other) {
        double newRe = this.re * other.re - this.im * other.im;
        double newIm = this.re * other.im + this.im * other.re;
        return new Complex(newRe, newIm);
    }

    public Complex divide(Complex other) {
        double den = other.re * other.re + other.im * other.im;
        if (den == 0) {
            throw new ArithmeticException("Ошибка! Деление на ноль!");
        }
        double newRe = (this.re * other.re + this.im * other.im)/den;
        double newIm = (this.im * other.re - this.re * other.im)/den;
        return new Complex(newRe, newIm);
    }

    public String toString() {
        if (im >= 0) {
            return re + "+" + Math.abs(im) + "i";
        }
        else {
            return re + "-" + Math.abs(-im) + "i";
        }
    }
}