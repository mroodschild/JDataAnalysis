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
package org.gitia.jdataanalysis;

import org.ejml.simple.SimpleMatrix;

/**
 *
 * @author @author Matías Roodschild <mroodschild@gmail.com>
 */
public class UtilMatrix {

    /**
     * This function inserts a column at the begin of the matrix
     *
     * @param matrix
     * @return
     */
    public static SimpleMatrix addColumnBefore(SimpleMatrix matrix) {
        SimpleMatrix c = new SimpleMatrix(matrix.numRows(), matrix.numCols() + 1);
        c.insertIntoThis(0, 1, matrix);//copy matrix b in matrix c, in the position selected
        return c;
    }

    /**
     * This function inserts a column at the begin of the matrix
     *
     * @param matrix
     * @param value valor a cargar en dicha columna
     * @return
     */
    public static SimpleMatrix addColumnBefore(SimpleMatrix matrix, double value) {
        SimpleMatrix c = new SimpleMatrix(matrix.numRows(), matrix.numCols() + 1);
        c.insertIntoThis(0, 1, matrix);//copy matrix b in matrix c, in the position selected
        c = setColumn(c, 0, value);
        return c;
    }

    /**
     * This function inserts a column at the begin of the matrix
     *
     * @param matrix
     * @return
     */
    public static SimpleMatrix addColumnAfter(SimpleMatrix matrix) {
        SimpleMatrix c = new SimpleMatrix(matrix.numRows(), matrix.numCols() + 1);
        c.insertIntoThis(0, 0, matrix);//copy matrix b in matrix c, in the position selected
        return c;
    }

    /**
     * This function inserts a column at the begin of the matrix
     *
     * @param matrix
     * @param value valor a cargar en dicha columna
     * @return
     */
    public static SimpleMatrix addColumnAfter(SimpleMatrix matrix, double value) {
        SimpleMatrix c = new SimpleMatrix(matrix.numRows(), matrix.numCols() + 1);
        c.insertIntoThis(0, 0, matrix);//copy matrix b in matrix c, in the position selected
        c = setColumn(c, c.numCols() - 1, value);
        return c;
    }

    /**
     *
     * @param matrix Matriz a modificar
     * @param column columna en la que agregar el valor
     * @param value valor a colocar en toda la columna
     * @return
     */
    public static SimpleMatrix setColumn(SimpleMatrix matrix, int column, double value) {
        int size = matrix.numRows();
        for (int i = 0; i < size; i++) {
            matrix.set(i, column, value);
        }
        return matrix;
    }

}
