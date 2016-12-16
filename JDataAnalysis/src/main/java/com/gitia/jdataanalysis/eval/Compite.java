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
