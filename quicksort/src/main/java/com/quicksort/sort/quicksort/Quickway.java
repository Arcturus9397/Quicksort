package com.quicksort.sort.quicksort;

import java.util.Scanner;

public class Quickway {

    private Quickway() { }

    public static Comparable[] sort(Comparable[] a) {
        shuffle(a);
        sort(a, 0, a.length - 1);
        assert isSorted(a);
        return a;
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int lt = lo, i = lo + 1, gt = hi;
        Comparable v = a[lo];
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) exch(a, lt++, i++);
            else if (cmp > 0) exch(a, i, gt--);
            else i++;
        }
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
        assert isSorted(a, lo, hi);
    }

    private static void shuffle(Object[] a) {
        for (int i = a.length - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            exch(a, i, j);
        }
    }

    private static boolean less(Comparable v, Comparable w) {
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
        for (int i = lo + 1; i <= hi; i++) {
            if (less(a[i], a[i - 1])) return false;
        }
        return true;
    }

    private static void show(Comparable[] a) {
        for (Comparable item : a) {
            System.out.println(item);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] inputStrings = input.split("\\s+");
        Comparable[] a = new Comparable[inputStrings.length];

        for (int i = 0; i < inputStrings.length; i++) {
            a[i] = Integer.parseInt(inputStrings[i]); // Parsing as integers
        }

        Quickway.sort(a);
        show(a);
        scanner.close();
    }

}