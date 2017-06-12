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

import org.ejml.simple.SimpleMatrix;

/**
 *
 * @author Matías Roodschild <mroodschild@gmail.com>
 */
public class STD {

    SimpleMatrix mean;
    SimpleMatrix standardDeviation;

    /**
     *
     */
    public STD() {
    }

    /**
     * Inicializamos la clase, ingresamos los parámetros de media y desviación
     * estandard el parámetro mean y desviación estandard deben estar en el
     * formato horizontal es decir:<br> [1, 5, 3]
     *
     * @param mean
     * @param standardDeviation
     */
    public STD(SimpleMatrix mean, SimpleMatrix standardDeviation) {
        this.mean = mean;
        this.standardDeviation = standardDeviation;
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
        mean = Mean.mean(x);
        //obtenemos el mean para restar elemento a elemento
        SimpleMatrix aux = meanMatrix(x);
        //obtenemos la desviación estandar para cada columna
        aux = x.minus(aux);//.elementPower(2);
        aux = aux.elementPower(2);
        //standardDeviation = Sum.sum(x.minus(aux).elementPower(2)).divide(x.numRows() - 1).elementPower(0.5);//sqrt
        standardDeviation = Sum.sum(aux).divide(x.numRows() - 1).elementPower(0.5);//sqrt
    }

    /**
     *
     * @param x
     * @return
     */
    private SimpleMatrix meanMatrix(SimpleMatrix x) {
        SimpleMatrix meanAux = new SimpleMatrix(x.numRows(), 1);
        meanAux.set(1);
        return meanAux.mult(mean);
    }

    /**
     *
     * @param x
     * @return
     */
    private SimpleMatrix stdMatrix(SimpleMatrix x) {
        SimpleMatrix stdAux = new SimpleMatrix(x.numRows(), 1);
        stdAux.set(1);
        return stdAux.mult(standardDeviation);
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
        return standardDeviation;
    }

    public SimpleMatrix getMean() {
        return mean;
    }

    public void setMean(SimpleMatrix mean) {
        this.mean = mean;
    }

    public void setStandardDeviation(SimpleMatrix standardDeviation) {
        this.standardDeviation = standardDeviation;
    }

}
