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

import java.sql.Timestamp;

/**
 * @author Brume
 **/
@Data
@Builder
public class BillingAddressDTO {
    private Integer id;
    private Timestamp created_at;
    private Timestamp updated_at;
    private Timestamp deleted_at;
    private Integer business_id;
    private Integer event_id;
    private String uuid;
    private String region;
    private String city;
    private String street;
    private String address;
    private String longitude;
    private String latitude;
    private String postal_code;
    private String country;
}
