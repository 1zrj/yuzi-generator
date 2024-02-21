package com.yupi.generator;

import com.yupi.model.MainTemplateConfig;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DynamicGenerator {
    public static void main(String[] args) throws IOException, TemplateException {
        String projectPath = System.getProperty("user.dir");
        System.out.println(projectPath);
        String inputPath = projectPath + File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
        String outputPath = projectPath + File.separator + "/MainTemplate.java";
        System.out.println(outputPath);
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

        // 解决中文乱码问题
        Template template = cfg.getTemplate(templateName,"utf-8");

        //生成
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(Paths.get(outputPath)), StandardCharsets.UTF_8));
        template.process(model, out);
        out.close();
    }
}
