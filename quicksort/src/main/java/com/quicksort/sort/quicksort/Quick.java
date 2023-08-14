package com.quicksort.sort.quicksort;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Quick {

    private Quick() {
    }

    public static Comparable[] sort(Comparable[] a) {
        List<Comparable> list = Arrays.asList(a);
        Collections.shuffle(list);
        a = list.toArray(new Comparable[list.size()]);
        sort(a, 0, a.length - 1);
        assert isSorted(a);
        return a;
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
        assert isSorted(a, lo, hi);
    }

    public static int partition(Comparable[] a, int lo, int hi) {
        int i = lo; 
        int j = hi + 1;
        Comparable v = a[lo];
        while (true) {
            while (less(a[++i], v)) { 
                if (i == hi) break; 
            }
            while (less(v, a[--j])) {
                if (j == lo) break;
            }
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    public static Comparable select(Comparable[] a, int k) {
        if (k < 0 || k >= a.length) {
            throw new IllegalArgumentException("index is not between 0 and " + a.length + ": " + k);
        }
        List<Comparable> list = Arrays.asList(a);
        Collections.shuffle(list);
        a = list.toArray(new Comparable[list.size()]);
        int lo = 0, hi = a.length - 1;
        while (hi > lo) {
            int i = partition(a, lo, hi);
            if (i > k) hi = i - 1;
            else if (i < k) lo = i + 1;
            else return a[i];
        }
        return a[lo];
    }

    private static boolean less(Comparable v, Comparable w) {
        if (v == w) return false;
        return v.compareTo(w) < 0;
    }

    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] inputStrings = scanner.nextLine().split("\\s");
        Integer[] a = new Integer[inputStrings.length];

        for (int i = 0; i < inputStrings.length; i++) {
            a[i] = Integer.parseInt(inputStrings[i]); // Parsing as integers
        }

        Quick.sort(a);
        show(a);
        assert isSorted(a);

        List<Integer> list = Arrays.asList(a);
        Collections.shuffle(list);
        a = list.toArray(new Integer[list.size()]);

        System.out.println();
        for (int i = 0; i < a.length; i++) {
            Integer ith = (Integer) Quick.select(a, i);
            System.out.println(ith);
        }

        scanner.close();
    }

    }


