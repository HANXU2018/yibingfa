package concurrent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author hanxu (hanxu05@rd.netease.com)
 * @date 2021/8/5 16:49
 */
public class TestCopyOnWriteArrayList {
    public static void main(String[] args) {
        //ArrayList<String> names = new ArrayList<String>();
        CopyOnWriteArrayList<String> names = new CopyOnWriteArrayList<String>();

        names.add("zs");
        names.add("ls");
        names.add("ww");
        Iterator<String> iter = names.iterator();
        while (iter.hasNext()){
            System.out.println(iter.next());
            names.add("x");
        }
    }
}
