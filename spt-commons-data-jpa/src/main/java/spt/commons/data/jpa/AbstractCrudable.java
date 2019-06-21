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
package spt.commons.data.jpa;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

import javax.persistence.MappedSuperclass;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import javax.persistence.Version;

import lombok.Data;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Abstract {@link Crudable}
 */
@MappedSuperclass
@Data
@Accessors(chain = true)
public abstract class AbstractCrudable implements Crudable {
	
	/**
	 * Unix epoch
	 */
	private static final LocalDateTime UNIX_EPOCH = LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.UTC);
	
	/**
	 * {@link Crudable#isPersistent()}
	 */
	@Setter
	@Transient
	private boolean persistent;
	
	/**
	 * {@link Crudable#getCreatedDate()}
	 */
	private LocalDateTime createdDate;
	
	/**
	 * {@link Crudable#getLastModifiedDate()}
	 */
	private LocalDateTime lastModifiedDate;
	
	/**
	 * {@link Crudable#getVersion()}
	 */
	@Version
	private Long version;
	
	@Override
	public LocalDateTime getCreatedDate() {
		
		return Optional.ofNullable(this.createdDate).orElseGet(() -> UNIX_EPOCH);
	}
	
	@Override
	public LocalDateTime getLastModifiedDate() {
		
		return Optional.ofNullable(this.lastModifiedDate).orElseGet(() -> UNIX_EPOCH);
	}
	
	/**
	 * {@link PostLoad}
	 */
	@PostLoad
	public void postLoad() {
		
		this.setPersistent(true);
	}
	
	/**
	 * {@link PrePersist}
	 */
	@PrePersist
	public void prePersist() {
		
		LocalDateTime now = LocalDateTime.now();
		
		this.setCreatedDate(Optional.ofNullable(this.createdDate).orElse(now));
		this.setLastModifiedDate(Optional.ofNullable(this.lastModifiedDate).orElse(now));
	}
	
	/**
	 * {@link PreUpdate}
	 */
	@PreUpdate
	public void preUpdate() {
		
		this.setLastModifiedDate(LocalDateTime.now());
	}
}
