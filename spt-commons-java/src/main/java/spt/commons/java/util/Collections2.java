/*
 * Copyright 2018-2019 the original author or authors.
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

package spt.commons.java.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * {@link Collections} 2
 */
public class Collections2 {
	
	/**
	 * {@link Random}
	 */
	public static final Random RANDOM;
	
	static {
		
		try {
			
			RANDOM = SecureRandom.getInstanceStrong();
		}
		catch (NoSuchAlgorithmException e) {
			
			throw new IllegalStateException("Failed to instantiate Random", e);
		}
	}
	
	/**
	 * Constructor
	 */
	protected Collections2() {
		
		/* NOP */
	}
	
	/**
	 * Shuffle
	 * 
	 * @param values values
	 */
	public static void shuffle(List<?> values) {
		
		Collections.shuffle(values, RANDOM);
	}
}
