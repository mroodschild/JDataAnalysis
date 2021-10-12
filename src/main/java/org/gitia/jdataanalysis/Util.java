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
package org.gitia.jdataanalysis;

//import dnl.utils.text.table.TextTable;
import java.util.Arrays;
//import javafx.scene.chart.XYChart;
import org.ejml.simple.SimpleMatrix;

/**
 *
 * @author Matias Roodschild <mroodschild@gmail.com>
 */
public class Util implements java.io.Serializable {

    /**
     * convierte una arquitectura double[] en un String
     *
     * @param arquitectura
     * @return
     */
    public static String arquitecturaConvertirString(int[] arquitectura) {
        String arquitecturaNeuronas = "";
        for (int j = 0; j < arquitectura.length; j++) {
            arquitecturaNeuronas += " " + arquitectura[j];
        }
        return arquitecturaNeuronas;
    }

    /**
     * Imprime la matriz en la consola
     *
     * @param matriz
     * @param nombre
     */
    public static void mostrarMatriz(double[][] matriz, String nombre) {
        System.out.println("Matriz: " + nombre);
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.printf("%.3f\t", matriz[i][j]);
            }
            System.out.println("");
        }
    }

    /**
     * Imprime la matriz en la consola
     *
     * @param matriz
     * @param nombre
     */
    public static void mostrarMatriz(int[][] matriz, String nombre) {
        System.out.println("Matriz: " + nombre);
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.printf("%d\t", matriz[i][j]);
            }
            System.out.println("");
        }
    }

    /**
     * precisión %.3f
     *
     * @param vector [1, 4, 6, 8, 3]
     * @param nombre null no muestra el nombre
     */
    public static void mostrarVector(double[] vector, String nombre) {
        mostrarVector(vector, nombre, "%.3f\t");
    }

    /**
     *
     * @param vector
     * @param nombre null no muestra nombre
     * @param format example: "%.5f\t"
     */
    public static void mostrarVector(double[] vector, String nombre, String format) {
        if (nombre != null) {
            System.out.println("Vector " + nombre + ":");
        }
        for (int i = 0; i < vector.length; i++) {
            System.out.printf(format, vector[i]);
        }
        System.out.println("");
    }

    public static void mostrarVector(String nombre, double[]... vectores) {
        System.out.println("Vectores " + nombre + ":");
        for (int j = 0; j < vectores[0].length; j++) {
            for (int i = 0; i < vectores.length; i++) {
                System.out.printf("%.3f\t", vectores[i][j]);
            }
            System.out.println("");
        }
        System.out.println("");
    }

//    public static void show(String[] headers, double[]... vectores) {
//        //TextTable textTable = new TextTable(headers, vectores);
//        TextTable textTable = new TextTable(headers, convertVectorToArray(vectores));
//        textTable.printTable();
//    }

    public static void mostrarVector(int[] vector, String nombre) {
        System.out.println("Vector " + nombre + ":");
        for (int i = 0; i < vector.length; i++) {
            System.out.printf("%d \t", vector[i]);
        }
        System.out.println("");
    }

    /**
     * esta función copia una matriz en otra, a la matriz destino la inicializa
     * y copia las filas y columnas de la origen
     *
     * @param destino
     * @param origen
     */
    public static void matrizCopiar(double[][] destino, double[][] origen) {
        for (int i = 0; i < origen.length; i++) {
            System.arraycopy(origen[i], 0, destino[i], 0, origen[i].length);
        }
    }

    /**
     * obtenemos la ultima columna de una matriz (útil para sacar los
     * resultados)
     *
     * @param datos
     * @return
     */
    public static double[] matrizUltimaColumna(double[][] datos) {
        double salida[] = new double[datos.length];
        for (int i = 0; i < salida.length; i++) {
            salida[i] = datos[i][datos[0].length - 1];
        }
        return salida;
    }

    /**
     * tomamos los datos que son utilizados para la entrada de la red neuronal
     *
     * @param datos
     * @return
     */
    public static double[][] matrizQuitarUltimaColumna(double[][] datos) {
        double[][] salida = new double[datos.length][datos[0].length - 1];
        for (int i = 0; i < datos.length; i++) {
            System.arraycopy(datos[i], 0, salida[i], 0, datos[i].length - 1);
        }
        return salida;
    }

    // /**
    //  *
    //  * @param serie
    //  */
    // public static void mostrarXYChartSeries(XYChart.Series serie) {
    //     // for (int i = 0; i < serie.getData().size(); i++) {
    //     //     System.out.println(serie.getData().get(i).toString());
    //     // }
    // }

    /**
     *
     * @param vector
     * @return
     */
    public static Double[][] convertVectorToArray(double[]... vector) {
        Double data[][] = new Double[vector[0].length][vector.length];
        for (int i = 0; i < vector.length; i++) {
            for (int j = 0; j < vector[0].length; j++) {
                data[j][i] = vector[i][j];
            }
        }
        return data;
    }

    /**
     *
     * @param vector
     * @return
     */
    public static String[] convertVectorToString(double[] vector) {
        String data[] = new String[vector.length];
        for (int i = 0; i < vector.length; i++) {
            data[i] = String.valueOf(vector[i]);
        }
        return data;
    }

    /**
     *
     * @param vector
     * @return
     */
    public static String[][] convertMatrixToString(double[][] vector) {
        String data[][] = new String[vector.length][vector[0].length];
        for (int i = 0; i < vector.length; i++) {
            for (int j = 0; j < vector[0].length; j++) {
                data[i][j] = String.valueOf(vector[i][j]);
            }
        }
        return data;
    }

    public static int[] ordenarVector(int[] vector) {
        int[] aux = new int[vector.length];
        System.arraycopy(vector, 0, aux, 0, vector.length);
        Arrays.sort(aux);
        return aux;
    }
    
    public static double[][] toArray(SimpleMatrix matrix) {
        double array[][] = new double[matrix.numRows()][matrix.numCols()];
        for (int r = 0; r < matrix.numRows(); r++) {
            array[r] = matrix.extractVector(true, r).getDDRM().getData();
        }
        return array;
    }
}
