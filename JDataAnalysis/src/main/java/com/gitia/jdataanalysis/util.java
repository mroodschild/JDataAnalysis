/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gitia.jdataanalysis;

/**
 *
 * @author M
 */
public class util {
    
    /**
     * 
     * @param vect
     * @param name nombre del vector a mostrar 
     */
    public static void print(double[] vect, String name){
        System.out.printf("%s:\t", name);
        for (int i = 0; i < vect.length; i++) {
            System.out.printf("%.4f\t", vect[i]);
            //System.out.println(vect[i]);
        }
        System.out.println("");
    }
    
}
