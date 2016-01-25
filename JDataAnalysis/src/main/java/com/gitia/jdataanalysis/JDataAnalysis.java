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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
            System.out.println("Header Map Size: " + parser.getHeaderMap().size());

            for (int i = 0; i < datos.size(); i++) {
                CSVRecord next = datos.get(i);
                int csvrSize = next.size();
                for (int j = 0; j < csvrSize; j++) {
                    System.out.printf("%s\t", next.get(j));
                }
                System.out.printf("\n");
                
            }
            System.out.println("Records ( " + parser.getHeaderMap().size() + " x " + datos.size() + ")");
            parser.close();
            return datos;
        } catch (IOException ex) {
            Logger.getLogger(JDataAnalysis.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
