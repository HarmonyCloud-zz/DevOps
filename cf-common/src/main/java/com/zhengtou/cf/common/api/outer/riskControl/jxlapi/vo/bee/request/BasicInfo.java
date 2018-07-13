package com.zhengtou.cf.common.api.outer.riskControl.jxlapi.vo.bee.request;

public class BasicInfo{
    private String name;
    private String id_card_num;
    private String cell_phone_num;
    private String home_addr;
    private String word_tel;
    private String word_addr;
    private String home_tel;
    private String cell_phone_num2;

    public BasicInfo(String name, String id_card_num, String cell_phone_num) {
        this.name = name;
        this.id_card_num = id_card_num;
        this.cell_phone_num = cell_phone_num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId_card_num() {
        return id_card_num;
    }

    public void setId_card_num(String id_card_num) {
        this.id_card_num = id_card_num;
    }

    public String getCell_phone_num() {
        return cell_phone_num;
    }

    public void setCell_phone_num(String cell_phone_num) {
        this.cell_phone_num = cell_phone_num;
    }

    public String getHome_addr() {
        return home_addr;
    }

    public void setHome_addr(String home_addr) {
        this.home_addr = home_addr;
    }

    public String getWord_tel() {
        return word_tel;
    }

    public void setWord_tel(String word_tel) {
        this.word_tel = word_tel;
    }

    public String getWord_addr() {
        return word_addr;
    }

    public void setWord_addr(String word_addr) {
        this.word_addr = word_addr;
    }

    public String getHome_tel() {
        return home_tel;
    }

    public void setHome_tel(String home_tel) {
        this.home_tel = home_tel;
    }

    public String getCell_phone_num2() {
        return cell_phone_num2;
    }

    public void setCell_phone_num2(String cell_phone_num2) {
        this.cell_phone_num2 = cell_phone_num2;
    }
}
