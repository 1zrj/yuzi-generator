package com.yupi.generator;

import com.yupi.model.MainTemplateConfig;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class DynamicGenerator {
    public static void main(String[] args) throws IOException, TemplateException {
        String projectPath = System.getProperty("user.dir");
        System.out.println(projectPath);
        String inputPath = projectPath + File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
        String outputPath = projectPath + File.separator + "MainTemplate.java";
        //创建数据模型
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("zzz");
        mainTemplateConfig.setLoop(true);
        mainTemplateConfig.setOutputText("求和结果:");
        doGenerate(inputPath, outputPath, mainTemplateConfig);

    }

    public static void doGenerate(String inputPath, String outputPath, Object model) throws TemplateException, IOException {
        //FreeMarket配置
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_32);

        File templateDir = new File(inputPath).getParentFile();
        cfg.setDirectoryForTemplateLoading(templateDir);

        cfg.setDefaultEncoding("UTF-8");
        //解决数字分隔符问题
        cfg.setNumberFormat("0.######");
        //创建模板对象，加载指定模板
        String templateName = new File(inputPath).getName();
        Template template = cfg.getTemplate(templateName);

        //生成
        Writer out = new FileWriter(outputPath);
        template.process(model, out);

        out.close();
    }
}
