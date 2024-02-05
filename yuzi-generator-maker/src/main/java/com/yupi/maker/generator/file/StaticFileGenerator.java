package com.yupi.maker.generator.file;

import cn.hutool.core.io.FileUtil;

import java.io.File;

public class StaticFileGenerator {


    /**
     * 拷贝文件（Hutool实现）
     * @param inputPath
     * @param outputPath
     */
    public static void copyFilesByHutool(String inputPath, String outputPath){
        FileUtil.copy(inputPath, outputPath, false);
    }
}
