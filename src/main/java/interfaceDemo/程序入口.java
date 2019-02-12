package interfaceDemo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.swing.JOptionPane;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class 程序入口 {
    static 打印店.打印机 低级打印机 = new 打印店.打印机() {
        @Override
        public void 打印(String 打印内容) {
            System.out.println(打印内容);
        }
    };
    static 打印店.打印机 图片打印机 = new 打印店.打印机() {
        @Override
        public void 打印(String 打印内容) {
            JOptionPane.showMessageDialog(null,打印内容);
        }
    };
    static 打印店.打印机 可以边播放音乐边打印的打印机 = new 打印店.打印机() {
        @Override
        public void 打印(String 打印内容) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    JOptionPane.showMessageDialog(null, 打印内容);
                }
            };
            new Thread(runnable).start();//用线程打印
            //再原来线程播放
            try {
                Player player=new Player(new FileInputStream("D:\\Development\\idea\\JavaGame\\src\\main\\resources\\music1.mp3"));
                player.play();
            } catch (JavaLayerException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                System.out.println("MP3文件不存在你打印个鬼");
            }

        }
    };




    public static void main(String[] args) {
        打印店 广大店 = new 打印店();

        //广大店.安装打印机(低级打印机);
        //广大店.安装打印机(图片打印机);
        //广大店.安装打印机(可以边播放音乐边打印的打印机);

        广大店.开始打印("hello");


    }
}
