
package spt.commons.java.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

/**
 * Custom {@link String}
 */
public class Randoms {
	
	/**
	 * {@link Random}
	 */
	public static final Random INSTANCE;
	
	static {
		
		try {
			
			INSTANCE = SecureRandom.getInstanceStrong();
		}
		catch (NoSuchAlgorithmException e) {
			
			throw new IllegalStateException("Failed to get Random instance", e);
		}
	}
	
	/**
	 * Constructor
	 */
	protected Randoms() {
		
		/* NOP */
	}
}
