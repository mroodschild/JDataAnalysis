/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

    public DataContainer(SimpleMatrix input, SimpleMatrix output) {
        this.input = input;
        this.output = output;
    }

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
