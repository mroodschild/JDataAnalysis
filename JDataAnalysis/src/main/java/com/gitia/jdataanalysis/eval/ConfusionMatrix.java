/*
 * Copyright 2016 Matías Roodschild <mroodschild@gmail.com>.
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
package com.gitia.jdataanalysis.eval;

import org.ejml.simple.SimpleMatrix;

/**
 *
 * @author Matías Roodschild <mroodschild@gmail.com>
 */
public class ConfusionMatrix {

    SimpleMatrix conteo;

    public ConfusionMatrix() {
    }

    /**
     * 
     * @param calc
     * @param obs 
     */
    private void eval(SimpleMatrix calc, SimpleMatrix obs) {
        conteo = new SimpleMatrix(obs.numCols(), obs.numCols());
        conteo.set(0);
        
    }

}
