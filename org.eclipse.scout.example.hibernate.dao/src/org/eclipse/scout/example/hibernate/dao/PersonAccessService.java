/*******************************************************************************
 * Copyright (c) 2010 BSI Business Systems Integration AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     BSI Business Systems Integration AG - initial API and implementation
 ******************************************************************************/
package org.eclipse.scout.example.hibernate.dao;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.example.hibernate.dao.internal.SessionUtil;
import org.eclipse.scout.example.hibernate.entity.Person;
import org.eclipse.scout.example.hibernate.server.dao.IPersonAccessService;
import org.eclipse.scout.service.AbstractService;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * 
 */
public class PersonAccessService extends AbstractService implements IPersonAccessService {

  @Override
  public List<Person> getAllPersons() throws ProcessingException {
    Session session = SessionUtil.getCurrentSession();
    LinkedList<Person> persons = new LinkedList<Person>();
    Query q = session.createQuery("from Person");
    List honeys = q.list();
    for (java.util.Iterator<?> iter = honeys.iterator(); iter.hasNext();) {
      Person element = (Person) iter.next();
      persons.add(element);

    }
    return persons;
  }

  @Override
  public Person getPersonById(Long id) throws ProcessingException {
    Session session = SessionUtil.getCurrentSession();
    Person person = (Person) session.load(Person.class, id);
    return person;
  }

  @Override
  public void updatePerson(Person person) throws ProcessingException {
    Session session = SessionUtil.getCurrentSession();
    session.update(person);
  }

  @Override
  public void createPerson(Person person) throws ProcessingException {
    Session session = SessionUtil.getCurrentSession();
    session.save(person);
  }
}
