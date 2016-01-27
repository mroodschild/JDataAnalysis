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

    /**
     * this function open a file and show the info
     *
     * @param path
     */
    public List<CSVRecord> open(String path) {

        try {
            parser = new CSVParser(
                    new FileReader(path),
                    CSVFormat.DEFAULT.withHeader()
            );
            datos = IteratorUtils.toList(parser.iterator());
            System.out.println("");
            show();
            //parser.close();
            return datos;
        } catch (IOException ex) {
            Logger.getLogger(JDataAnalysis.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * show 10 rows
     */
    public void show() {
        printHeader(parser.getHeaderMap());
        for (int i = 0; i < datos.size() && i < 10; i++) {
            CSVRecord next = datos.get(i);
            int csvrSize = next.size();
            for (int j = 0; j < csvrSize; j++) {
                System.out.printf("%s\t", next.get(j));
            }
            System.out.printf("\n");
        }
        System.out.println("...");
        System.out.println("Records ( " + parser.getHeaderMap().size() + " x " + datos.size() + ")\n");
    }

    /**
     * show all rows
     */
    public void showAll() {
        printHeader(parser.getHeaderMap());
        for (int i = 0; i < datos.size(); i++) {
            CSVRecord next = datos.get(i);
            int csvrSize = next.size();
            for (int j = 0; j < csvrSize; j++) {
                System.out.printf("%s\t", next.get(j));
            }
            System.out.printf("\n");
        }
        System.out.println("Records ( " + parser.getHeaderMap().size() + " x " + datos.size() + ")\n");
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
        System.out.println("Records ( " + headers.length + " x " + datos.size() + ")\n");
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

    private void printHeader(Map<String, Integer> headerMap) {
        for (Map.Entry<String, Integer> entry : headerMap.entrySet()) {
            String key = entry.getKey();
            //Integer value = entry.getValue();
            System.out.printf("%s\t", key);
        }
        System.out.println("");
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
}
