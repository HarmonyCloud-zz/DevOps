package com.zhengtou.cf.test;

import com.zhengtou.cf.common.pojo.entity.BaseEntity;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class BaseTestWithTransaction extends AbstractTransactionalJUnit4SpringContextTests {
    private static final Map<String, BaseEntity> data = new HashMap<String, BaseEntity>();

    protected void setData(String key, BaseEntity value) {
        data.put(key, value);
    }

    protected <T extends BaseEntity> T getData(String key, Class<T> clazz) {
        return (T) data.get(key);
    }

    protected long getTime(int year, int month, int date, int hourOfDay, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, date, hourOfDay, minute, second);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    protected long getTime(int year, int month, int date) {
        return getTime(year, month, date, 0, 0, 0);
    }

    protected Date getDate(int year, int month, int date, int hourOfDay, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, date, hourOfDay, minute, second);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    protected Date getDate(int year, int month, int date) {
        return getDate(year, month, date, 0, 0, 0);
    }
}
