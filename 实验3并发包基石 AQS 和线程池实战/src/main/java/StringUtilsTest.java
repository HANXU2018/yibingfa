/**
 * @author hanxu (hanxu05@rd.netease.com)
 * @date 2021/8/6 18:51
 */
public class StringUtilsTest {

    public static void main(String[] args) {
        System.out.println(Character.isLetter('に'));

        System.out.println(SortUtils.isLetter('A'));
        System.out.println(SortUtils.isLetter('a'));
        System.out.println(SortUtils.isLetter('B'));
        System.out.println(SortUtils.isLetter('0'));
        System.out.println(SortUtils.isLetter('哥'));
        System.out.println(SortUtils.isLetter('に'));
        // 日语符号竟然 当作了 字母 真是害人不浅


    }
}
