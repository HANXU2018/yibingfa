package concurrent;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author hanxu (hanxu05@rd.netease.com)
 * @date 2021/8/5 18:57
 */
public class TestConcurrentHashMap {
    public static void main(String[] args) {
        ConcurrentHashMap<String, String> chm = new ConcurrentHashMap<>();
        chm.put("key1","value1");
        chm.put("key2","value2");
        chm.put("key3","value3");
        chm.put("key3","value33");
        chm.putIfAbsent("key4","value4");
        System.out.println(chm);
    }
}
