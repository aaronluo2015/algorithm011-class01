<h1>第一周学习总结</h1>

第一周的学习，有点不太适应，虽然已经工作多年，也只是写了多年的业务代码。以前也经常看一些算法相关的视频，但都是只是看过，没有太多深刻的理解和动手写过。所以这一周的学习让我非常充实，虽然基本都在默写背诵别人的代码，但是学习到非常多的精妙代码和技巧。听超哥的第一周的视频，主要有几点感悟：

<h2>1.心态转变，设定目标</h2>
首先要明确我们来这次训练营的目的，按照超哥说的：职业顶尖级别、轻松拿到一线互联网公司的offer、LeetCode300+。
对于以前的我来说，这个目标太难，当然现在也太难，但是有哪个人从一开始就能达到顶尖水平的呢。

<h2>2.注重学习方法，刻意练习</h2>
超哥提到的一本书籍《异类》，里面有个“1万小时理论”，意思是想要精通某个领域，需要花“1万小时”就可以达成目标。虽然事情没有绝对，除了花时间和精力外，我想更需要注重学习方法。
<ul>
<li>三分看视频，七分练习。而且要1.5~2.0倍速播放视频，遇到难点暂定并反复观看理解。这个方法第一次听，并实践了下，效果非常棒。</li>
<li>不要死磕，摒弃旧习惯。这个说到了心坎上，以前做一个难题，就会思索几个小时甚至几天。首先心里面过不了这个坎“我就不信搞你不定”，然后死磕到底，最后得出的结论是：“算法太难”，“算法太无趣”等，最后连精妙的题解都没有兴趣。
直接运用五毒神掌，快速把优秀的代码学会，这个有点像站在巨人肩膀上学习的感觉，高效、实用！并且要刷遍数，类似于运动，把优秀代码形成自己的条件反射。
</li>
</ul>

<h2>3.坚持不懈，不忘初心</h2>
借用超哥开营的寄语共勉
“算法的学习和训练不是春种秋收，而是延迟满足感的修行。春种秋收，只要在春天播下种子，就能在秋天收获果实。这四个字背后充满了对获取短期成果的急切心理。有些人太容易觉得人生痛苦，都是因为太在乎即时满足：一点付出，没有立刻得到回报，就会觉得痛苦。”

<br/>
<br/>
<h1>第一周作业</h1>
<h2>1.用 add first 或 add last 这套新的 API 改写 Deque 的代码</h2>
<pre>
  <code>
  private static void initDeque(Deque<String> deque) {
    deque.addLast("b");
    deque.addLast("c");
    deque.addFirst("a");
  }
  public static void main(String[] args) {
    Deque<String> deque = new ArrayDeque<>();
    Queue<String> queue = new PriorityQueue<>();
    initDeque(deque);
    System.out.println(deque.toString());

    System.out.println(deque.peek());
    System.out.println(deque.peek());
    System.out.println(deque.toString());

    while (!deque.isEmpty()) {
      System.out.println(deque.removeLast());
    }
    System.out.println(deque.toString());

    initDeque(deque);
    System.out.println(deque.toString());
    while (!deque.isEmpty()) {
      System.out.println(deque.removeFirst());
    }
    System.out.println(deque.toString());
  }
</code>
</pre>
<h2>2.分析 Queue 和 Priority Queue 的源码</h2>
<p>语言：java</p>
<h3>Queue</h3>
Queue是在Collection基础上扩展的接口，扩展了以下几个队列的方法：
<h4>1）boolean add(E e)</h4>
<ul>
<li>功能：把泛型元素e放入到队里中，但是会受队列容量限制</li>
<li>输入：需要放入队列的元素</li>
<li>输出：成功返回true，失败返回false</li>
<li>
抛出异常：</br>
<code>
IllegalStateException 因为队列容量限制，不能放入队列
ClassCastException 因为放入的元素类型和Queue声明的泛型类型不一致
NullPointerException 放入队列的元素是null
IllegalArgumentException 因为元素的某些属性导致不能被放入队列
</code>
</li>
</ul>
<h4>2）boolean offer(E e)</h4>
<ul>
<li>功能：把泛型元素e放入到队里中，不受队列容量限制，底层自动扩容队列容量</li>
<li>输入：需要放入队列的元素</li>
<li>输出：成功返回true，失败返回false</li>
<li>抛出异常：<br/>
<code>
ClassCastException 因为放入的元素类型和Queue声明的泛型类型不一致
NullPointerException 放入队列的元素是null
IllegalArgumentException 因为元素的某些属性导致不能被放入队列
</code>
</li>
</ul>
<h4>3）E remove()</h4>
<ul>
  <li>功能：队列头第一个元素出列，队列空异常抛出</li>
<li>输入：无</li>
<li>输出：删除队列头第一个元素并返回元素</li>
<li>抛出异常：<br/>
<code>
NoSuchElementException 队列为空时抛出异常
</code>
</li>
</ul>
<h4>4）E poll()</h4>
<ul>
<li>功能：队列头第一个元素出列，队列空返回null</li>
<li>输入：无</li>
<li>输出：删除队列头第一个元素并返回元素</li>
<li>抛出异常：无</li>
  </ul>
<h4>5）E element()</h4>
<ul>
<li>功能：获取队列头第一个元素，但是不出列，队列空时抛出异常</li>
<li>输入：无</li>
<li>输出：获取队列头第一个元素</li>
<li>抛出异常：
  <br/>
  <code>
NoSuchElementException 队列为空时抛出异常
  </code></li>
  </ul>
<h4>6）E peek()</h4>
<ul>
<li>功能：获取队列头第一个元素，但是不出列，队列空时返回null</li>
<li>输入：无</li>
<li>输出：获取队列头第一个元素</li>
<li>抛出异常：无</li>
</ul>

<h3>Priority Queue</h3>
<p>Priority Queue是一个优先级队列，队列中的元素按照提供的comparator或者元素实现的Comparator接口做比较，按优先级排序放入队列中，继承自AbstractQueue，而AbstractQueue实现了Queue接口的方法</p>
<h4>1）boolean add(E e)</h4>
<p>直接调用offer方法。</p>
<h4>2）boolean offer(E e)</h4>
<ul>
<li>第一步：检查队列当前数组容量，如果当前容量不足，则根据当前容量值进行扩展，
如果当前容量oldCapacity小于64，则扩展一倍+2，否则扩展当前容量的50%。
如果超过Integer.MAX_VALUE，则抛出OutOfMemoryError异常
</li>
<li>第二步：存放队列数据如果队列为空，直接插入第一个未知。否则进行比较，如果队列有设置comparator则使用设置的comparator对元素进行比较
否则使用元素实现的Comparable接口方法进行比较，插入对应的数据优先级位置</li>
</ul>
<h4>3）E remove()</h4>
<p>调用poll方法，如果没有元素则抛出NoSuchElementException异常</p>
<h4>4）E poll()</h4>
<li>获取第一个元素</li>
</li>将最后一个元素数组置空，并放到正确位置</li>
<h4>5）E element()</h4>
<p>调用peek方法，如果没有元素则抛出NoSuchElementException异常</p>
<h4>6）E peek()</h4>
<p>获取第一个元素</p>
