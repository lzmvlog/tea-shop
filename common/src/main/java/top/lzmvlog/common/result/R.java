package top.lzmvlog.common.result;

import lombok.Data;

/**
 * 数据返回结构体
 *
 * @author zhang1591313226@163.com
 * @since 2021-02-23 16:14
 */
@Data
public class R {

    /**
     * 响应码
     */
    private Integer code = 200;

    /**
     * 响应数据
     */
    private Object data;

    private R() {
        this.code = 200;
    }

    /**
     * 返回状态和数据
     *
     * @param code 响应信息
     * @param data 响应数据
     */
    public R(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }

    public static R ok() {
        return new R();
    }

    public static R ok(Object data) {
        return new R(200, data);
    }
}
