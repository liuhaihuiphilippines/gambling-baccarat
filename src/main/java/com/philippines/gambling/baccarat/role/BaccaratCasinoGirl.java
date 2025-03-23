/**
 * Copyright (c) 2025 (Philippines) All Rights Reserved.
*/
package com.philippines.gambling.baccarat.role;

import lombok.Builder;
import lombok.Data;

/**
 * @author winter
 * @date 6 Feb 2025 1:50:27 am
 */
@Data
@Builder
public class BaccaratCasinoGirl {

	private String casinoGirlName;
	private String casinoTableName;
	private String workingTimeStart;
	private String workingTimeEnd;
}

