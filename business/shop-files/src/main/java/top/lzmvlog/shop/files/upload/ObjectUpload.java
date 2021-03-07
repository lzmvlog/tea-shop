package top.lzmvlog.shop.files.upload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import top.lzmvlog.shop.files.service.ResourceService;

/**
 * @author ShaoJie
 * @Date 2020年07月12 20:33
 * @Description: 抽象文件上传接口
 */
@Component
public class ObjectUpload {

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private UploadFactory uploadFactory;

    /**
     * 配置上传方式 ：
     * 1、qiniuyun
     * 2、oss
     * 3、minio
     */
    @Value("${methodName}")
    private String methodName;

    /**
     * 上传文件
     *
     * @param multipartFile 文件流
     * @return
     */
    public Object upLoadFile(MultipartFile multipartFile) {
        return uploadFactory.getUploadMethod(methodName).upLoadFile(multipartFile);
    }

}
