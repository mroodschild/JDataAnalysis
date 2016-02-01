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

/**
 * 
 * @author @author Matías Roodschild <mroodschild@gmail.com>
 */
public class Util {
    
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
    
    /**
     * Imprime la matriz en la consola
     *
     * @param matriz
     * @param nombre
     */
    public static void print(double[][] matriz, String nombre) {
        System.out.println("Matriz: " + nombre);
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.printf("%.3f\t", matriz[i][j]);
            }
            System.out.println("");
        }
    }
    
}
