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

package spt.commons.json;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.NonNull;

/**
 * {@link ObjectMapper} utilities
 */
public class ObjectMappers {
	
	/**
	 * {@link Logger}
	 */
	private static final Logger logger = LoggerFactory.getLogger(ObjectMappers.class);
	
	/**
	 * Constructor
	 */
	protected ObjectMappers() {
		
		/* NOP */
	}
	
	/**
	 * Serialize
	 * 
	 * @param mapper {@link ObjectMapper}
	 * @param value value
	 * @return JSON
	 * @throws UncheckedIOException if failed to serialize
	 */
	public static String serialize(@NonNull ObjectMapper mapper, Object value) throws UncheckedIOException {
		
		try {
			
			return mapper.writeValueAsString(value);
		}
		catch (JsonProcessingException e) {
			
			throw new UncheckedIOException("Failed to serialize", e);
		}
	}
	
	/**
	 * Deserialize
	 * 
	 * @param mapper {@link ObjectMapper}
	 * @param json JSON
	 * @param clazz {@link Class}
	 * @param fallback fallback
	 * @param <T> value type
	 * @return value
	 */
	public static <T> T deserialize(@NonNull ObjectMapper mapper, String json, Class<T> clazz,
		@NonNull Supplier<T> fallback) {
		
		try {
			
			return mapper.readValue(json, clazz);
		}
		catch (NullPointerException | IOException e) {
			
			logger.warn("Failed to deserialize as class '{}': {}", clazz, json, e);
			
			return fallback.get();
		}
	}
	
	/**
	 * Deserialize
	 * 
	 * @param mapper {@link ObjectMapper}
	 * @param json JSON
	 * @param reference {@link TypeReference}
	 * @param fallback fallback
	 * @param <T> value type
	 * @return value
	 */
	public static <T> T deserialize(@NonNull ObjectMapper mapper, String json, TypeReference<T> reference,
		@NonNull Supplier<T> fallback) {
		
		try {
			
			return mapper.readValue(json, reference);
		}
		catch (NullPointerException | IOException e) {
			
			logger.warn("Failed to deserialize as type '{}': {}", reference, json, e);
			
			return fallback.get();
		}
	}
}
