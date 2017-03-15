/*
 * Copyright 2016 Matias.
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

import org.ejml.data.DenseMatrix64F;
import org.ejml.ops.CommonOps;
import org.ejml.simple.SimpleMatrix;

/**
 *
 * @author Matias
 */
public class FilterConstantColumns {

    int[] colsFiltered;

    public FilterConstantColumns() {
    }

    public FilterConstantColumns(int[] colsFiltered) {
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
        colsFiltered = new int[matrix.numCols()];
        for (int i = 0; i < matrix.numCols(); i++) {
            if (constant(matrix.extractVector(false, i))) {
                colsFiltered[i] = 1;
            } else {
                colsFiltered[i] = 0;
            }
        }
    }

    /**
     *
     * Quitamos las columnas que fueron seleccionadas previamente
     * 
     * @param matrix
     * @return
     */
    public SimpleMatrix eval(SimpleMatrix matrix) {
        SimpleMatrix aux = null;
        for (int i = 0; i < colsFiltered.length; i++) {
            //las columas con datos a quitar estan marcadas con 1
            //las marcadas con 0 será usadas para crear una nueva matriz
            if (colsFiltered[i] == 0) {
                if (aux == null) {
                    //inicializamos la matriz
                    aux = matrix.extractVector(false, i).copy();
                } else {
                    //agregamos las columas que tienen información
                    aux = aux.combine(0, SimpleMatrix.END, matrix.extractVector(false, i));
                }
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
        return (CommonOps.elementMax(vector.getMatrix()) == CommonOps.elementMin(vector.getMatrix()));
    }

}
