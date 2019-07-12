package com.card;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/ApplicationContext-dataSource.xml", "classpath:spring/ApplicationContext-main.xml"})
public class JuintTest {

    @org.junit.Test
    public void tetest() throws Exception {
    }
}
