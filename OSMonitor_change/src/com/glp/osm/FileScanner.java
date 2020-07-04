package com.glp.osm;

import java.io.File;

public class FileScanner {
    //深序遍历
    public static void scannerDirectory(FileTreeNode node) {
        //获取当前目录的子目录或文件列表
        File[] files = node.getFile().listFiles();//File的一个方法listFiles返回一个文件对象的数组
        if (files == null) {
            return;
        }
        //遍历子目录或者文件
        for (File file : files) {
            FileTreeNode child = new FileTreeNode();
            child.setFile(file);
            child.setFileName(file.getName());
            if (file.isDirectory()) {
                //继续递归子目录
                scannerDirectory(child);
            } else {
                //计算文件大小
                child.setTotalLength(file.length());
            }
            node.setTotalLength(node.getTotalLength() + child.getTotalLength());
            node.addChildNode(child);

        }
    }
}
