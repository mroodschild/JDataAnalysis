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
package com.gitia.jdataanalysis.data;

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
