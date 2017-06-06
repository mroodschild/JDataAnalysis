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
package com.gitia.jdataanalysis;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import dnl.utils.text.table.TextTable;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.ejml.simple.SimpleMatrix;

/**
 *
 * @author @author Matías Roodschild <mroodschild@gmail.com>
 */
public class JDataAnalysis {

    private List<CSVRecord> datos;
    private CSVParser parser;
    private boolean isHeader = false;
    private String[] columnNames;
    private String[][] data; //quitar, mal diseño
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
     * false: ocutlar <br>
     *
     * por defecto indicamos que el archivo tiene cabecera
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
     * @param header
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
     * @param show
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
     * @param isHeader
     * @param show
     * @return
     */
    private List<CSVRecord> open(String path, boolean isHeader, boolean show) {
        this.isHeader = isHeader;
        this.path = path;
        obtainData();
        generateColumnNames();
        if (show) {
            show();
        }
        return datos;
    }

    public void save(String[][] data, String[] headers, String folder, String fileName) {
        String NEW_LINE_SEPARATOR = "\n";

        FileWriter fileWriter = null;

        CSVPrinter csvFilePrinter = null;

        //Create the CSVFormat object with "\n" as a record delimiter
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);
        try {
            //initialize FileWriter object
            File file = new File(folder + "/" + fileName);
            fileWriter = new FileWriter(file);

            //initialize CSVPrinter object 
            csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);

            //Create CSV file header
            csvFilePrinter.printRecord(headers);

            //Write a new student object list to the CSV file
            for (int i = 0; i < data.length; i++) {
                //List studentDataRecord = new ArrayList();
                csvFilePrinter.printRecord(data[i]);
            }
            System.out.println("CSV file was created successfully !!!");
            System.out.println(folder + "/" + fileName);

        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
                csvFilePrinter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter/csvPrinter !!!");
                e.printStackTrace();
            }
        }
    }
    
    /**
     * 
     * @param list
     * @param folder
     * @param fileName 
     */
    public void save(List<String> list, String folder, String fileName) {
        String NEW_LINE_SEPARATOR = "\n";

        FileWriter fileWriter = null;

        CSVPrinter csvFilePrinter = null;

        //Create the CSVFormat object with "\n" as a record delimiter
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);
        try {
            //initialize FileWriter object
            File file = new File(folder + "/" + fileName);
            fileWriter = new FileWriter(file);

            //initialize CSVPrinter object 
            csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);
            
            //Create CSV file header
            //csvFilePrinter.printRecord(headers);

            //Write a new student object list to the CSV file
            for (int i = 0; i < list.size(); i++) {
                //List studentDataRecord = new ArrayList();
                csvFilePrinter.printRecord(list.get(i));
            }
            System.out.println("CSV file was created successfully !!!");
            System.out.println(folder + "/" + fileName);

        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
                csvFilePrinter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter/csvPrinter !!!");
                e.printStackTrace();
            }
        }
    }

    public void save(String[] data, String headers, String folder, String fileName) {
        String datos[][] = new String[data.length][1];
        String[] cabeceras = new String[1];
        cabeceras[0] = headers;
        for (int i = 0; i < data.length; i++) {
            datos[i][0] = data[i];
        }
        save(datos, cabeceras, folder, fileName);
    }

    /**
     * aún sin terminar
     *
     * @param data
     * @param selectedFeature
     */
    public void replace(String[] data, String selectedFeature) {
        String[] feature = new String[datos.size()];
        if (data.length == datos.size()) {
            for (int i = 0; i < datos.size(); i++) {
                //   datos.get(i).get(selectedFeature) = data[i];
            }
        } else {
            System.out.println("El tamaño del los datos no coinciden");
        }
    }

    private void obtainData() {
        try {
            CSVFormat csvf;
            if (this.isHeader) {
                csvf = CSVFormat.DEFAULT.withHeader();

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

            } else {
                csvf = CSVFormat.DEFAULT.withIgnoreHeaderCase(isHeader);

                CsvMapper mapper = new CsvMapper();
                // important: we need "array wrapping" (see next section) here:
                mapper.enable(CsvParser.Feature.WRAP_AS_ARRAY);
                File csvFile = new File("src/main/resources/handwrittennumbers/mnist_train_in.csv"); // or from String, URL etc
                MappingIterator<double[]> it = mapper.readerFor(double[].class).readValues(csvFile);
                int a = 1;
                List<double[]> listData = it.readAll();
                double[][] data = new double[listData.size()][listData.get(0).length];
                for (int i = 0; i < listData.size(); i++) {
                    data[i] = listData.get(i);
                    System.out.println(a++ + ":\t");
                }
                SimpleMatrix A = new SimpleMatrix(data);
                A.print();

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
        if (isHeader) {
            columnNames = getHeader(parser.getHeaderMap());
        } else {
            for (int i = 0; i < datos.get(0).size(); i++) {
                columnNames[i] = String.valueOf(i);
            }
        }
    }

    public String[] getColumnNames() {
        return columnNames;
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
     * show N rows
     */
    public void show(int files) {
        int size = files;
        if (files > data.length) {
            size = data.length;
        }
        String[][] selected = getFeatureString(size);
        TextTable textTable = new TextTable(columnNames, selected);
        textTable.printTable();
        System.out.println("...");
        System.out.println("Records (" + data.length + " x " + data[0].length + ")\n");//parser.getHeaderMap().size() + ")\n");
    }

    /**
     * show selected features
     *
     * @param features
     */
    public void show(String... features) {
        String[][] selected = getFeaturesString(features);
        TextTable textTable = new TextTable(features, selected);
        textTable.printTable();
        System.out.println("...");
        System.out.println("Records (" + data.length + " x " + data[0].length + ")\n");
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
     *
     * @param numRows Número de filas a recuperar
     * @return
     */
    public double[][] getFeature(int numRows) {
        int size = numRows;
        if (data.length < numRows) {
            size = data.length;
        }
        double[][] feature = new double[size][data[0].length];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < data[0].length; j++) {
                feature[i][j] = Double.valueOf(data[i][j]);
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
    public String[][] getFeatureString(int numRows) {
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
     * Retorna todas las características
     *
     * @return
     */
    public double[][] getFeatures() {
        int size = data.length;
        return getFeature(size);
    }

    /**
     * Retorna todas las características como String
     *
     * @return
     */
    public String[][] getFeaturesString() {
        int size = data.length;
        return getFeatureString(size);
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
     * get the feature desired in a vector
     *
     * @param selectedFeature
     * @return
     */
    public String[] getFeatureString(String selectedFeature) {
        String[] feature = new String[datos.size()];
        for (int i = 0; i < datos.size(); i++) {
            feature[i] = datos.get(i).get(selectedFeature);
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
     *
     * @param Features
     * @return
     */
    public String[][] getFeaturesString(String... Features) {
        String[][] selected = new String[data.length][Features.length];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < Features.length; j++) {
                selected[i][j] = datos.get(i).get(Features[j]);
            }
        }
        return selected;
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
