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
package org.gitia.jdataanalysis.data;

import org.gitia.jdataanalysis.RidgeRegression;

/**
 *
 * @author @author Matías Roodschild <mroodschild@gmail.com>
 */
public class CrossValidation {

    int k;
    double l2_penalty;
    double[][] input;
    double[] output;
    RidgeRegression[] modelos;

    public CrossValidation() {
    }

    /**
     * 
     * @param k partes en la que será dividido el conjunto de datos
     * @param l2_penalty penalidad del Ridge Regression
     * @param input Datos de Entrada
     * @param output Datos de Salida
     * @return Retorna los modelos de RidgeRegression
     */
    public RidgeRegression[] kFoldCrossValidation(int k, double l2_penalty, double[][] input, double[] output) {
        this.k = k;
        this.l2_penalty = l2_penalty;
        this.input = input;
        this.output = output;
        modelos = new RidgeRegression[k];
        entrenarModelos();
        return modelos;
    }

    /**
     * Entrenamos todos los modelos
     * 
     * @return
     */
    private double entrenarModelos() {
        double sum = 0;
        double cost = 0;
        double sumVal = 0;
        double costVal = 0;
        for (int i = 0; i < k; i++) {
            double[][] xTrain = getCrossTrain(i, k, input);
            double[] yTrain = getCrossTrain(i, k, output);
            double[][] xVal = getCrossValidation(i, k, input);
            double[] yVal = getCrossValidation(i, k, output);
            modelos[i] = new RidgeRegression(l2_penalty);
            cost = modelos[i].fit(xTrain, yTrain);
            costVal = modelos[i].fit(xVal, yVal);
            sum += cost;
            sumVal += costVal;
        }
        System.out.println("Average cost: " + sum / (double) modelos.length);
        System.out.println("Average cost Validating: " + sumVal / (double) modelos.length);
        return sumVal / (double) modelos.length;
    }

    /**
     *
     * @param k particiones del k-fold
     * @param sizeDatos cantidad de datos
     * @return retornamos una matriz con los índices start / end
     */
    public int[][] getCrossIndex(int k, int sizeDatos) {
        int[][] index = new int[k][2];
        int start, end;
        int n = sizeDatos;
        for (int i = 0; i < k; i++) {
            start = (n * i) / k;
            end = (n * (i + 1)) / k - 1;
            index[i][0] = start;
            index[i][1] = end;
            //System.out.println("Ki: " + i + "\tStart: " + start + "\tEnd: " + end);
        }
        return index;
    }

    /**
     *
     * @param part parte seleccionada
     * @param k particiones a realizar
     * @param datos características seleccionadas
     * @return datos extraídos
     */
    public double[][] getCrossTrain(int part, int k, double[][] datos) {
        int[][] idx = getCrossIndex(k, datos.length);
        int size = 0;
        double[][] data;
        for (int i = 0; i < k; i++) {//recorremos todas las partes
            if (i != k - (part + 1)) {//ignoramos las partes que no seran cargadas
                size = size + idx[i][1] - idx[i][0] + 1;//el 1, corresponde al inicio del conteo
            }
        }
        data = new double[size][datos[0].length];//creamos el espacio para el conjunto

        int count = 0;
        for (int i = 0; i < k; i++) {//recorremos todas las partes
            if (i != k - (part + 1)) {//salteamos las partes que son de validación
                for (int j = idx[i][0]; j < idx[i][1]; j++) { //recorremos todos los valores del indice
                    System.arraycopy(datos[j], 0, data[count], 0, datos[0].length); //copiamos los valores
                    count++;//incrementamos la fila del dato
                }
            }
        }
        return data;
    }

    /**
     *
     * @param part parte seleccionada
     * @param k particiones a realizar
     * @param datos características seleccionadas
     * @return datos extraídos
     */
    public double[] getCrossTrain(int part, int k, double[] datos) {
        int[][] idx = getCrossIndex(k, datos.length);
        int size = 0;
        double[] data;
        for (int i = 0; i < k; i++) {//recorremos todas las partes
            if (i != k - (part + 1)) {//ignoramos las partes que no seran cargadas
                size = size + idx[i][1] - idx[i][0] + 1;//el 1, corresponde al inicio del conteo
            }
        }
        data = new double[size];//creamos el espacio para el conjunto

        int count = 0;
        for (int i = 0; i < k; i++) {//recorremos todas las partes
            if (i != k - (part + 1)) {//salteamos las partes que son de validación
                for (int j = idx[i][0]; j < idx[i][1]; j++) { //recorremos todos los valores del indice
                    data[count] = datos[j];//copiamos los valores
                    count++;//incrementamos la fila del dato
                }
            }
        }
        return data;
    }

    /**
     *
     * @param part
     * @param k
     * @param datos
     * @return datos de validación
     */
    public double[][] getCrossValidation(int part, int k, double[][] datos) {
        int[][] idx = getCrossIndex(k, datos.length);
        int size = 0;
        double[][] data;
        for (int i = 0; i < k; i++) {//recorremos todas las partes
            if (i == k - (part + 1)) {//ignoramos las partes que no seran cargadas
                size = size + idx[i][1] - idx[i][0] + 1;//el 1, corresponde al inicio del conteo
            }
        }

        data = new double[size][datos[0].length];//creamos el espacio para el conjunto

        int count = 0;
        for (int i = 0; i < k; i++) {//recorremos todas las partes
            if (i == k - (part + 1)) {//salteamos las partes que son de validación
                for (int j = idx[i][0]; j < idx[i][1]; j++) { //recorremos todos los valores del indice
                    System.arraycopy(datos[j], 0, data[count], 0, datos[0].length); //copiamos los valores
                    count++;//incrementamos la fila del dato
                }
            }
        }
        return data;
    }

    /**
     *
     * @param part
     * @param k
     * @param datos
     * @return datos de validación
     */
    public double[] getCrossValidation(int part, int k, double[] datos) {
        int[][] idx = getCrossIndex(k, datos.length);
        int size = 0;
        double[] data;
        for (int i = 0; i < k; i++) {//recorremos todas las partes
            if (i == k - (part + 1)) {//ignoramos las partes que no seran cargadas
                size = size + idx[i][1] - idx[i][0] + 1;//el 1, corresponde al inicio del conteo
            }
        }

        data = new double[size];//creamos el espacio para el conjunto

        int count = 0;
        for (int i = 0; i < k; i++) {//recorremos todas las partes
            if (i == k - (part + 1)) {//salteamos las partes que son de validación
                for (int j = idx[i][0]; j < idx[i][1]; j++) { //recorremos todos los valores del indice
                    data[count] = datos[j];//copiamos los valores
                    count++;//incrementamos la fila del dato
                }
            }
        }
        return data;
    }

}
