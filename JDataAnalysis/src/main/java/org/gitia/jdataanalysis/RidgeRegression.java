/*
 * The MIT License
 *
 * Copyright 2017 Matías Roodschild <mroodschild@gmail.com>.
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

import org.ejml.simple.SimpleMatrix;

/**
 *
 * @author @author Matías Roodschild <mroodschild@gmail.com>
 */
public class RidgeRegression extends MultipleLinearRegression {

    private double l2Penalty;

    /**
     *
     */
    public RidgeRegression() {
    }

    /**
     *
     * @param alpha
     */
    public RidgeRegression(double alpha) {
        this.l2Penalty = alpha;
    }

    /**
     * no imprime los coeficientes parciales por defecto
     *
     * @param input
     * @param output
     * @return cost = RSS + alpha * Wt * W
     */
    public double fit(double[][] input, double[] output) {
        return fit(input, output, false);
    }

    /**
     * para imprimir ponga print = true
     *
     * @param input
     * @param output
     * @return cost = RSS + alpha * Wt * W
     */
    public double fit(double[][] input, double[] output, boolean print) {
        this.input = input;
        this.output = output;

        H = UtilMatrix.addColumnBefore(new SimpleMatrix(input), 1);
        h = H.transpose();
        W = new SimpleMatrix(H.numCols(), 1);
        W.zero();
        Yobs = new SimpleMatrix(output.length, 1, true, output);
        //Yest = H.mult(W);
        if (print) {
            System.out.println("Initial RSS: " + getRSS(H, Yobs) + " Initial magnitude_coefficients: " + magnitude_coefficients());
        }
        adjustW();
        if (print) {
            System.out.println("Finish RSS: " + getRSS(H, Yobs) + " Finish magnitude_coefficients: " + magnitude_coefficients());
        }

        return cost();
    }

    /**
     *
     * @return Wt*W
     */
    public double magnitude_coefficients() {
        if (W == null) {
            return 0;
        }
        return W.transpose().mult(W).get(0);
    }

    /**
     * This is the regression cost = RSS + alpha * Wt * W
     *
     * @return (Y - H * W)t * (Y - H * W) + alpha * Wt * W
     */
    protected double cost() {
        return getRSS(input, output) + getL2Penalty() * W.transpose().mult(W).get(0);
    }

    /**
     *
     * @param input
     * @param output
     * @return
     */
    public double cost(double[][] input, double[] output) {
        return getRSS(input, output) + getL2Penalty() * W.transpose().mult(W).get(0);
    }

    /**
     *
     */
    @Override
    protected void adjustW() {
        //(alpha * I) del tamaño de H
//        H = new SimpleMatrix(8, 6, true,
//                64, 2, 3, 61, 60, 6,
//                9, 55, 54, 12, 13, 51,
//                17, 47, 46, 20, 21, 43,
//                40, 26, 27, 37, 36, 30,
//                32, 34, 35, 29, 28, 38,
//                41, 23, 22, 44, 45, 19,
//                49, 15, 14, 52, 53, 11,
//                8, 58, 59, 5, 4, 62);

        SimpleMatrix diag = SimpleMatrix.identity(H.numCols()).scale(getL2Penalty());
//        System.out.println("H");
////        H.print("%.6f");
//        diag.print("%.6f");
////        
//        SimpleMatrix aux;
        //(Ht * H + alpha * I)^(-1) * Ht * Y
        W = (H.transpose().mult(H).plus(diag)).pseudoInverse().mult(H.transpose()).mult(Yobs);

//        System.out.println("Ht*H");
//        aux = H.transpose().mult(H);
//        aux.print("%.6f");
//        aux = aux.plus(diag);
//        System.out.println("Ht * H + alfa");
//        aux.print("%.6f");
//        System.out.println("(Ht * H + alfa)^-1");
//        aux = aux.pseudoInverse();
//        aux.print("%.6f");
//        System.out.println("((Ht * H + alfa)^-1)*Ht");
//        aux = aux.mult(H.transpose());
//        aux.print("%.6f");
//        System.out.println("((Ht * H + alfa)^-1)*Ht * Yobs");
//        aux = aux.mult(Yobs);
//        aux.print("%.6f");
//        System.out.println("Hola Mundo");
    }

    /**
     * @return the l2Penalty
     */
    public double getL2Penalty() {
        return l2Penalty;
    }

    /**
     * @param l2Penalty the l2Penalty to set
     */
    public void setL2Penalty(double l2Penalty) {
        this.l2Penalty = l2Penalty;
    }

}
