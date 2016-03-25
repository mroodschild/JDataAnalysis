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
public class RidgeRegression extends MultipleLinearRegression {

    double alpha;
    boolean normalize;

    public RidgeRegression() {
    }

    public RidgeRegression(double alpha, boolean normalize) {
        this.alpha = alpha;
        this.normalize = normalize;
    }

    public void fit(double[][] input, double[] output) {
        this.input = input;
        this.output = output;

        H = UtilMatrix.addColumnBefore(new SimpleMatrix(input));
        H = UtilMatrix.setColumn(H, 0, 1);
        //H = new SimpleMatrix(input);
        h = H.transpose();
        W = new SimpleMatrix(H.numCols(), 1);
        W.zero();
        Yobs = new SimpleMatrix(output.length, 1, true, output);
        Yest = H.mult(W);
        System.out.println("Initial RSS: " + getRSS(H, Yobs) + " Initial magnitude_coefficients: " + magnitude_coefficients());
        adjustW();
        System.out.println("Finish RSS: " + getRSS(H, Yobs) + " Finish magnitude_coefficients: " + magnitude_coefficients());
    }

    /**
     *
     * @return Wt*W
     */
    protected double magnitude_coefficients() {
        return W.transpose().mult(W).get(0);
    }

    /**
     * This is the regression cost = RSS + alpha * Wt * W
     *
     * @return (Y - H * W)t * (Y - H * W) + alpha * Wt * W
     */
    protected double cost() {
        return getRSS(input, output) + alpha * W.transpose().mult(W).get(0);
    }

    /**
     *
     */
    @Override
    protected void adjustW() {
        //(alpha * I) del tamaño de H
        SimpleMatrix diag = SimpleMatrix.identity(H.numCols()).scale(alpha);
        //(Ht * H + alpha * I)^(-1) * Ht * Y
        W = (H.transpose().mult(H).plus(diag)).invert().mult(H.transpose()).mult(Yobs);
    }

}
