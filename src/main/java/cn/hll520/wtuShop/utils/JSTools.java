package cn.hll520.wtuShop.utils;

/**
 * @author lpc
 * @create 2020-07-29-15:27
 */
public class JSTools {


    /**
     * 弹窗并返回上个页面
     * @param msg 弹窗内容
     * @return JS代码
     */
    public static String alterBack(String msg){
        return alter(msg)+historyGo(-1);
    }

    /**
     * 浏览器向前或后 N个页面
     * @param N 移动数，负数为后退，0为刷新当前，正数为前进
     * @return  JS代码
     */
    public static String historyGo(int N){
        return "<script>history.go("+N+");</script>";
    }

    /**
     * 弹窗
     * @param msg 弹窗内容
     * @return  JS代码
     */
    public static String alter(String msg){
        return "<script>alert('"+msg+"');</script>";
    }
}
