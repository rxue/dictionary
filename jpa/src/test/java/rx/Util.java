package rx;

import jakarta.transaction.UserTransaction;

public class Util {
    private Util() {}
    public static UserTransaction userTransaction() {
        return com.arjuna.ats.jta.UserTransaction.userTransaction();
    }
    public static String getPortNumber() {
        final String property = "port";
        final String valueFromSystem = System.getProperty(property);
        if (valueFromSystem != null)
            return valueFromSystem;
        return "3306";
    }
    public static String getJdbcURL() {
        return "jdbc:mariadb://localhost:" + Util.getPortNumber() + "/test";
    }


}
