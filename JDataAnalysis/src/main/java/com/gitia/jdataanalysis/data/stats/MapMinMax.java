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
package com.gitia.jdataanalysis.data.stats;

import org.ejml.ops.CommonOps;
import org.ejml.simple.SimpleMatrix;

/**
 *
 * @author Matías Roodschild <mroodschild@gmail.com>
 */
public class MapMinMax {

    SimpleMatrix max;
    SimpleMatrix min;

    /**
     *
     */
    public MapMinMax() {
    }

    /**
     * Inicializamos la clase, ingresamos los parámetros de media y desviación
     * estandard el parámetro mean y desviación estandard deben estar en el
     * formato horizontal es decir:<br> [1, 5, 3]
     *
     * @param max
     * @param min
     */
    public MapMinMax(SimpleMatrix max, SimpleMatrix min) {
        this.max = max;
        this.min = min;
    }

    /**
     * mean = SumRows(X<sub>ij</sub>)/numRow<br>
     * standardDeviation = sqrt([sum(X<sub>ij</sub> - Xmean)<sup>2</sup>] / (m -
     * 1))
     *
     * @param x value to fit
     */
    public void fit(SimpleMatrix x) {
        //calculamos el mean de cada columna
        max = x.extractVector(true, 0);
        min = x.extractVector(true, 0);
        for (int i = 0; i < x.numCols(); i++) {
            min.set(i, CommonOps.elementMin(x.extractVector(false, i).getMatrix()));
            max.set(i, CommonOps.elementMax(x.extractVector(false, i).getMatrix()));
        }
    }

    /**
     * mean = SumRows(X<sub>ij</sub>)/numRow<br>
     * standardDeviation = sqrt([sum(X<sub>ij</sub> - Xmean)<sup>2</sup>] / (m -
     * 1))
     *
     * @param numberInput number of columns or features
     * @param min min value for all columns
     * @param max max value for all columns
     */
    public void fit(int numberInput, int min, int max) {
        this.min = new SimpleMatrix(1, numberInput);
        this.max = new SimpleMatrix(1, numberInput);
        
        
    }

    /**
     *
     * @param x
     * @return
     */
    private SimpleMatrix meanMatrix(SimpleMatrix x) {
        SimpleMatrix meanAux = new SimpleMatrix(x.numRows(), 1);
        meanAux.set(1);
        return meanAux.mult(max);
    }

    /**
     *
     * @param x
     * @return
     */
    private SimpleMatrix stdMatrix(SimpleMatrix x) {
        SimpleMatrix stdAux = new SimpleMatrix(x.numRows(), 1);
        stdAux.set(1);
        return stdAux.mult(min);
    }

    /**
     *
     * Xn = (X - mean(X)) / std(X)
     *
     * @param x
     * @return
     */
    public SimpleMatrix eval(SimpleMatrix x) {
        SimpleMatrix aux = meanMatrix(x);
        SimpleMatrix stdAux = stdMatrix(x);
        aux = x.minus(aux);
        aux = aux.elementDiv(stdAux);
        //System.out.println("MB: " + (double) (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024 / 1024);
        return aux;
    }

    /**
     * X = Xn * std(X) + mean(X)
     *
     * @param x
     * @return
     */
    public SimpleMatrix reverse(SimpleMatrix x) {
        SimpleMatrix meanAux = meanMatrix(x);
        SimpleMatrix stdAux = stdMatrix(x);
        return x.elementMult(stdAux).plus(meanAux);
    }

    public SimpleMatrix getStandardDeviation() {
        return min;
    }

    public SimpleMatrix getMean() {
        return max;
    }

    public void setMean(SimpleMatrix mean) {
        this.max = mean;
    }

    public void setStandardDeviation(SimpleMatrix standardDeviation) {
        this.min = standardDeviation;
    }

}
