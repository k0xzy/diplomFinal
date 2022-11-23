package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.DriverManager;

public class DatabaseHelper {
    private static final String datasource = System.getProperty("datasource");
    private static final String user = "adm";
    private static final String pass = "pass";
    @SneakyThrows
    public static void databaseCleanUp() {
        var runner = new QueryRunner();
        var deleteFromOrder = "DELETE FROM order_entity;";
        var deleteFromCredit = "DELETE FROM credit_request_entity;";
        var deleteFromPayment = "DELETE FROM payment_entity;";

        try (var connection = DriverManager.getConnection(
                datasource, user, pass)) {
            runner.update(connection, deleteFromOrder);
            runner.update(connection, deleteFromCredit);
            runner.update(connection, deleteFromPayment);
        }
    }

    @SneakyThrows
    public static CreditRequestEntityInfo getCreditRequestInfo() {
        var runner = new QueryRunner();
        var creditRequestInfo = "SELECT * FROM credit_request_entity WHERE created order by created DESC limit 1;";
//        var creditRequestInfo = "SELECT * FROM credit_request_entity WHERE created = (SELECT MAX(created) FROM credit_request_entity);";

        try (var connection = DriverManager.getConnection(
                datasource, user, pass)) {
            return runner.query(connection, creditRequestInfo, new BeanHandler<>(CreditRequestEntityInfo.class));
        }
    }

    @SneakyThrows
    public static PaymentEntityInfo getPaymentInfo() {
        var runner = new QueryRunner();
        var paymentInfo = "select * from payment_entity WHERE created order by created DESC limit 1;";
//        var paymentInfo = "SELECT * FROM payment_entity WHERE created = (SELECT MAX(created) FROM payment_entity);";

        try (var connection = DriverManager.getConnection(
                datasource, user, pass)) {
            return runner.query(connection, paymentInfo, new BeanHandler<>(PaymentEntityInfo.class));
        }
    }

    @SneakyThrows
    public static OrderEntityInfo getOrderInfo() {
        var runner = new QueryRunner();
        var orderInfo = "SELECT * FROM order_entity WHERE created order by created DESC limit 1;";
//        var orderInfo = "SELECT * FROM order_entity WHERE created = (SELECT MAX(created) FROM order_entity);";

        try (var connection = DriverManager.getConnection(
                datasource, user, pass)) {
            return runner.query(connection, orderInfo, new BeanHandler<>(OrderEntityInfo.class));
        }
    }
}
