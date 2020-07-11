class Solution {
	private Map<Integer, Integer> indexMap;
	public TreeNode buildTree(int[] preorder, int[] inorder) {
		int n = preorder.length;
		if (n == 0) return null;
		indexMap = new HashMap<Integer, Integer>();
		for (int i=0; i<n; i++) {
			indexMap.put(inorder[i], i);
		}
		return this.myBuildTree(preorder, inorder, 0, n-1, 0, n-1);
	}
	
	private TreeNode myBuildTree(int[] preorder, int[] inorder, int preorder_left, int preorder_right, int inorder_left, int inorder_right) {
		if (preorder_left > preorder_right) {
			return null;
		}
		int preorder_root = preorder_left;
		int inorder_root = indexMap.get(preorder[preorder_root]);
		int size_left_subtree = inorder_root - inorder_left;
		TreeNode root = new TreeNode(preorder[preorder_root]);
		root.left = this.myBuildTree(preorder, inorder, preorder_left + 1, preorder_left + size_left_subtree, inorder_left, inorder_root -1 );
		root.right = this.myBuildTree(preorder, inorder, preorder_left + 1 + size_left_subtree, preorder_right, inorder_root+1, inorder_right);
		return root;
	}
}