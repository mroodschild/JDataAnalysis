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
package org.gitia.jdataanalysis.data;

/**
 *
 * @author Matías Roodschild <mroodschild@gmail.com>
 */
public class Normalize {

    private double min[];
    private double max[];

    /**
     * normalizamos los datos entre 0 y 1, según un máximo y un mínimo
     *
     * @param data
     * @return
     */
    public double[][] normalize(double[][] data) {
        findMaxMin(data);
        return reduce(data);
    }

    /**
     * reducimos los datos entre 0 y 1
     *
     * @param data
     * @return
     */
    private double[][] reduce(double[][] data) {
        double aux[][] = new double[data.length][data[0].length];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                aux[i][j] = reducir(data[i][j], getMin()[j], getMax()[j]);
            }
        }
        return aux;
    }

    /**
     *
     * @param x valor a reducir
     * @param min mínimo del rango de valores
     * @param max máximo del rango de valores
     * @return valor reducido
     */
    private double reducir(double x, double min, double max) {
        /**
         * para evitar NaN cuando haya una columna toda llena con el mismo
         * parámetro
         */
        if ((min - max) == 0) {
            return -1;
        } else {
            return ((2 * x - min - max) / (max - min));
        }
    }

    /**
     * Buscamos los máximos y Mínimos del conjunto de datos
     *
     * @param data
     */
    private void findMaxMin(double[][] data) {
        max = new double[data[0].length];
        min = new double[data[0].length];
        //ponemos como maximo el primer valor de todas las columnas
        System.arraycopy(data[0], 0, getMax(), 0, data[0].length);
        //ponemos como mínimo el primer valor de todas las columnas
        System.arraycopy(data[0], 0, getMin(), 0, data[0].length);
        //recorremos todos los valores buscando los máximos
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                if (getMax()[j] < data[i][j]) {
                    max[j] = data[i][j];
                }
                if (getMin()[j] > data[i][j]) {
                    min[j] = data[i][j];
                }
            }
        }
    }

    /**
     * @return the min
     */
    public double[] getMin() {
        return min;
    }

    /**
     * @return the max
     */
    public double[] getMax() {
        return max;
    }

}
