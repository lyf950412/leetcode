package github.lyf;

/**
 * @author lyf
 */
public class LowestCommonAncestor1 {
    /**
     *给定一个二叉树（无序）, 找到该树中两个指定节点的最近公共祖先。
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，
     * 最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     * *说明:
     *      * 所有节点的值都是唯一的。
     *      * p、q 为不同节点且均存在于给定的二叉搜索树中。
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        /**
         * 思路：
         *      * 三种情况：
         *      * 1、p q 一个在左子树 一个在右子树 那么当前节点即是最近公共祖先
         *      * 2、p q 都在左子树
         *      * 3、p q 都在右子树
         */
        if(root==null) return null;
        if(root.val==p.val||root.val==q.val) return root;
        TreeNode left= lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        // 表示p  q一个在左边的树，一个在右边的树
        if(left != null && right != null){
            return root;
        }
        //p  q都在右边
        if(left==null){
            return right;
        }
        //p  q都在左边
        if(right==null){
            return left;
        }
        return  null;
    }
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
