# 总结 W10

## 数据结构
需要了解结构的特性，以及时间、空间复杂度。

* 一维
	* 基础
		* 数组
		* 链表
	* 高级
		* 栈
			* 括号匹配问题
			* 直方图中间找一个最大面积问题，以及其引申问题，下雨可以盛多少水
		* 队列
			* 双端队列：在头部和尾部进行出入的操作
			* 滑动窗口问题
			* 优先队列
				* 堆：第 k 大元素问题以及堆排序问题
		* 集合
			* `HashSet` `O(1)`的修改和访问的时间复杂度
			* `TreeSet` 底层使用二叉搜索树实现，所以修改和访问均是 `O(logn)`
		* 映射
			* `HashMap` 修改和访问时间复杂度`O(1)`
			* `HashSet` 修改和访问时间复杂度 `O(logn)`
* 二维
	* 基础
		* 树
			* DFS
			* BFS 分层遍历
			* 现实中要解决的问题的状态是一颗树，如硬币兑换问题以及爬楼梯问题
		* 图
	* 	高级
		* 二叉搜索树、红黑树、AVL
		* 堆
		* 并查集
		* 字典树 Trie
* 特殊
	* 位运算：主要用来进行判重。比如在递归、搜索、遍历的时候用来判断这个状态是否已经访问过，用位运算进行存储。例如N皇后问题，数独问题。
	* 布隆过滤器
	* LRU Cache

![](https://tva1.sinaimg.cn/large/007S8ZIlgy1gi4a2zrbjfj30nj0d60w8.jpg)

## 算法
* branch (if-else, switch)
* iteration (for, while loop)
* recursion (分治，回溯)
* 搜索：DFS, BFS, `A*`
* 动态规划
	* 搜索的过程中，发现子问题有所谓的最优解，且在中间可以淘汰次优解，即拥有最佳子结构，则可以用动态规划来解决问题。
	* 动态规划可以是递归的，然后加上备忘录，让重复的子状态被存储起来；也可以是基于一个数组，从最开始就把初始状态放好了之后写循环进行顺推。
* 二分查找
* 贪心
* 数学、几何

借助mindmap进行回顾：

* https://naotu.baidu.com/file/b832f043e2ead159d584cca4efb19703?token=7a6a56eb2630548c
* https://naotu.baidu.com/file/0a53d3a5343bd86375f348b2831d3610?token=5ab1de1c90d5f3ec

## 代码模板
*  [递归代码模板](https://shimo.im/docs/EICAr9lRPUIPHxsH/)  ‼️
*  [分治代码模板](https://shimo.im/docs/zvlDqLLMFvcAF79A/) ‼️
*  [DFS 代码模板（递归写法、非递归写法）](https://shimo.im/docs/UdY2UUKtliYXmk8t/) ‼️
*  [BFS 代码模板](https://shimo.im/docs/ZBghMEZWix0Lc2jQ/) ‼️
*  [二分查找代码模板](https://shimo.im/docs/xvIIfeEzWYEUdBPD/) 
*  [Tire 树代码模板](https://shimo.im/docs/DP53Y6rOwN8MTCQH) ‼️
*  [并查集代码模板](https://shimo.im/docs/VtcxL0kyp04OBHak) ‼️
*  [`A*` 代码模板](https://shimo.im/docs/8CzMlrcvbWwFXA8r) 
*  [布隆过滤器 Python 代码示例](https://shimo.im/docs/UITYMj1eK88JCJTH) 
*  [LRU Cache Python 代码示例](https://shimo.im/docs/CoyPAyXooGcDuLQo) 
*  [快速排序代码示例](https://shimo.im/docs/TX9bDbSC7C0CR5XO) ‼️
*  [归并排序代码示例](https://shimo.im/docs/sDXxjjiKf3gLVVAU)  ‼️
*  [堆排序代码示例](https://shimo.im/docs/M2xfacKvwzAykhz6) ‼️
*  [Atoi 代码示例](https://shimo.im/docs/5kykuLmt7a4DdjSP) 
*  [字符串匹配暴力法代码示例](https://shimo.im/docs/8G0aJqNL86wWrPUE) 
*  [Rabin-Karp 代码示例](https://shimo.im/docs/1wnsM7eaZ6Ab9j9M) 
*  [N 皇后位运算代码示例](https://shimo.im/docs/YzWa5ZZrZPYWahK2) ‼️

## 经典问题

* 爬楼梯、硬币兑换
	* 这两个问题是可以相互转换的
	* DFS + 备忘录、动态规划、BFS。所有解法都要掌握
	* 这两个问题基本上涵盖了面试最高频的算法题
* 括号匹配：栈
* 括号生成：递归 DFS
* 直方图最大面积：栈。扩展问题：下雨后盛水容器
* 滑动窗口：双端队列
* 二叉树遍历：DFS 前中后序；BFS
* 分层输出树：DFS；BFS
* 判断二叉排序树
	* 定义
	* 中序遍历得到上升数组
* 股票买卖、偷房⼦、字符串编辑距离、最⻓上升⼦序列、最⻓公共⼦序列：DP
* 异位词（判断和归类）、回⽂串（最⼤回⽂串）、regex和通配符匹配
* ⾼级数据结构（Trie、BloomFilter、LRU cache、etc）：代码模板

## 五毒神掌
第⼀遍：不要死磕，要看代码学习（⼀定要看国际版的⾼票回答）
第⼆遍：⾃⼰写
第三遍：24⼩时后
第四遍：⼀周后
第五遍：⾯试前

## 面试技巧

调整心态：

* 不是和题目竞争，而是其他候选人竞争; 
* 和面试官是未来同事的关系，面试的过程是合作的关系，让对方感觉与你合作是很舒服的事情
* 面试题，遇到开胃菜需要迅速解决掉，马上会有后续

解题步骤：
1. Clarification：明确题⽬意思、边界、数据规模
2. Possible solutions：穷尽所有可能的解法
	* compare time/space
	* optimal solution 
4. Coding：代码简洁、⾼性能、美感
	* https://shimo.im/docs/rHTyt8hcpT6D9Tj8 
4. Test cases

## 参考链接
*  [Big O Cheat Sheet](https://www.bigocheatsheet.com/) 
