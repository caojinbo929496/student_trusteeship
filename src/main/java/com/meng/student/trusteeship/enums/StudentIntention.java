package com.meng.student.trusteeship.enums;

public enum StudentIntention {

    WANT("想来", 1), HESITATE("犹豫", 2), NOTCOMING("不来", 3), COMEBACKNEXTTIME("下期再来", 4);


    // 成员变量
    private String intention;
    private int index;
    // 构造方法
    private StudentIntention(String intention, int index) {
        this.intention = intention;
        this.index = index;
    }
    // 普通方法
    public static String getIntention(int index) {
        for (StudentIntention c : StudentIntention.values()) {
            if (c.getIndex() == index) {
                return c.intention;
            }
        }
        return null;
    }

    public String getIntention() {
        return intention;
    }

    public void setIntention(String intention) {
        this.intention = intention;
    }

    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
}
