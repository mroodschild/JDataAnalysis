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
public class Mean {

    /**
     * Calculamos la Media de un conjunto de datos<br>
     * es decir: <br><br>
     * si X = [4, 5, 6]<br>
     * Xm = (4 + 5 + 6) / 3<br>
     * Xm = 7.5
     *
     * @param datos
     * @return
     */
    public static double mean(double[] datos) {
        double size = datos.length;
        return Sum.sum(datos)/size;
    }

    /**
     * Calculamos la Media para una matriz de datos<br>
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
     * Xm = [7.5, 5, 9]
     *
     * @param datos
     * @return
     */
    public static SimpleMatrix mean(SimpleMatrix datos) {
        double size = datos.numRows();
        return Sum.sum(datos).divide(size);
    }
}
