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
package org.eclipse.scout.example.hibernate.dao.internal;

import org.eclipse.scout.rt.server.transaction.AbstractTransactionMember;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * 
 */
public class HibernateTransactionMember extends AbstractTransactionMember {

  Transaction tx;
  private Transaction m_transaction;
  private Session m_hibernateSession;

  /**
   * @param transactionMemberId
   */
  public HibernateTransactionMember(String transactionId, Session hibernateSession, Transaction transaction) {
    super(transactionId);
    m_hibernateSession = hibernateSession;
    m_transaction = transaction;
  }

  @Override
  public boolean needsCommit() {

    return true;
  }

  @Override
  public boolean commitPhase1() {
    m_transaction.commit();
    return true;
  }

  @Override
  public void commitPhase2() {
  }

  @Override
  public void rollback() {
    m_transaction.rollback();
  }

  @Override
  public void release() {
    // void 
  }

  public Session getHibernateSession() {
    return m_hibernateSession;
  }

  public Transaction getTransaction() {
    return m_transaction;
  }

}
