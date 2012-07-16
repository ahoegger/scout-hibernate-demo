package org.eclipse.scout.example.hibernate.dao.internal;

import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.logger.IScoutLogger;
import org.eclipse.scout.commons.logger.ScoutLogManager;
import org.eclipse.scout.rt.server.ThreadContext;
import org.eclipse.scout.rt.server.transaction.ITransaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionUtil {

  private static IScoutLogger logger = ScoutLogManager.getLogger(SessionUtil.class);
  private static final SessionUtil instance = new SessionUtil();
  private static final String HIBERNATE_TRANSACTION_MEMBER_ID = SessionUtil.class.getName() + ".hibernateTransactionId";

  private SessionFactory sessionFactory;

  private SessionUtil() {
  }

  public static Session getCurrentSession() throws ProcessingException {
    return instance.getCurrentSessionImpl();
  }

  /**
   * @return
   * @throws ProcessingException
   */
  private Session getCurrentSessionImpl() throws ProcessingException {
    ITransaction transaction = ThreadContext.getTransaction();
    HibernateTransactionMember transacitonMember = (HibernateTransactionMember) transaction.getMember(HIBERNATE_TRANSACTION_MEMBER_ID);
    if (transacitonMember == null) {
      Session session = getSessionFactoryImpl().getCurrentSession();
      transacitonMember = new HibernateTransactionMember(HIBERNATE_TRANSACTION_MEMBER_ID, session, session.beginTransaction());
      transaction.registerMember(transacitonMember);
      return session;
    }
    else {
      return transacitonMember.getHibernateSession();
    }
  }

  private SessionFactory getSessionFactoryImpl() {
    if (sessionFactory == null) {
      try {
        URL configUrl = FileLocator
            .find(new URL(
                "platform:/plugin/org.eclipse.scout.example.hibernate.dao/hibernate.cfg.xml"));
        Configuration configuration = new Configuration();
        sessionFactory = configuration.configure(configUrl)
            .buildSessionFactory();
      }
      catch (Exception e) {
        logger.error("could not set up Hibernate SessionFactory.", e);
      }
    }
    return sessionFactory;
  }
}
