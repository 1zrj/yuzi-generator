package com.yupi.maker.meta.enums;

/**
 * 文件生成类型枚举类
 */
public enum FileGenerateTypeEnum {
    STATIC("静态", "static"),
    DYNAMIC("动态", "dynamic");

    private String text;
    private String value;

    FileGenerateTypeEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public String getValue() {
        return value;
    }
}
