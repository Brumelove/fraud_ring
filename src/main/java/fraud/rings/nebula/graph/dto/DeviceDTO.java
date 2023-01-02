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
public class DeviceDTO {
    private Integer id;
    private Timestamp created_at;
    private Timestamp updated_at;
    private Timestamp deleted_at;
    private Integer business_id;
    private Integer event_id;
    private String uuid;
    private String manufacturer;
    private String device_type;
    private String os;
    private String device_model;
    private String user_agent;
    private String ip_address;
    private String device_id;
}
