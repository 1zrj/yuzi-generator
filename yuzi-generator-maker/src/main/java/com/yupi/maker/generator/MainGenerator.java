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

public class MainGenerator extends GeneratorTemplate{
    @Override
    protected void buildDist(String outputPath, String sourceCopyDestPath, String jarPath, String shellOutputFilePath) {
        System.out.println("不生成精简代码程序");
    }
}
