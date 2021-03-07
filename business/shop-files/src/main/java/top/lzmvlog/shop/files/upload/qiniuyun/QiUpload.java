package top.lzmvlog.shop.files.upload.qiniuyun;

import cn.hutool.core.util.IdUtil;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.lzmvlog.common.utils.StringUtil;
import top.lzmvlog.shop.files.service.ResourceService;
import top.lzmvlog.shop.files.upload.UploadResource;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * @author ShaoJie
 * @Date 2020年07月12 20:38
 * @Description: 七牛云文件上传
 */
@Service
@Slf4j
public class QiUpload implements UploadResource {

    @Value("${qiniuyun.access.key}")
    private String accessKey;

    @Value("${qiniuyun.secret.key}")
    private String secretKey;

    @Value("${qiniuyun.bucket.name}")
    private String bucket;

    @Value("${qiniuyun.domainName}")
    private String domainName;

    @Autowired
    private ResourceService resourceService;

    /**
     * 上传文件
     *
     * @param multipartFile 文件流
     * @return
     */
    @Override
    public String upLoadFile(MultipartFile multipartFile) {
        //构造一个带指定 Region 对象的配置类
        Configuration configuration = new Configuration(Region.region0());
        // 构建上传管理器
        UploadManager uploadManager = new UploadManager(configuration);
        Response response = null;
        String url = null;
        try {
            if (multipartFile == null) {
                log.info("上传文件为空");
                throw new RuntimeException("上传文件为空");
            }

            ByteArrayInputStream byteInputStream = new ByteArrayInputStream(multipartFile.getBytes());
            Auth auth = Auth.create(accessKey, secretKey);
            // 上传的 token
            String upToken = auth.uploadToken(bucket);

            try {
                response = uploadManager.put(byteInputStream, IdUtil.fastSimpleUUID(), upToken, null, multipartFile.getContentType());
                //解析上传成功的结果
                DefaultPutRet defaultPutRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                log.info("上传文件名 {} - 上传的hash {}", defaultPutRet.key, defaultPutRet.hash);
                String suffix = StringUtil.getSuffix(multipartFile.getOriginalFilename());
                // 保存文件
                url = String.format("%s/%s", domainName, defaultPutRet.key);
//                resourceService.save(new ResourceVo().setUrl(url).setFileName(multipartFile.getOriginalFilename()).setType(suffix));
            } catch (QiniuException ex) {
                log.error("qiniuyun - 上传出错 - {}", ex);
                throw new RuntimeException("上传出错");
            }
        } catch (IOException e) {
            log.error("qiniuyun - 文件 multipartFile 转换 byte[] 失败 - {}", e);
            throw new RuntimeException("文件 multipartFile 转换 byte[] 失败");
        }
        return url;
    }

}
