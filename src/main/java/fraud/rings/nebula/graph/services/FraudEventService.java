package fraud.rings.nebula.graph.services;

import com.vesoft.nebula.client.graph.exception.IOErrorException;
import com.vesoft.nebula.jdbc.impl.NebulaResultSet;
import fraud.rings.nebula.graph.dao.impl.*;
import fraud.rings.nebula.graph.dao.interfaces.*;
import fraud.rings.nebula.graph.dto.FraudDTO;
import fraud.rings.nebula.graph.exception.GraphDatabaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * @author Brume
 **/
@Slf4j
@Service
public class FraudEventService {
    private final FraudEventUserDao fraudEventUserDao = new FraudEventUserDaoImpl();
    private final FraudEventTransactionsDao fraudEventTransactionsDao = new FraudEventTransactionsDaoImpl();
    private final FraudEventAddressDao fraudEventAddressDao = new FraudEventAddressDaoImpl();
    private final FraudEventDeviceDao fraudEventDeviceDao = new FraudEventDeviceDaoImpl();
    private final FraudEventPaymentDao fraudEventPaymentDao = new FraudEventPaymentDaoImpl();
    private final FraudGraphDao fraudGraphDao = new FraudGraphDaoImpl();

    public void processKGFraudEvents(FraudDTO p) throws GraphDatabaseException, InterruptedException, IOErrorException, GraphDatabaseException {
        processFraudEventSpace();
        processFraudEventTransactionTags();
        processFraudEventEdge();
        processFraudEventVertices(p);
        processFraudEventTransactionIndex();
        processFraudEventTransactionRebuildIndex();
//        FraudEvenTransactionsDao.testNebulaResult();
    }

    public NebulaResultSet getPath() throws GraphDatabaseException, IOErrorException, SQLException {
        return fraudEventTransactionsDao.testNebulaResult();
    }

    public void processFraudEventSpace() throws IOErrorException, InterruptedException {
        fraudGraphDao.createSpace();
        Thread.sleep(10000);

    }

    private void processFraudEventTransactionTags() throws GraphDatabaseException, InterruptedException {
        fraudEventUserDao.createTag();
        fraudEventTransactionsDao.createTag();
        fraudEventDeviceDao.createTag();
        fraudEventPaymentDao.createTag();
        fraudEventAddressDao.createTag();
        Thread.sleep(10000);
    }

    private void processFraudEventEdge() throws GraphDatabaseException, InterruptedException {
        fraudEventPaymentDao.createEdge();
        fraudEventDeviceDao.createEdge();
        fraudEventAddressDao.createEdge();
        fraudEventTransactionsDao.createEdge();
        Thread.sleep(10000);
    }

    private void processFraudEventVertices(FraudDTO p) throws GraphDatabaseException, InterruptedException {
        fraudEventUserDao.createVertex(p.getUser());
        fraudEventTransactionsDao.createVertex(p.getTransaction());
        fraudEventDeviceDao.createVertex(p.getDevice());
        fraudEventPaymentDao.createVertex(p.getPayment());
        fraudEventAddressDao.createVertex(p.getBillingAddress());
        Thread.sleep(10000);

        processInsertFraudEventEdge(p);
    }

    private void processInsertFraudEventEdge(FraudDTO fraudDTO) throws GraphDatabaseException, InterruptedException {
        String userId = fraudDTO.getUser().getUser_id().toString();

        fraudEventUserDao.insertEmailEdge(userId, fraudDTO.getTransaction().getTransaction_id(), fraudDTO.getUser().getEmail());
        fraudEventUserDao.insertPhoneNumberEdge(userId, fraudDTO.getTransaction().getTransaction_id(), fraudDTO.getUser().getMobile());
        fraudEventTransactionsDao.insertEdge(userId, fraudDTO.getTransaction().getTransaction_id(), fraudDTO.getTransaction().getCreated_at());
        fraudEventDeviceDao.insertEdge(userId, fraudDTO.getDevice().getDevice_id(), fraudDTO.getDevice().getCreated_at(), fraudDTO.getDevice().getIp_address());
        fraudEventPaymentDao.insertEdge(userId, fraudDTO.getPayment().getPayment_method_id(), fraudDTO.getPayment().getCreated_at());
        fraudEventAddressDao.insertEdge(userId, fraudDTO.getBillingAddress().getId(), fraudDTO.getBillingAddress().getLongitude(), fraudDTO.getBillingAddress().getLatitude());
        Thread.sleep(10000);
    }

    private void processFraudEventTransactionIndex() throws GraphDatabaseException, InterruptedException {
        fraudEventUserDao.createIndex();
        fraudEventTransactionsDao.createIndex();
        fraudEventDeviceDao.createIndex();
        fraudEventPaymentDao.createIndex();
        fraudEventAddressDao.createIndex();
        Thread.sleep(10000);
    }

    private void processFraudEventTransactionRebuildIndex() throws GraphDatabaseException {
        fraudEventUserDao.reBuildIndex();
        fraudEventTransactionsDao.reBuildIndex();
        fraudEventDeviceDao.reBuildIndex();
        fraudEventPaymentDao.reBuildIndex();
        fraudEventAddressDao.reBuildIndex();
    }
}
