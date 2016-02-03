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
        H = new SimpleMatrix(input);
        h = H.transpose();
        W = new SimpleMatrix(input[0].length, 1);
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

    @Override
    public void adjustW() {
        while (gradRSS().normF()> tolerance) {
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
