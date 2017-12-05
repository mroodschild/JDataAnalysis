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
public class MultipleLinearRegressionGD extends MultipleLinearRegression {

    //SimpleMatrix n;//step
    double tolerance = 0.01;//stop criteria
    double stepSize;

    public MultipleLinearRegressionGD() {
    }

    public MultipleLinearRegressionGD(double[][] input, double[] output, double stepSize, double tolerance) {
        this.stepSize = stepSize;
        this.tolerance = tolerance;
        H = UtilMatrix.addColumnBefore(new SimpleMatrix(input));
        H = UtilMatrix.setColumn(H, 0, 1);
        h = H.transpose();
        W = new SimpleMatrix(H.numCols(), 1);
        W.zero();
        Yobs = new SimpleMatrix(output.length, 1, true, output);
        Yest = H.mult(W);
        double rss = getRSS(H, Yobs);
        System.out.println("Initial RSS: " + rss);
        adjustW();
        System.out.println("Finish RSS: " + getRSS(H, Yobs));
        System.out.println("Finish W: ");
        W.print();
    }

    /**
     *
     * @param feature_matrix
     * @param output
     * @param stepSize
     * @param tolerance
     * @param initial_weights
     */
    public void regression_gradient_descent(double[][] feature_matrix, double[] output, double stepSize, double tolerance, double... initial_weights) {
        this.stepSize = stepSize;
        this.tolerance = tolerance;
        H = UtilMatrix.addColumnBefore(new SimpleMatrix(feature_matrix));
        H = UtilMatrix.setColumn(H, 0, 1);
        h = H.transpose();
        //W = new SimpleMatrix(H.numCols(), 1);
        W = new SimpleMatrix(initial_weights.length, 1, true, initial_weights);//.zero()
        Yobs = new SimpleMatrix(output.length, 1, true, output);
        Yest = H.mult(W);
        double rss = getRSS(H, Yobs);
        System.out.println("Initial RSS: " + rss);
        adjustW();
        System.out.println("Finish RSS: " + getRSS(H, Yobs));
        System.out.println("Finish W: ");
        W.print();
    }

    @Override
    public void adjustW() {
        while (gradRSS().normF() > tolerance) {
            W = W.minus(gradRSS().scale(stepSize));// W(old) - n * gradRSS()
        }
    }

    /**
     * output vertical vector
     *
     * @return - 2 Ht * ( Yobs - H * w)
     */
    public SimpleMatrix gradRSS() {
        SimpleMatrix gradRSS = H.transpose().mult(Yobs.minus(H.mult(W))).scale(-2);
        return gradRSS;
    }

}
