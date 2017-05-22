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
package com.gitia.jdataanalysis.eval;

import org.apache.commons.lang.ArrayUtils;
import org.ejml.simple.SimpleMatrix;

/**
 *
 * @author Matías Roodschild <mroodschild@gmail.com>
 */
public class ConfusionMatrix {

    SimpleMatrix confusionMatrix;
    double elements;
    double aciertos;
    double aciertosPorc;

    public ConfusionMatrix() {
    }

    /**
     * los datos ingresados ya deben estar formados de la siguiente manera:<br>
     * <br>
     * [1,0,0]<br>
     * [0,1,0]<br>
     * [0,0,1]<br>
     * [1,0,0]<br>
     * <br>
     * IMPORTANTE: tener presente que solo un valor debe ser 1 y el resto 0.
     *
     * @param calc
     * @param obs
     */
    public void eval(SimpleMatrix calc, SimpleMatrix obs) {
        //iniciamos el conteo
        confusionMatrix = new SimpleMatrix(obs.numCols(), obs.numCols());
        confusionMatrix.set(0);
        elements = calc.numRows();
        int rows = calc.numRows();
        for (int i = 0; i < rows; i++) {
            add(getIndex(obs.extractVector(true, i)), getIndex(calc.extractVector(true, i)));
        }
        aciertos = confusionMatrix.extractDiag().elementSum();
        aciertosPorc = aciertos / elements;
    }

    /**
     *
     * Esta función indica el índice de la clase, es decir:<br>
     * si a = [0,0,0,1], devuelve index = 3<br>
     * si no es encontrado devuelve -1<br>
     *
     * @param a
     * @return
     */
    private int getIndex(SimpleMatrix a) {
        return ArrayUtils.indexOf(a.getMatrix().getData(), 1);
    }

    /**
     *
     * @param obs este parámetro es la clase observada
     * @param calc este parámetro es la clase calculada
     */
    private void add(int obs, int calc) {
        //sumamos uno en la posición indicada
        double a = confusionMatrix.get(obs, calc) + 1;
        //actualizamos
        confusionMatrix.set(obs, calc, a);
    }

    public SimpleMatrix getConfusionMatrix() {
        return confusionMatrix;
    }

    public static void main(String[] args) {
        SimpleMatrix obs = new SimpleMatrix(5, 3, true,
                1, 0, 0,
                0, 1, 0,
                0, 0, 1,
                1, 0, 0,
                0, 1, 0);
//        obs.print();
//        for (int i = 0; i < obs.numRows(); i++) {
//            System.out.println("i\t" + i + "\t" + obs.extractVector(true, i).hashCode());
//        }
//        SimpleMatrix b = obs.extractVector(true, 0);
//        SimpleMatrix c = obs.extractVector(true, 1);
//        b.print();
//        c.print();
//        System.out.println("//");
//        System.out.println(ArrayUtils.indexOf(b.transpose().getMatrix().getData(), 1));
//        System.out.println(ArrayUtils.indexOf(c.transpose().getMatrix().getData(), 1));

        SimpleMatrix calc = new SimpleMatrix(5, 3, true,
                1, 0, 0,
                0, 1, 0,
                0, 1, 0,
                1, 0, 0,
                0, 0, 1);

        System.out.println("Obs");
        obs.print();
        System.out.println("Calc");
        calc.print();

        ConfusionMatrix matrix = new ConfusionMatrix();
        matrix.eval(calc, obs);
        System.out.println("");
        matrix.printStats();
    }
    
    public void printStats() {
        System.out.println(this.toString());
    }
    
    @Override
    public String toString() {
        String info = "";
        info += "\nConfusion Matrix\n";
        info += confusionMatrix.toString();
        info += "\nAciertos:\t" + aciertos + "/" + elements;
        info += "\nAciertos %:\t" + aciertosPorc;
        int size = confusionMatrix.numCols();
        double VP = 0;
        double FN = 0;
        info += "\nAciertos por clase:\t";
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i != j) {
                    FN += confusionMatrix.get(i, j);
                } else {
                    VP = confusionMatrix.get(i, j);
                }
            }
            info += "\nClase " + i + ": \t" + VP / (VP + FN)+"\t";
            VP = 0;
            FN = 0;
        }
        return info;
    }

}