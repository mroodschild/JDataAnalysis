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

import java.util.List;
import org.apache.commons.csv.CSVRecord;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author @author Matías Roodschild <mroodschild@gmail.com>
 */
public class JDataAnalysisTest {

    static JDataAnalysis data;

    public JDataAnalysisTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        String folder = "src/main/resources/week4/quiz2/";
        String train_valid_shuffled = folder + "wk3_kc_house_train_valid_shuffled.csv";
        data = new JDataAnalysis(train_valid_shuffled);
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getCrossData method, of class JDataAnalysis.
     */
    @Test
    public void testGetCrossData() {
        System.out.println("getCrossData");
        int k = 4;
        String[] features = {"price"};
        data.getCrossData(k, features);
        assertEquals(0, 0, 0);
    }

}
