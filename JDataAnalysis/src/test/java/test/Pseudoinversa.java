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
package test;

import org.ejml.simple.SimpleMatrix;

/**
 *
 * @author Matías Roodschild <mroodschild@gmail.com>
 */
public class Pseudoinversa {

    public static void main(String[] args) {
        SimpleMatrix A = new SimpleMatrix(8, 6, true,
                64, 2, 3, 61, 60, 6,
                9, 55, 54, 12, 13, 51,
                17, 47, 46, 20, 21, 43,
                40, 26, 27, 37, 36, 30,
                32, 34, 35, 29, 28, 38,
                41, 23, 22, 44, 45, 19,
                49, 15, 14, 52, 53, 11,
                8, 58, 59, 5, 4, 62);
        SimpleMatrix b = new SimpleMatrix(8, 1, true, 260, 260, 260, 260, 260, 260, 260, 260);
        System.out.println("A");
        A.print();
        System.out.println("B");
        b.print();
        System.out.println("x = A.pseudoInverse().mult(b);");
        SimpleMatrix x = A.pseudoInverse().mult(b);
        x.print("%.4f");
        System.out.println("y = A.solve(b);");
        SimpleMatrix y = A.solve(b);
        y.print();
        System.out.println("Norm(x):\t"+x.normF());
        System.out.println("Norm(y):\t"+y.normF());
        
        
        A.pseudoInverse().print("%.4f");
        //A.mult(A.pseudoInverse()).print("%.4f");
        A.pseudoInverse().mult(A).print("%.4f");
        
        
    }
}
