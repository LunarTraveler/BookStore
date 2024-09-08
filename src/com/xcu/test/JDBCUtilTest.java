package com.xcu.test;

import com.xcu.utils.JDBCUtil;
import org.junit.Test;

public class JDBCUtilTest {

    @Test
    public void testForJDBC() {
        for (int i = 0; i < 22; i++) {
            System.out.println(JDBCUtil.getConnection());
            JDBCUtil.release();
        }
    }

}
