package github.lyf.hard;

import java.util.HashMap;
import java.util.LinkedHashSet;

/**
 * @author lyf
 */
public class LFUCache {
    /**
     *设计并实现最不经常使用（LFU）缓存的数据结构。它应该支持以下操作：get 和 put。
     *
     * get(key) - 如果键存在于缓存中，则获取键的值（总是正数），否则返回 -1。
     * put(key, value) - 如果键不存在，请设置或插入值。当缓存达到其容量时，它应该在插入新项目之前，使最不经常使用的项目无效。在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，最近最少使用的键将被去除。
     *
     * 进阶：
     * 你是否可以在 O(1) 时间复杂度内执行两项操作？
     */
    /**
     * 容量大小
     */
    public int capacity;
    /**
     * 存储put进去的key和value
     */
    public HashMap<Integer, Integer> map = new HashMap<>();
    /**
     * 存储每个key的频率值
     */
    public HashMap<Integer, Integer> frequent = new HashMap<>();
    /**
     * 存储每个频率的相应的key的值的集合，这里用HashSet是因为其是由HashMap底层实现的，可以O(1)时间复杂度查找元素
     *     而且linked是有序的，同一频率值越往后越最近访问
     */
    public HashMap<Integer, LinkedHashSet<Integer>> list = new HashMap<>();
    /**
     标记当前频率中的最小值
     */
    int min = -1;

    public LFUCache(int capacity) {
        this.capacity = capacity;
    }


    public int get(int key) {
        if(!map.containsKey(key)){
            return -1;
        }else{
            //获取元素的value值
            int value = map.get(key);
            int count = frequent.get(key);
            frequent.put(key, count + 1);
            //先移除当前key
            list.get(count).remove(key);

            //更改min的值
            if(count == min && list.get(count).size() == 0)
                min++;

            LinkedHashSet<Integer> set = list.containsKey(count + 1) ? list.get(count + 1) : new LinkedHashSet<Integer>();
            set.add(key);
            list.put(count + 1, set);

            return value;
        }

    }

    public void put(int key, int value) {
        if(capacity <= 0){
            return;
        }
        //这一块跟get的逻辑一样
        if(map.containsKey(key)){
            map.put(key, value);
            int count = frequent.get(key);
            frequent.put(key, count + 1);
            //先移除当前key
            list.get(count).remove(key);

            //更改min的值
            if (count == min && list.get(count).size() == 0)
                min++;

            LinkedHashSet<Integer> set = list.containsKey(count + 1) ? list.get(count + 1) : new LinkedHashSet<Integer>();
            set.add(key);
            list.put(count + 1, set);
        }else{
            if(map.size() >= capacity){
                Integer removeKey = list.get(min).iterator().next();
                list.get(min).remove(removeKey);
                map.remove(removeKey);
                frequent.remove(removeKey);
            }
            map.put(key, value);
            frequent.put(key, 1);
            LinkedHashSet<Integer> set = list.containsKey(1) ? list.get(1) : new LinkedHashSet<Integer>();
            set.add(key);
            list.put(1, set);

            min = 1;
        }
    }
}
