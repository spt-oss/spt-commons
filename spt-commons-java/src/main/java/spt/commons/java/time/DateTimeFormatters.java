/*
 * Copyright 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package spt.commons.java.time;

import java.time.format.DateTimeFormatter;

/**
 * {@link DateTimeFormatter} utilities
 */
public class DateTimeFormatters {
	
	/**
	 * Constructor
	 */
	protected DateTimeFormatters() {
		
		/* NOP */
	}
	
	/**
	 * {@link DateTimeFormatter#ISO_LOCAL_DATE_TIME}: Without nanos
	 */
	public static final DateTimeFormatter ISO_LOCAL_DATE_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
}
