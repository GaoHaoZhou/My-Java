package com.glp.osm;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileTreeNode {
    private String fileName;//文件名
    private long totalLength;//文件的总长
    List<FileTreeNode> childrens = new ArrayList<>();//记录子目录

    private File file; //记录系统文件对象

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setTotalLength(long totalLength) {
        this.totalLength = totalLength;
    }

    public long getTotalLength() {
        return totalLength;
    }

    public void addChildNode(FileTreeNode node){
        this.childrens.add(node);
    }

    public List<FileTreeNode> getChildrens() {
        return childrens;
    }


}
