package com.zhengtou.cf.common.api.outer.riskControl.jxlapi.vo.bee.request;

import com.zhengtou.cf.common.api.outer.riskControl.jxlapi.vo.bee.WebsiteVO;

import java.util.Collections;
import java.util.List;

/**
 * 运营商京东数据采集结构
 * @author 葛文镇
 */
public class CollectionPhoneJDRequest {
    private List<WebsiteVO> selected_website= Collections.emptyList();
    private BasicInfo basic_info;
    private List<ContactVO> contacts= Collections.emptyList();
    private boolean skip_mobile;
    private String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public boolean isSkip_mobile() {
        return skip_mobile;
    }

    public void setSkip_mobile(boolean skip_mobile) {
        this.skip_mobile = skip_mobile;
    }

    public List<WebsiteVO> getSelected_website() {
        return selected_website;
    }

    public void setSelected_website(List<WebsiteVO> selected_website) {
        this.selected_website = selected_website;
    }

    public BasicInfo getBasic_info() {
        return basic_info;
    }

    public void setBasic_info(BasicInfo basic_info) {
        this.basic_info = basic_info;
    }

    public List<ContactVO> getContacts() {
        return contacts;
    }

    public void setContacts(List<ContactVO> contacts) {
        this.contacts = contacts;
    }

    private class ContactVO{
        private String contact_tel;
        private String contact_name;
        private String contact_type;

        public String getContact_tel() {
            return contact_tel;
        }

        public void setContact_tel(String contact_tel) {
            this.contact_tel = contact_tel;
        }

        public String getContact_name() {
            return contact_name;
        }

        public void setContact_name(String contact_name) {
            this.contact_name = contact_name;
        }

        public String getContact_type() {
            return contact_type;
        }

        public void setContact_type(String contact_type) {
            this.contact_type = contact_type;
        }
    }
}
