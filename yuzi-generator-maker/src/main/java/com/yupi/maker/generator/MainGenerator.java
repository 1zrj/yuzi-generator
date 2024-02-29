package com.yupi.maker.generator;

public class MainGenerator extends GeneratorTemplate{
    @Override
    protected void buildDist(String outputPath, String sourceCopyDestPath, String jarPath, String shellOutputFilePath) {
        System.out.println("不生成精简代码程序");
    }
}
