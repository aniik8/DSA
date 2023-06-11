package Bst;

import java.util.*;

public class BstGfG {
    public static void main(String[] args) {

    }
    boolean isBST(Tree root)
    {
        List<Integer> list = new ArrayList<>();
        checkIsBst(root, list);
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i) >= list.get(i+1))
                return false;
        }
        return true;
    }
    void checkIsBst(Tree root, List<Integer> list){
        if(root == null)
            return;
        checkIsBst(root.left, list);
        list.add(root.val);
        checkIsBst(root.right, list);
    }
}
