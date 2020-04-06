package com.bitedu.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OSMonitorApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
    // 1. 加载.fxml 文件
        //getResource从resource下获取资源，类加载器
    FXMLLoader loader = new
                 FXMLLoader(getClass().getClassLoader().getResource("os_monitor_tab.fxml"));
    //真正的加载
    Parent root = loader.load();
        // 在主窗口弹出窗口
    OSMonitorController controller=loader.getController();//加载fxml时，loader包含controller
    //将主窗口primaryStage传给OSMonitorController
    controller.setPrimaryStage(primaryStage);
    // 2. 创建一个场景对象       
    Scene scene = new Scene(root, 800, 600);
    // 3. 给舞台对象设置标题
    primaryStage.setTitle("OS Monitor");
    // 4. 给舞台对象stage 设置场景对象scene
    primaryStage.setScene(scene);
    //当关闭窗口时，关闭timer线程
    primaryStage.setOnCloseRequest((e) -> controller.shutdown());
    // 5. 展示舞台
    primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
