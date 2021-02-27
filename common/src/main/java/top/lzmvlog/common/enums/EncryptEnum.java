package top.lzmvlog.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 加密枚举类
 *
 * @author chenghao
 * @since 2021-02-27
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum EncryptEnum {

    /**
     * 手机号码
     */
    MOBILE_PHONE(0, "手机号码"),

    /**
     * 电子邮箱
     */
    EMAIL(1, "电子邮箱"),

    /**
     * 身份证号码
     */
    ID_NUMBER(2, "身份证号码");


    /**
     * 加密序号
     */
    private Integer encryptNo;

    /**
     * 加密名称
     */
    private String encryptName;
}
