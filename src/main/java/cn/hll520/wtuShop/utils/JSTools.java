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
     * 弹窗并刷新指定页面
     * @param msg 弹窗信息
     * @param url 指定页面，不带http:// 为相对当前页
     * @return JS代码
     */
    public static String alterReplace(String msg,String url){
        return alter(msg)+replace(url);
    }




    /**
     * 弹窗并跳转到指定页面
     * @param msg   弹窗信息
     * @param url   指定页面地址 不加Http:// 均认为是相对于当前页面的地址
     * @return  JS代码
     */
    public static String alterUrl(String msg,String url){return alter(msg)+location(url);}

    /**
     * 浏览器向前或后 N个页面
     * @param N 移动数，负数为后退，0为刷新当前，正数为前进
     * @return  JS代码
     */
    public static String historyGo(int N){
        return "<script>history.go('"+N+"');</script>";
    }

    /**
     * 弹窗
     * @param msg 弹窗内容
     * @return  JS代码
     */
    public static String alter(String msg){
        return "<script>alert('"+msg+"');</script>";
    }

    /**
     * 跳转页面
     * @param url 目标地址
     * @return JS代码
     */
    public static String location(String url){return "<script>window.location.href='"+url+"'</script>";}

    /**
     * 跳转并刷新指定页面
     * @param url 指定页面
     * @return JS代码
     */
    public static String replace(String url){ return "<script>location.replace('"+url+"')</script>"; }


}
