package com.lu.generator;

import cn.hutool.core.io.FileUtil;

public class StaticFileGenerator {

    /**
     * 拷贝文件（Hutool实现：将输入目录完整拷贝到输出目录下）
     *
     * @param inputPath  输入目录
     * @param outputPath 输出目录
     */
    public static void copyFilesByHutool(String inputPath, String outputPath) {
        FileUtil.copy(inputPath, outputPath, false);
    }
}