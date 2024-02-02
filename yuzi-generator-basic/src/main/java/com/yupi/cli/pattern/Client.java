package com.yupi.cli.pattern;

public class Client {
    public static void main(String[] args) {
        //创建不同接受者对象
        Device tv = new Device("TV");
        Device stereo = new Device("Sterro");

        //创建具体的命令对象，不同的设备可以执行不同的命令
        TurnOffCommand turnOffCommand = new TurnOffCommand(tv);
        TurnOnCommand turnOnCommand = new TurnOnCommand(stereo);

        //创建调用者
        RemoteControl remoteControl = new RemoteControl();

        //执行命令
        remoteControl.setCommand(turnOffCommand);
        remoteControl.pressButton();

        remoteControl.setCommand(turnOnCommand);
        remoteControl.pressButton();

    }
}
