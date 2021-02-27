package top.lzmvlog.common.utils;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import lombok.extern.slf4j.Slf4j;

/**
 * 通用生成代码
 *
 * @author zhang1591313226@163.com
 * @since 2021-02-24 10:25
 */
@Slf4j
public class CodeGenerator {

    public static void main(String[] args) {
        //1. 全局配置
        GlobalConfig config = new GlobalConfig();
        // 是否支持AR模式
        config.setActiveRecord(true)
                // 作者
                .setAuthor("zhang1591313226@163.com")
                // 文件覆盖
                .setFileOverride(true)
                // 设置日期格式
                .setDateType(DateType.ONLY_DATE)
                // 设置生成的service接口的名字的首字母是否为I，默认Service是以I开头的
                .setServiceName("%sService")
                //生成基本的resultMap
                .setBaseResultMap(true)
                //生成基本的SQL片段
                .setBaseColumnList(true)
                // 是否打开
                .setOpen(false);

        //2. 数据源配置
        DataSourceConfig dsConfig = new DataSourceConfig();
        // 设置数据库类型
        dsConfig.setDbType(DbType.MYSQL)
                .setDriverName("com.mysql.cj.jdbc.Driver")
                .setUrl("jdbc:mysql://127.0.0.1:3306/tea-shop?useUnicode=true&characterEncoding=utf8&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=GMT%2B8")
                .setUsername("root")
                .setPassword("Root5683@");

        //3. 策略配置globalConfiguration中
        StrategyConfig stConfig = new StrategyConfig();
        //全局大写命名
        stConfig.setCapitalMode(true)
                // 数据库表映射到实体的命名策略
                .setNaming(NamingStrategy.underline_to_camel)
                .setTablePrefix("")
                .setRestControllerStyle(true)
                .setEntityTableFieldAnnotationEnable(true)
                .setEntityLombokModel(true);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setController("/templates/controller");
        templateConfig.setEntity("/templates/model");
        templateConfig.setMapper("/templates/mapper");
        templateConfig.setService("/templates/service");
        templateConfig.setServiceImpl("/templates/serviceimpl");

        // 每次生成之前都需要确定是哪一个服务下面的信息!!!!!-----------------------------------------------------很重要
        String service = "customer";
        //4. 包名策略配置
        PackageConfig pkConfig = new PackageConfig();
        pkConfig.setParent("top.lzmvlog.shop." + service)
                .setMapper("mapper")
                .setService("service")
                .setController("controller")
                .setEntity("model")
                .setXml("resources.mapper");

        //5. 整合配置
        AutoGenerator generator = new AutoGenerator();
        generator.setGlobalConfig(config)
                .setDataSource(dsConfig)
                .setStrategy(stConfig)
                .setPackageInfo(pkConfig)
                .setTemplate(templateConfig);

        //6. 执行
        generator.execute();
        log.info("生成完成");
    }

}
