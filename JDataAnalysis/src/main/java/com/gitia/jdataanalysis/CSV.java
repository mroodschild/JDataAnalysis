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
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
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
    public static SimpleMatrix open(String path) throws IOException{
        double[][] data = null;
//        try {
            CsvMapper mapper = new CsvMapper();
            mapper.enable(CsvParser.Feature.WRAP_AS_ARRAY);
            File csvFile = new File(path);
            MappingIterator<double[]> it = mapper.readerFor(double[].class).readValues(csvFile);
            List<double[]> listData = it.readAll();
            data = new double[listData.size()][listData.get(0).length];
            for (int i = 0; i < listData.size(); i++) {
                data[i] = listData.get(i);
            }
//        } catch (IOException ex) {
//            Logger.getLogger(CSV.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return new SimpleMatrix(data);
    }
    
    /**
     * @param listOfMap
     * @param writer
     * @throws IOException
     */
    public static void write(SimpleMatrix matrix, String Name) throws IOException {
        File file = new File(Name);
        Writer writer = new FileWriter(file, false);
        CsvSchema schema = null;
        CsvSchema.Builder schemaBuilder = CsvSchema.builder();
        schema = schemaBuilder.build().withLineSeparator("\r").withoutHeader();
        CsvMapper mapper = new CsvMapper();
        mapper.writer(schema).writeValues(writer).writeAll(Util.toArray(matrix));
        writer.flush();
    }
}
