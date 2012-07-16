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
package org.eclipse.scout.example.hibernate.server.dao;

import java.util.List;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.example.hibernate.entity.Person;
import org.eclipse.scout.service.IService2;

/**
 * 
 */
public interface IPersonAccessService extends IService2 {

  /**
   * @return
   * @throws ProcessingException
   */
  List<Person> getAllPersons() throws ProcessingException;

  /**
   * @param id
   * @return
   * @throws ProcessingException
   */
  Person getPersonById(Long id) throws ProcessingException;

  /**
   * @param person
   */
  void updatePerson(Person person) throws ProcessingException;

  /**
   * @param person
   */
  void createPerson(Person person) throws ProcessingException;

}
