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

import org.ejml.simple.SimpleMatrix;

/**
 *
 * @author Matías Roodschild <mroodschild@gmail.com>
 */
public class Constant {

    double c = 1;

    public Constant() {
    }

    public Constant(double c) {
        this.c = c;
    }

    /**
     *
     * divide all the element with a constant c;
     *
     * @param x
     * @return
     */
    public SimpleMatrix eval(SimpleMatrix x) {
        SimpleMatrix matrix = x.divide(c);
        return matrix;
    }

    /**
     *
     * divide all the element with a constant c;
     *
     * @param x
     * @return
     */
    public SimpleMatrix eval(SimpleMatrix x, double c) {
        SimpleMatrix matrix = x.divide(c);
        return matrix;
    }

    /**
     *
     * multiply all the element with the constant c
     *
     * @param x
     * @return
     */
    public SimpleMatrix reverse(SimpleMatrix x) {
        return x.scale(c);
    }

    public void setC(double c) {
        this.c = c;
    }

    public double getC() {
        return c;
    }

}
