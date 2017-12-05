/*
 * The MIT License
 *
 * Copyright 2017 Matías Roodschild <mroodschild@gmail.com>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package LinearRegression;


import org.gitia.jdataanalysis.UtilMatrix;
import org.ejml.simple.SimpleMatrix;

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
