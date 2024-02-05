package com.yupi.maker.meta;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.json.JSONUtil;

public class MetaManager {
    private static volatile Meta meta;

    private MetaManager(){
    }

    public static Meta getMeta() {
        if (meta == null){
            synchronized (MetaManager.class) {
                if (meta == null) {
                    meta = initMetaObject();
                }
            }
        }
        return meta;
    }

    public static Meta initMetaObject(){
        String metaJson = ResourceUtil.readUtf8Str("meta.json");
        Meta newMeta = JSONUtil.toBean(metaJson, Meta.class);
        //todo 校验和处理默认值
        return newMeta;
    }
}
