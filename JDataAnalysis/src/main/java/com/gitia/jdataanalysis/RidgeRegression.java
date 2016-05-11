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
     *
     * @param input
     * @param output
     * @return cost = RSS + alpha * Wt * W
     */
    public double fit(double[][] input, double[] output) {
        this.input = input;
        this.output = output;

        H = UtilMatrix.addColumnBefore(new SimpleMatrix(input), 1);
        h = H.transpose();
        W = new SimpleMatrix(H.numCols(), 1);
        W.zero();
        Yobs = new SimpleMatrix(output.length, 1, true, output);
        //Yest = H.mult(W);
        System.out.println("Initial RSS: " + getRSS(H, Yobs) + " Initial magnitude_coefficients: " + magnitude_coefficients());
        adjustW();
        System.out.println("Finish RSS: " + getRSS(H, Yobs) + " Finish magnitude_coefficients: " + magnitude_coefficients());
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
