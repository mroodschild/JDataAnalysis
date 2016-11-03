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

import org.ejml.ops.CommonOps;
import org.ejml.simple.SimpleMatrix;

/**
 *
 * @author Matias
 */
public class Sum {
    
    /**
     * Calculamos la Suma de un conjunto de datos<br>
     * es decir: <br><br>
     * si X = [4, 5, 6]<br>
     * Xm = (4 + 5 + 6)<br>
     * Xm = 15
     *
     * @param datos
     * @return
     */
    public static double sum(double[] datos) {
        double size = datos.length;
        double sum = 0;
        for (int i = 0; i < size; i++) {
            sum = sum + datos[i];
        }
        return sum;
    }

    /**
     * Calculamos la Suma para una matriz de datos<br>
     * es decir: <br><br>
     * si X =
     * <table>
     * <tr>
     * <td>8 </td><td>2 </td><td>9</td>
     * </tr>
     * <tr>
     * <td>7 </td><td>8 </td><td>9.</td>
     * </tr>
     * </table>
     * 
     * Xm = [15, 10, 18]
     *
     * @param datos
     * @return
     */
    public static SimpleMatrix sum(SimpleMatrix datos) {
        return new SimpleMatrix(CommonOps.sumCols(datos.getMatrix(), null));
    }
}
