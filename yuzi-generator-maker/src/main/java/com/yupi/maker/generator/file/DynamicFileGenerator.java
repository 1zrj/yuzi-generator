package com.yupi.maker.generator.file;

import cn.hutool.core.io.FileUtil;
import com.yupi.maker.model.DataModel;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DynamicFileGenerator {


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

        // 文件不存在则创建文件和父目录
        if (!FileUtil.exist(outputPath)) {
            FileUtil.touch(outputPath);
        }

        //生成
        Writer out = new FileWriter(outputPath);
        template.process(model, out);
        out.close();
    }
}
