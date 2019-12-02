package wr1ttenyu.f1nal.study.designpattern.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public interface DesignPatterUtil {
    static String getType() {
        BufferedReader strIn = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("input pizza type:");
        return getString(strIn);
    }

    static String getFactoryName() {
        BufferedReader strIn = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("input pizza factory name:");
        return getString(strIn);
    }

    static String getString(BufferedReader strIn) {
        String str;
        try {
            str = strIn.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
        return str;
    }
}
