package org.jackJew.algorithm.BalancedBinaryTree;

/**
 * 有序二叉树的实现
 * <br>
 * 查找/删除/插入的时间复杂度 O(logN)
 * @author zhurunjia
 */
public final class BalancedTree {
	
	/**
	 * 根节点
	 */
	private TreeNode root;
	/**
	 * 节点数目
	 */
	private int size;
	
	/**
	 * 插入单个节点
	 */
	public void insertNode(int data){
		//首次创建根节点
		if(root == null){
			root = new TreeNode(data);
			return;
		}
		
		TreeNode current = root;	//当前节点
		TreeNode parent;	//父节点
		while(true){
			parent = current;
			if(data < current.value){
				current = current.left;
				if(current == null){	//左节点为空, 将值作为新节点置入这个位置
					parent.left = new TreeNode(data);
					return;
				}
			}
			else{
				current = current.right;
				if(current == null) {	//右节点为空, 将值作为新节点置入这个位置
					parent.right = new TreeNode(data);
					return;
				}
			}
		}
	}
	
	/**
	 * 创建二叉排序树
	 */
	public TreeNode createBalancedTree(int[] datas){
		if(datas == null || datas.length < 1)
			throw new IllegalArgumentException("请提供具有元素的数组!");
		
		for(int i = 0; i < datas.length; i++){
			insertNode(datas[i]);
		}
		this.size = datas.length;
		
		System.out.println("根节点: " + root.fullValue());
		return root;	//返回根节点的值
	}
	
	/**
	 * 查找指定值
	 */
	public TreeNode searchNode(int data){
		
		TreeNode current = root;
		while(current != null && current.value != data){	//不断遍历, 直至找到
			current = data < current.value ? current.left : current.right;
		}
		
		System.out.print("查找到的节点: ");
		if(current != null)
			System.out.println(current.fullValue());
		else
			System.out.println("null");
		return current;
	}
	
	/**
	 * 删除指定值的节点
	 * <br> recursion 递归
	 */
	public void deleteNode(TreeNode node, int data){
		if(node == null)
			return;
		
		if(data == node.value){		//找到节点
			//如何删除此节点
			if(node.left == null && node.right == null)		//完全是叶子节点
				node = null;
			else if(node.left != null && node.right == null)	//左节点非空
				node = node.left;
			else if(node.left == null && node.right != null)	//右节点非空
				node = node.right;
			else {	//左右节点都存在, 查找右子树的最左节点
				TreeNode currentParent = node.right;
				TreeNode current = currentParent;
				while(current.left != null){
					currentParent = current;
					current = current.left;
				}
				
				if(currentParent.value != current.value){
					currentParent.left = null;
					currentParent.left = current.right;
				}
				node.value = current.value;	//设为新值
				
				System.out.println("node value: " + node.value);
			}
		}
		else if(data < node.value)
			deleteNode(node.left, data);
		else
			deleteNode(node.right, data);
		
		this.size--;
	}
	
	/**
	 * 按二叉树 根, 左, 右节点输出 (前序遍历递归版)
	 * <br> recursion 递归
	 */
	public void displayByTree(TreeNode node){
		if(node != null){
			System.out.print(node.value + ", ");
			
			displayByTree(node.left);
			displayByTree(node.right);
		}
	}
	
	/**
	 * 按二叉树 根, 左, 右节点输出 (前序遍历非递归版)
	 * <br> recursion 递归
	 */
	public void displayByTreeNonRecursive(TreeNode node){
		if(node != null){
			System.out.print(node.value + ", ");
			TreeNode temp;
			while(node != null){				
				temp = node.left;
				if(temp != null){  // 根
					System.out.print(node.value + ", ");
				} else {
					System.out.print(node.value + ", ");
				}
				node = temp;
			}
		}
	}
	
	/**
	 * 升序排列输出
	 */
	public void displayByASC(){
		Object[] values = new Object[this.size];
		int index = 0;
		
	}
	
	public TreeNode getRoot(){
		return this.root;
	}

}
