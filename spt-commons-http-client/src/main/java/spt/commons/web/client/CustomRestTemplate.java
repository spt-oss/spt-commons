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

package spt.commons.web.client;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.NonNull;

/**
 * Custom {@link RestTemplate}
 */
public class CustomRestTemplate extends RestTemplate implements CustomRestOperations {
	
	/**
	 * Get {@link ObjectMapper}
	 * 
	 * @param restTemplate {@link RestTemplate}
	 * @return {@link ObjectMapper}
	 * @throws IllegalStateException if not found
	 */
	public static ObjectMapper getObjectMapper(RestTemplate restTemplate) throws IllegalStateException {
		
		return getMappingJackson2HttpMessageConverter(restTemplate).getObjectMapper();
	}
	
	/**
	 * Get {@link MappingJackson2HttpMessageConverter}
	 * 
	 * @param restTemplate {@link RestTemplate}
	 * @return {@link MappingJackson2HttpMessageConverter}
	 * @throws IllegalStateException if not found
	 */
	public static MappingJackson2HttpMessageConverter getMappingJackson2HttpMessageConverter(
		@NonNull RestTemplate restTemplate) throws IllegalStateException {
		
		for (HttpMessageConverter<?> converter : restTemplate.getMessageConverters()) {
			
			if (converter instanceof MappingJackson2HttpMessageConverter) {
				
				return (MappingJackson2HttpMessageConverter) converter;
			}
		}
		
		throw new IllegalStateException("MappingJackson2HttpMessageConverter not found in RestTemplate");
	}
}
