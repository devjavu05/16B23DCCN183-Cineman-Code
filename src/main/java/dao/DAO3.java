package dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Lop DAO truu tuong: giu ket noi CSDL dung chung cho toan he thong (theo giao trinh,
 * cac lop DAO ke thua lop nay de tiet kiem tai nguyen).
 */
public abstract class DAO3 {
    /** Ket noi dung chung cho cac lop con. */
    protected static Connection con;

    public DAO3() {
        if (con == null) {
            try {
                Properties p = new Properties();
                try (InputStream in = DAO3.class.getResourceAsStream("/db.properties")) {
                    if (in != null) p.load(in);
                }
                String driver = System.getProperty("db.driver", p.getProperty("db.driver"));
                String url = System.getProperty("db.url", p.getProperty("db.url"));
                String user = System.getProperty("db.user", p.getProperty("db.user"));
                String pass = System.getProperty("db.password", p.getProperty("db.password"));
                Class.forName(driver);
                con = DriverManager.getConnection(url, user, pass);
            } catch (Exception e) {
                throw new IllegalStateException("Khong ket noi duoc CSDL: " + e.getMessage(), e);
            }
        }
    }

    /** Dam bao ket noi con song (ket noi dung chung co the bi MySQL dong sau thoi gian roi). */
    protected Connection conn() throws java.sql.SQLException {
        if (con == null || con.isClosed() || !con.isValid(2)) {
            con = null;
            new DAO3() { }; // mo lai ket noi
        }
        return con;
    }
}
