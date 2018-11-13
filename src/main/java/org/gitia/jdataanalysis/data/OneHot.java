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
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.ArrayUtils;

/**
 *
 * @author Matías Roodschild <mroodschild@gmail.com>
 */
public class OneHot {

    LabelEncoder<String> labelEncoder;
    private final int numberOfClasses;

    /**
     * inicializamos OneHot con las etiquetas a utilizar, remueve las etiquetas
     * duplicadas
     *
     * @param words
     */
    public OneHot(String[] words) {
        String[] aux = removeDuplicates(words);
        labelEncoder = new LabelEncoder<>(aux);
        this.numberOfClasses = labelEncoder.getNumClasses();
    }

    /**
     * devuelve la cantidad de etiquetas encontradas
     *
     * @return
     */
    public int getNumberOfClasses() {
        return numberOfClasses;
    }

    /**
     * binariza el codigo de la etiqueta
     *
     * @param label
     * @return
     */
    public double[] encode(double label) {
        double[] oneHot = new double[numberOfClasses];
        oneHot[(int) label] = 1;
        return oneHot;
    }

    /**
     * convierte la etiqueta de un estring a binario
     *
     * @param label
     * @return
     */
    public double[] encode(String label) {
        return this.encode(labelEncoder.encode(label));
    }

    public double[] encode(String[] tags) {
        double[] result = new double[this.numberOfClasses * tags.length];
        int count = 0;
        for (int i = 0; i < tags.length; i++) {
            String tag = tags[i];
            double[] code = this.encode(tag);
            ArrayUtils.insert(count, result, code);
            count += code.length;
        }
        return result;
    }

    /**
     * convierte el binario a etiqueta
     *
     * @param oneHot
     * @return
     */
    public double decode(double[] oneHot) {
        return ArrayUtils.indexOf(oneHot, 1);
    }

    /**
     * convierte el binario a etiqueta
     *
     * @param oneHot
     * @return
     */
    public String tag(double[] oneHot) {
        return labelEncoder.decode((int) this.decode(oneHot));
    }

    public String tag(double code) {
        return labelEncoder.decode((int) code);
    }

    private String[] removeDuplicates(String[] words) {
        Set<String> wordSet = new HashSet<>(Arrays.asList(words));
        String[] wordArray = wordSet.toArray(new String[0]);
        return wordArray;
    }
}
