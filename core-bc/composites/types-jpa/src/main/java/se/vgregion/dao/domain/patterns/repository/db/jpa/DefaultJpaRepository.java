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
package se.vgregion.dao.domain.patterns.repository.db.jpa;

import java.io.Serializable;

import se.vgregion.dao.domain.patterns.entity.Entity;

/**
 * A convenient implementation of JpaRepository where Entity ID and database primary key(PK) are equal and of type
 * Long. Use this when PK=ID to get find(ID) and remove(ID) implemented.
 * 
 * @param T
 *            the Entity Type
 * @param ID  the Identity type of the Entity
 * 
 * @author Anders Asplund - <a href="http://www.callistaenterprise.se">Callista Enterprise</a>
 */
public class DefaultJpaRepository<T extends Entity<ID>, ID extends Serializable> extends
        AbstractJpaRepository<T, ID, ID> {

    public DefaultJpaRepository() {
    }

    public DefaultJpaRepository(Class<? extends T> type) {
        super(type);
    }

    /**
     * {@inheritDoc}
     */
    public T find(ID id) {
        return findByPrimaryKey(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(ID id) {
        removeByPrimaryKey(id);
    }
}
