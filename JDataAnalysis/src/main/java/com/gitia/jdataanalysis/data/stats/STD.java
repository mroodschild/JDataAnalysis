/*
 * Copyright 2016 Matías Roodschild <mroodschild@gmail.com>.
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
package com.gitia.jdataanalysis.data.stats;

import org.ejml.ops.CommonOps;
import org.ejml.simple.SimpleMatrix;

/**
 *
 * @author Matías Roodschild <mroodschild@gmail.com>
 */
public class STD {

    double mean;
    SimpleMatrix meanSimple;
    double standardDeviation;
    SimpleMatrix standardDeviationSimple;

    /**
     * Laplaician smoothing.
     */
    public STD() {
    }

    /**
     *
     * @param mean
     * @param standardDeviation
     */
    public STD(double mean, double standardDeviation) {
        this.mean = mean;
        this.standardDeviation = standardDeviation;
    }

    /**
     *
     * @param x
     */
    public void fit(double[] x) {
        mean = Mean.mean(x);
        double[] Xi2 = new double[x.length];
        double sum = 0;
        for (int i = 0; i < x.length; i++) {
            Xi2[i] = Math.pow((x[i] - mean), 2);
            sum += Xi2[i];
        }
        this.standardDeviation = Math.sqrt(sum / (double) (Xi2.length - 1));
    }

    /**
     *
     * @param x
     */
    public void fit(SimpleMatrix x) {
        meanSimple = Mean.mean(x);
        standardDeviationSimple = new SimpleMatrix(1, x.numCols());
        SimpleMatrix ones = meanSimple.transpose().copy();
        ones.set(1);
        ones.mult(meanSimple);
        double sum = 0;
        for (int i = 0; i < x.length; i++) {
            Xi2[i] = Math.pow((x[i] - mean), 2);
            sum += Xi2[i];
        }
        this.standardDeviation = Math.sqrt(sum / (double) (Xi2.length - 1));
    }

    /**
     *
     * @param x
     * @return
     */
    public double eval(double x) {
        return ((x - this.mean) / this.standardDeviation);
    }

    /**
     *
     * @param x
     * @return
     */
    public double reverse(double x) {
        return ((x * this.standardDeviation) + this.mean);
    }

    public double getMean() {
        return mean;
    }

    public double getStandardDeviation() {
        return standardDeviation;
    }

}
