/*
 * Copyright 2017 Matías Roodschild <mroodschild@gmail.com>.
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

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.ejml.simple.SimpleMatrix;

/**
 *
 * @author Matías Roodschild <mroodschild@gmail.com>
 */
public class CSV {

    public CSV() {
    }

    /**
     * Debemos abrir un archivo csv sin cabecera
     *
     * @param path "src/main/resources/handwrittennumbers/mnist_train_in.csv"
     * @return
     */
    public static SimpleMatrix open(String path){
        double[][] data = null;
        try {
            CsvMapper mapper = new CsvMapper();
            mapper.enable(CsvParser.Feature.WRAP_AS_ARRAY);
            File csvFile = new File(path);
            MappingIterator<double[]> it = mapper.readerFor(double[].class).readValues(csvFile);
            List<double[]> listData = it.readAll();
            data = new double[listData.size()][listData.get(0).length];
            for (int i = 0; i < listData.size(); i++) {
                data[i] = listData.get(i);
            }
        } catch (IOException ex) {
            Logger.getLogger(CSV.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new SimpleMatrix(data);
    }
}
