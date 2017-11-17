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
     * compare this with the output observated.
     *
     * @param input
     * @param output Y observada
     * @return
     */
    public double getRSS(double[][] input, double[] output) {
        SimpleMatrix in = UtilMatrix.addColumnBefore(new SimpleMatrix(input), 1);
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
        W = (H.transpose().mult(H)).pseudoInverse().mult(H.transpose()).mult(Yobs);// (Ht * H)^(-1) * Ht * Y
        //W = (H.transpose().mult(H)).invert().mult(H.transpose()).mult(Yobs);// (Ht * H)^(-1) * Ht * Y
    }

    public void getCoefficients() {
        System.out.println("\nCoefficients: (first coefficient is intercept)");
        for (int i = 0; i < W.getNumElements(); i++) {
            System.out.printf(" %s x%d | ", String.format("%6.3e", W.get(i)), i);
        }
        System.out.println("\n");
    }

    /**
     *
     * @param featureMatrix
     * @return
     */
    public double[] predictOutcome(double[][] featureMatrix) {
        SimpleMatrix input = UtilMatrix.addColumnBefore(new SimpleMatrix(featureMatrix), 1);
        SimpleMatrix output = input.mult(W);
        double[] s = new double[output.numRows()];
        int size = s.length;
        for (int i = 0; i < size; i++) {
            s[i] = output.get(i, 0);
        }
        return s;
    }

}
