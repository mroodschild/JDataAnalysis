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
package com.gitia.jdataanalysis;

/**
 *
 * @author @author Matías Roodschild <mroodschild@gmail.com>
 */
public class SimpleLinearRegression {

    double[] x;
    double[] y;
    protected double sumX;
    protected double sumY;
    protected double meanX;
    protected double meanY;
    protected double sumSquaredX;
    protected double meanSquaredX;
    protected double n;
    protected double sumProdXY;
    protected double intercept;
    protected double slope;

    public SimpleLinearRegression() {
    }

    public SimpleLinearRegression(double[] x, double[] y) {
        this.x = x;
        this.y = y;
        calcular();
    }

    public void calcular() {
        sumX = sum(x);
        sumY = sum(y);
        meanX = mean(x);
        meanY = mean(y);
        sumSquaredX = sumSquared(x);
        meanSquaredX = meanSquared(x);
        n = x.length;
        sumProdXY = sumProd(x, y);
        slope = getSlope();
        intercept = intercept();
    }

    /**
     *
     * @param vect
     * @return
     */
    public double sum(double[] vect) {
        double sum = 0;
        for (int i = 0; i < vect.length; i++) {
            sum += vect[i];
        }
        return sum;
    }

    /**
     *
     * @param vect1
     * @param vect2
     * @return
     */
    public double sumProd(double[] vect1, double[] vect2) {
        double sum = 0;
        for (int i = 0; i < vect1.length; i++) {
            sum += vect1[i] * vect2[i];
        }
        return sum;
    }

    /**
     *
     * @param vect
     * @return
     */
    public double sumSquared(double[] vect) {
        double sum = 0;
        for (int i = 0; i < vect.length; i++) {
            sum += Math.pow(vect[i], 2);
        }
        return sum;
    }

    public double meanSquared(double[] vect) {
        return sumSquared(vect) / vect.length;
    }

    public double getSlopeNumerator() {
        //System.out.println(getSumProdXY() - getSumX() * getSumY() / getN());
        return sumProdXY - sumX * sumY / n;
    }

    public double getSlopeDenominator() {
        //System.out.println(getSumSquaredX() - getSumX() * getSumX() / getN());
        return sumSquaredX - sumX * sumX / n;
    }

    public double getSlope() {
        return getSlopeNumerator() / getSlopeDenominator();
    }

    public double intercept() {
        return meanY - slope * meanX;
    }

    /**
     *
     * @param input
     * @return
     */
    public double prediction(double input) {
        return intercept + slope * input;
    }

    /**
     *
     * @param input
     * @return
     */
    public double[] prediction(double[] input) {
        double[] prediction = new double[input.length];
        for (int i = 0; i < input.length; i++) {
            prediction[i] = prediction(input[i]);
        }
        return prediction;
    }

    /**
     * this function takes the input, and calculate the output estimated, and
     * compare this with the output estimated
     *
     * @param input
     * @param output
     * @return
     */
    public double getRSS(double[] input, double[] output) {
        double rss = 0;
        for (int i = 0; i < input.length; i++) {
            rss = rss + Math.pow((output[i]) - prediction(input[i]), 2);
        }
        return rss;
    }

    /**
     *
     * @param output
     * @return
     */
    public double inverseRegressionPredictions(double output) {
        // y = intercept + slope * input;
        // x = (y - intercept)/slope
        return (output - intercept) / slope;
    }

    /**
     *
     * @param output
     * @return
     */
    public double[] inverseRegressionPredictions(double[] output) {
        double[] inverse = new double[output.length];
        for (int i = 0; i < output.length; i++) {
            inverse[i] = inverseRegressionPredictions(output[i]);
        }
        return inverse;
    }

    public double getIntercept() {
        return intercept;
    }

    /**
     *
     * @param vect
     * @return
     */
    public double mean(double[] vect) {
        return sum(vect) / vect.length;
    }

    public int getN() {
        return x.length;
    }

    public double getSumX() {
        return sum(x);
    }

    public double getSumY() {
        return sum(y);
    }

    public double getMeanX() {
        return mean(x);
    }

    public double getMeanY() {
        return mean(y);
    }

    public double getSumSquaredX() {
        return sumSquared(x);
    }

    public double getMeanSquaredX() {
        return sumSquared(x) / x.length;
    }

    public double getSumProdXY() {
        return sumProd(x, y);
    }

    public double[] getX() {
        return x;
    }

    public void setX(double[] x) {
        this.x = x;
    }

    public double[] getY() {
        return y;
    }

    public void setY(double[] y) {
        this.y = y;
    }

    public void printSum() {
        System.out.println("Sum X:\t" + sumX + "\t Sum Y: \t" + sumY);
    }

    public void printMean() {
        System.out.println("Mean X:\t" + meanX + "\t Mean Y:\t" + meanY);
    }

    public void printSumSquaredX() {
        System.out.println("Sum Squared X: " + sumSquaredX);
    }

    public void printSumSquaredY() {
        System.out.println("Mean Squared X: " + meanSquaredX);
    }

    public void printSumProdXY() {
        System.out.println("Sum Prod X*Y: " + sumProdXY);
    }

    public void printSlope() {
        System.out.println("Slope: \t" + slope);
    }

    public void printIntercept() {
        System.out.println("Intercept: \t" + intercept);
    }

    public void setSlope(double slope) {
        this.slope = slope;
    }

    public void setIntercep(double intercept) {
        this.intercept = intercept;
    }

    public void getCoefficients() {
        System.out.println("intercept: " + intercept + "\t slope: " + slope);
    }

    public void setCoefficients(double intercept, double slope) {
        this.intercept = intercept;
        this.slope = slope;
    }

}
