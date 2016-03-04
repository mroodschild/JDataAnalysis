package LinearRegression;


import com.gitia.jdataanalysis.UtilMatrix;
import org.ejml.simple.SimpleMatrix;

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
/**
 *
 * @author @author Matías Roodschild <mroodschild@gmail.com>
 */
public class testSimpleMatrix {

    public static void main(String[] args) {
        double[][] datos = {{1, 2}, {3, 4}};
        double valueToInsert = 1;
        SimpleMatrix b = new SimpleMatrix(datos);//original matrix
        SimpleMatrix c = new SimpleMatrix(datos.length, datos[0].length + 1);//new matrix with 1 more column after
        SimpleMatrix d = new SimpleMatrix(datos.length, datos[0].length + 1);//new matrix with 1 more column before
        
        c.insertIntoThis(0, 1, b);//copy matrix b in matrix c, in the position selected
        System.out.println("Mostramos c");
        c.print();
        b.print();
        b=UtilMatrix.addColumnBefore(b);
        System.out.println("Mostramos el nuevo b");
        b.print();
        b=UtilMatrix.addColumnAfter(b);
        b.print();
        
        //insert values in the last column of the new matrix
        b = UtilMatrix.setColumn(b, 0, 1);
        b.print();
        b = UtilMatrix.setColumn(b, 3, 3);
        b.print();
        
        
//        for (int i = 0; i < size; i++) {
//            d.setColumn(size, i, valueToInsert);
//        }
//        d.print();//matrix with new values
    }

}
