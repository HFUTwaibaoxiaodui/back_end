package edu.hfut.back_end.Utils;

import edu.hfut.back_end.Constant.CommonConstant;
import lombok.Data;

import java.io.Serializable;


@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 成功标志
     */
    private boolean success = true;

    /**
     * 返回处理消息
     */
    private String message = "操作成功！";

    /**
     * 返回代码
     */
    private Integer code = 0;

    /**
     * 返回数据对象 data
     */
    private T result;

    /**
     * 时间戳
     */
    private long timestamp = System.currentTimeMillis();

    public Result() {

    }

    public edu.hfut.back_end.Utils.Result<T> success(String message) {
        this.message = message;
        this.code = CommonConstant.SC_OK_200;
        this.success = true;
        return this;
    }


    public static edu.hfut.back_end.Utils.Result<Object> ok() {
        edu.hfut.back_end.Utils.Result<Object> r = new edu.hfut.back_end.Utils.Result<Object>();
        r.setSuccess(true);
        r.setCode(CommonConstant.SC_OK_200);
        r.setMessage("成功");
        return r;
    }

    public static edu.hfut.back_end.Utils.Result<Object> ok(String msg) {
        edu.hfut.back_end.Utils.Result<Object> r = new edu.hfut.back_end.Utils.Result<Object>();
        r.setSuccess(true);
        r.setCode(CommonConstant.SC_OK_200);
        r.setMessage(msg);
        return r;
    }

    public static edu.hfut.back_end.Utils.Result<Object> ok(Object data) {
        edu.hfut.back_end.Utils.Result<Object> r = new edu.hfut.back_end.Utils.Result<Object>();
        r.setSuccess(true);
        r.setCode(CommonConstant.SC_OK_200);
        r.setResult(data);
        return r;
    }

    public static edu.hfut.back_end.Utils.Result<Object> error(String msg) {
        return error(CommonConstant.SC_INTERNAL_SERVER_ERROR_500, msg);
    }

    public static edu.hfut.back_end.Utils.Result<Object> error(int code, String msg) {
        edu.hfut.back_end.Utils.Result<Object> r = new edu.hfut.back_end.Utils.Result<Object>();
        r.setCode(code);
        r.setMessage(msg);
        r.setSuccess(false);
        return r;
    }

    public edu.hfut.back_end.Utils.Result<T> error500(String message) {
        this.message = message;
        this.code = CommonConstant.SC_INTERNAL_SERVER_ERROR_500;
        this.success = false;
        return this;
    }

    /**
     * 无权限访问返回结果
     */
    public static edu.hfut.back_end.Utils.Result<Object> noauth(String msg) {
        return error(CommonConstant.SC_JEECG_NO_AUTHZ, msg);
    }
}
