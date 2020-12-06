public class HeapChecker {

    HeapChecker(){}

    /**
     * Consumes a Heap, an elt that is being added to a heap, and a binary tree. It checks
     * to see if the binary tree would be a valid variation of the inputted heap with the element
     * added.
     * @param hOrig
     * @param elt
     * @param hAdded
     * @return Returns true if the binary tree is a valid heap with the element added.
     */
    public boolean addEltTester(IHeap hOrig, int elt, IBinTree hAdded) {
        return recursiveCheckHeapNodesValid(hAdded) &&
                isOneBigger(hOrig, hAdded) &&
                containsSameElements(hOrig, hAdded) &&
                hAdded.hasElt(elt) &&
                addEltProductHasRightNumElt(hOrig, elt, hAdded) &&
                elementsInBothRightNumTimes(hOrig, elt, hAdded);
    }

    /**
     * Takes in a binary tree and checks if it is a valid Heap.
     * @param binTree
     * @return True if the binary tree is a valid heap.
     */
    public boolean recursiveCheckHeapNodesValid(IBinTree binTree) {

        // if both the left and right nodes are null, return true, because it cycled through the entirety
        // of the heap and didn't trigger the return false
        if( (binTree.getLeft().getData() == 0 && binTree.getRight().getData() == 0)) {
            return true;
        }
        //When the left node data = null, and the data is less than the right subtree data,
        //continue iterating for the right subtree
        if( binTree.getLeft().getData() == 0 && binTree.getData() < binTree.getRight().getData()) {
            return recursiveCheckHeapNodesValid(binTree.getRight());
        }
        //When the right node data = null, and the data is less than the right subtree data,
        // continue iterating for the left subtree
        if ( binTree.getRight().getData() == 0 && binTree.getData() < binTree.getLeft().getData()) {
            return recursiveCheckHeapNodesValid(binTree.getLeft());
        }
        //If the data is greater than EITHER the data from the left or right subtree, return false
        if ((binTree.getData() > binTree.getLeft().getData()) || (binTree.getData() > binTree.getRight().getData())) {
            return false;
        }
        //If the data is less than or equal to the data from BOTH the left and right subtree, run the recursive
        //function on both subtrees and return true if the result of both are true
        if ((binTree.getData() <= binTree.getLeft().getData() && (binTree.getData() <= binTree.getRight().getData()))) {
            return recursiveCheckHeapNodesValid(binTree.getLeft()) && recursiveCheckHeapNodesValid(binTree.getRight());
        }

        return true;
    }

    /**
     * Takes in a Heap and a Binary Tree and checks if the Binary tree has one more element than
     * the Heap.
     * @param hOrig
     * @param hAdded
     * @return true if the Binary Tree has one more element than the heap does
     * (heap size + 1 = element size).
     */
    public boolean isOneBigger(IBinTree hOrig, IBinTree hAdded) {
        // Checks to make sure that the size of the bin tree representing a valid heap with
        // the element is one larger than the original (Since one element has been added)
        return ((hAdded.size() == (hOrig.size()) +1 ));
    }

    /**
     * Checks if an inputted Binary Tree has all the same elements as an inputted Heap
     * @param hOrig
     * @param hAdded
     * @return true if all of the values in the Heap are also included in the Binary Tree
     */
    public boolean containsSameElements(IBinTree hOrig, IBinTree hAdded) {

        //If it doesn't have the data, it should return false
        if(!hAdded.hasElt(hOrig.getData())) {
            return false;
        }

        //If both the left and the right nodes have 0 (null) as their data, then
        //the function should return true since it made it to the end without triggering
        //a false
        if(hOrig.getRight().getData() == 0 && hOrig.getLeft().getData() == 0) {
            return true;
        }

        //If the right subtree data is equal to null, continue testing only the left side
        if(hOrig.getRight().getData() == 0) {
            return containsSameElements(hOrig.getLeft(), hAdded);
        }

        //If the left side == null, continue testing the right side
        if(hOrig.getLeft().getData() == 0) {
            return containsSameElements(hOrig.getRight(), hAdded);
        }

        //Checking to see that hAdded has the data of the node somewhere in the BinTree
        if(hAdded.hasElt(hOrig.getData())) {
            return containsSameElements( hOrig.getLeft(), hAdded) &&
                    containsSameElements(hOrig.getRight(), hAdded);
        }

        return true;
    }

    /**
     * Takes in an accumulator(0), an element, and a Binary Tree and returns the number of times the
     * element is in the Binary Tree.
     * @param accum
     * @param elt
     * @param hOrig
     * @return the number of times an element is in the Binary Tree.
     */
    int numElementInBinTreeAccum(int accum, int elt, IBinTree hOrig) {
        //Adds one to the accum for number of the given elt in the BinTree
        if(hOrig.getData() == elt) {
            accum = 1 + numElementInBinTreeAccum(0, elt, hOrig.getLeft());
            accum += numElementInBinTreeAccum(0, elt, hOrig.getRight());
        }
        //When the end of the bin tree is reached, return the accum for num of times
        //the elt appeared
        if(hOrig.getLeft().getData() == 0 && hOrig.getRight().getData() == 0) {
            return accum;
        }
        //If the right subtree data is null, but the right contains data, run the accum
        //on the left subtree to check for more duplicates
        if(hOrig.getRight().getData() == 0) {
            return numElementInBinTreeAccum(accum, elt, hOrig.getLeft());
        }
        //If the left subtree data is null, but the right contains data, run the accum
        //on the right subtree to check for more duplicates
        if(hOrig.getLeft().getData() == 0) {
            return numElementInBinTreeAccum(accum, elt, hOrig.getRight());
        }
        //Adds one to the accum for number of the given elt in the BinTree
        if(hOrig.getData() == elt) {
        }
        //Runs on left and right subtree if the data doesn't equal the elt
        if(hOrig.getData() != elt) {
            accum = numElementInBinTreeAccum(0, elt, hOrig.getLeft());
            accum += numElementInBinTreeAccum(0, elt, hOrig.getRight());
        }
        return accum;
    }

    /**
     * Consumes a Heap, an element, and a Binary Tree and checks if the Binary tree has the right
     * number of an element.
     * @param hOrig
     * @param elt
     * @param hAdded
     * @return true if the element is in the Binary tree one more time than it is in the Heap.
     */
    boolean addEltProductHasRightNumElt(IBinTree hOrig, int elt,  IBinTree hAdded) {
        //checks to make sure that the elt appears one more time in hAdded than in hOrig
        return numElementInBinTreeAccum(0, elt, hAdded) ==
                numElementInBinTreeAccum(0, elt, hOrig) + 1;
    }

    /**
     * Checks to make sure that all of the elements in the Heap are in the Binary Tree
     * the right number of times.
     * @param hOrig
     * @param elt
     * @param hAdded
     * @return true if each element in the Heap is in the Binary Tree
     */
    boolean elementsInBothRightNumTimes(IBinTree hOrig, int elt, IBinTree hAdded) {
        //if the left and right subtrees both represent ends of data, return true
        //because false wasn't triggered before here
        if (hOrig.getLeft().getData() == 0 && hOrig.getRight().getData() == 0) {
            return true;
        }
        //if the left subtree data represents an end, continue running the recursive
        //test on the right subtree
        if (hOrig.getLeft().getData() == 0) {
            return elementsInBothRightNumTimes(hOrig.getRight(), elt, hAdded);
        }
        //if the right subtree data represents an end, continue running the recursive
        //test on the left subtree
        if (hOrig.getRight().getData() == 0) {
            return elementsInBothRightNumTimes(hOrig.getLeft(), elt, hAdded);
        }
        //If the data is the elt that is getting added or removed, skip past it
        //since hAdded will have it included but hOrig will not.
        if(hOrig.getData() == elt) {
            return elementsInBothRightNumTimes(hOrig.getRight(), elt, hAdded) &&
                    elementsInBothRightNumTimes(hOrig.getLeft(), elt, hAdded);
        }
        //If the data does not have it, check to make sure that the data for hAdded has it
        // the same number of times as hOrig
        if(hOrig.getData() != elt) {
            if( numElementInBinTreeAccum(0, hOrig.getData(), hAdded) ==
                     numElementInBinTreeAccum(0, hOrig.getData(), hOrig)) {
                return elementsInBothRightNumTimes(hOrig.getRight(), hOrig.getData(), hAdded) &&
                        elementsInBothRightNumTimes(hOrig.getLeft(), elt, hAdded);
            }
        }
        //Backup return statement for if it didn't trigger a fale (don't think thats likely)
        return true;
    }

    /**
     * Checks to make sure that hRemoved is a valid heap that would result from removing the element at the top
     * of the Heap.
     * @param hOrig
     * @param hRemoved
     * @return true if hRemoved is a valid heap with the element at the top of the heap removed.
     */
    public boolean remMinEltTester(IHeap hOrig, IBinTree hRemoved) {
        return recursiveCheckHeapNodesValid(hRemoved) &&
                isOneBigger(hRemoved, hOrig) &&
                containsSameElements(hRemoved, hOrig) &&
                addEltProductHasRightNumElt(hRemoved, hOrig.getData(), hOrig) &&
                elementsInBothRightNumTimes(hRemoved, hOrig.getData(), hOrig);
    }
}
