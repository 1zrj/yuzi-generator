package com.yupi.maker.meta;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

public class MetaValidator {
    public static void doValidAndFill(Meta meta){
        validAndFillMetaRoot(meta);

        validAndFillFileConfig(meta);

        validAndFillModelConfig(meta);
    }

    private static void validAndFillModelConfig(Meta meta) {
        //modelConfig 校验和默认值
        Meta.ModelConfig modelConfig = meta.getModelConfig();
        if (modelConfig == null) {
            return;
        }
        List<Meta.ModelConfig.ModelInfo> modelInfoList = modelConfig.getModels();
        if (CollectionUtil.isNotEmpty(modelInfoList)) {
            for (Meta.ModelConfig.ModelInfo modelInfo : modelInfoList) {
                // 输出路径默认值
                String fieldName = modelInfo.getFieldName();
                if (StrUtil.isBlank(fieldName)) {
                    throw new MetaException("未填写 fieldName");
                }

                String modelInfoType = modelInfo.getType();
                if (StrUtil.isEmpty(modelInfoType)) {
                    modelInfo.setType("String");
                }
            }
        }
    }

    private static void validAndFillFileConfig(Meta meta) {
        //fireConfig 校验和默认值
        Meta.FileConfig fileConfig = meta.getFileConfig();
        if (fileConfig == null) {
            return;
        }
        //sourceRootPath必填
        String sourceRootPath = fileConfig.getSourceRootPath();
        if(StrUtil.isBlank(sourceRootPath)){
            throw new MetaException("未填写 sourceRootPath");
        }

        // inputRootPath: .source + sourceRootPath 的最后一个层级路径
        String inputRootPath = fileConfig.getInputRootPath();
        String defaultInputRootPath = ".source" + File.separator + FileUtil.getLastPathEle(Paths.get(sourceRootPath)).getFileName().toString();
        if (StrUtil.isEmpty(inputRootPath)) {
            fileConfig.setInputRootPath(defaultInputRootPath);
        }
        // outputRootPath: 默认为当前路径下的 generated
        String outputRootPath = fileConfig.getOutputRootPath();
        String defaultOutputRootPath = "generated";
        if (StrUtil.isEmpty(outputRootPath)) {
            fileConfig.setOutputRootPath(defaultOutputRootPath);
        }

        String fileConfigType = fileConfig.getType();
        String defaultType = "dir";
        if (StrUtil.isEmpty(fileConfigType)) {
            fileConfig.setType(defaultType);
        }

        List<Meta.FileConfig.FileInfo> fileInfoList = fileConfig.getFiles();
        if (!CollectionUtil.isNotEmpty(fileInfoList)) {
            return;
        }
        for (Meta.FileConfig.FileInfo fileInfo : fileInfoList) {
            // inputPath 必填
            String inputPath = fileInfo.getInputPath();
            if (StrUtil.isBlank(inputPath)) {
                throw new MetaException("未填写 inputPath");
            }
            // outputPath 默认等于 inputPath
            String outputPath = fileInfo.getOutputPath();
            if (StrUtil.isEmpty(outputPath)) {
                fileInfo.setOutputPath(inputPath);
            }

            // type: 默认 inputPath 有文件后缀（如.java）为 file，否则为 dir
            String type = fileInfo.getType();
            if (StrUtil.isBlank(type)) {
                // 无文件后缀
                if (StrUtil.isBlank(FileUtil.getSuffix(inputPath))) {
                    fileInfo.setType("dir");
                } else {
                    fileInfo.setType("file");
                }
            }
            // generateType: 如果文件结尾不为 .ftl，默认为static，否则为 dynamic
            String generateType = fileInfo.getGenerateType();
            if (StrUtil.isBlank(generateType)) {
                // 动态模板
                if (inputPath.endsWith(".ftl")) {
                    fileInfo.setGenerateType("dynamic");
                } else {
                    fileInfo.setGenerateType("static");
                }
            }
        }
    }

    private static void validAndFillMetaRoot(Meta meta) {
        //基础信息校验及默认值
        String name = StrUtil.blankToDefault(meta.getName(), "my-generator");
        meta.setName(name);

        String description = meta.getDescription();
        if (StrUtil.isEmpty(description)) {
            description = "我的模板代码生成器";
            meta.setDescription(description);
        }

        String basePackage = meta.getBasePackage();
        if (StrUtil.isBlank(basePackage)) {
            basePackage = "com.dexcode";
            meta.setBasePackage(basePackage);
        }

        String version = meta.getVersion();
        if (StrUtil.isEmpty(version)) {
            version = "1.0";
            meta.setVersion(version);
        }

        String author = meta.getAuthor();
        if (StrUtil.isEmpty(author)) {
            author = "dexter";
            meta.setAuthor(author);
        }

        String createTime = meta.getCreateTime();
        if (StrUtil.isEmpty(createTime)) {
            createTime = DateUtil.now();
            meta.setCreateTime(createTime);
        }
    }
}
