package core.jdbc;

import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

public class ConnectionManagerTest {

    @Test
    public void connection(){
        Connection conn = ConnectionManager.getConnection();
        assertNotNull(conn);
    }
}