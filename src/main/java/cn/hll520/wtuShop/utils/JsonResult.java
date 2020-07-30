package cn.hll520.wtuShop.utils;

/**
 * @author lpc
 * @create 2020-07-30-9:48
 */
public class JsonResult {

    /** 请求成功 statusCode=200 */
    public final static int STATUS_SUCCESS_OK = 200;

    /** 请求成功并重定向 statusCode=302 并转发redirect：url */

    public final static int STATUS_SUCCESS_REDIRECT = 302;

    /** 未定义的请求 statusCode=404 */
    public final static int STATUS_NOTFOUND = 404;
    
    /** 请求错误 statusCode=500 */
    public final static int STATUS_ERROR = 500;


    /** 响应状态码 */
    private int statusCode = STATUS_SUCCESS_OK;

    /** 响应提示信息 */
    private String msg;

    /** 响应的数据 */
    private Object data;


    public int getStatusCode() {
        return statusCode;
    }

    /**
     * 设置状态码
     *
     * @param statusCode 状态码200 302  404 500
     */
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMsg() {
        return msg;
    }

    /**
     * 设置提示信息
     * @param msg 提示信息
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    /**
     * 设置数据
     * @param data 数据，如果是嵌套的MAP或其他类别，真实数据为 其中的 list 名称
     */
    public void setData(Object data) {
        this.data = data;
    }
}
