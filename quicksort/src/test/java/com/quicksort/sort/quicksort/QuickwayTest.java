package com.quicksort.sort.quicksort;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class QuickwayTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    
    @Test
    public void testSortEmptyArray() {
        Comparable[] arr = {};
        assertArrayEquals(new Comparable[]{}, Quickway.sort(arr));
    }

    @Test
    public void testSortSingleElementArray() {
        Comparable[] arr = {1};
        assertArrayEquals(new Comparable[]{1}, Quickway.sort(arr));
    }

    @Test
    public void testSortRandomArray() {
        Comparable[] arr = {3, 1, 4, 5, 2};
        assertArrayEquals(new Comparable[]{1, 2, 3, 4, 5}, Quickway.sort(arr));
    }

    @Test
    public void testSortAlreadySortedArray() {
        Comparable[] arr = {1, 2, 3, 4, 5};
        assertArrayEquals(new Comparable[]{1, 2, 3, 4, 5}, Quickway.sort(arr));
    }

    @Test
    public void testSortReverseSortedArray() {
        Comparable[] arr = {5, 4, 3, 2, 1};
        assertArrayEquals(new Comparable[]{1, 2, 3, 4, 5}, Quickway.sort(arr));
    }

    @Test
    public void testSortWithAllSameElements() {
        Comparable[] arr = {3, 3, 3, 3, 3};
        Quickway.sort(arr);
        assertArrayEquals(new Comparable[]{3, 3, 3, 3, 3}, arr);
    }

   

    @Test
    public void testSortWithNegativeNumbers() {
        Comparable[] arr = {-5, -4, -3, -2, -1};
        assertArrayEquals(new Comparable[]{-5, -4, -3, -2, -1}, Quickway.sort(arr));
    }


    @Test
    public void testSortWithMixedNumbers() {
        Comparable[] arr = {3, -1, 4, 5, -2};
        assertArrayEquals(new Comparable[]{-2, -1, 3, 4, 5}, Quickway.sort(arr));
    }


    @Test
    public void testShowMethod1() throws Exception {
        Comparable[] arr = {1, 2, 3};

        // Capture the standard output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outContent, true, StandardCharsets.UTF_8.name());
        System.setOut(printStream);

        // Invoke the private show method using reflection
        Method showMethod = Quickway.class.getDeclaredMethod("show", Comparable[].class);
        showMethod.setAccessible(true);
        showMethod.invoke(null, new Object[]{arr});

        // Flush the stream to make sure everything is written to ByteArrayOutputStream
        printStream.flush();

        // Verify the output, using the platform-specific line separator
        String lineSeparator = System.lineSeparator();
        String expectedOutput = "1" + lineSeparator + "2" + lineSeparator + "3" + lineSeparator;
        assertEquals(expectedOutput, outContent.toString(StandardCharsets.UTF_8.name()));

        // Restore the standard output stream and close the ByteArrayOutputStream
        System.setOut(System.out);
        outContent.close();
    }

    @Test
    public void testSortLargeArray() {
        int size = 10000;
        Comparable[] arr = new Comparable[size];
        for (int i = 0; i < size; i++) {
            arr[i] = size - i;
        }
        Quickway.sort(arr);
        for (int i = 0; i < size - 1; i++) {
            assertTrue("Array not sorted at index " + i, arr[i].compareTo(arr[i + 1]) <= 0);
        }
    }

    @Test
    public void testMultipleSortCalls() {
        Comparable[] arr1 = {3, 1, 4};
        Comparable[] arr2 = {5, 2, 1};
        Quickway.sort(arr1);
        Quickway.sort(arr2);
        assertArrayEquals(new Comparable[]{1, 3, 4}, arr1);
        assertArrayEquals(new Comparable[]{1, 2, 5}, arr2);
    }

    @Test
    public void testBoundaryVerySmallArray() {
        Comparable[] arr = {3};
        Quickway.sort(arr);
        assertArrayEquals(new Comparable[]{3}, arr);
    }

    @Test
    public void testBoundaryVeryLargeArray() {
        int size = 1000000; // 1 million elements
        Comparable[] arr = new Comparable[size];
        for (int i = 0; i < size; i++) {
            arr[i] = size - i;
        }
        Quickway.sort(arr);
        for (int i = 0; i < size - 1; i++) {
            assertTrue("Array not sorted at index " + i, arr[i].compareTo(arr[i + 1]) <= 0);
        }
    }

    @Test
    public void testLessEqualElements() throws Exception {
        Method lessMethod = Quickway.class.getDeclaredMethod("less", Comparable.class, Comparable.class);
        lessMethod.setAccessible(true);

        assertFalse((Boolean) lessMethod.invoke(null, 5, 5));
        assertFalse((Boolean) lessMethod.invoke(null, "apple", "apple"));
    }

    @Test
    public void testLessVIsLessThanW() throws Exception {
        Method lessMethod = Quickway.class.getDeclaredMethod("less", Comparable.class, Comparable.class);
        lessMethod.setAccessible(true);

        assertTrue((Boolean) lessMethod.invoke(null, 3, 5));
        assertTrue((Boolean) lessMethod.invoke(null, "apple", "banana"));
    }

    @Test
    public void testLessVIsGreaterThanW() throws Exception {
        Method lessMethod = Quickway.class.getDeclaredMethod("less", Comparable.class, Comparable.class);
        lessMethod.setAccessible(true);

        assertFalse((Boolean) lessMethod.invoke(null, 5, 3));
        assertFalse((Boolean) lessMethod.invoke(null, "banana", "apple"));
    }

    @Test
    public void testIsSortedWithSortedArray() throws Exception {
        Comparable[] arr = {1, 2, 3, 4, 5};
        
        Method isSortedMethod = Quickway.class.getDeclaredMethod("isSorted", Comparable[].class, int.class, int.class);
        isSortedMethod.setAccessible(true);
        
        boolean result = (Boolean) isSortedMethod.invoke(null, arr, 0, arr.length - 1);
        
        assertTrue(result);
    }

    @Test
    public void testIsSortedWithUnsortedArray() throws Exception {
        Comparable[] arr = {1, 3, 2, 4, 5};
        
        Method isSortedMethod = Quickway.class.getDeclaredMethod("isSorted", Comparable[].class, int.class, int.class);
        isSortedMethod.setAccessible(true);
        
        boolean result = (Boolean) isSortedMethod.invoke(null, arr, 0, arr.length - 1);
        
        assertFalse(result);
    }
    @Test
    public void testIsSortedAssertionWithSortedArray() throws Exception {
        Comparable[] arr = {1, 2, 3, 4, 5};
        int lo = 1;
        int hi = 3;

        Method isSortedMethod = Quickway.class.getDeclaredMethod("isSorted", Comparable[].class, int.class, int.class);
        isSortedMethod.setAccessible(true);
        
        boolean result = (Boolean) isSortedMethod.invoke(null, arr, lo, hi);
        
        assertTrue(result); // Should be sorted between lo and hi
    }

    @Test
    public void testIsSortedAssertionWithUnsortedArray() throws Exception {
        Comparable[] arr = {1, 3, 2, 4, 5};
        int lo = 1;
        int hi = 2;

        Method isSortedMethod = Quickway.class.getDeclaredMethod("isSorted", Comparable[].class, int.class, int.class);
        isSortedMethod.setAccessible(true);
        
        boolean result = (Boolean) isSortedMethod.invoke(null, arr, lo, hi);
        
        assertFalse(result); // Should not be sorted between lo and hi
    }
    @Test(expected = ClassCastException.class)
    public void testSortWithIncompatibleTypes() {
        Comparable[] arr = {3, "apple", 4, "banana"};
        Quickway.sort(arr);
    }

    @Test
    public void testSortWithFloatingPointNumbers() {
        Comparable[] arr = {3.5, 1.2, 2.7, 4.1, 0.5};
        assertArrayEquals(new Comparable[]{0.5, 1.2, 2.7, 3.5, 4.1}, Quickway.sort(arr));
    }

    @Test
    public void testIsSortedForLessThanMutation() {
        Comparable[] arr = {1, 2, 3, 4, 5};
        assertTrue(Quickway.isSorted(arr));
    }
    
    @Test
    public void testIsSortedForLoopConditionMutation() {
        Comparable[] arr = {1, 2, 3, 5, 4};
        assertFalse(Quickway.isSorted(arr));
    }
    
    @Test
    public void testIsSortedForReturnMutation() {
        Comparable[] arr = {1, 2, 3, 4, 5};
        assertTrue(Quickway.isSorted(arr));
    }

    @Test
    public void testIsSortedForLessCallRemoval() {
        Comparable[] arr = {1, 3, 2, 4, 5};
        assertFalse(Quickway.isSorted(arr));
    }
}