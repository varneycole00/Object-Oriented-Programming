class MtBT implements IBinTree {
    MtBT(){}

    // returns false since empty tree has no elements
    public boolean hasElt(int e) {
        return false;
    }

    // returns 0 since enpty tree has no elements
    public int size() {
        return 0;
    }

    // returns 0 since empty tree has no branches
    public int height() {
        return 0;
    }

    //returns null since the tree is empty
    public int getData() {return 0;}

    //returns an empty IBinTree since the BT is empty
    public IBinTree getLeft() { return new MtBT();}

    //returns an empty IBinTree since the BT is empty
    public IBinTree getRight() { return new MtBT();}

    // Returns true since the data will always be larger than the subtrees because they are empty
    public boolean dataSmallerThanNext(IBinTree heap) {
        return true;
    }

}
