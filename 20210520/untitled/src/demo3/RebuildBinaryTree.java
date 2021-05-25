package demo3;

import java.util.Arrays;

public class RebuildBinaryTree {
    public static void main(String[] args) {
        //根据前序中序顺序，重建二叉树
        int[] preorder = {3,9,20,15,7};
        int[] inOrder = {9,3,15,20,7};

        //重建方法---返回根结点
        TreeNode root = RebuildTree(preorder, inOrder);

        //循环遍历
        Print(root);

    }

    public static TreeNode RebuildTree(int[] preorder, int[] inorder) {
        if(preorder.length == 0 || inorder.length == 0) {
            return null;
        }
        int val = preorder[0];
        int Inindex = 0; //根结点在中序数组中的下标
        TreeNode root = new TreeNode(val); //根结点
        root.left = root.right = null;

        //从中序遍历数组找到根结点，左边就是左子树，右边就是右子树
        for (int i = 0; i < inorder.length && inorder[i] != val; i++) {
            Inindex++;  //得到root在inorder数组中的索引
        }

        //左子树
        //Arrays.copyOfRange();  //from 开始（包括），to结束 （不包括）
        root.left = RebuildTree(Arrays.copyOfRange(preorder,1, 1 +  Inindex),
                Arrays.copyOfRange(inorder,0, Inindex));
        //右子树
        root.right = RebuildTree(Arrays.copyOfRange(preorder,1+Inindex,preorder.length),
                Arrays.copyOfRange(inorder,1 + Inindex,inorder.length));
        return root;
    }

    public static void Print(TreeNode root) {
        //前序遍历
        if(root == null)
            return;
        System.out.print(root.val + " ");
        Print(root.left);
        Print(root.right);
    }
}
