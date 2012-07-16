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
package org.eclipse.scout.example.hibernate.server;

import javax.security.auth.Subject;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

import org.eclipse.scout.rt.server.scheduler.Scheduler;
import org.eclipse.scout.commons.security.SimplePrincipal;

public class Activator extends Plugin{


  public static String PLUGIN_ID="org.eclipse.scout.example.hibernate.server";
  // The shared instance
  private static Activator plugin;

  /**
   * Returns the shared instance
   * @return the shared instance
   */
  public static Activator getDefault() {
    return plugin;
  }


  private Scheduler m_scheduler;
  /**
   * The subject used for backend activity, independent of any (client) user
   */
  private Subject m_subject;

  /**
   * The constructor
   */
  public Activator() {
  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
   */
  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    plugin = this;
    //create the backend subject
    m_subject=new Subject();
    m_subject.getPrincipals().add(new SimplePrincipal("server"));
  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
   */
  @Override
  public void stop(BundleContext context) throws Exception {
    if(m_scheduler!=null){
      m_scheduler.stop();
      m_scheduler=null;
    }
    plugin = null;
    super.stop(context);
  }

  public Subject getBackendSubject() {
    return m_subject;
  }

  public Scheduler getScheduler() {
    return m_scheduler;
  }

  public void setScheduler(Scheduler scheduler) {
    m_scheduler=scheduler;
  }

}
