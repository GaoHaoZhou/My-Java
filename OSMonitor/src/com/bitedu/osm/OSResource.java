package com.bitedu.osm;

import javax.net.ssl.ManagerFactoryParameters;
import java.lang.management.ManagementFactory;
//import java.lang.management.OperatingSystemMXBean;
import com.sun.management.OperatingSystemMXBean;

public class OSResource {
    //1利用JMX 获取系统资源，内存，cpu占有率等
    private static  OperatingSystemMXBean mxBean = ManagementFactory.
            getPlatformMXBean(OperatingSystemMXBean.class);

    private static final int DATA_LENGTH = 60;
    private static int firstIndex = DATA_LENGTH;
    //4 记录60个坐标点即一个统计周期
    private  static XYPair[] xyPairs = new XYPair[DATA_LENGTH ];//60个坐标对象
    static {
        //XYPair[]60个坐标初始化
        for(int i=0;i<xyPairs.length;++i){
            xyPairs[i] = new XYPair(0,0);
        }
    }
    //3 坐标类，保存横纵坐标
    public  static class XYPair{
        private  double x;
        private  double y;
        public XYPair (double x, double y){
            this.x =x;
            this.y=y;
        }
        public double getX() {
            return x;
        }

        public void setX(double x) {
            this.x = x;
        }

        public double getY() {
            return y;
        }

        public void setY(double y) {
            this.y = y;
        }
    }
    //2 获取cpu占有率（重要）
    public static XYPair[] getCPUPercetage(){
        double cpu= mxBean.getSystemCpuLoad();
        //拿到新的坐标点放入表中
        moveCPUData(cpu);
        return xyPairs;
    }
    //获取OSName文本
    public static String getOSName(){
        return mxBean.getName();
    }
    //获取cpuArch文本
    public static String getcpuArch(){
        return mxBean.getArch();
    }
    //获取Version文本
    public static String getVersion(){
        return mxBean.getVersion();
    }
   // 5 核心程序（0—59）
    private static void moveCPUData(double cpuPercetage) {
        int movIdx = -1;
        if (firstIndex == 0) {
            movIdx = firstIndex + 1;
        } else {
            movIdx = firstIndex;
            firstIndex--;
        }
        //不断向前覆盖，最后在后面插入新的值
        for (; movIdx < xyPairs.length; ++movIdx) {
            xyPairs[movIdx - 1].setX(xyPairs[movIdx].getX() - 1);
            xyPairs[movIdx - 1].setY(xyPairs[movIdx].getY());
        }
        movIdx--; //回到59的位置
        xyPairs[movIdx] = new XYPair(movIdx, cpuPercetage);
    }

    }
