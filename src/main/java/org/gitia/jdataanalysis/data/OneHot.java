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

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.ejml.simple.SimpleMatrix;

/**
 *
 * @author Matías Roodschild <mroodschild@gmail.com>
 */
public class OneHot {

    public static SimpleMatrix decode(String[] labels) {
        String[] targets = getUniqueslabels(labels);
        SimpleMatrix onehot = new SimpleMatrix(labels.length, targets.length);
        
        for (int i = 0; i < targets.length; i++) {
            SimpleMatrix v = new SimpleMatrix(1, targets.length);
            v.zero();
            
            
            for (int j = 0; j < labels.length; j++) {
                String label = labels[j];
                
            }
            
        }
        for (int i = 0; i < labels.length; i++) {
            String label = labels[i];

        }

        return null;
    }
    
    /**
     * obtenemos las etiquetas que aparecen una sola vez
     * @param labels
     * @return 
     */
    public static String[] getUniqueslabels(String[] labels){
        Set<String> myset = new HashSet<>();
        Collections.addAll(myset, labels);
        String[] array = Arrays.copyOf(myset.toArray(), myset.size(), String[].class);
        return array;
    }

//    public static void main(String args[]) {
//
//        String[] labels = {"aim", "rajesh", "raju", "aim"};
//
//        Set<String> myset = new HashSet<>();
//        Set<String> l = new 
//        //Collections.addAll(myset, labels);
//
//        System.out.println(myset);
//        myset.parallelStream().forEach((string) -> {
//            if(string.equals("aim"))
//                string = "0";
//            System.out.println(string);
//        });
//        
//        String[] array = Arrays.copyOf(myset.toArray(), myset.size(), String[].class);
//        
//        for (int i = 0; i < array.length; i++) {
//            String string = array[i];
//            System.out.println(string);
//        }
//        
//    }
}