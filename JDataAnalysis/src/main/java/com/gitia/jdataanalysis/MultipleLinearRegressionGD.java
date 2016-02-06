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
     * @param step_size
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
