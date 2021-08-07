/**
 * @author hanxu (hanxu05@rd.netease.com)
 * @date 2021/8/6 19:19
 */

/**
 * @author: ouyangchenhui
 * @create: 2021/7/20 14:23
 * @description: 用来做article, column的列表排序
 */
class SortUtils {


    public static int sortTitleAtoZ(String title1, String title2){
        //优先级：数字>字母>汉字>特殊符号
        char[] c1 = title1.toCharArray();
        char[] c2 = title2.toCharArray();
        int i = 0;
        while (i<c1.length && i<c2.length){
            if(c1[i] == c2[i]){
                //字符相同 向后遍历
                i++;
            }else {
                int type1 = getType(c1[i]);
                int type2 = getType(c2[i]);
                //类型不同直接按优先级返回
                if (type1 != type2) {
                    return type1 - type2;
                }else if(type1 == 4){
                    //数字排序 从小到大 ASCII：0-47，9-58
                    return c2[i]-c1[i];
                }else if(type1 == 3){
                    //字母排序 按首字母A-Z的顺序排列，同一字母，小写排在大写之前 ASCII : A-65,Z-90,a-97,z-122
                    char lowc1 = Character.toUpperCase(c1[i]);
                    char lowc2 = Character.toUpperCase(c2[i]);
                    if(lowc1!=lowc2){
                        // A-Z的顺序排列
                        return lowc2-lowc1;
                    }else{
                        // 小写排在大写之前
                        return c1[i]-c2[i];
                    }
                }else  if(type1 == 2){
                    //汉字排序 保留原逻辑
                    return title1.compareTo(title2);
                }else{
                    //特殊符号排序
                    return c1[i]-c2[i];
                }
            }
        }
        return c2.length-c1.length;// 短的那个靠前
    }

    public static int sortTitleZtoA(String title1, String title2){
        //优先级：数字>字母>汉字>特殊符号
        char[] c1 = title1.toCharArray();
        char[] c2 = title2.toCharArray();
        int i = 0;
        while (i<c1.length && i<c2.length){
            if(c1[i] == c2[i]){
                //字符相同 向后遍历
                i++;
            }else {
                int type1 = getType(c1[i]);
                int type2 = getType(c2[i]);
                //类型不同直接按优先级返回
                if (type1 != type2) {
                    return type1 - type2;
                }else if(type1 == 4){
                    //数字排序 从小到大 ASCII：0-47，9-58
                    return c2[i]-c1[i];
                }else if(type1 == 3){
                    //字母排序 按首字母A-Z的顺序排列，同一字母，小写排在大写之前 ASCII : A-65,Z-90,a-97,z-122
                    char lowc1 = Character.toUpperCase(c1[i]);
                    char lowc2 = Character.toUpperCase(c2[i]);
                    if(lowc1!=lowc2){
                        // Z-A的顺序排列
                        return lowc1-lowc2;
                    }else{
                        // 小写排在大写之前
                        return c1[i]-c2[i];
                    }
                }else  if(type1 == 2){
                    //汉字排序 保留原逻辑

                    return title2.compareTo(title1);
                }else{
                    //特殊符号排序
                    return c1[i]-c2[i];
                }
            }
        }
        return c2.length-c1.length;// 短的那个靠前
    }

    public static int getType(char c) {
        //优先级：4数字>3字母>2汉字>1特殊符号
        // 汉字优先 字母识别 不然汉字会识别成字幕
        // 日文韩文 当作特殊符号识别
        if(Character.isDigit(c)) {
            return 4;
        }
        if(isHanZi(c)) {
            return 2;
        }
        if(isLetter(c)) {
            return 3;
        }
        //Character.isDigit(c) 存在误判 如 '.'等会返回true
        return 1;
    }

    public static boolean isLetter(char c){
        if(c >= 'a' &&  c<= 'z'){
            return true;
        }else if(c >= 'A' && c<= 'Z'){
            return true;
        }
        return false;
    }


    private static final String REGEX = "[\u4e00-\u9fa5]";

    /**
     * 判断单个字符是否为汉字
     *
     * @param c 字符
     * @return 是否为汉字
     */
    public static boolean isHanZi(char c) {
        return String.valueOf(c).matches(REGEX);
    }
}
