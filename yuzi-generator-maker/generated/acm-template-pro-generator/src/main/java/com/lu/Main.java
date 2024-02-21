package com.lu;

import com.lu.cli.CommandExecutor;

public class Main{
    public static void main(String[] args){
        CommandExecutor commandExecutor = new CommandExecutor();
        commandExecutor.doExecute(args);
    }
}