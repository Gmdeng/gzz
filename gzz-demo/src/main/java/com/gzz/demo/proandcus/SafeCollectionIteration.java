package com.gzz.demo.proandcus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 *
 * 在集合API中，最初设计的Vector和Hashtable是多线程安全的。例如：对于Vector来说，用来添加和删除元素的方法是同步的。
 * 如果只有一个线程与Vector的实例交互，那么，要求获取和释放对象锁便是一种浪费，另外在不必要的时候如果滥用同步化，也有可能会带来死锁。
 * 因此，对于更改集合内容的方法，没有一个是同步化的。集合本质上是非多线程安全的，当多个线程与集合交互时，为了使它多线程安全，必须采取额外的措施。
 *
 *      在Collections类 中有多个静态方法，它们可以获取通过同步方法封装非同步集合而得到的集合：
 *
 *      public static Collection synchronizedCollention(Collection c)
 *
 *      public static List synchronizedList(list l)
 *
 *      public static Map synchronizedMap(Map m)
 *
 *      public static Set synchronizedSet(Set s)
 *
 *      public static SortedMap synchronizedSortedMap(SortedMap sm)
 *
 *      public static SortedSet synchronizedSortedSet(SortedSet ss)
 *
 *      这些方法基本上返回具有同步集合方法版本的新类。比如，为了创建多线程安全且由ArrayList支持的List，可以使用如下代码：
 *
 * List list = Collection.synchronizedList(new ArrayList());
 *
 *      注意，ArrayList实例马上封装起来，不存在对未同步化ArrayList的直接引用（即直接封装匿名实例）。这是一种最安全的途径。
 *      如果另一个线程要直接引用ArrayList实例，它可以执行非同步修改。
 */
public class SafeCollectionIteration extends Object {
    public static void main(String[] args) {
        //为了安全起见，仅使用同步列表的一个引用，这样可以确保控制了所有访问
        //集合必须同步化，这里是一个List
        List wordList = Collections.synchronizedList(new ArrayList());

        //wordList中的add方法是同步方法，会获取wordList实例的对象锁
        wordList.add("Iterators");
        wordList.add("require");
        wordList.add("special");
        wordList.add("handling");

        //获取wordList实例的对象锁，
        //迭代时，阻塞其他线程调用add或remove等方法修改元素
        synchronized ( wordList ) {
            Iterator iter = wordList.iterator();
            while ( iter.hasNext() ) {
                String s = (String) iter.next();
                System.out.println("found string: " + s + ", length=" + s.length());
            }
        }
    }
}