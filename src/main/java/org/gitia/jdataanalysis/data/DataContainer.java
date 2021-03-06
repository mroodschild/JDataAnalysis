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

import org.ejml.simple.SimpleMatrix;

/**
 *
 * @author Matías Rodschild <mroodschild@gmail.com>
 */
public class DataContainer {

    SimpleMatrix input;
    SimpleMatrix output;

    public DataContainer() {
    }

    /**
     * @param input
     * @param output 
     */
    public DataContainer(SimpleMatrix input, SimpleMatrix output) {
        this.input = input;
        this.output = output;
    }

    /**
     * 
     * Obtener los datos en formato vertical u horizontal
     * 
     * @param idx
     * @param vertical
     * @return 
     */
    public SimpleMatrix getInput(int[] idx, boolean vertical) {
        if (vertical == false) {
            return getData(input, idx);
        } else {
            return getData(input.transpose(), idx).transpose();
        }
    }

    public SimpleMatrix getOutput(int[] idx, boolean vertical) {
        if (vertical == false) {
            return getData(output, idx);
        } else {
            return getData(output.transpose(), idx).transpose();
        }
    }

    private SimpleMatrix getData(SimpleMatrix data, int[] idx) {
        SimpleMatrix m = new SimpleMatrix(data.numRows(), idx.length);
        for (int i = 0; i < idx.length; i++) {
            m.setColumn(i, 0, data.extractVector(false, idx[i]).getDDRM().getData());
        }
        return m;
    }

    public void setInput(SimpleMatrix input) {
        this.input = input;
    }

    public void setOutput(SimpleMatrix output) {
        this.output = output;
    }

    public SimpleMatrix getInput() {
        return input;
    }

    public SimpleMatrix getOutput() {
        return output;
    }
}
