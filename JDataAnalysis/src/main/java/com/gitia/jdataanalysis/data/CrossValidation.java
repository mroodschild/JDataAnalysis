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
package com.gitia.jdataanalysis.data;

import com.gitia.jdataanalysis.RidgeRegression;

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
