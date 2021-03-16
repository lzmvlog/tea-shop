package top.lzmvlog.common.result;

import lombok.Data;
import lombok.experimental.Accessors;
import top.lzmvlog.common.constant.SystemConstant;

/**
 * 响应数据结构体
 *
 * @author zhang1591313226@163.com
 * @since 2021-02-23 16:14
 */
@Data
@Accessors(chain = true)
public class R<T> {

    /**
     * 响应码
     */
    private Integer code;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 响应信息
     */
    private String msg;

    /**
     * 成功响应数据
     *
     * @param data 需要返回的数据
     * @return 数据
     */
    public static <T> R<T> ok(T data) {
        return new R<T>(SystemConstant.SUCCESS, data, SystemConstant.SUCCESS_MSG);
    }

    /**
     * 成功响应数据
     */
    public static <T> R<T> ok() {
        return new R<T>(SystemConstant.SUCCESS, SystemConstant.SUCCESS_MSG);
    }

    /**
     * 失败响应信息
     *
     * @param msg 需要返回的信息
     * @return 信息
     */
    public static <T> R<T> fail(String msg) {
        return new R<T>(SystemConstant.FAIL, msg);
    }

    /**
     * 判断需要响应的数据
     *
     * @param bool 需要判断的数据
     * @return 成功 || 失败
     */
    public static R<Boolean> bool(Boolean bool) {
        return bool ? new R<Boolean>(SystemConstant.SUCCESS, SystemConstant.SUCCESS_MSG) :
                new R<Boolean>(SystemConstant.FAIL, SystemConstant.FAIL_MSG_ONE);
    }

    public R(Integer code) {
        this.code = code;
    }

    public R(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public R(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public R(Integer code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }
}
