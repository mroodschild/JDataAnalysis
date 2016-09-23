package com.gitia.jdataanalysis;

import com.gitia.jdataanalysis.JDataAnalysis;

/**
 *
 * @author Matías Roodschild <mroodschild@gmail.com>
 */
public class Temp {

    public static void main(String[] args) {
        String folder = "src/main/resources/";

        System.out.println("\nEntrenamos cada uno de los sets");

        JDataAnalysis JDA = new JDataAnalysis(folder + "llenadoTemp_11277.csv", true);
        String[][] tempData = JDA.getFeaturesString("temp_aero");

        String tempInit = tempData[0][0];
        String tempFinish = tempData[0][0];
        int count = 0;
        int pos_init = 0;
        int pos_finish = 0;

        for (int i = 0; i < tempData.length; i++) {
            if (!tempData[i][0].equals("NaN")) {
                if (count == 0) {
                    tempInit = tempData[i][0];
                } else {
                    tempFinish = tempData[i][0];
                    double part = 1;
                    double temp1 = Double.valueOf(tempInit);
                    double temp2 = Double.valueOf(tempFinish);
                    double div = count + 1;
                    for (int j = pos_init; j <= pos_finish; j++) {
                        double a = temp1 + (temp2 - temp1) * part / div;
                        tempData[j][0] = String.valueOf(a);
                        part++;
                    }
                }
                count = 0;
                tempInit = tempFinish;
            } else {
                if (count == 0) {
                    pos_init = i;
                    pos_finish = i;
                } else {
                    pos_finish = i;
                }
                count++;
            }
        }
        for (int i = 0; i < 10; i++) {
            System.out.println("i: " + i + " temp: " + tempData[i][0]);
        }
    }
}
