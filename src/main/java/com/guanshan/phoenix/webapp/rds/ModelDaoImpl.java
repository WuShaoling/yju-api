package com.guanshan.phoenix.webapp.rds;

import com.guanshan.phoenix.webapp.shared.entity.Model;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

/**
 * Created by bennettqian on 31/08/2017.
 */
@Component
public class ModelDaoImpl implements BaseDao<Model>{
    @Autowired
    @Qualifier("nlpJdbcTemplate")
    private JdbcTemplate jdbcTemplate;
    private static Logger LOG = Logger.getLogger(ModelDaoImpl.class);


    public int createModel(String sql,String name,String time){
        KeyHolder holder = new GeneratedKeyHolder();
        LOG.info(name+" "+time);
        jdbcTemplate.update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection connection)
                    throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, name);
                ps.setString(2, time);
                return ps;
            }
        }, holder);

        return  holder.getKey().intValue();
    }

    @Override
    public int insert(String sql, Map params) {
        return 0;
    }

    @Override
    public int update(String sql, Map params) {
        return 0;
    }

    @Override
    public Model findOne(String sql, Map params) {
        return null;
    }

    @Override
    public List<Model> findAll(String sql, Map params) {
        return null;
    }

    @Override
    public void delete(String sql, Map params) {

    }
}
