package com.yupi.maker.model;

import lombok.Data;

@Data
public class DataModel {
    /**
     * 是否生成循环
     */
    private boolean loop;

    /**
     * 作者注释
     */
    private String author = "zrj";

    /**
     * 输出信息
     */
    private String outputText = "sum =";

}
