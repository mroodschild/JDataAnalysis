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
public class MultipleLinearRegression {

    double[][] input;
    double[] output;
    SimpleMatrix W;
    SimpleMatrix H;//H = h transp
    SimpleMatrix h;
    SimpleMatrix Yobs;
    SimpleMatrix Yest;

    public MultipleLinearRegression() {
    }

    public MultipleLinearRegression(double[][] input, double[] output) {
        H = UtilMatrix.addColumnBefore(new SimpleMatrix(input));
        H = UtilMatrix.setColumn(H, 0, 1);
        //H = new SimpleMatrix(input);
        h = H.transpose();
        W = new SimpleMatrix(H.numCols(), 1);
        W.zero();
        Yobs = new SimpleMatrix(output.length, 1, true, output);
        Yest = H.mult(W);
        double rss = getRSS(H, Yobs);
        System.out.println("Initial RSS: " + rss);
        adjustW();
        System.out.println("Finish RSS: " + getRSS(H, Yobs));
    }

    /**
     * this function takes the input, and calculate the output estimated, and
     * compare this with the output observada
     *
     * @param input
     * @param output Y observada
     * @return
     */
    public double getRSS(double[][] input, double[] output) {
        SimpleMatrix in = UtilMatrix.addColumnBefore(new SimpleMatrix(input));
        in = UtilMatrix.setColumn(in, 0, 1);
        //convertimos la salida observada en simple matrix
        SimpleMatrix Yo = new SimpleMatrix(output.length, 1, true, output);
        return getRSS(in, Yo);
    }

    /**
     * only use this internally this function takes the input, and calculate the
     * output estimated, and compare this with the output observada
     *
     * @param input (H)
     * @param output w (vertical)
     * @return
     */
    protected double getRSS(SimpleMatrix input, SimpleMatrix output) {
        SimpleMatrix dif = output.minus(input.mult(W));//(Yi-Hw)
        SimpleMatrix rss = dif.transpose().mult(dif);//(Yi-Hw)t * (Yi-Hw)
        return rss.get(0, 0);
    }

    protected void adjustW() {
        W = (H.transpose().mult(H)).invert().mult(H.transpose()).mult(Yobs);// (Ht * H)^(-1) * Ht * Y
    }

    public void getCoefficients() {
        System.out.println("\nCoefficients: (first coefficient is intercept)");
        for (int i = 0; i < W.getNumElements(); i++) {
            System.out.printf(" %.2f x%d | ", W.get(i), i);
//            if (i % 5 == 0 && i != 0) {
//                System.out.println("");
//            }
        }
        System.out.println("\n");
    }

    /**
     *
     * @param featureMatrix
     * @return
     */
    public double[] predictOutcome(double[][] featureMatrix) {
        SimpleMatrix input = UtilMatrix.addColumnBefore(new SimpleMatrix(featureMatrix));
        input = UtilMatrix.setColumn(input, 0, 1);
        SimpleMatrix output = input.mult(W);
        double[] s = new double[output.numRows()];
        int size = s.length;
        for (int i = 0; i < size; i++) {
            s[i] = output.get(i, 0);
        }
        return s;
    }

}
