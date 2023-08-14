package com.quicksort.sort.quicksort;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;

public class QuickTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    
    @Test
    public void testSortEmptyArray() {
        Comparable[] arr = {};
        Quick.sort(arr);
        assertArrayEquals(new Comparable[]{}, arr);
    }

    @Test
    public void testSortSingleElementArray() {
        Comparable[] arr = {1};
        Quick.sort(arr);
        assertArrayEquals(new Comparable[]{1}, arr);
    }

    @Test
    public void testSelectFirstElement() {
        Comparable[] arr = {3, 1, 4, 5, 2};
        assertEquals(1, Quick.select(arr, 0));
    }

    @Test
    public void testSelectLastElement() {
        Comparable[] arr = {3, 1, 4, 5, 2};
        assertEquals(5, Quick.select(arr, 4));
    }

    @Test
    public void testSelectMiddleElement() {
        Comparable[] arr = {3, 1, 4, 5, 2};
        assertEquals(3, Quick.select(arr, 2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSelectWithNegativeIndex() {
        Comparable[] arr = {3, 1, 4, 5, 2};
        Quick.select(arr, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSelectWithIndexOutOfBounds() {
        Comparable[] arr = {3, 1, 4, 5, 2};
        Quick.select(arr, 5);
    }

    @Test
    public void testSelectWithSingleElementArray() {
        Comparable[] arr = {1};
        assertEquals(1, Quick.select(arr, 0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSelectWithEmptyArray() {
        Comparable[] arr = {};
        Quick.select(arr, 0);
    }

    @Test
    public void testSelectWithDuplicates() {
        Comparable[] arr = {3, 1, 4, 3, 2};
        assertEquals(3, Quick.select(arr, 2));
    }

    @Test
    public void testSelectWithAllSameElements() {
        Comparable[] arr = {3, 3, 3, 3, 3};
        assertEquals(3, Quick.select(arr, 2));
    }
    @Test
    public void testSortRandomArray() {
        Comparable[] arr = {3, 1, 4, 5, 2};
        assertArrayEquals(new Comparable[]{1, 2, 3, 4, 5}, Quick.sort(arr));
    }

    @Test
    public void testSortAlreadySortedArray() {
        Comparable[] arr = {1, 2, 3, 4, 5};
        assertArrayEquals(new Comparable[]{1, 2, 3, 4, 5}, Quick.sort(arr));
    }

    @Test
    public void testSortReverseSortedArray() {
        Comparable[] arr = {5, 4, 3, 2, 1};
        assertArrayEquals(new Comparable[]{1, 2, 3, 4, 5}, Quick.sort(arr));
    }

    @Test
    public void testSortWithAllSameElements() {
        Comparable[] arr = {3, 3, 3, 3, 3};
        Quick.sort(arr);
        assertArrayEquals(new Comparable[]{3, 3, 3, 3, 3}, arr);
    }

    @Test
    public void testSelectWithRandomArray() {
        Comparable[] arr = {3, 1, 4, 5, 2};
        assertEquals(4, Quick.select(arr, 3));
    }

    @Test
    public void testSelectWithSortedArray() {
        Comparable[] arr = {1, 2, 3, 4, 5};
        assertEquals(3, Quick.select(arr, 2));
    }

    @Test
    public void testSelectLargeArray() {
        Comparable[] arr = new Comparable[1000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        assertEquals(500, Quick.select(arr, 500));
    }

    @Test
    public void testSortWithNegativeNumbers() {
        Comparable[] arr = {-5, -4, -3, -2, -1};
        assertArrayEquals(new Comparable[]{-5, -4, -3, -2, -1}, Quick.sort(arr));
    }

    @Test
    public void testSelectWithNegativeNumbers() {
        Comparable[] arr = {-3, -1, -4, -5, -2};
        assertEquals(-4, Quick.select(arr, 1));
    }

    @Test
    public void testSortWithMixedNumbers() {
        Comparable[] arr = {3, -1, 4, 5, -2};
        assertArrayEquals(new Comparable[]{-2, -1, 3, 4, 5}, Quick.sort(arr));
    }


    @Test
    public void testShowMethod1() throws Exception {
        Comparable[] arr = {1, 2, 3};

        // Capture the standard output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outContent, true, StandardCharsets.UTF_8.name());
        System.setOut(printStream);

        // Invoke the private show method using reflection
        Method showMethod = Quick.class.getDeclaredMethod("show", Comparable[].class);
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
    public void testIsSortedForLessThanMutation() {
        Comparable[] arr = {1, 2, 3, 4, 5};
        assertTrue(Quick.isSorted(arr));
    }
    
    @Test
    public void testIsSortedForLoopConditionMutation() {
        Comparable[] arr = {1, 2, 3, 5, 4};
        assertFalse(Quick.isSorted(arr));
    }
    
    @Test
    public void testIsSortedForReturnMutation() {
        Comparable[] arr = {1, 2, 3, 4, 5};
        assertTrue(Quick.isSorted(arr));
    }

    @Test
    public void testIsSortedForLessCallRemoval() {
        Comparable[] arr = {1, 3, 2, 4, 5};
        assertFalse(Quick.isSorted(arr));
    }
    
    @Test
    public void testSortWithFloatingPointNumbers() {
        Comparable[] arr = {3.5, 1.2, 2.7, 4.1, 0.5};
        assertArrayEquals(new Comparable[]{0.5, 1.2, 2.7, 3.5, 4.1}, Quick.sort(arr));
    }
    

    @Test
    public void testIsSortedWithSortedArray() throws Exception {
        Comparable[] arr = {1, 2, 3, 4, 5};
        
        Method isSortedMethod = Quick.class.getDeclaredMethod("isSorted", Comparable[].class, int.class, int.class);
        isSortedMethod.setAccessible(true);
        
        boolean result = (Boolean) isSortedMethod.invoke(null, arr, 0, arr.length - 1);
        
        assertTrue(result);
    }

    @Test
    public void testIsSortedWithUnsortedArray() throws Exception {
        Comparable[] arr = {1, 3, 2, 4, 5};
        
        Method isSortedMethod = Quick.class.getDeclaredMethod("isSorted", Comparable[].class, int.class, int.class);
        isSortedMethod.setAccessible(true);
        
        boolean result = (Boolean) isSortedMethod.invoke(null, arr, 0, arr.length - 1);
        
        assertFalse(result);
    }
    @Test
    public void testIsSortedAssertionWithSortedArray() throws Exception {
        Comparable[] arr = {1, 2, 3, 4, 5};
        int lo = 1;
        int hi = 3;

        Method isSortedMethod = Quick.class.getDeclaredMethod("isSorted", Comparable[].class, int.class, int.class);
        isSortedMethod.setAccessible(true);
        
        boolean result = (Boolean) isSortedMethod.invoke(null, arr, lo, hi);
        
        assertTrue(result); // Should be sorted between lo and hi
    }

    @Test
    public void testIsSortedAssertionWithUnsortedArray() throws Exception {
        Comparable[] arr = {1, 3, 2, 4, 5};
        int lo = 1;
        int hi = 2;

        Method isSortedMethod = Quick.class.getDeclaredMethod("isSorted", Comparable[].class, int.class, int.class);
        isSortedMethod.setAccessible(true);
        
        boolean result = (Boolean) isSortedMethod.invoke(null, arr, lo, hi);
        
        assertFalse(result); // Should not be sorted between lo and hi
    }
    @Test(expected = ClassCastException.class)
    public void testSortWithIncompatibleTypes() {
        Comparable[] arr = {3, "apple", 4, "banana"};
        Quick.sort(arr);
    }

    @Test
    public void testPartitionWithPivotAtBeginning() {
        Comparable[] arr = {5, 4, 3, 2, 1};
        int lo = 0, hi = 4;
        int pivotIndex = Quick.partition(arr, lo, hi);
        assertArrayEquals(new Comparable[]{1, 4, 3, 2, 5}, arr);
        assertEquals(4, pivotIndex);
    }

    @Test
    public void testPartitionWithPivotInMiddle() {
        Comparable[] arr = {3, 1, 4, 5, 2};
        int lo = 0, hi = 4;
        int pivotIndex = Quick.partition(arr, lo, hi);
        assertArrayEquals(new Comparable[]{2, 1, 3, 5, 4}, arr);
        assertEquals(2, pivotIndex);
    }

    @Test
    public void testPartitionWithAlreadySortedArray() {
        Comparable[] arr = {1, 2, 3, 4, 5};
        int lo = 0, hi = 4;
        int pivotIndex = Quick.partition(arr, lo, hi);
        assertArrayEquals(new Comparable[]{1, 2, 3, 4, 5}, arr);
        assertEquals(0, pivotIndex); // Expect pivot index to be at the beginning
    }
    
}
