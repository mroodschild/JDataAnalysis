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
