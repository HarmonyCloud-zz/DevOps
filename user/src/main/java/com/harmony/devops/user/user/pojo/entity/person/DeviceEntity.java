package com.harmony.devops.user.user.pojo.entity.person;

import com.zhengtou.cf.common.pojo.entity.BaseEntity;
import com.harmony.devops.user.user.pojo.entity.ConsumerUserEntity;

import javax.persistence.*;

/**
 * 设备信息
 * @author 葛文镇
 */
@Entity
@Table(name = "t_person_device")
public class DeviceEntity extends BaseEntity {
    @ManyToOne
    private ConsumerUserEntity consumer;
    //运营商类型
    private String carrierType;
    //设备指纹
    private String fingerprinting;
    //设备类型
    private String deviceType;
    //设备IP地址
    private String deviceIp;
    //地理位置GPS
    private String location;
    //wifi ssid名称
    private String wifiSsid;
    //苹果手机越狱
    private String iphoeBreakout;


    public ConsumerUserEntity getConsumer() {
        return consumer;
    }

    public void setConsumer(ConsumerUserEntity consumer) {
        this.consumer = consumer;
    }

    public String getCarrierType() {
        return carrierType;
    }

    public void setCarrierType(String carrierType) {
        this.carrierType = carrierType;
    }

    public String getFingerprinting() {
        return fingerprinting;
    }

    public void setFingerprinting(String fingerprinting) {
        this.fingerprinting = fingerprinting;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceIp() {
        return deviceIp;
    }

    public void setDeviceIp(String deviceIp) {
        this.deviceIp = deviceIp;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWifiSsid() {
        return wifiSsid;
    }

    public void setWifiSsid(String wifiSsid) {
        this.wifiSsid = wifiSsid;
    }

    public String getIphoeBreakout() {
        return iphoeBreakout;
    }

    public void setIphoeBreakout(String iphoeBreakout) {
        this.iphoeBreakout = iphoeBreakout;
    }
}
