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
package org.eclipse.scout.example.hibernate.ui.swt.application;

import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;


/** <h3>ApplicationWorkbenchWindowAdvisor</h3>
 *  Used for workbench window behaviors.
 */
public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {


	public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
		super(configurer);
	}

  @Override
	public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
		return new ApplicationActionBarAdvisor(configurer);
	}

  @Override
	public void preWindowOpen() {
		IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
		configurer.setInitialSize(new Point(1024, 800));
		configurer.setShowCoolBar(false);
		configurer.setShowStatusLine(true);
		configurer.setShowProgressIndicator(true);
		configurer.setShowMenuBar(true);
		configurer.setShowPerspectiveBar(false);
	}
}
