/*
 * Copyright 2016 @author Matías Roodschild <mroodschild@gmail.com>.
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
package com.gitia.jdataanalysis;

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
     */
    public static SimpleMatrix addColumnAfter(SimpleMatrix matrix) {
        SimpleMatrix c = new SimpleMatrix(matrix.numRows(), matrix.numCols() + 1);
        c.insertIntoThis(0, 0, matrix);//copy matrix b in matrix c, in the position selected
        return c;
    }
    
    public static SimpleMatrix setColumn(SimpleMatrix matrix, int col, double val){
        int size = matrix.numRows();
        for (int i = 0; i < size; i++) {
            matrix.set(i, col, val);
        }
        return matrix;
    }

}
