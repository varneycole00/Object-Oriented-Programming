import java.lang.Math;

interface IBinTree {
 // determines whether element is in the tree
 boolean hasElt(int e);
 // returns number of nodes in the tree; counts duplicate elements as separate items
 int size();
 // returns depth of longest branch in the tree
 int height();
 // returns the data associated data
 int getData();
 //returns the left sub-tree
 IBinTree getLeft();
 // returns the right sub-tree
 IBinTree getRight();
 // Determines if the binary tree follows the rule for heaps where the data is larger than the first of its left
 // and right subtree

}