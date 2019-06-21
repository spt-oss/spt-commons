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

import org.hibernate.boot.model.naming.Identifier; // TODO @checkstyle:ignore
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment; // TODO @checkstyle:ignore
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;

/**
 * Custom {@link SpringPhysicalNamingStrategy}
 */
public class CustomSpringPhysicalNamingStrategy extends SpringPhysicalNamingStrategy {
	
	@Override
	public Identifier toPhysicalCatalogName(Identifier name, JdbcEnvironment jdbcEnvironment) {
		
		return super.toPhysicalCatalogName(this.apply(name), jdbcEnvironment);
	}
	
	@Override
	public Identifier toPhysicalSchemaName(Identifier name, JdbcEnvironment jdbcEnvironment) {
		
		return super.toPhysicalSchemaName(this.apply(name), jdbcEnvironment);
	}
	
	@Override
	public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment jdbcEnvironment) {
		
		return super.toPhysicalTableName(this.apply(name), jdbcEnvironment);
	}
	
	@Override
	public Identifier toPhysicalSequenceName(Identifier name, JdbcEnvironment jdbcEnvironment) {
		
		return super.toPhysicalSequenceName(this.apply(name), jdbcEnvironment);
	}
	
	@Override
	public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment jdbcEnvironment) {
		
		return super.toPhysicalColumnName(this.apply(name), jdbcEnvironment);
	}
	
	/**
	 * Apply
	 * 
	 * @param name {@link Identifier}
	 * @return {@link Identifier}
	 */
	protected Identifier apply(Identifier name) {
		
		if (name == null) {
			
			return null;
		}
		
		// Convert "[UC][UC]" to "[UC]_[UC]"
		StringBuilder buffer = new StringBuilder(name.getText());
		
		for (int i = 1; i < buffer.length() - 1; i++) {
			
			// XYz => X_Yz
			if (Character.isUpperCase(buffer.charAt(i - 1)) && Character.isUpperCase(buffer.charAt(i))
				&& Character.isLowerCase(buffer.charAt(i + 1))) {
				
				buffer.insert(i++, '_');
			}
			
			// xYZ => x_YZ
			else if (Character.isLowerCase(buffer.charAt(i - 1)) && Character.isUpperCase(buffer.charAt(i))
				&& Character.isUpperCase(buffer.charAt(i + 1))) {
				
				buffer.insert(i++, '_');
			}
		}
		
		return new Identifier(buffer.toString(), name.isQuoted());
	}
}
