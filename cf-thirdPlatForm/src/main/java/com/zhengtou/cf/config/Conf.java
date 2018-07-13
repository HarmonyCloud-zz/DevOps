package com.zhengtou.cf.config;

import com.zhengtou.cf.config.jxl.JxlConf;
import com.zhengtou.cf.config.phone.ShjConf;
import com.zhengtou.cf.config.sms.YmConf;
import com.zhengtou.cf.config.td.TdConf;
import com.zhengtou.cf.config.yibao.YibaoConf;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 三方对接平台主配置项
 *
 * @author 葛文镇
 */
@Configuration
public class Conf {
    /**
     * 同盾配置
     */
    @Bean(name = "tdConf")
    public TdConf getTdConf() {
        return new TdConf("zhengtou_web");
    }

    @Bean(name = "tdIosConf")
    public TdConf getTdIosConf() {
        return new TdConf("zhengtou_ios");
    }

    @Bean(name = "tdAndConf")
    public TdConf getTdAndConf() {
        return new TdConf("zhengtou_and");
    }

    /**
     * 聚信立配置
     */
    @Bean(name = "jxlConf")
    public JxlConf getJxlConf() {
        return new JxlConf();
    }

    /**
     * 易宝配置
     */
    @Bean(name = "yibaoConf")
    public YibaoConf getYibaoConf() {
        return new YibaoConf();
    }

    /**
     * 亿美sms配置
     */
    @Bean(name = "ymConf")
    public YmConf getYmConf() {
        return new YmConf();
    }

    /**
     * 深海杰phone配置
     */
    @Bean(name = "shjConf")
    public ShjConf getShjConf() {
        return new ShjConf();
    }
}

