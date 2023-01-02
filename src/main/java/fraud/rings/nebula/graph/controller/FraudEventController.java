package fraud.rings.nebula.graph.controller;

import fraud.rings.nebula.graph.dto.FraudDTO;
import fraud.rings.nebula.graph.services.FraudEventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

;

/**
 * @author Brume
 **/
@Slf4j
@RequiredArgsConstructor
public class FraudEventController {

    private final FraudEventService fraudEventService;

    // For test purpose. inserting sample data into db
    @PostMapping
    public ResponseEntity<Void> createFraudEvent(@RequestBody FraudDTO fraudDTO) {
        try {
            fraudEventService.processKGFraudEvents(fraudDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public void get() {

    }
}
