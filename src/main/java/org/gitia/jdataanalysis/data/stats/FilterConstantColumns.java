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

import java.util.Arrays;
import java.util.stream.IntStream;
import org.apache.commons.lang3.ArrayUtils;
import org.ejml.dense.row.CommonOps_DDRM;
import org.ejml.simple.SimpleMatrix;

/**
 *
 * @author Matias
 */
public class FilterConstantColumns {

    double[] colsFiltered;

    public FilterConstantColumns() {
    }

    public FilterConstantColumns(double[] colsFiltered) {
        this.colsFiltered = colsFiltered;
    }

    /**
     * generamos un vector de tamaño igual a la cantidad de columnas, colocamos
     * un 1 en las columas que serán quitadas y colocamos un 0 en las columnas a
     * mantener.
     *
     * @param matrix
     */
    public void fit(SimpleMatrix matrix) {
        colsFiltered = new double[matrix.numCols()];
        int size = matrix.numCols();
        IntStream.range(0, size).parallel()
                .forEach(i -> colsFiltered[i] = 
                        (constant(matrix.extractVector(false, i)) ? 1 : 0));
//        for (int i = 0; i < matrix.numCols(); i++) {
//            if (constant(matrix.extractVector(false, i))) {
//                colsFiltered[i] = 1;
//            } else {
//                colsFiltered[i] = 0;
//            }
//        }
    }

    /**
     *
     * Quitamos las columnas que fueron seleccionadas previamente
     *
     * @param matrix
     * @return
     */
    public SimpleMatrix eval(SimpleMatrix matrix) {
        SimpleMatrix a = new SimpleMatrix(1, colsFiltered.length, true, colsFiltered);
        int sum = (int) a.elementSum();
        SimpleMatrix aux = new SimpleMatrix(matrix.numRows(), matrix.numCols() - sum);
        int count = 0;
        for (int i = 0; i < colsFiltered.length; i++) {
            //las columas con datos a quitar estan marcadas con 1
            //las marcadas con 0 será usadas para crear una nueva matriz
            if (colsFiltered[i] == 0) {
                //inicializamos la matriz
                aux.setColumn(count++, 0, matrix.extractVector(false, i).getDDRM().getData());
            }
        }
        return aux;
    }

    /**
     * evaluamos si el vector es una constante
     *
     * @param vector
     * @return
     */
    private boolean constant(SimpleMatrix vector) {
        double val = vector.get(0);
        double[] data = vector.getDDRM().getData();
        boolean constant = true;
        for (int i = 0; i < data.length; i++) {
            if (data[i] != val) {
                constant = false;
                break;
            }
        }
        return constant;
        //return (CommonOps_DDRM.elementMax(vector.getMatrix()) == CommonOps_DDRM.elementMin(vector.getMatrix()));
    }

}
