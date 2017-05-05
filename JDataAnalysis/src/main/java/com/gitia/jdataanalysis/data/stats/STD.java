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
        SimpleMatrix meanAux = meanMatrix(x);
        //obtenemos la desviación estandar para cada columna
        standardDeviation
                = Sum.sum(x.minus(meanAux)
                        .elementPower(2))
                .divide(x.numRows() - 1
                )
                .elementPower(0.5);//sqrt
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
        SimpleMatrix meanAux = meanMatrix(x);
        SimpleMatrix stdAux = stdMatrix(x);
        SimpleMatrix out = x.minus(meanAux);
        System.out.println("MB: " + (double) (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024 / 1024);
        out = out.elementDiv(stdAux);
        return out;
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
