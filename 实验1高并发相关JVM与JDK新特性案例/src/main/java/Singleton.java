/**
 * @author hanxu (hanxu05@rd.netease.com)
 * @date 2021/8/5 10:37
 */
public class Singleton {
    private volatile static Singleton instance = null;
    private Singleton(){};
    public Singleton getInstance(){
        if(instance == null){
            synchronized(Singleton.class){
                if(instance == null){
                    instance = new Singleton();
                }
            }
        }
        return  instance;
    }
}
