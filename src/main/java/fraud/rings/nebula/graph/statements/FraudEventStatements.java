package fraud.rings.nebula.graph.statements;


import fraud.rings.nebula.graph.dto.*;
import fraud.rings.nebula.graph.util.Constants;
import lombok.extern.slf4j.Slf4j;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * @author Brume
 **/
@Slf4j
public class FraudEventStatements extends Constants {

    public static void prepareInsertFraudEventUser(UserDTO fraudEventUser, PreparedStatement statement)
            throws SQLException {
        statement.setInt(1, fraudEventUser.getUser_id());
        statement.setString(2, fraudEventUser.getEmail());
        statement.setString(3, fraudEventUser.getUuid());
        statement.setString(4, fraudEventUser.getFirst_name());
        statement.setString(5, fraudEventUser.getLast_name());
        statement.setString(6, fraudEventUser.getMobile());
        statement.setString(7, fraudEventUser.getRegistration_time());
        statement.setTimestamp(8, fraudEventUser.getCreated_at());
        statement.setDouble(9, fraudEventUser.getId());
        statement.setTimestamp(10, fraudEventUser.getDeleted_at());

    }

    public static void prepareInsertFraudEventTransactions(TransactionDTO transactions, PreparedStatement statement)
            throws SQLException {
        statement.setInt(1, transactions.getId());
        statement.setString(2, transactions.getUuid());
        statement.setString(3, transactions.getAmount());
        statement.setString(4, transactions.getCurrency());
        statement.setString(5, transactions.getGateway());
        statement.setString(6, transactions.getGateway_reference());
        statement.setString(7, transactions.getManufacturer());
        statement.setString(8, transactions.getTransaction_id());
    }

    public static void prepareInsertFraudEventAddress(BillingAddressDTO address, PreparedStatement statement)
            throws SQLException {
        statement.setInt(1, address.getId());
        statement.setString(2, address.getCity());
        statement.setString(3, address.getStreet());
        statement.setString(4, address.getCountry());
        statement.setString(5, address.getLatitude());
        statement.setString(6, address.getLongitude());
        statement.setString(7, address.getPostal_code());
        statement.setString(7, address.getAddress());
    }

    public static void prepareInsertFraudEventAddressEdge(String userId, int addressId, String longitude, String latitude, PreparedStatement statement)
            throws SQLException {
        statement.setString(1, userId);
        statement.setInt(2, addressId);
        statement.setString(3, longitude);
        statement.setString(4, latitude);
    }

    public static void prepareInsertFraudEventDevice(DeviceDTO device, PreparedStatement statement)
            throws SQLException {
        statement.setString(1, device.getDevice_id());
        statement.setString(2, device.getUuid());
        statement.setString(3, device.getManufacturer());
        statement.setString(4, device.getDevice_type());
        statement.setString(5, device.getOs());
        statement.setString(6, device.getDevice_model());
        statement.setString(7, device.getUser_agent());
        statement.setString(8, device.getIp_address());
    }

    public static void prepareInsertFraudEventPayment(PaymentDTO payment, PreparedStatement statement)
            throws SQLException {
        statement.setString(1, payment.getPayment_method_id());
        statement.setString(2, payment.getMethod_type());
        statement.setString(3, payment.getCard_last_four());
        statement.setString(4, payment.getExpiry_month());
        statement.setString(5, payment.getExpiry_year());
        statement.setInt(6, payment.getId());
        statement.setString(7, payment.getUuid());

    }


    public static void prepareInsertFraudEventTimestamp(String timestamp, PreparedStatement statement)
            throws SQLException {
        statement.setString(1, timestamp);
        statement.setString(2, timestamp);
    }

    public static void prepareInsertFraudEventDeviceEdge(String userId, String deviceId, Timestamp createdAt, String ipAddress, PreparedStatement statement) throws SQLException {
        statement.setString(1, userId);
        statement.setString(2, deviceId);
        statement.setTimestamp(3, createdAt);
        statement.setString(4, ipAddress);
    }


    public static void prepareInsertFraudEventPaymentEdge(String userId, String paymentId, Timestamp createdAt, PreparedStatement statement) throws SQLException {
        statement.setString(1, userId);
        statement.setString(2, paymentId);
        statement.setTimestamp(3, createdAt);
    }

    public static void prepareInsertFraudEventTransactionEdge(String userId, String transactionId, Timestamp createdAt, PreparedStatement statement) throws SQLException {
        statement.setString(1, userId);
        statement.setString(2, transactionId);
        statement.setTimestamp(3, createdAt);
    }

    public static void prepareInsertFraudEventUserEdge(String userId, String transactionId, String email, PreparedStatement statement) throws SQLException {
        statement.setString(1, userId);
        statement.setString(2, transactionId);
        statement.setString(3, email);
    }
}
