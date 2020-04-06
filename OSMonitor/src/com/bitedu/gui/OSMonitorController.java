package com.bitedu.gui;

import com.bitedu.osm.FileScanner;
import com.bitedu.osm.FileTreeNode;
import com.bitedu.osm.OSResource;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tab;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class OSMonitorController {
    //FXML注解 提高效率
    @FXML private LineChart cpuChart;//需要将CPUchart和我们构建的坐标点联系起来，cpuChart即为xml中的id名
    @FXML private TreeTableView<FileTreeNode> fileStat;
    @FXML private Text osType;
    @FXML private Text cpuArch;
    @FXML private Text Version;
    //定时器线程
    private Timer timer = new Timer();
    //定时器任务
    private TimerTask timerTask = null;

    private final Image image = new Image(getClass().getClassLoader().getResourceAsStream("Folder.png"));

    public void handleCPUSelectionChanged(Event event) {
        Tab tab = (Tab)event.getTarget();//拿到触发事件的对象
        if(tab.isSelected()){
            //创建一个线程任务
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    //拿到坐标点
                    OSResource.XYPair[] xyPairs = OSResource.getCPUPercetage();
                    XYChart.Series series = new XYChart.Series();//用XYChart存放坐标点，Series里面有很多坐标点
                    for(OSResource.XYPair xyPair:xyPairs){
                        //将数据转换为XYChart的坐标
                        XYChart.Data data = new XYChart.Data(xyPair.getX(),xyPair.getY());
                        series.getData().add(data);
                    }
                    //将渲染逻辑切换到主线程执行
                    Platform.runLater(
                            () ->{
                                //清除上一次图表中的数据点
                                if(cpuChart.getData().size()>0){
                                    cpuChart.getData().remove(0);
                                }
                                cpuChart.getData().add(series);//数据点
                                osType.setText(OSResource.getOSName());
                                cpuArch.setText(OSResource.getcpuArch());
                                Version.setText(OSResource.getVersion());
                            }
                    );


                }
            };
            //0代表任务安排后，立刻执行，1000ms为周期执行时间
            timer.schedule(timerTask,0,1000);
        }else{
            //Tab页没被选中时，关掉定时器TimerTask
            if(timerTask!=null){
                timerTask.cancel();
                timerTask = null;
            }
        }

    }
    //程序退出时，退出timer线程
    public void shutdown(){
        if(timer !=null){
            timer.cancel();
        }
    }

    //------------开始磁盘空间统计的Controlle设计----------------------------
    private Stage primaryStage = null;
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void handleSelectFile(ActionEvent actionEvent) {

        //在重新选择时，清空数据
        fileStat.setRoot(null);
        //文件选择,返回一个文件对象
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File file = directoryChooser.showDialog(primaryStage);//file代表返回的选择目录或文件

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                FileTreeNode rootNode = new FileTreeNode();
                rootNode.setFile(file);
                rootNode.setFileName(file.getName());
                FileScanner.scannerDirectory(rootNode);//扫描目录
                // //准备好逻辑部分后开始渲染
                TreeItem rootItem = new TreeItem(rootNode, new ImageView(image));
                rootItem.setExpanded(true);//树展开
                fillTreeItem(rootNode,rootItem);
                //转换到主线程执行
                Platform.runLater(
                        () ->{
                            fileStat.setRoot(rootItem);
                        }
                );
            }
        });
        thread.setDaemon(true);//线程执行完毕自动释放资源
        thread.start();
    }
    //递归渲染 FileTreeNode转为TreeItem
    private void fillTreeItem(FileTreeNode rootNode, TreeItem rootItem){
        List<FileTreeNode> childs = rootNode.getChildrens();
        for(FileTreeNode node:childs){
            //树转换
            TreeItem item = new TreeItem(node);
            //如果孩子节点大于0，说明还有子目录
            if(node.getChildrens().size()>0){
                item.setGraphic(new ImageView(image));
            }
            rootItem.getChildren().add(item);
            //递归
            fillTreeItem(node,item);
        }
    }




}
