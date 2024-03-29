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

import java.io.Serializable;
import java.util.Optional;

import javax.persistence.Id;
import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.transaction.annotation.Transactional;

/**
 * Custom {@link JpaRepository}
 * 
 * @param <T> entity type
 * @param <I> ID type
 */
public interface CustomJpaRepository<T, I extends Serializable>
	extends JpaRepository<T, I>, JpaSpecificationExecutor<T> {
	
	/**
	 * Find first by
	 * 
	 * @return entity
	 */
	Optional<T> findFirstBy();
	
	/**
	 * Find for update by {@link Id}
	 * 
	 * @param id {@link Id}
	 * @return entity
	 */
	@Transactional
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	Optional<T> findForUpdateById(I id);
}
