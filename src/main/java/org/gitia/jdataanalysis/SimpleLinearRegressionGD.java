/*
 * The MIT License
 *
 * Copyright 2017 
 *   Matías Roodschild <mroodschild@gmail.com>.
 *   Jorge Gotay Sardiñas <jgotay57@gmail.com>.
 *   Adrian Will <adrian.will.01@gmail.com>.
 *   Sebastián Rodriguez <sebastian.rodriguez@gitia.org>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.gitia.jdataanalysis;

/**
 *
 * @author @author Matías Roodschild <mroodschild@gmail.com>
 */
public class SimpleLinearRegressionGD extends SimpleLinearRegression {

    double initial_intercept;
    double initial_slope;
    double step_size;
    double tolerance;
    double[] errors;
    double sumErrorY = 0;
    double sumErrorX = 0;

    /**
     * initial slope = 0; initial intercept = 0; step_size = 0.05; tolerance =
     * 0.01;
     */
    public SimpleLinearRegressionGD() {
        this(0, 0, 0.05, 0.01);
    }

    /**
     *
     * @param initial_intercept
     * @param initial_slope
     * @param step_size
     * @param tolerance
     */
    public SimpleLinearRegressionGD(double initial_intercept, double initial_slope, double step_size, double tolerance) {
        this.initial_intercept = initial_intercept;
        this.initial_slope = initial_slope;
        intercept = initial_intercept;
        slope = initial_slope;
        this.step_size = step_size;
        this.tolerance = tolerance;
    }

    /**
     *
     * @param initial_intercept
     * @param initial_slope
     * @param step_size
     * @param tolerance
     * @param x
     * @param y
     */
    public SimpleLinearRegressionGD(double initial_intercept, double initial_slope, double step_size, double tolerance, double[] x, double[] y) {
        this(initial_intercept, initial_slope, step_size, tolerance);
        this.x = x;
        this.y = y;
        errors = new double[x.length];
    }

    public void compute() {
        double count = 0;
        do {
            compute_step();
            count++;
        } while (magnitude() > tolerance);
        System.out.println("Pasos:\t" + count);
        mostrarResultados();
    }

    /**
     *
     * @param step
     */
    public void compute(int step) {
        for (int i = 0; i < step; i++) {
            compute_step();
        }
        mostrarResultados();
    }

    private void compute_step() {
        predic_error();
        sumErrorY = sum(errors);
        sumErrorX = prodVect(x, errors);
        ajustIntercept();
        ajustSlope();
    }

    private double magnitude() {
        return Math.sqrt(Math.pow(sumErrorX, 2) + Math.pow(sumErrorY, 2));
    }

    private void ajustIntercept() {
        intercept = intercept - step_size * sumErrorY;
    }

    private void ajustSlope() {
        slope = slope - step_size * sumErrorX;
    }

    public void mostrarResultados() {
        //Util.print(errors, "Errores: ");//actualizamos el vector de errores
        System.out.println("Step:\t" + sumErrorY * step_size);
        System.out.println("Suma Errores Y:\t" + sumErrorY);
        System.out.println("Suma Errores X:\t" + sumErrorX);
        System.out.println("New Intercept:\t" + intercept);
        System.out.println("new Slope:\t" + slope);
    }

    /**
     *
     * @param a
     * @param b
     * @return
     */
    public double prodVect(double[] a, double[] b) {
        double sum = 0;
        for (int i = 0; i < b.length; i++) {
            sum += a[i] * b[i];
        }
        return sum;
    }

    /**
     * Sum(predict - Y)
     *
     * @return
     */
    public double[] predic_error() {
        for (int i = 0; i < y.length; i++) {
            errors[i] = prediction(x[i]) - y[i];
        }
        return errors;
    }

}
