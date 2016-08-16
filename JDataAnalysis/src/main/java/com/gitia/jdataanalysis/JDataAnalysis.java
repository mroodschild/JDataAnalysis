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

import dnl.utils.text.table.TextTable;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author @author Matías Roodschild <mroodschild@gmail.com>
 */
public class JDataAnalysis {

    private List<CSVRecord> datos;
    private CSVParser parser;
    private boolean header = false;
    private String[] columnNames;
    private String[][] data;
    private String path;

    public JDataAnalysis() {
    }

    /**
     * Abrimos el Archivo seleccionado y <br>
     * por defecto mostramos los 10 primeros elementos
     *
     * @param path
     */
    public JDataAnalysis(String path) {
        open(path, true, true);
    }

    /**
     * Abrimos el Archivo seleccionado e <br>
     * indicamos si mostramos los 10 primeros elementos.<br>
     *
     * true: mostrar <br>
     * false: ocutlar
     *
     * @param path
     * @param show
     */
    public JDataAnalysis(String path, boolean show) {
        open(path, true, show);
    }

    /**
     * Abrimos el Archivo seleccionado e <br>
     * indicamos si mostramos los 10 primeros elementos.<br>
     *
     * true: mostrar <br>
     * false: ocutlar
     *
     * @param path
     * @param show
     */
    public JDataAnalysis(String path, boolean header, boolean show) {
        open(path, header, show);
    }

    /**
     * Abrimos el Archivo seleccionado y <br>
     * por defecto mostramos los 10 primeros elementos
     *
     * @param path
     * @return
     */
    public List<CSVRecord> open(String path) {
        return open(path, true, true);
    }

    /**
     * Abrimos el Archivo seleccionado e <br>
     * indicamos si mostramos los 10 primeros elementos.<br>
     *
     * true: mostrar <br>
     * false: ocutlar
     *
     * @param path
     * @return
     */
    public List<CSVRecord> open(String path, boolean show) {
        return open(path, true, true);
    }

    /**
     * Abrimos el Archivo seleccionado e <br>
     * indicamos si mostramos los 10 primeros elementos.<br>
     *
     * true: mostrar <br>
     * false: ocutlar
     *
     * @param path
     * @return
     */
    public List<CSVRecord> open(String path, boolean header, boolean show) {
        this.header = header;
        this.path = path;
        obtainData();
        generateColumnNames();
        if (show) {
            show();
        }
        return datos;
    }

    private void obtainData() {
        try {
            CSVFormat csvf;
            if (this.header) {
                csvf = CSVFormat.DEFAULT.withHeader();
            } else {
                csvf = CSVFormat.DEFAULT.withIgnoreHeaderCase(header);
            }
            parser = new CSVParser(
                    new FileReader(path),
                    csvf
            );
            datos = IteratorUtils.toList(parser.iterator());
            data = new String[datos.size()][datos.get(0).size()];
            for (int i = 0; i < datos.size(); i++) {
                for (int j = 0; j < datos.get(0).size(); j++) {
                    data[i][j] = datos.get(i).get(j);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(JDataAnalysis.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void generateColumnNames() {
        columnNames = new String[datos.get(0).size()];
        if (header) {
            columnNames = getHeader(parser.getHeaderMap());
        } else {
            for (int i = 0; i < datos.get(0).size(); i++) {
                columnNames[i] = String.valueOf(i);
            }
        }
    }

    /**
     * show 10 rows
     */
    public void show() {
        show(10);
    }
    
    /**
     * show all rows
     */
    public void showAll() {
        show(data.length);
    }

    /**
     * show 10 rows
     */
    public void show(int files) {
        int size = files;
        if (files > data.length) {
            size = data.length;
        }
        String[][] selected = getFeature(size);
        TextTable textTable = new TextTable(columnNames, selected);
        textTable.printTable();
        System.out.println("...");
        System.out.println("Records (" + data.length + " x " + data[0].length + ")\n");//parser.getHeaderMap().size() + ")\n");
    }

    /**
     * show selected headers
     *
     * @param headers
     */
    public void show(String... headers) {
        printHeaders(headers);
        for (int i = 0; i < datos.size(); i++) {
            CSVRecord next = datos.get(i);
            int csvrSize = next.size();
            for (int j = 0; j < headers.length; j++) {
                System.out.printf("%s\t", next.get(headers[j]));
            }
            System.out.printf("\n");
        }
        System.out.println("Records (" + datos.size() + " x " + headers.length + ")\n");
    }

    /**
     * print headers
     *
     * @param headers
     */
    private void printHeaders(String... headers) {
        for (int i = 0; i < headers.length; i++) {
            System.out.printf("%s\t", headers[i]);
        }
        System.out.println("");
    }

    /**
     * Obtenemos los nombres de las columnas
     *
     * @param headerMap
     * @return
     */
    private String[] getHeader(Map<String, Integer> headerMap) {
        String[] columNames = null;
        if (headerMap != null) {
            columNames = new String[headerMap.size()];
            int count = 0;
            for (Map.Entry<String, Integer> entry : headerMap.entrySet()) {
                columNames[count] = entry.getKey();
                count++;
            }
        }
        return columNames;
    }

    /**
     * create a simple linear regression
     *
     * @param inputFeature
     * @param outputFeature
     * @return
     */
    public SimpleLinearRegression simpleLinearRegression(String inputFeature, String outputFeature) {
        double[] input = getFeature(inputFeature);
        double[] output = getFeature(outputFeature);
        SimpleLinearRegression simpleLinearRegression = new SimpleLinearRegression(input, output);
        return simpleLinearRegression;
    }

    /**
     * get the feature desired in a vector
     *
     * @param numRows Número de filas a recuperar
     * @return
     */
    public String[][] getFeature(int numRows) {
        int size = numRows;
        if (data.length < numRows) {
            size = data.length;
        }
        String[][] feature = new String[size][data[0].length];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < data[0].length; j++) {
                feature[i][j] = data[i][j];
            }
        }
        return feature;
    }

    /**
     * get the feature desired in a vector
     *
     * @param numRows Número de filas a recuperar
     * @return
     */
    public String[][] getFeatures() {
        int size = data.length;
        return getFeature(size);
    }

    /**
     * get the feature desired in a vector
     *
     * @param selectedFeature
     * @return
     */
    public double[] getFeature(String selectedFeature) {
        double[] feature = new double[datos.size()];
        for (int i = 0; i < datos.size(); i++) {
            feature[i] = Double.valueOf(datos.get(i).get(selectedFeature));
        }
        return feature;
    }

    /**
     *
     * @param Features
     * @return
     */
    public double[][] getFeatures(String... Features) {
        double[][] data = new double[datos.size()][Features.length];
        for (int i = 0; i < datos.size(); i++) {
            for (int j = 0; j < Features.length; j++) {
                data[i][j] = Double.valueOf(datos.get(i).get(Features[j]));
            }

        }
        return data;
    }

    /**
     * Con esta función obtenemos el polinomio de una característica dada. y[][]
     * = x1^1 , x1^2 , ... , x1^poly poly >= 1
     *
     * @param feature
     * @param polynomial >=1
     * @return
     */
    public double[][] getPolynomial(String feature, int polynomial) {
        double aux[] = getFeature(feature);
        int cantExponentes = polynomial;
        if (polynomial == 0) {
            cantExponentes = 1;
            polynomial = 1;
        }
        double data[][] = new double[aux.length][polynomial];
        int cantDatos = aux.length;
        for (int j = 0; j < cantExponentes; j++) {
            if (j == 0) {
                for (int i = 0; i < cantDatos; i++) {
                    data[i][j] = aux[i];
                }
            } else {
                for (int i = 0; i < cantDatos; i++) {
                    data[i][j] = Math.pow(aux[i], j + 1);
                }
            }
        }
        return data;
    }

    public double mean(String selectedFeature) {
        double mean = 0;
        double sum = 0;
        for (int i = 0; i < datos.size(); i++) {
            sum = sum + Double.valueOf(datos.get(i).get(selectedFeature));
        }
        return sum / datos.size();
    }

    public void statistics(String selectedFeature) {
        double mean;
        double sum = 0;
        double num;
        double min = Double.valueOf(datos.get(0).get(selectedFeature));
        double max = Double.valueOf(datos.get(0).get(selectedFeature));
        for (int i = 0; i < datos.size(); i++) {
            num = Double.valueOf(datos.get(i).get(selectedFeature));
            sum = sum + num;
            if (num < min) {
                min = num;
            }
            if (num > max) {
                max = num;
            }
        }
        mean = sum / (double) datos.size();
        System.out.println("mean: " + mean + "\tmax: " + max + "\tmin: " + min);
    }

    /**
     *
     * @param k
     * @param features
     * @return retornamos una matriz con los indices start/end
     */
    public int[][] getCrossIndex(int k, String... features) {
        int[][] index = new int[k][2];
        int start, end;
        int n = getFeatures(features).length;
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
     * @param features características seleccionadas
     * @return datos extraídos
     */
    public double[][] getCrossTrain(int part, int k, String... features) {
        int[][] idx = getCrossIndex(k, features);
        int size = 0;
        double[][] data;
        double[][] aux = getFeatures(features);//cargamos los datos
        for (int i = 0; i < k; i++) {//recorremos todas las partes
            if (i != k - (part + 1)) {//ignoramos las partes que no seran cargadas
                size = size + idx[i][1] - idx[i][0] + 1;//el 1, corresponde al inicio del conteo
            }
        }
        data = new double[size][features.length];//creamos el espacio para el conjunto

        int count = 0;
        for (int i = 0; i < k; i++) {//recorremos todas las partes
            if (i != k - (part + 1)) {//salteamos las partes que son de validación
                for (int j = idx[i][0]; j < idx[i][1]; j++) { //recorremos todos los valores del indice
                    System.arraycopy(aux[j], 0, data[count], 0, features.length); //copiamos los valores
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
     * @param features
     * @return datos de validación
     */
    public double[][] getCrossValidation(int part, int k, String... features) {
        int[][] idx = getCrossIndex(k, features);
        int size = 0;
        double[][] data;
        double[][] aux = getFeatures(features);//cargamos los datos
        for (int i = 0; i < k; i++) {//recorremos todas las partes
            if (i == k - (part + 1)) {//ignoramos las partes que no seran cargadas
                size = size + idx[i][1] - idx[i][0] + 1;//el 1, corresponde al inicio del conteo
            }
        }
        data = new double[size][features.length];//creamos el espacio para el conjunto

        int count = 0;
        for (int i = 0; i < k; i++) {//recorremos todas las partes
            if (i == k - (part + 1)) {//salteamos las partes que son de validación
                for (int j = idx[i][0]; j < idx[i][1]; j++) { //recorremos todos los valores del indice
                    System.arraycopy(aux[j], 0, data[count], 0, features.length); //copiamos los valores
                    count++;//incrementamos la fila del dato
                }
            }
        }
        return data;
    }

}
