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
package test;

import java.util.Random;
import org.ejml.simple.SimpleMatrix;

/**
 *
 * @author @author Matías Roodschild <mroodschild@gmail.com>
 */
public class TestDiagMatrix {

    public static void main(String[] args) {
        double alpha = 0.25;
        double[] a = new double[4];
        SimpleMatrix H = SimpleMatrix.random(5, 3, 2, 4, new Random());
        H.print();
        SimpleMatrix Diag = SimpleMatrix.identity(H.numCols()).scale(alpha);
        Diag.print();
        H.transpose().print();
        H.transpose().mult(H).print();
        H.transpose().mult(H).plus(Diag).print();
        
    }

}
