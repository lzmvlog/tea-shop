package top.lzmvlog.shop.files.upload.oss;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
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
 * @Date 2020年07月16 11:05
 * @Description: oss 文件上传
 */
@Service
@Slf4j
public class OSSUpload implements UploadResource {

    @Value("${oss.access.key}")
    private String accessKey;

    @Value("${oss.secret.key}")
    private String secretKey;

    @Value("${oss.bucket.name}")
    private String bucket;

    @Value("${oss.endpoint}")
    private String endpoint;

    @Value("${oss.domainName}")
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
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKey, secretKey);
        String resourceId = IdUtil.fastSimpleUUID();
        String url = null;
        try {
            String suffix = StringUtil.getSuffix(multipartFile.getOriginalFilename());
            String file = String.format("%s/%s.%s", DateUtil.today(), resourceId, suffix);
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, file, new ByteArrayInputStream(multipartFile.getBytes()));
            // 上传字符串。
            ossClient.putObject(putObjectRequest);
            // 保存文件
            url = String.format("%s/%s", domainName, file);

//            resourceService.save(new ResourceVo().setUrl(url).setFileName(multipartFile.getOriginalFilename()).setType(suffix));
            // 关闭OSSClient。
            ossClient.shutdown();
        } catch (IOException e) {
            log.error("oss 上传失败 e:{}", e);
            throw new RuntimeException("上传失败");
        }
        return null;
    }

}
