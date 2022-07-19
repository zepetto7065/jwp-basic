package next.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import next.model.User;
import core.jdbc.JdbcTemplate;
import core.jdbc.SelectJdbcTemplate;

public class UserDao {


    public List<User> findAll() throws SQLException {
        // TODO 구현 필요함.
        return new ArrayList<User>();
    }

    public User findByUserId(String userId) throws SQLException {
        SelectJdbcTemplate template = new SelectJdbcTemplate() {
            @Override
            protected void setParameters(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, userId);
            }

            @Override
            protected Object mapRow(ResultSet rs) throws SQLException {
                User user = null;
                if (rs.next()) {
                    user = new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
                            rs.getString("email"));
                }

                return user;
            }
        };
        String sql = "select & from USERS where userId = ?";
        return (User) template.executeQuery(sql);
    }


    public void insert(User user) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate() {
            @Override
            public void setParameters(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, user.getUserId());
                pstmt.setString(2, user.getPassword());
                pstmt.setString(3, user.getName());
                pstmt.setString(4, user.getEmail());
            }
        };
        String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
        jdbcTemplate.executeUpdate(sql);
    }

    public void removeUser(String userId) throws SQLException {
        String sql = "delete from USERS where userId = ?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate() {
            @Override
            public void setParameters(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, userId);
            }
        };
        jdbcTemplate.executeUpdate(sql);
    }


    public void updateUser(User user) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate() {
            @Override
            public void setParameters(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, user.getUserId());
                pstmt.setString(2, user.getPassword());
                pstmt.setString(3, user.getName());
                pstmt.setString(4, user.getEmail());
            }
        };
        String sql = "update USERS set password = ? , name = ? , email = ? where userId = ?";
        jdbcTemplate.executeUpdate(sql);
    }
}
