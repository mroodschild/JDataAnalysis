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
package com.gitia.jdataanalysis.eval;

import org.ejml.simple.SimpleMatrix;

/**
 *
 * @author Matías Roodschild <mroodschild@gmail.com>
 */
public class Compite {
    
    public static SimpleMatrix eval(SimpleMatrix matrix){
        SimpleMatrix aux = matrix;
        double max;
        double pos;
        for (int i = 0; i < aux.numRows(); i++) {
            SimpleMatrix row = aux.extractVector(true, i);
            max = row.get(0);
            pos = 0;
            for (int j = 0; j < row.numCols(); j++) {
                if (max < row.get(j)) {
                    max = row.get(j);
                    pos = j;
                }
            }
            for (int j = 0; j < row.numCols(); j++) {
                if (j == pos) {
                    row.set(j, 1);
                } else {
                    row.set(j, 0);
                }
            }
            aux.setRow(i, 0, row.getMatrix().getData());
        }
        return aux;
    }
    
}
