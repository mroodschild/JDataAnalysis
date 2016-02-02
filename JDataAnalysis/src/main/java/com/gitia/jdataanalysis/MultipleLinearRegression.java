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
    }

    /**
     * this function takes the input, and calculate the output estimated, and
     * compare this with the output estimated
     *
     * @param input
     * @param output
     * @return
     */
    public double getRSS(double[][] input, double[] output) {
        SimpleMatrix in = new SimpleMatrix(input);
        SimpleMatrix Yo = new SimpleMatrix(output.length, 1, true, output);
        return getRSS(in, Yo);
    }

    /**
     * this function takes the input, and calculate the output estimated, and
     * compare this with the output estimated
     *
     * @param input (H)
     * @param output w (vertical)
     * @return
     */
    public double getRSS(SimpleMatrix input, SimpleMatrix output) {
        SimpleMatrix dif = output.minus(input.mult(W));//(Yi-Hw)
        SimpleMatrix rss = dif.transpose().mult(dif);//(Yi-Hw)t * (Yi-Hw)
        return rss.get(0, 0);
    }

    public void adjustW() {
        W = (H.transpose().mult(H)).invert().mult(H.transpose()).mult(Yobs);// (Ht * H)^(-1) * Ht * Y
    }

}
