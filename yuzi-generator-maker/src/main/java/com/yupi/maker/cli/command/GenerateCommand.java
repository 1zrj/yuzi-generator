package com.yupi.maker.cli.command;

import cn.hutool.core.bean.BeanUtil;
import com.yupi.maker.generator.file.FileGenerator;
import com.yupi.maker.model.DataModel;
import freemarker.template.TemplateException;
import lombok.Data;
import picocli.CommandLine;

import java.io.IOException;

@CommandLine.Command(name = "generator", mixinStandardHelpOptions = true)
@Data
public class GenerateCommand implements Runnable{
    @CommandLine.Option(names = {"-l", "--loop"}, arity = "0..1", description = "是否循环", interactive = true, echo = true)
    private boolean loop;

    @CommandLine.Option(names = {"-a", "--author"}, arity = "0..1", description = "作者", interactive = true, echo = true)
    private String author;

    @CommandLine.Option(names = {"-o", "--outputText"}, arity = "0..1", description = "输出文本", interactive = true, echo = true)
    private String outputText;

    @Override
    public void run(){
        DataModel dataModel = new DataModel();
        BeanUtil.copyProperties(this, dataModel);
        try {
            FileGenerator.doGenerate(dataModel);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }
}
