package interfaceDemo;

public class 打印店 {
    打印机 打印机1;

    public void 安装打印机(打印机 打印机1) {
        this.打印机1 = 打印机1;
    }
    public void 开始打印(String 打印内容){
        打印机1.打印(打印内容);
    }

    static interface 打印机 {
        void 打印(String 打印内容);
    }
}
