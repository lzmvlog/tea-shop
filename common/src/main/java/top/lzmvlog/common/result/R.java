package top.lzmvlog.common.result;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 数据返回结构体
 *
 * @author zhang1591313226@163.com
 * @since 2021-02-23 16:14
 */
@Data
@Accessors(chain = true)
public class R {

    private static final Integer SUCCESS = 200;

    /**
     * 响应码
     */
    private Integer code;

    /**
     * 响应数据
     */
    private Object data;

    /**
     * 响应信息
     */
    private String msg;

    /**
     * 成功响应
     *
     * @return
     */
    public static R ok() {
        return new R().setCode(SUCCESS);
    }

    /**
     * 响应数据
     *
     * @param data 数据信息
     * @return
     */
    public static R ok(Object data) {
        return new R().setCode(SUCCESS).setData(data);
    }

    /**
     * bool信息响应
     *
     * @param bool
     * @return
     */
    public static R bool(boolean bool) {
        return bool ? R.ok() : R.fail("操作失败, 请重试!");
    }

    /**
     * 响应失败信息
     *
     * @param message
     * @return
     */
    private static R fail(String message) {
        return new R().setMsg(message);
    }
}
