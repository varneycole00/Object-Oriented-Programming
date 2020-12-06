import org.junit.Test;

import javax.xml.crypto.Data;
import java.sql.DatabaseMetaData;

import static junit.framework.TestCase.*;

public class Examples {
    IHeap heap1 = new DataHeap(5, new DataHeap(6), new DataHeap(7));
    IHeap heap2 = new DataHeap(8, new DataHeap(11), new DataHeap(13));
    IHeap finalTestHeap = new DataHeap(2, heap2, heap1);

    IBinTree dataBT3 = new DataBT(7, new DataBT(30), new MtBT());
    IBinTree dataBT1 = new DataBT(5, new DataBT(6), dataBT3);
    IBinTree dataBT2 = new DataBT(8, new DataBT(11), new DataBT(13));
    IBinTree finalBT1Valid = new DataBT(2, dataBT2, dataBT1);

    HeapChecker check = new HeapChecker();

    IHeap finalTestHeapInvalid = new TestHeap(4, new DataHeap(2), new DataHeap(2));

    DataBT smallDataBT = new DataBT(2, new DataBT(3), new DataBT(4));
    DataBT bt1 = new DataBT(4, new DataBT(5), new MtBT());

    IHeap heap234 = new DataHeap(2, new DataHeap(3), new DataHeap(4));

    IHeap subRemoveHeap = new DataHeap(3, new DataHeap(4), new MtHeap());
    IHeap finalRemoveHeap = new DataHeap(1, new DataHeap(2), subRemoveHeap);

    IBinTree finalRemoveBT = new DataBT(2, new DataBT(3), new DataBT(4));

    IBinTree invalidRemoveBT = new DataBT(1, finalRemoveBT, new MtBT());


    @Test
    public void testIsRightSize() {
        finalTestHeapInvalid.addElt(4);
        finalTestHeapInvalid.addElt(10);
        finalTestHeapInvalid.addElt(15);
        finalTestHeapInvalid.addElt(3);
        finalTestHeapInvalid.addElt(3);
        finalTestHeapInvalid.addElt(3);


        System.out.println(finalTestHeap.size());
        System.out.println(finalBT1Valid.size());
        System.out.println(finalTestHeapInvalid.size());

        assertTrue(check.isOneBigger(finalTestHeap, finalBT1Valid));
        assertFalse(check.isOneBigger(finalTestHeapInvalid, finalBT1Valid));
    }

    //Test should return true because the Binary Tree would be a valid product of adding 30.
    @Test
    public void test1AddEltTester() {
        assertTrue(check.addEltTester(finalTestHeap, 30, finalBT1Valid) );
        assertFalse(check.addEltTester(finalTestHeap, 30, finalTestHeapInvalid));
    }

    //Binary tree is an invalid heap with the right number values contained within.
    @Test
    public void test2AddEltTester() {
        IBinTree invBinTree3 = new DataBT(30, new DataBT(7), new DataBT(6));
        IBinTree invBinTree2 = new DataBT(13, new DataBT(11), new DataBT(8));
        IBinTree invHeapBinTree = new DataBT(2, invBinTree2, invBinTree3);
        assertFalse(check.addEltTester(finalTestHeap, 30, invHeapBinTree));
    }

    //Tests if the Binary tree has the added element but had an element from the original heap removed.
    //(Binary tree has the same size as the heap as well)
    @Test
    public void test3AddEltTester() {



        IBinTree dataBT3 = new DataBT(25);
        IBinTree dataBT1 = new DataBT(5, new DataBT(6), dataBT3);
        IBinTree dataBT2 = new DataBT(8, new DataBT(11), new DataBT(13));
        IBinTree finalBT1 = new DataBT(2, dataBT2, dataBT1);
        assertFalse(check.addEltTester(finalTestHeap, 25, finalBT1));
    }

    //Checks that addEltTester returns false when the added element is added twice but still creates a valid heap.
    @Test
    public void test4AddEltTester() {
        DataBT hasEltTwice3 = new DataBT(7, new DataBT(30), new DataBT(30));
        DataBT hasEltTwice1 = new DataBT(5, new DataBT(6), hasEltTwice3);
        DataBT hasEltTwice2 = new DataBT(8, new DataBT(11), new DataBT(13));
        DataBT finalHasEltTwice = new DataBT(2, hasEltTwice1, hasEltTwice2);

        assertFalse(check.addEltTester(finalTestHeap, 30, finalHasEltTwice));
    }

    //Tests if there is the wrong number of a preexisting element that wasn't added into the heap, that addEltTester
    //identifies that the BinaryTree is not a valid product
    @Test
    public void test5AddEltTester() {
        DataBT wrongNumDifElt3 = new DataBT(7, new DataBT(7), new DataBT(30));
        DataBT wrongNumDifElt1 = new DataBT(5, new DataHeap(6), wrongNumDifElt3);
        DataBT wrongNumDifElt2 = new DataBT(8, new DataHeap(11), new DataHeap(13));
        DataBT wrongNumDifElt = new DataBT(2, wrongNumDifElt1, wrongNumDifElt1);

        assertFalse(check.addEltTester(finalTestHeap, 30, wrongNumDifElt));
    }

    //Tests when all items in the BinaryTree are from the heap, but though 30 is added, one was removed from the heap
    // causing the binary tree to be an invalid product of adding the elt
    @Test
    public void test6AddEltTester() {
        DataBT btWrongNumberElement1 = new DataBT(5, new DataBT(6), new DataBT(30));
        DataBT btWrongNumberElement2 = new DataBT(8, new DataBT(11), new DataBT(13));
        DataBT btWrongNumberElement = new DataBT(2, btWrongNumberElement1, btWrongNumberElement2);

        assertFalse(check.addEltTester(finalTestHeap, 30, btWrongNumberElement));
    }

    //------------------------------------------------------------------------------------------------------------------

    // checks to make sure that with a valid product remMinEltTester returns true
    @Test
    public void test1RemMinEltTester() {
        assertTrue(check.remMinEltTester(finalRemoveHeap, finalRemoveBT));

    }

    //Checks that a result that returns an invalid heap with the right elements returns false
    @Test
    public void test2RemMinEltTester() {
        //invalid heap that has the right elements
        IBinTree invalidWRightElements1 = new DataBT(4, new DataBT(3), new MtHeap());
        IBinTree invalidWRightElements = new DataBT(2, new MtHeap(), invalidWRightElements1);

        assertFalse(check.remMinEltTester(finalRemoveHeap, invalidWRightElements));
    }

    //Checks that when product is right size, but the minimum element still occurs one too many times
    //remMinEltTester returns false
    @Test
    public void test3RemMinEltTester() {
        DataBT removedEltOccursWrongNumTimes1 = new DataBT(5, new DataBT(6), new MtBT());
        DataBT removedEltOccursWrongNumTimes2 = new DataBT(8, new DataBT(11), new DataBT(13));
        DataBT removedEltOccursWrongNumTimes = new DataBT(2, removedEltOccursWrongNumTimes1, removedEltOccursWrongNumTimes2 );

        assertFalse(check.remMinEltTester(finalTestHeap, removedEltOccursWrongNumTimes));
    }

    // When an element that is not being removed from the heap occurs the wrong number of times while
    // still being a valid heap for a result, just not a valid product.
    @Test
    public void test4RemMinEltTester() {
        DataBT otherEltOccurWrongNumTimes1 = new DataBT(5, new DataBT(6), new DataBT(6));
        DataBT otherEltOccurWrongNumTimes2 = new DataBT(11, new MtBT(), new DataBT(13));
        DataBT otherEltOccurWrongNumTimes = new DataBT(8, otherEltOccurWrongNumTimes1, otherEltOccurWrongNumTimes2 );

        assertFalse(check.remMinEltTester(finalTestHeap, otherEltOccurWrongNumTimes));
    }
    // Tests where the returned binary Tree has the wrong number of elements within it, but all elements that were
    // within the original heap
    @Test
    public void test5RemMinEltTester() {
        DataBT wrongSizeBT1 = new DataBT(5, new DataBT(6), new DataBT(6));
        DataBT wrongSizeBT2 = new DataBT(11, new DataBT(11), new DataBT(13));
        DataBT wrongSizeBT = new DataBT(8, wrongSizeBT1, wrongSizeBT2 );

        assertFalse(check.remMinEltTester(finalTestHeap, wrongSizeBT));
    }

    // Tests where the returned binary Tree has the wrong number of elements within it, but some elements differ from
    // what were in the original heap.
    @Test
    public void test6RemMinEltTester() {
        DataBT wrongSizeWDifEltBT1 = new DataBT(5, new DataBT(6), new DataBT(9));
        DataBT wrongSizeWDifEltBT2 = new DataBT(11, new DataBT(7), new DataBT(13));
        DataBT wrongSizeWDifEltBT = new DataBT(8, wrongSizeWDifEltBT1, wrongSizeWDifEltBT2 );

        assertFalse(check.remMinEltTester(finalTestHeap, wrongSizeWDifEltBT));
    }

    @Test
    public void test7RemMinEltTester() {
        assertFalse(check.remMinEltTester(finalRemoveHeap, invalidRemoveBT));
    }

//-----------------------------------------------HELPERS---------------------------------------------------------------

    @Test
    public void recursiveCheckHeapNodesValid() {

        assertTrue(check.recursiveCheckHeapNodesValid(finalTestHeap));
        assertFalse(check.recursiveCheckHeapNodesValid(finalTestHeapInvalid));

    }

    @Test
    public void checkValidHeapWTestHeap3() {
        TestHeap3 invalidTestHeap3 = new TestHeap3(2, new DataHeap(3), new DataHeap(4));

        // Test heap 3 merges invalidTestHeap with a new DataHeap with two empty subtrees so always a valid heap
        // but not valid addElt since the structure has changed.

        //EXTRA note ~ the addElt() in TestHeap3 does return a valid heap but it is an invalid product because
        //it returns the Element in the new list twice.

        invalidTestHeap3.addElt(5);

        assertFalse(check.addEltTester(finalTestHeap,5, invalidTestHeap3));
        assertTrue(check.recursiveCheckHeapNodesValid(invalidTestHeap3));

    }

    @Test
    public void testContainsSameElements() {
        IBinTree test = new DataHeap(2, heap1, heap2);
        assertTrue(check.containsSameElements(finalTestHeap, test));
        assertFalse(check.containsSameElements(finalBT1Valid, test));
        assertFalse(check.containsSameElements(finalBT1Valid, finalTestHeap));
    }

    @Test
    public void testAddEltTesterTestHeap5() {
        TestHeap5 testHeap5Invalid = new TestHeap5(2, new DataHeap(3), new DataHeap(4));
        testHeap5Invalid.addElt(5);

        assertFalse(check.addEltTester(finalTestHeap, 5, testHeap5Invalid));
    }

    @Test
    public void testNumElementInBinTreeAccum() {
        DataBT testDuplicateHeap = new DataBT(3, new DataBT(3), new DataBT(3));
        assertEquals(3, check.numElementInBinTreeAccum(0,3, testDuplicateHeap));
        DataBT testDuplicateHeap2 = new DataBT(3, new DataBT(3), testDuplicateHeap);
        assertEquals(5, check.numElementInBinTreeAccum(0, 3, testDuplicateHeap2));
        assertEquals(0, check.numElementInBinTreeAccum(0, 7, testDuplicateHeap2));

        assertTrue(check.recursiveCheckHeapNodesValid(testDuplicateHeap));
    }

    @Test
    public void testAddEltProductHasRightNumElt() {
        assertTrue(check.addEltProductHasRightNumElt(finalTestHeap, 30, finalBT1Valid));
    }

    @Test
    public void testElementInBothRightNumTimes() {
        assertTrue(check.elementsInBothRightNumTimes(finalTestHeap, 30, finalBT1Valid));
    }

    boolean contains(int[] collection, int target) {
        int low = 0;
        int high = collection.length-1; // MISTAKE HERE
        while (low <= high) {
            int mid = (low+high)/2;
            int rc = collection[mid] - target;
            if (rc < 0) { low = mid+1; }
            else if (rc > 0) { high = mid-1; }
            else { return true; }
        }
        return false;
    }

    @Test
    public void testContains() {
        int[] a = new int[] {1,2,3,4,5,6,7};
        boolean checks = contains(a, 9);
        assertTrue(checks);
    }

}
