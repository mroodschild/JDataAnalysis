/*
 * Copyright 2016 @author Matías Roodschild <mroodschild@gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gitia.jdataanalysis;

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
        util.print(errors, "Errores: ");//actualizamos el vector de errores
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
