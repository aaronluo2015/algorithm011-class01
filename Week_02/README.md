# HashMap源码分析
## 简介
HashMap是一种Map，HashMap仅是一种Map的实现版本。HashMap将根据key的hashCode值来找到存储value的位置，如果hash函数比较完美的话，因为可以很快的找到key对应的value存储的位置，所以具有很高的效率，需要注意的一点是，HashMap因为是基于key的hashCode值来存储value的，所以遍历HashMap不会保证它的顺序和插入时的顺序一致，可以说很大概率这个顺序是不一致的，所以如果需要保持插入顺序，你不可以选择HashMap。还要一点是HashMap允许key为null，但是只允许有一个key为null，再次说明，HashMap不是线程安全的，并发环境下你应该首选ConcurrentHashMap，ConcurrentHashMap是一种高效的并发Map，它是线程安全版本的HashMap

## HashMap内部结构

首先来看一下HashMap内部结构是什么样子的。通过观察源码，可以发现HashMap在实现上使用了数组+链表+红黑树三种数据结构，可以说在实现上HashMap是比较复杂的，但是这种复杂性带来的收益是很大的，HashMap是一种非常高效的Map，这也是它为什么这么受欢迎的主要原因。
```java
    static class Node<K,V> implements Map.Entry<K,V> {
        final int hash; //哈希值，HashMap用这个值来确定记录的位置
        final K key; //记录key
        V value; //记录value
        Node<K,V> next;//链表下一个节点

        Node(int hash, K key, V value, Node<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final K getKey()        { return key; }
        public final V getValue()      { return value; }
        public final String toString() { return key + "=" + value; }

        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        public final boolean equals(Object o) {
            if (o == this)
                return true;
            if (o instanceof Map.Entry) {
                Map.Entry<?,?> e = (Map.Entry<?,?>)o;
                if (Objects.equals(key, e.getKey()) &&
                    Objects.equals(value, e.getValue()))
                    return true;
            }
            return false;
        }
    }
```
下面是上面图中展示的数组：
```java
transient Node<K,V>[] table;
```
这个table就是存储数据的数组，上面图中的每个黑色的球是一个Node。下面展示了几个重要的成员变量：
```java
    /**
     * The number of key-value mappings contained in this map.
     */
    transient int size;
    
    /**
     * The next size value at which to resize (capacity * load factor).
     *
     * @serial
     */
    // (The javadoc description is true upon serialization.
    // Additionally, if the table array has not been allocated, this
    // field holds the initial array capacity, or zero signifying
    // DEFAULT_INITIAL_CAPACITY.)
    int threshold;    
    
     /**
     * The load factor for the hash table.
     *
     * @serial
     */
    final float loadFactor;   
    
     /**
     * The default initial capacity - MUST be a power of two.
     */
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16   

    /**
     * The maximum capacity, used if a higher value is implicitly specified
     * by either of the constructors with arguments.
     * MUST be a power of two <= 1<<30.
     */
    static final int MAXIMUM_CAPACITY = 1 << 30;

    /**
     * The load factor used when none specified in constructor.
     */
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
```
需要注意的一点是，HashMap的哈希桶table的大小必须为2的n次方，初始大小为16，下文中将会说明为什么一定要是2的n次方。size字段的意思是当前记录数量，loadFactor是负载因子，默认为0.75，而threshold是作为扩容的阈值而存在的，它是由负载银子决定的。下面的方法是返回与给定数值最接近的2的n次方的值：
```java
    /**
     * Returns a power of two size for the given target capacity.
     */
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
```
## HashMap如何确定记录的table位置
在理解了HashMap的基本存储结构之后，首先来分析一下HashMap是如何确定记录的table位置的。这是至关重要的一步，也是众多HashMap操作的第一步，因为要想找到记录，首先要确定记录在table中的index，然后才能去table的index上的链表或者红黑树里面去寻找记录。下面的方法hash展示了HashMap是如何计算记录的hashCode值的方法：
```java
    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
```
上面的hash方法仅仅是第一步，它只是计算出了hashCode值，但是还可以确定table中的index，接下来的一步需要做的就是根据hashCode来定位index，也就是需要对hashCode取模（hashCode % length），length是table的长度，但是我们知道，取模运算是较为复杂的计算，是非常耗时的计算，那有没有方法不通过取模计算而达到取模的效果呢，答案是肯定的，上文中提到，table的长度必然是2的n次方，这点很重要，HashMap通过设定table的长度为2的n次方，在取模的时候就可以通过下面的算法来进行：
```java
int index = hashCode & (length -1)
```
在length总是2的n次方的前提下，上面的算法等效于hashCode%length，但是现在通过使用&代替了%，而&的效率要远比%高
## HashMap插入元素的过程详解
上面分析了HashMap计算记录在table中的index的方法，下面来分析一下HashMap是如何将一个新的记录插入到HashMap中去的。也就是HashMap中非常重要的方法put，下面展示了它的实现细节：
```java
    public V put(K key, V value) {
        return putVal(hash(key), key, value, false, true);
    }

final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict) {
        Node<K,V>[] tab; Node<K,V> p; int n, i;
        if ((tab = table) == null || (n = tab.length) == 0)
            n = (tab = resize()).length;
        if ((p = tab[i = (n - 1) & hash]) == null)
            tab[i] = newNode(hash, key, value, null);
        else {
            Node<K,V> e; K k;
            if (p.hash == hash &&
                ((k = p.key) == key || (key != null && key.equals(k))))
                e = p;
            else if (p instanceof TreeNode)
                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
            else {
                for (int binCount = 0; ; ++binCount) {
                    if ((e = p.next) == null) {
                        p.next = newNode(hash, key, value, null);
                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                            treeifyBin(tab, hash);
                        break;
                    }
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    p = e;
                }
            }
            if (e != null) { // existing mapping for key
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                afterNodeAccess(e);
                return oldValue;
            }
        }
        ++modCount;
        if (++size > threshold)
            resize();
        afterNodeInsertion(evict);
        return null;
    }
```
	首先判断table是否为null或者长度为0，如果是，那么调用方法resize来初始化table，resize的细节将在下文中进行分析，这个方法用来对HashMap的table数组扩容，它将发生在初始化table以及table中的记录数量达到阈值之后。
	然后计算记录的hashCode，以及根据上文中提到的方法来计算记录在table中的index，如果发现index未知上为null，则调用newNode来创建一个新的链表节点，然后放在table的index位置上，此时表面没有哈希冲突。
	如果table的index位置不为空，那么说明造成了哈希冲突，这时候如果记录和index位置上的记录相等，则直接覆盖，否则继续判断
	如果index位置上的节点TreeNode，如果是，那么说明此时的index位置上是一颗红黑树，需要调用putTreeVal方法来将这新的记录插入到红黑树中去。否则走下面的逻辑。
	如果index位置上的节点类型不是TreeNode，那么说明此位置上的哈希冲突还没有达到阈值，还是一个链表结构，那么就根据插入链表插入新节点的算法来找到合适的位置插入，这里面需要注意的是，新插入的记录会覆盖老的记录，如果这个新的记录是首次插入，那么就会插入到该index位置上链表的最尾部，这里面还需要一次判断，如果插入了新的节点之后达到了阈值，那么就需要调用方法treeifyBin来讲链表转化为红黑树。
	在插入完成之后，哈希桶中记录的数量是否达到了哈希桶设置的阈值，如果达到了，那么就需要调用方法resize来扩容。

## HashMap扩容resize方法详解
上文分析了HashMap的put方法的细节，其中提到，当初始化table以及记录数量达到阈值之时会触发HashMap的扩容，而扩容是通过方法resize来进行的，下面来分析一下resize方法是如何工作的。
```java
    final Node<K,V>[] resize() {
        Node<K,V>[] oldTab = table;
        int oldCap = (oldTab == null) ? 0 : oldTab.length;
        int oldThr = threshold;
        int newCap, newThr = 0;
        if (oldCap > 0) {
            if (oldCap >= MAXIMUM_CAPACITY) {
                threshold = Integer.MAX_VALUE;
                return oldTab;
            }
            else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
                     oldCap >= DEFAULT_INITIAL_CAPACITY)
                newThr = oldThr << 1; // double threshold
        }
        else if (oldThr > 0) // initial capacity was placed in threshold
            newCap = oldThr;
        else {               // zero initial threshold signifies using defaults
            newCap = DEFAULT_INITIAL_CAPACITY;
            newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
        }
        if (newThr == 0) {
            float ft = (float)newCap * loadFactor;
            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
                      (int)ft : Integer.MAX_VALUE);
        }
        threshold = newThr;
        @SuppressWarnings({"rawtypes","unchecked"})
            Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
        table = newTab;
        if (oldTab != null) {
            for (int j = 0; j < oldCap; ++j) {
                Node<K,V> e;
                if ((e = oldTab[j]) != null) {
                    oldTab[j] = null;
                    if (e.next == null)
                        newTab[e.hash & (newCap - 1)] = e;
                    else if (e instanceof TreeNode)
                        ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
                    else { // preserve order
                        Node<K,V> loHead = null, loTail = null;
                        Node<K,V> hiHead = null, hiTail = null;
                        Node<K,V> next;
                        do {
                            next = e.next;
                            if ((e.hash & oldCap) == 0) {
                                if (loTail == null)
                                    loHead = e;
                                else
                                    loTail.next = e;
                                loTail = e;
                            }
                            else {
                                if (hiTail == null)
                                    hiHead = e;
                                else
                                    hiTail.next = e;
                                hiTail = e;
                            }
                        } while ((e = next) != null);
                        if (loTail != null) {
                            loTail.next = null;
                            newTab[j] = loHead;
                        }
                        if (hiTail != null) {
                            hiTail.next = null;
                            newTab[j + oldCap] = hiHead;
                        }
                    }
                }
            }
        }
        return newTab;
    }

```
上面展示了resize方法的细节，可以看到扩容的实现时较为复杂的，但是我们知道所谓扩容，就是新申请一个较大容量的数组table，然后将原来的table中的内容都重新计算哈希落到新的数组table中来，然后将老的table释放掉。这里面有两个关键点，一个是新哈希数组的申请以及老哈希数组的释放，另外一个是重新计算记录的哈希值以将其插入到新的table中去。首先第一个问题是，扩容会扩大到多少，通过观察上面的代码可以确定，每次扩容都会扩大table的容量为原来的两倍，当然有一个最大值，如果HashMap的容量已经达到最大值了，那么就不会再进行扩容操作了。第二个问题是HashMap是如何在扩容之后将记录从老的table迁移到新的table中来的。上文中已经提到，table的长度确保是2的n次方，那么有意思的是，每次扩容容量变为原来的两倍，那么一个记录在新table中的位置要么就和原来一样，要么就需要迁移到(oldCap + index)的位置上。
## HashMap获取记录操作详解
上面分析了插入记录的操作流程，下面来分析一下HashMap是如何支持获取记录的操作的。我们既然知道了HashMap的结果，就应该大概猜到HashMap需要在我们获取记录的时候要做什么，首先，因为可能会发生哈希冲突，所以我们需要获取的记录可能会存储在一个链表上，也可能存储在一棵红黑树上，这需要实际判断，所以，获取操作首先应该就算记录的hashCode，然后根据hashCode来计算在table中的index，然后判断该数组位置上是一条链表还是一棵红黑树，如果是链表，那么就遍历链表来找到我们需要的记录，否则如果是一棵红黑树，那么就通过遍历这棵红黑树找到我们需要的记录，当然，寻找记录可能会找不到，因为可能我们获取的记录根本就不存在，那么就要返回null暗示用户，当然，HashMap返回null不仅可以代表没有这个记录的信息之外，还可以代表该记录key对应着的value就是null，所以你不能通过HashMap是否返回null来判断HashMap中是否有相应的记录，如果你有类似的需求，你应该调用HashMap的方法：containsKey，这个方法将在下文中进行分析。
下面来看一下HashMap是如何做的，获取元素是通过调用HashMap的get方法来进行的，下面展示了get方法的代码：
```java
    public V get(Object key) {
        Node<K,V> e;
        return (e = getNode(hash(key), key)) == null ? null : e.value;
    }

    final Node<K,V> getNode(int hash, Object key) {
        Node<K,V>[] tab; Node<K,V> first, e; int n; K k;
        if ((tab = table) != null && (n = tab.length) > 0 &&
            (first = tab[(n - 1) & hash]) != null) {
            if (first.hash == hash && // always check first node
                ((k = first.key) == key || (key != null && key.equals(k))))
                return first;
            if ((e = first.next) != null) {
                if (first instanceof TreeNode)
                    return ((TreeNode<K,V>)first).getTreeNode(hash, key);
                do {
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        return e;
                } while ((e = e.next) != null);
            }
        }
        return null;
    }
```
首先会获得当前table的一个快照，然后根据需要查找的记录的key的hashCode来定位到table中的index，如果该位置为null，说明没有没有记录落到该位置上，也就不存在我们查找的记录，直接返回null。如果该位置不为null，说明至少有一个记录落到该位置上来，那么就判断该位置的第一个记录是否使我们查找的记录，如果是则直接返回，否则，根据该index上是一条链表还是一棵红黑树来分别查找我们需要的记录，找到则返回记录，否则返回null。下面来看一下如何判断HashMap中是否有一个记录的方法：
```java
    public boolean containsKey(Object key) {
        return getNode(hash(key), key) != null;
    }
```
这个方法调用了getNode来从table中获得一个Node，返回null，说明不存在该记录，否则存在，containsKey方法和get方法都是通过调用getNode方法来进行的，但是他们的区别在于get方法在判断得到的Node不为null的情况下任然可能返回null，因为Node的value可能为null，所以应该在合适的时候调用合适的方法。
## HashMap删除记录详解
现在来看一下HashMap是如何实现删除一个记录的。下面首先展示了相关的代码：
```java
    public V remove(Object key) {
        Node<K,V> e;
        return (e = removeNode(hash(key), key, null, false, true)) == null ?
            null : e.value;
    }

    final Node<K,V> removeNode(int hash, Object key, Object value,
                               boolean matchValue, boolean movable) {
        Node<K,V>[] tab; Node<K,V> p; int n, index;
        if ((tab = table) != null && (n = tab.length) > 0 &&
            (p = tab[index = (n - 1) & hash]) != null) {
            Node<K,V> node = null, e; K k; V v;
            if (p.hash == hash &&
                ((k = p.key) == key || (key != null && key.equals(k))))
                node = p;
            else if ((e = p.next) != null) {
                if (p instanceof TreeNode)
                    node = ((TreeNode<K,V>)p).getTreeNode(hash, key);
                else {
                    do {
                        if (e.hash == hash &&
                            ((k = e.key) == key ||
                             (key != null && key.equals(k)))) {
                            node = e;
                            break;
                        }
                        p = e;
                    } while ((e = e.next) != null);
                }
            }
            if (node != null && (!matchValue || (v = node.value) == value ||
                                 (value != null && value.equals(v)))) {
                if (node instanceof TreeNode)
                    ((TreeNode<K,V>)node).removeTreeNode(this, tab, movable);
                else if (node == p)
                    tab[index] = node.next;
                else
                    p.next = node.next;
                ++modCount;
                --size;
                afterNodeRemoval(node);
                return node;
            }
        }
        return null;
    }
```
首先，通过记录的hashCode来找到他在table中的index，因为最后需要返回被删除节点的值，所以需要记录被删除的节点。当然记录被删除的节点也是有意义的，比如对于table中的index位置上为一条链表的情况来说，我们只需要记住需要删除的Node，然后真正删除的时候就可以只需要操作该node就可以了，当然对于链表的相关操作详解将在另外的篇章中进行。以及红黑树等高级数据结构的分析总结也会在新的篇章中介绍，目前只需要知道HashMap通过在合适的时候使用不同的数据结构来达到高效的目的就可以了。


