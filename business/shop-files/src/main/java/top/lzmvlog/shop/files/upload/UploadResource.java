package top.lzmvlog.shop.files.upload;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author ShaoJie
 * @Date 2020年07月17 09:44
 * @Description: 创建统一上传接口
 * <p>
 * 因各种文件存储服务器的需要，可能会增加不同的认证信息去访问文件服务器的内容
 * 所以采用工厂模式构建 当前的文件上传服务
 */
public interface UploadResource {

    /**
     * 上传文件
     *
     * @param multipartFile 文件
     * @return object 任意值
     */
    Object upLoadFile(MultipartFile multipartFile);
}
