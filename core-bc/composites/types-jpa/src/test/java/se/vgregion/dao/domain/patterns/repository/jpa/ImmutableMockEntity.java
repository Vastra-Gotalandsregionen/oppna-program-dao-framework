/**
 * Copyright 2010 Västra Götalandsregionen
 *
 *   This library is free software; you can redistribute it and/or modify
 *   it under the terms of version 2.1 of the GNU Lesser General Public
 *   License as published by the Free Software Foundation.
 *
 *   This library is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU Lesser General Public License for more details.
 *
 *   You should have received a copy of the GNU Lesser General Public
 *   License along with this library; if not, write to the
 *   Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 *   Boston, MA 02111-1307  USA
 *
 */
package se.vgregion.dao.domain.patterns.repository.jpa;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import se.vgregion.dao.domain.patterns.entity.AbstractEntity;
import se.vgregion.dao.domain.patterns.entity.EntityBuilder;

@Entity
@Table(name = "vgr_test_entity")
public class ImmutableMockEntity extends AbstractEntity<Long> implements Serializable {

    public static class MockBuilder implements EntityBuilder<MockBuilder, ImmutableMockEntity> {
 
        private ImmutableMockEntity entity = new ImmutableMockEntity();

        public MockBuilder() {
        }
        
        public MockBuilder(ImmutableMockEntity other) {
            entity.id = other.id;
            entity.name = other.name;
        }
        
        public MockBuilder setName(String name) {
            entity.name = name;
            return this;
        }
        
        public ImmutableMockEntity build() {
            return entity;
        }
    }
    
    private static final long serialVersionUID = 5067859147916759914L;

    // Make JPA happy
    protected ImmutableMockEntity() { }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public ImmutableMockEntity(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
