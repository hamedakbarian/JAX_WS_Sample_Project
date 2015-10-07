package jndi;

/**
 * Created by weblogic12 on 5/3/2015.
 */
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class ConnectionFactory extends org.apache.log4j.jdbc.JDBCAppender {
    public static interface Singleton {
        final ConnectionFactory INSTANCE = new ConnectionFactory();
    }

    public  DataSource dataSource = null;

    public ConnectionFactory() {
        try {
            Context ctx = null;
            Hashtable ht = new Hashtable();
            ht.put(Context.INITIAL_CONTEXT_FACTORY,
                    "weblogic.jndi.WLInitialContextFactory");
            ht.put(Context.PROVIDER_URL,
                    "t3://localhost:7001");
            ctx = new InitialContext(ht);
            dataSource =  (DataSource)ctx.lookup("logger");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Connection getDatabaseConnection() throws SQLException {
        return Singleton.INSTANCE.dataSource.getConnection();
    }

    protected java.sql.Connection getConnection() throws java.sql.SQLException {
        return getDatabaseConnection();
    }

    protected void closeConnection() throws java.sql.SQLException {
        if (connection != null && !connection.isClosed())
            connection.close();
    }
}