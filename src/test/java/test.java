import com.alibaba.fastjson.JSON;
import com.test.util.DataSourceFactory;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class test {

    @Test
    public static void test() throws SQLException {
        Connection connection = DataSourceFactory.getConnection(0);
        System.out.println(connection);

    }

    @Test
    public void test1(){

        RegBean regBean = new RegBean();
        regBean.setRegCode("00");
        regBean.setUserId("00");
        regBean.setRegStatus("注册成功");

        Map map = new HashMap<>();
        map.put("respData",regBean);
        System.out.println(JSON.toJSONString(map));


    }

    @Data
    class RegBean{
        String regCode;
        String userId;
        String regStatus;

    }
}
