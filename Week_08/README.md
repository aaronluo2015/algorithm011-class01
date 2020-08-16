# 初级排序

## 1 选择排序
### 算法步骤
- 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置。
- 再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
- 重复第二步，直到所有元素均排序完毕。
### Java 代码实现
```java
public class Solution {
    public int[] selectSort(int[] nums) {
        if (null == nums || nums.length < 2) return nums;
		int length = nums.length;
		for (int i = 0 ; i < length - 1; i++) {
			int minIndex = i;
			for (int j = i + 1; j < length; j++) {
				if (nums[minIndex] > nums[j]) minIndex = j;
			}
			if (i != minIndex) {
				int temp = nums[i];
				nums[i] = nums[minIndex];
				nums[minIndex] = temp;
			}
		}
		return nums;
    }
}
```
## 2 插入排序
### 算法步骤
- 将第一待排序序列第一个元素看做一个有序序列，把第二个元素到最后一个元素当成是未排序序列。
- 从头到尾依次扫描未排序序列，将扫描到的每个元素插入有序序列的适当位置。（如果待插入的元素与有序序列中的某个元素相等，则将待插入元素插入到相等元素的后面。）
### Java 代码实现
```java
public class Solution {
    public int[] insertSort(int[] nums) {
        if (null == nums || nums.length < 2) return nums;
		int length = nums.length;
		for (int i = 0 ; i < length - 1; i++) {
			int minIndex = i + 1;
			for (int j = i; j >= 0; j--) {
				if (nums[j] > nums[minIndex]) {
					int temp = nums[minIndex];
					nums[minIndex] = nums[j];
					nums[j] = temp;
					minIndex = j;
				}
			}
		}
		return nums;
    }
}
```
# 3 冒泡排序
### 算法步骤
- 比较相邻的元素。如果第一个比第二个大，就交换他们两个。
- 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。这步做完后，最后的元素会是最大的数。
- 针对所有的元素重复以上的步骤，除了最后一个。
- 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
### Java 代码实现
```java
public class Solution {
    public int[] bubbleSort(int[] nums) {
        if (null == nums || nums.length < 2) return nums;
		int length = nums.length;
		for (int i = 0 ; i < length; i++) {
			int maxIndex = 0;
			for (int j = 1; j < length - i; j++) {
				if (nums[j] < nums[maxIndex]) {
					int temp = nums[maxIndex];
					nums[maxIndex] = nums[j];
					nums[j] = temp;
				}
				maxIndex = j;
			}
		}
		return nums;
    }
}
```