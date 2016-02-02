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

import org.ejml.simple.SimpleMatrix;

/**
 *
 * @author @author Matías Roodschild <mroodschild@gmail.com>
 */
public class EJMLTest {

    public static void main(String[] args) {
        System.out.println("Hola Mundo");
        double[][] data = {{1, 2, 3}, {1, 2, 3}, {1, 2, 3}};
        double[][] w = {{1, 2, 3}};
        double[][] x = {{1, 2, 3}};
        double[][] a = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

//        SimpleMatrix W = new SimpleMatrix(w);
//        SimpleMatrix X = new SimpleMatrix(x);
//        SimpleMatrix S;
//        W.transpose().print();
//        X.print();
//        S = W.transpose().mult(X);
//        S.print();
//        S = W.mult(X.transpose());
//        S.print();
        SimpleMatrix h = new SimpleMatrix(a);
        h.print();
        h.transpose().print();
        h.transpose().transpose().print();
    }

}
