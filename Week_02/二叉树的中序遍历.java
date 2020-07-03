// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
	public List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> res = new ArrayList<Integer>();
		this.inorderTraversal(root, res);
		return res;
	}
	
	private void inorderTraversal(TreeNode node, List<Integer> res) {
		if (null == node) {
			return;
		}
		if (null != node.left) {
			this.inorderTraversal(node.left, res);
		}
		res.add(node.val);
		if (null != node.right) {
			this.inorderTraversal(node.right, res);
		}
	}
}