/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
        }
    }

    public int[] getIdxTrain() {
        return idxTrain;
    }

    public int[] getIdxValidation() {
        return idxValidation;
    }

}