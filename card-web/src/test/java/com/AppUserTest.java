package com;

import com.fh.util.PageData;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/ApplicationContext-dataSource.xml", "classpath:spring/ApplicationContext-main.xml", "classpath:spring/ApplicationContext-mvc.xml" ,"classpath:spring/ApplicationContext-shiro.xml" })
public class AppUserTest {

    @org.junit.Test
    public void qrQrCodeUrl() throws Exception {

    }

}
