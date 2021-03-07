package top.lzmvlog.shop.files.upload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.lzmvlog.shop.files.upload.minlo.MinIoUpLoad;
import top.lzmvlog.shop.files.upload.oss.OSSUpload;
import top.lzmvlog.shop.files.upload.qiniuyun.QiUpload;

import javax.validation.constraints.NotNull;

/**
 * @author ShaoJie
 * @Date 2020年07月17 09:50
 * @Description: 上传工厂
 * <p>
 * 用于配置默认的文件上传服务
 */
@Component
public class UploadFactory {

    /**
     * MINLO
     */
    @Autowired
    private MinIoUpLoad minIoUpLoad;

    /**
     * OSS
     */
    @Autowired
    private OSSUpload ossUpload;

    /**
     * 七牛云
     */
    @Autowired
    private QiUpload qiUpload;

    /**
     * 获取文件上传方式，实现不同上传的功能
     *
     * @param methodName 上传方式
     * @return UploadResource 上传请求方式
     */
    public UploadResource getUploadMethod(@NotNull String methodName) {
        // minlo 文件上传
        if (methodName.equals("minlo")) {
            return minIoUpLoad;
        }
        // oss 文件上传
        if (methodName.equals("oss")) {
            return ossUpload;
        }
        // 七牛云文件上传
        if (methodName.equals("qiniuyun")) {
            return qiUpload;
        }
        return null;
    }

}
