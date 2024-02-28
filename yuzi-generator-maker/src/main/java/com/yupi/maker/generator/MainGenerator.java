package com.yupi.maker.generator;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.StrUtil;
import com.yupi.maker.generator.JarGenerator;
import com.yupi.maker.generator.file.DynamicFileGenerator;
import com.yupi.maker.generator.file.StaticFileGenerator;
import com.yupi.maker.meta.Meta;
import com.yupi.maker.meta.MetaManager;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

public class MainGenerator {
    public static void main(String[] args) throws TemplateException, IOException, InterruptedException {
        Meta meta = MetaManager.getMeta();
        System.out.println(meta);

        // 输出的根路径
        String projectPath = System.getProperty("user.dir");
        String outputPath = projectPath + File.separator + "generated" + File.separator + meta.getName();
        if (!FileUtil.exist(outputPath)) {
            FileUtil.mkdir(outputPath);
        }

        //复制原始文件
        String sourceRootPath = meta.getFileConfig().getSourceRootPath();
        // 将模板文件复制到项目目录下
        String sourceCopyDestPath = outputPath + File.separator + ".source";
        FileUtil.copy(sourceRootPath, sourceCopyDestPath, false);

        // 读取resources目录
        ClassPathResource classPathResource = new ClassPathResource("");
        String inputResourcePath = classPathResource.getAbsolutePath();

        // Java包的基础路径
        String outputBasePackage = meta.getBasePackage();
        String outputBasePackagePath = StrUtil.join("/", StrUtil.split(outputBasePackage, "."));
        String outputBaseJavaPackagePath = outputPath + File.separator + "src/main/java" + File.separator + outputBasePackagePath;

        String inputFilePath;
        String outputFilePath;

        //// model.DataModel
        //inputFilePath = inputResourcePath + File.separator + "templates/java/model/DataModel.java.ftl";
        //outputFilePath = outputBaseJavaPackagePath + File.separator + "/model/DataModel.java";
        //DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);
        //
        ////cli.command.ConfigCommand
        //inputFilePath = inputResourcePath + File.separator + "templates/java/cli/command/ConfigCommand.java.ftl";
        //outputFilePath = outputBaseJavaPackagePath + File.separator + "/cli/command/ConfigCommand.java";
        //DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);
        //
        ////cli.command.GenerateCommand
        //inputFilePath = inputResourcePath + File.separator + "templates/java/cli/command/GenerateCommand.java.ftl";
        //outputFilePath = outputBaseJavaPackagePath + File.separator + "/cli/command/GenerateCommand.java";
        //DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);
        //
        ////cli.command.ListCommand
        //inputFilePath = inputResourcePath + File.separator + "templates/java/cli/command/ListCommand.java.ftl";
        //outputFilePath = outputBaseJavaPackagePath + File.separator + "/cli/command/ListCommand.java";
        //DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);
        //
        ////cli.CommandExecutor
        //inputFilePath = inputResourcePath + File.separator + "templates/java/cli/CommandExecutor.java.ftl";
        //outputFilePath = outputBaseJavaPackagePath + File.separator + "/cli/CommandExecutor.java";
        //DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);
        //
        ////Main
        //inputFilePath = inputResourcePath + File.separator + "templates/java/Main.java.ftl";
        //outputFilePath = outputBaseJavaPackagePath + File.separator + "/Main.java";
        //DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);
        //
        //// generator.StaticFileGenerator
        //inputFilePath = inputResourcePath + File.separator + "templates/java/generator/StaticFileGenerator.java.ftl";
        //outputFilePath = outputBaseJavaPackagePath + "/generator/StaticFileGenerator.java";
        //DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);
        //
        //// generator.DynamicFileGenerator
        //inputFilePath = inputResourcePath + File.separator + "templates/java/generator/DynamicFileGenerator.java.ftl";
        //outputFilePath = outputBaseJavaPackagePath + "/generator/DynamicFileGenerator.java";
        //DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);
        //
        //// generator.MainGenerator
        //inputFilePath = inputResourcePath + File.separator + "templates/java/generator/MainGenerator.java.ftl";
        //outputFilePath = outputBaseJavaPackagePath + "/generator/MainGenerator.java";
        //DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);
        //
        //// generator.pom.xml
        //inputFilePath = inputResourcePath + File.separator + "templates/pom.xml.ftl";
        //outputFilePath = outputPath + "/pom.xml";
        //DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);
        //
        ////READ.md
        //inputFilePath = inputResourcePath + File.separator + "templates/Reader.md.ftl";
        //outputFilePath = outputPath + "/READ.md";
        //DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);
        //
        ////构建jar包
        //JarGenerator.doGenerate(outputPath);
        //
        ////封装脚本
        //String shellOutPutPath = outputPath + File.separator + "generator";
        //String jarName = String.format("%s-%s-jar-with-dependencies.jar",
        //        meta.getName(),meta.getVersion());
        //String jarPath = "target/" + jarName;
        //ScriptGenerator.doGenerator(shellOutPutPath, jarPath);

        // 封装脚本
        String shellOutputFilePath = outputPath + File.separator + "generator";
        String jarName = String.format("%s-%s-jar-with-dependencies.jar", meta.getName(), meta.getVersion());
        String jarPath = "target/" + jarName;
        ScriptGenerator.doGenerator(shellOutputFilePath, jarPath);

        // 生成精简版的程序（产物包）
        String distOutputPath = outputPath + "-dist";
        //  - 拷贝jar包
        String targetAbsolutePath = distOutputPath + File.separator + "target";
        FileUtil.mkdir(targetAbsolutePath);
        String jarAbsolutePath = outputPath + File.separator + jarPath;
        FileUtil.copy(jarAbsolutePath, targetAbsolutePath, true);
        //  - 拷贝脚本文件
        FileUtil.copy(shellOutputFilePath, distOutputPath, true);
        FileUtil.copy(shellOutputFilePath + ".bat", distOutputPath, true);
        //  - 拷贝模板文件
        FileUtil.copy(sourceCopyDestPath, distOutputPath, true);

    }
}
