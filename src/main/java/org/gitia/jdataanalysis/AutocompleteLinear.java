/*
 * The MIT License
 *
 * Copyright 2017 
 *   Matías Roodschild <mroodschild@gmail.com>.
 *   Jorge Gotay Sardiñas <jgotay57@gmail.com>.
 *   Adrian Will <adrian.will.01@gmail.com>.
 *   Sebastián Rodriguez <sebastian.rodriguez@gitia.org>.
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
package org.gitia.jdataanalysis;

/**
 *
 * @author Matías Roodschild <mroodschild@gmail.com>
 */
public class AutocompleteLinear {

    /**
     * Los espacios vacíos deben estar completados con "NaN"
     *
     * @param feature
     * @return
     */
    public static String[] complete(String[] feature) {
        System.out.println("AutocompleteLinear Started");
        int completados = 0;
        String[] tempData = feature;
        String tempInit = tempData[0];
        String tempFinish = tempData[0];
        int count = 0;
        int pos_init = 0;
        int pos_finish = 0;

        for (int i = 0; i < tempData.length; i++) {
            if (!tempData[i].equals("NaN")) {
                if (count == 0) {
                    tempInit = tempData[i];
                } else {
                    tempFinish = tempData[i];
                    double part = 1;
                    double temp1 = Double.valueOf(tempInit);
                    double temp2 = Double.valueOf(tempFinish);
                    double div = count + 1;
                    for (int j = pos_init; j <= pos_finish; j++) {
                        double a = temp1 + (temp2 - temp1) * part / div;
                        tempData[j] = String.valueOf(a);
                        part++;
                    }
                    completados += count;
                }
                count = 0;
                tempInit = tempFinish;
            } else {
                if (count == 0) {
                    pos_init = i;
                    pos_finish = i;
                } else {
                    pos_finish = i;
                }
                count++;
            }
        }
        System.out.println("Datos Completados: " + completados);
        System.out.println("AutocompleteLinear Finished");
        return tempData;
    }
}
