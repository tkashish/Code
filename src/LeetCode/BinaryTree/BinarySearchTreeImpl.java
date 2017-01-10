package LeetCode.BinaryTree;

import com.sun.istack.internal.Nullable;

/**
 * Created by kashishtayal on 1/9/17.
 */
public class BinarySearchTreeImpl implements IBinaryTree{
    private TreeNode _root;

    public BinarySearchTreeImpl(){
        _root = null;
    }

    @Override
    public void add(int inKey) {
        final TreeNode parentNode = findParentMaybeNull(inKey);
        final TreeNode node = new TreeNode(inKey);
        if(parentNode == null){
            _root = node;
        }else{
            if(parentNode.val > inKey){
                TreeNode temp = parentNode.left;
                parentNode.left = node;
                node.left = temp;
            }else{
                TreeNode temp = parentNode.right;
                parentNode.right = node;
                node.right = temp;
            }
        }
    }

    @Override
    public void delete(int inKey) {
        final TreeNode parentNode = findParentMaybeNull(inKey);
        if(parentNode == null){
            if(_root.val == inKey){
                _root = null;
            }
        }else{
            if(parentNode.val > inKey){
                TreeNode nodeToDelete = parentNode.left;
                TreeNode replacement = replaceNodeToDelete(nodeToDelete);
                parentNode.left = replacement;
            }else{
                TreeNode nodeToDelete = parentNode.right;
                TreeNode replacement = replaceNodeToDelete(nodeToDelete);
                parentNode.right = replacement;
            }
        }
    }

    @Override
    public boolean find(int inKey) {
        TreeNode nodeParent = findParentMaybeNull(inKey);
        if(nodeParent == null) return false;
        if(nodeParent.val > inKey){
            return nodeParent.left != null;
        }else{
            return nodeParent.right != null;
        }
    }

    @Override
    public TreeNode getRoot() {
        return _root;
    }

    @Nullable
    private TreeNode findParentMaybeNull(int inKey){
        TreeNode parent = null;
        TreeNode currNode = _root;
        while(currNode != null && currNode.val != inKey){
            parent = currNode;
            if(currNode.val > inKey){
                currNode = currNode.left;
            }else{
                currNode = currNode.right;
            }
        }
        return parent;
    }

    @Nullable
    private TreeNode findSuccessorParentMaybeNull(TreeNode inNode){
        TreeNode parent = inNode;
        TreeNode successor = inNode.right;
        if(successor == null){
            return parent;
        }
        TreeNode leftNode = successor.left;
        while(leftNode != null){
            parent = successor;
            successor = leftNode;
            leftNode = leftNode.left;
        }
        return parent;
    }

    @Nullable
    private TreeNode replaceNodeToDelete(TreeNode inNodeToDelete){
        TreeNode replacement = null;
        if(inNodeToDelete != null){
            TreeNode successorParent = findSuccessorParentMaybeNull(inNodeToDelete);
            if(successorParent == inNodeToDelete){
                if(successorParent.right == null){
                    replacement = inNodeToDelete.left;
                    inNodeToDelete.left = null;
                }else{
                    TreeNode successor = successorParent.right;
                    replacement = successor;
                    inNodeToDelete.right = null;
                    successor.left = inNodeToDelete.left;
                    inNodeToDelete.left = null;
                }
            }else{
                TreeNode successor = successorParent.left;
                successorParent.left = successor.right;
                successor.left = inNodeToDelete.left;
                successor.right = inNodeToDelete.right;
                inNodeToDelete.left = null;
                inNodeToDelete.right = null;
                replacement = successor;
            }
        }
        return replacement;
    }
}
