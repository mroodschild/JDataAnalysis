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
package org.gitia.jdataanalysis.data.stats;

import org.ejml.ops.CommonOps;
import org.ejml.simple.SimpleMatrix;

/**
 *
 * @author Matías Roodschild <mroodschild@gmail.com>
 */
public class MapMinMax {

    SimpleMatrix max;
    SimpleMatrix min;
    double a = 0;
    double b = 1;

    /**
     *
     */
    public MapMinMax() {
    }

    /**
     *
     * Iniciamos la clase con los vectores máximos y mínimos de cada columna.
     *
     * @param max
     * @param min
     */
    public MapMinMax(SimpleMatrix max, SimpleMatrix min) {
        this.max = max;
        this.min = min;
    }

    /**
     *
     * Iniciamos la clase con los vectores máximos y mínimos de cada columna y
     * la escala [a, b] a la que deben ser convertidos los valores.
     *
     * @param max
     * @param min
     * @param a
     * @param b
     */
    public MapMinMax(SimpleMatrix max, SimpleMatrix min, double a, double b) {
        this(max, min);
        this.a = a;
        this.b = b;
    }

    /**
     *
     * Ajusta los valores maximos y minimos de la matrix acorde a las columnas.
     *
     * @param x value to fit
     */
    public void fit(SimpleMatrix x) {
        max = x.extractVector(true, 0);
        min = x.extractVector(true, 0);
        for (int i = 0; i < x.numCols(); i++) {
            min.set(i, CommonOps.elementMin(x.extractVector(false, i).getMatrix()));
            max.set(i, CommonOps.elementMax(x.extractVector(false, i).getMatrix()));
        }
        double[] test = max.minus(min).getMatrix().getData();
        for (int i = 0; i < test.length; i++) {
            if (test[i] == 0) {
                throw new IllegalArgumentException("Difference between min and max '" + i + "' are zero");
            }
        }
    }

    /**
     *
     * Ajusta los valores maximos y minimos de la matrix acorde a las columnas.
     *
     * @param x value to fit
     * @param a min value for the scale
     * @param b max value for the scale
     */
    public void fit(SimpleMatrix x, double a, double b) {
        if (a > b) {
            throw new IllegalArgumentException("'a' can´t be less than 'b'");
        }
        if (a == b) {
            throw new IllegalArgumentException("Numbers 'a' and 'b' can´t be the same");
        }
        this.a = a;
        this.b = b;
        fit(x);
    }

    /**
     *
     * @param numberColumns number of columns or features
     * @param min min value for all columns
     * @param max max value for all columns
     */
    public void fit(int numberColumns, double min, double max) {
        if (min == max) {
            throw new IllegalArgumentException("Numbers min and max can´t be the same");
        }
        if (min > max) {
            throw new IllegalArgumentException("'min' can´t be less than 'max'");
        }
        this.min = new SimpleMatrix(1, numberColumns);
        this.max = new SimpleMatrix(1, numberColumns);
        this.min.set(min);
        this.max.set(max);
    }

    /**
     *
     * @param numberColumns number of columns or features
     * @param min min value for all columns
     * @param max max value for all columns
     * @param a min value for the scale
     * @param b max value for the scale
     */
    public void fit(int numberColumns, double min, double max, double a, double b) {
        if (a > b) {
            throw new IllegalArgumentException("'a' can´t be less than 'b'");
        }
        if (a == b) {
            throw new IllegalArgumentException("Numbers 'a' and 'b' can´t be the same");
        }
        this.a = a;
        this.b = b;
        fit(numberColumns, min, max);
    }

    /**
     *
     * y = (b - a) * (x - xmin) / (xmax - xmin) + a;
     *
     * @param x
     * @return
     */
    public SimpleMatrix eval(SimpleMatrix x) {
        SimpleMatrix xmin = new SimpleMatrix(x.numRows(), 1);
        SimpleMatrix xmax = new SimpleMatrix(x.numRows(), 1);
        xmin.set(1);
        xmax.set(1);
        xmin = xmin.mult(min);
        xmax = xmax.mult(max);
        return x.minus(xmin).scale(b - a).elementDiv(xmax.minus(xmin)).plus(a);
    }

    /**
     *
     * x = (y - a) * (xmax - xmin) / (b - a) + xmin
     *
     * @param x
     * @return
     */
    public SimpleMatrix reverse(SimpleMatrix x) {
        SimpleMatrix dif = new SimpleMatrix(x.numRows(), 1);
        dif.set(1);
        dif = dif.mult(max.minus(min));
        SimpleMatrix xmin = new SimpleMatrix(x.numRows(), 1);
        xmin.set(1);
        xmin = xmin.mult(min);
        return x.minus(a).elementMult(dif).divide(b - a).plus(xmin);
    }

}
