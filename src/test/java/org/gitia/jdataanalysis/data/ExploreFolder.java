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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Matías Roodschild <mroodschild@gmail.com>
 */
public class ExploreFolder {

    public static void main(String[] args) {
        String path = "/home/matias/";
        String[] files = getFiles(path);
        if (files != null) {
            int size = files.length;
            for (int i = 0; i < size; i++) {
                System.out.println(files[i]);
            }
        }
    }

    public static String[] getFiles(String dir_path) {
        String[] arr_res = null;
        File f = new File(dir_path);
        if (f.isDirectory()) {
            List<String> res = new ArrayList<>();
            File[] arr_content = f.listFiles();
            int size = arr_content.length;
            for (int i = 0; i < size; i++) {
                if (arr_content[i].isFile()) {
                    res.add(arr_content[i].toString());
                }
            }
            arr_res = res.toArray(new String[0]);
        } else {
            System.err.println("¡ Path NO válido !");
        }
        return arr_res;
    }

}
