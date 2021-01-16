package com.oom;

import java.util.ArrayList;

public class oomTest {
    public static final ArrayList<Integer> integers = new ArrayList<Integer>();

    public static void main(String[] args) {
        Integer i = 99999999;

        try {
            while (true) {
                integers.add(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
