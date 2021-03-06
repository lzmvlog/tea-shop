package top.lzmvlog.shop.files.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * resource
 *
 * @author zhang1591313226@163.com
 * @since 2021-03-06
 */
@Data
@TableName("resource")
public class Resource extends Model<Resource> {

    private static final long serialVersionUID = 1L;

    /**
     * 文件id
     */
    @TableId("resource_id")
    private String resourceId;

    /**
     * 文件名称
     */
    @TableField("file_name")
    private String fileName;

    /**
     * 文件存储路径
     */
    @TableField("url")
    private String url;

    /**
     * 文件类型
     */
    @TableField("type")
    private String type;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;
}
