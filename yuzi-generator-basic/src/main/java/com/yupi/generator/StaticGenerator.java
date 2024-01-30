package com.yupi.generator;

import cn.hutool.Hutool;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ArrayUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class StaticGenerator {
    public static void main(String[] args) {
        //获取整个目录的根路径.\.\yuzi-generator
        String projectPath = System.getProperty("user.dir");
        File parentFile = new File(projectPath).getParentFile();
        //输入路径： ACM示例代码模板
        String inputPath = new File(parentFile, "yuzi-generator-demo-projects/acm-template").getAbsolutePath();
        //输出路径：输出到项目根路径
        String outputPath = projectPath;
        copyFilesByHutool(inputPath, outputPath);
    }

    /**
     * 拷贝文件（Hutool实现）
     * @param inputPath
     * @param outputPath
     */
    public static void copyFilesByHutool(String inputPath, String outputPath){
        FileUtil.copy(inputPath, outputPath, false);
    }

    /**
     * 递归拷贝文件
     * @param inputPath
     * @param outputPath
     */
    public static void copyFilesByRecursive(String inputPath, String outputPath) throws IOException {
        File inputFile = new File(inputPath);
        File outputFile = new File(outputPath);
        copyFileByRecursive(inputFile, outputFile);
    }

    /**
     * 复制文件或子目录
     * @param inputFile
     * @param outputFile
     * @throws IOException
     */
    public static void copyFileByRecursive(File inputFile, File outputFile) throws IOException {
        // 区分是文件还是目录
        if (inputFile.isDirectory()){
            File destOutputFile = new File(outputFile, inputFile.getName());
            //如果是目录，创建该目录
            if (!destOutputFile.exists()){
                destOutputFile.mkdirs();
            }
            //获取目录下的所有子目录和文件
            File[] files = inputFile.listFiles();
            //如果无文件和子目录
            if (ArrayUtil.isEmpty(files)){
                return;
            }
            for (File file : files) {
                copyFileByRecursive(file, destOutputFile);
            }
        }else {
            //是文件，直接复制在目录下,inputFile.getName()用于获取输入文件的名字
            Path destPath = outputFile.toPath().resolve(inputFile.getName());
            Files.copy(inputFile.toPath(), destPath, StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
