package com.gzz.socket.packet;

import java.util.List;

/**
 * 指令集
 */
public class CommandSet {
    private int index = 0;
     // 指令集
    private List<String> commands;
    // 业主
    private String ownerId;
    // 是否为新连接
    private boolean isNewConnect = true;

    public CommandSet(){
        this.isNewConnect = true;
    }
    public void setOwnerId(String ownerId){
        this.ownerId = ownerId;
    }
    public String getOwnerId(){
        return this.ownerId;
    }
    public void setCommands(List<String> commands){
        this.commands = commands;
        this.isNewConnect = false;
    }

    // 获取当前指令
    public String getCommand(){
        String cmd = "END";
        if (commands.size() >= index){
            cmd = commands.get(index);
            this.index ++;
        }
        return cmd;
    }
    // 是否新的连接
    public boolean isNew(){
        return this.isNewConnect;
    }
    // 有
    public boolean hasNext(){
        if (commands == null || commands.size() <= index) return false;
        return true;
    }

    // 是否最后一条
    public boolean isLast(){
        if (commands == null)  return true;
        if (commands.size() < index) return true;
        return false;
    }

    // 复位指令
    public void reset(){
        this.index = 0;
    }
}
