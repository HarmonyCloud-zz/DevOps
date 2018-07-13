package com.zhengtou.cf.test;


import com.zhengtou.cf.thirdOperator.service.ApiTest;
import com.zhengtou.cf.thirdOperator.service.RiskApiTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ApiTest.class,RiskApiTest.class})
public class AllTest {
}
