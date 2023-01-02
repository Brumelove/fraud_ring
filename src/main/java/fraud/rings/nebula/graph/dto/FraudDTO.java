/*
 *
 *  * Copyright (c) 2022. Omni Data Science Inc and it's subsidiaries - All Rights Reserved
 *  *  Unauthorized copying of this file, via any medium is strictly prohibited Proprietary and
 *  *  confidential
 *  * Written by Ebot Tabi <e@voyancehq.com>, 2/3/2022
 *
 *
 *
 */

package fraud.rings.nebula.graph.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author Brume
 **/
@Data
@Builder
public class FraudDTO {
    private BillingAddressDTO billingAddress;
    private DeviceDTO device;
    private LocationDTO location;
    private PaymentDTO payment;
    private TransactionDTO transaction;
    private UserDTO user;
}
