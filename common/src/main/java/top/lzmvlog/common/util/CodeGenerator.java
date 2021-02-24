package top.lzmvlog.common.util;

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
        // 代码生成器
//        AutoGenerator mpg = new AutoGenerator();
//
//        // 全局配置
//        GlobalConfig gc = new GlobalConfig();
//        String projectPath = System.getProperty("user.dir") + "/";
//        // 子项目名称
////        String projectName = "bussiness/shop-order/test";
//        String projectName = "admin";
//        // pojo项目名称
//        String pojoName = "tea-shop";
//
//        gc.setOutputDir(projectPath + "/src/main/java");
//        // 覆盖已有文件
//        gc.setFileOverride(true);
//        gc.setAuthor("zhang1591313226@163.com");
//        // 是否开开输出文件
//        gc.setOpen(false);
//        mpg.setGlobalConfig(gc);
//
//        // 数据源配置
//        DataSourceConfig dataSourceConfig = new DataSourceConfig();
//        dataSourceConfig.setUrl("jdbc:mysql://127.0.0.1:3306/tea-shop?useUnicode=true&characterEncoding=utf8&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=GMT%2B8");
//        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
//        dataSourceConfig.setUsername("root");
//        dataSourceConfig.setPassword("root");
//        mpg.setDataSource(dataSourceConfig);
//
//        // 包配置
//        PackageConfig packageConfig = new PackageConfig();
//        packageConfig.setParent("top.lzmvlog.shop");
//        packageConfig.setEntity("model");
//        packageConfig.setService("service");
//        packageConfig.setMapper("mapper");
//        packageConfig.setServiceImpl("service.impl");
//        packageConfig.setController("controller");
//
//        // 定义生成文件的位置
//        String service = "order";
//        Map<String, String> pathInfo = new HashMap<>();
//        pathInfo.put("entity_path", projectPath + pojoName + "/src/main/java/top/lzmvlog/shop/" + service + "/model");
//        pathInfo.put("mapper_path", projectPath + projectName + "/src/main/java/top/lzmvlog/shop/" + service + "/mapper");
//        pathInfo.put("service_path", projectPath + projectName + "/src/main/java/top/lzmvlog/shop/" + service + "/service");
//        pathInfo.put("service_impl_path", projectPath + projectName + "/src/main/java/top/lzmvlog/shop/" + service + "/service/impl");
//        pathInfo.put("controller_path", projectPath + projectName + "/src/main/java/top/lzmvlog/shop/" + service + "/controller");
//        packageConfig.setPathInfo(pathInfo);
//        mpg.setPackageInfo(packageConfig);
//
//        // 自定义配置
//        InjectionConfig cfg = new InjectionConfig() {
//            @Override
//            public void initMap() {
//                // to do nothing
//            }
//        };

//        // 策略配置
//        StrategyConfig strategy = new StrategyConfig();
//        // 数据库表映射到实体的命名策略
//        strategy.setNaming(NamingStrategy.underline_to_camel);
//        // 数据库表字段映射到实体的命名策略, 未指定按照 naming 执行
//        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        // 自定义基础的Entity类，公共字
////        strategy.setSuperEntityColumns("create_time");
//        // 使用 lombok模型
//        strategy.setEntityLombokModel(true);
//        // 生成 @RestController 控制器
//        strategy.setRestControllerStyle(true);
//        // 驼峰转连字符
//        strategy.setControllerMappingHyphenStyle(true);
//        // 生成表名时去除前缀
//        strategy.setTablePrefix("sys");
//        // 生成实体时，生成字段注解
//        strategy.setEntityTableFieldAnnotationEnable(true);
//        mpg.setStrategy(strategy);
//        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
//        mpg.execute();

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
                .setPassword("root");

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

        // 每次生成之前都需要确定是哪一个服务下面的信息
        String service = "order";
        //4. 包名策略配置
        PackageConfig pkConfig = new PackageConfig();
        pkConfig.setParent("top.lzmvlog.shop." + service)
                .setMapper("mapper")
                .setService("service")
                .setController("controller")
                .setEntity("model")
                .setXml("resources.mapper");

        //5. 整合配置
        AutoGenerator ag = new AutoGenerator();
        ag.setGlobalConfig(config)
                .setDataSource(dsConfig)
                .setStrategy(stConfig)
                .setPackageInfo(pkConfig)
                .setTemplate(templateConfig);

        //6. 执行
        ag.execute();
        log.info("生成完成");
    }

}
