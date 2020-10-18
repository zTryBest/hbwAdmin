package cn.hbw.logging.annotation.type;

public enum LogType {
    // 增删改查
    ADD("增加"),
    SELECT("查询"),
    UPDATE("修改"),
    DELETE("删除");

    private String value;

    LogType(String value){
        this.value =  value;
    }

    public String getValue() {return this.value;}

    public void setValue(String value) {
        this.value = value;
    }
}
