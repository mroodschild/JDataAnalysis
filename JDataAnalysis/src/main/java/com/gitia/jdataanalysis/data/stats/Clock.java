/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gitia.jdataanalysis.data.stats;

import java.util.Calendar;

/**
 *
 * @author Matías Roodschild <mroodschild@gmail.com>
 */
public class Clock {

    Calendar i = Calendar.getInstance();
    Calendar f = Calendar.getInstance();

    public Clock() {
    }

    public void start() {
        i = Calendar.getInstance();
    }

    public void stop() {
        f = Calendar.getInstance();
    }

    public double time() {
        return f.getTimeInMillis() - i.getTimeInMillis();
    }

    /**
     * devuelve el tiempo en segundos
     * @return 
     */
    public double timeSec() {
        return time() / 1000.0;
    }

}
