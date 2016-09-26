package com.gitia.jdataanalysis;

/**
 *
 * @author Matías Roodschild <mroodschild@gmail.com>
 */
public class AutocompleteLinear {

    public static String[] complete(String[] feature) {
        String[] tempData = feature;
        String tempInit = tempData[0];
        String tempFinish = tempData[0];
        int count = 0;
        int pos_init = 0;
        int pos_finish = 0;

        for (int i = 0; i < tempData.length; i++) {
            if (!tempData[i].equals("NaN")) {
                if (count == 0) {
                    tempInit = tempData[i];
                } else {
                    tempFinish = tempData[i];
                    double part = 1;
                    double temp1 = Double.valueOf(tempInit);
                    double temp2 = Double.valueOf(tempFinish);
                    double div = count + 1;
                    for (int j = pos_init; j <= pos_finish; j++) {
                        double a = temp1 + (temp2 - temp1) * part / div;
                        tempData[j] = String.valueOf(a);
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
        return tempData;
    }
}
