package util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author tuanb
 */
public class DBContext {

    private static final String USERNAME = "sa";
    private static final String PASSWORD = "123";
    private static final String SERVER = "localhost";
    private static final String PORT = "1433";
    private static final String DATABASE_NAME = "SHOSE_SHOP_VER2";
    private static final boolean USING_SSL = true;

    private static String CONNECT_STRING;

    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            StringBuilder sb = new StringBuilder()
                    .append("jdbc:sqlserver://").append(SERVER).append(":").append(PORT).append(";")
                    .append("databaseName=").append(DATABASE_NAME).append(";")
                    .append("user=").append(USERNAME).append(";")
                    .append("password=").append(PASSWORD).append(";");
            if (USING_SSL) {
                sb.append("encrypt=true;trustServerCertificate=true;");
            }
            CONNECT_STRING = sb.toString();

            // In từng tham số kết nối xuống dòng
            System.out.println("Cấu hình kết nối:");
            System.out.println("  Máy chủ        : " + SERVER);
            System.out.println("  Cổng           : " + PORT);
            System.out.println("  Tên CSDL       : " + DATABASE_NAME);
            System.out.println("  Tên đăng nhập  : " + USERNAME);
            System.out.println("  Sử dụng SSL    : " + (USING_SSL ? "Có" : "Không"));
            System.out.println("");
        } catch (ClassNotFoundException ex) {
            System.err.println("Không tìm thấy driver JDBC: " + ex.getMessage());
        }
    }

    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(CONNECT_STRING);
            return conn;
        } catch (SQLException ex) {
            System.err.println("Không thể kết nối đến cơ sở dữ liệu: " + ex.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        Connection conn = getConnection();
        if (conn != null) {
            try {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("\nKết nối thành công đến cơ sở dữ liệu!");
                System.out.println("Thông tin CSDL:");
                System.out.println("  Driver JDBC      : " + meta.getDriverName());
                System.out.println("  Tên sản phẩm CSDL: " + meta.getDatabaseProductName());
                System.out.println("  Phiên bản CSDL    : " + meta.getDatabaseProductVersion());
            } catch (SQLException ex) {
                System.err.println("Lỗi khi lấy thông tin CSDL: " + ex.getMessage());
            } finally {
                try {
                    conn.close();
                } catch (SQLException ignored) {
                }
            }
        } else {
            System.out.println("\nChưa kết nối được đến cơ sở dữ liệu. Ứng dụng vẫn sẽ tiếp tục khởi động bình thường.");
        }
    }

    public static ResultSet query(String sql, Object[] args) {
        throw new UnsupportedOperationException("Chưa hỗ trợ phương thức query.");
    }
}
