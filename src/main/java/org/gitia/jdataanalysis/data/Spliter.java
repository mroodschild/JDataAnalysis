/*
 * The MIT License
 *
 * Copyright 2018 Matías Roodschild <mroodschild@gmail.com>.
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
package org.gitia.jdataanalysis.data;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Matías Rodschild <mroodschild@gmail.com>
 */
public class Spliter {

    //database size
    int sizeDatabase;

    //percet to split
    double percentTrain;

    //index
    ArrayList<Integer> index;
    int[] idxTrain;
    int[] idxValidation;

    public Spliter(int sizeDatabase, double percentTrain) {
        this.sizeDatabase = sizeDatabase;
        this.percentTrain = percentTrain;

        //alguna validación
        index = new ArrayList<>();
        for (int i = 0; i < sizeDatabase; i++) {
            index.add(i);
        }

        int sizeTrain = (int) ((double) sizeDatabase * percentTrain);

        idxTrain = new int[sizeTrain];
        idxValidation = new int[sizeDatabase - sizeTrain];
    }

    public void shuffle() {
        //mezclamos los datos
        for (int i = 0; i < index.size(); i++) {
            System.out.println(index.get(i));
        }
        Collections.shuffle(index);
        for (int i = 0; i < index.size(); i++) {
            System.out.println(index.get(i));
        }
        int v = 0;

        for (int i = 0; i < idxTrain.length; i++) {
            idxTrain[i] = index.get(i);
            v++;
        }
        
        for (int i = 0; i < idxValidation.length; i++) {
            idxValidation[i] = index.get(v);
            v++;//este faltaba
        }
    }

    public int[] getIdxTrain() {
        return idxTrain;
    }

    public int[] getIdxValidation() {
        return idxValidation;
    }

}