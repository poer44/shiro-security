package com.cheng.shiro.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cheng.shiro.dao.UserDao;
import com.cheng.shiro.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import javax.sql.RowSet;

/**
 * @author chengchenrui
 * @version Id: UserDaoImpl.java, v 0.1 2018/6/29 1:06 chengchenrui Exp $$
 */
@Repository
public class UserDaoImpl implements UserDao{

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public User getUserByUsername(String username) {
        User user=new User();
        jdbcTemplate.query("select username,password from users where username = ?", new Object[]{username}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
            }
        });
        return user;
    }

    @Override
    public List<String> getRolesByUserName(String username) {
        List<String> list=new ArrayList<>();
        jdbcTemplate.query("select user_roles from user_roles where username = ?", new Object[]{username}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                list.add(rs.getString("user_roles"));
            }
        });
        return list;
    }
}