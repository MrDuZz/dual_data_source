package cn.dpy.dual.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @Author: dupinyan
 * @Description:
 * @Date: 2019/10/30 19:07
 * @Version: 1.0
 */
@RestController
@RequestMapping("testJdbc")
@Slf4j
public class TestController extends BaseController {


    @Autowired
    @Qualifier("primaryJdbcTemplate")
    protected JdbcTemplate jdbcTemplate1;

    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    protected JdbcTemplate jdbcTemplate2;


    @RequestMapping("test2")
    public MessageResult testDual() {

        String sql = "SELECT * from PERSON";

        List<Map<String, Object>> resObj2 = (List<Map<String, Object>>) jdbcTemplate2.execute(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                        return con.prepareStatement(sql);
                    }
                }, new PreparedStatementCallback<List<Map<String, Object>>>() {

                    @Override
                    public List<Map<String, Object>> doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                        ps.execute();
                        ResultSet rs = ps.getResultSet();
                        while (rs.next()) {
                            // 这里需要getObject, Oracle得数据类型貌似不能getString
                            System.out.println("==" + rs.getObject("pname"));
                        }
                        return null;
                    }

                });
        return success("查询成功");
    }

    @RequestMapping("test")
    public MessageResult getCxzdb() {

        String sql = "SELECT * FROM users";

        List<Map<String, Object>> resObj = (List<Map<String, Object>>) jdbcTemplate1.execute(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                        return con.prepareStatement(sql);
                    }
                }, new PreparedStatementCallback<List<Map<String, Object>>>() {

                    @Override
                    public List<Map<String, Object>> doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                        ps.execute();
                        ResultSet rs = ps.getResultSet();
                        while (rs.next()) {
                            System.out.println("==" + rs.getInt("id"));
                            System.out.println("==" + rs.getString("userId"));
                            System.out.println("==" + rs.getString("passWord"));


                        }
                        return null;
                    }

                });

        return success("查询成功");
    }
}
