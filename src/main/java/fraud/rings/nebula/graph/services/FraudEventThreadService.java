package fraud.rings.nebula.graph.services;

import com.voyance.sigma.common.threads.ThreadUtils;
import com.voyance.sigma.common.util.JSON;
import com.voyance.sigma.db.dto.event.EventDTO;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;

/**
 * @author Brume
 **/
@Slf4j
public class FraudEventThreadService {
    protected static final ExecutorService schedulerService = ThreadUtils.newDaemonSingleThreadExecutor("Fraud-event-KG-Scheduler-Thread");

    protected static Runnable processKDFraudEventThread(EventDTO p) {
        return () -> {
            FraudEventService service = new FraudEventService();
            log.info("prepping");
            try {
                log.info("processing Fraud event now.");
                service.processKGFraudEvents(p);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

    public void processKGFraudEvent(EventDTO p) {
        log.info("Fraud Event: {}", JSON.pretty(p));
        schedulerService.execute(processKDFraudEventThread(p));
    }
}
