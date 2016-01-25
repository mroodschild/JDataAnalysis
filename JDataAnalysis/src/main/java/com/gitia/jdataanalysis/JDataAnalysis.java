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

    /**
     * this function open a file and show the info
     *
     * @param path
     */
    public List<CSVRecord> open(String path) {

        try {
            CSVParser parser = new CSVParser(
                    new FileReader(path),
                    CSVFormat.DEFAULT.withHeader()
            );
            List<CSVRecord> list = IteratorUtils.toList(parser.iterator());
            System.out.println("Size List: " + list.size());

            for (int i = 0; i < list.size(); i++) {
                CSVRecord next = list.get(i);
                int csvrSize = next.size();
                for (int j = 0; j < csvrSize; j++) {
                    System.out.printf("%s\t", next.get(j));
                }
                System.out.printf("\n");
            }
//            System.out.println(parser.getRecords().size());
//            for (int i = 0; i < parser.getRecords().size(); i++) {
//                CSVRecord csvr = parser.getRecords().get(i);
//                int csvrSize = csvr.size();
//                for (int j = 0; j < csvrSize; j++) {
//                    System.out.printf("%s\t", csvr.get(j));
//                }
//                System.out.printf("\n");
//            }

//            for (CSVRecord record : parser) {
//
//                System.out.printf("id %s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\n",
//                        parser.getRecordNumber(),
//                        record.get("HousePrice"),
//                        record.get("HsPrc ($10,000)"),
//                        record.get("CrimeRate"),
//                        record.get("MilesPhila"),
//                        record.get("PopChg"),
//                        record.get("Name"),
//                        record.get("County"),
//                        Double.valueOf(record.get("CrimeRate")) + Double.valueOf(record.get("HsPrc ($10,000)"))
//                );
//            }
            parser.close();
            return list;
        } catch (IOException ex) {
            Logger.getLogger(JDataAnalysis.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
