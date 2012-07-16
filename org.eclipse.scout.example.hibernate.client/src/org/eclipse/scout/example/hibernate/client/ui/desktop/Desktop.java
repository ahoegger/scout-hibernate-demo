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
package org.eclipse.scout.example.hibernate.client.ui.desktop;


import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.logger.IScoutLogger;
import org.eclipse.scout.commons.logger.ScoutLogManager;
import org.eclipse.scout.example.hibernate.client.ClientSession;
import org.eclipse.scout.example.hibernate.client.ui.forms.DesktopForm;
import org.eclipse.scout.example.hibernate.shared.Icons;
import org.eclipse.scout.rt.client.ClientSyncJob;
import org.eclipse.scout.rt.client.ui.action.keystroke.AbstractKeyStroke;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.desktop.AbstractDesktop;
import org.eclipse.scout.rt.client.ui.desktop.IDesktop;
import org.eclipse.scout.rt.client.ui.desktop.bookmark.menu.AbstractBookmarkMenu;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.client.ui.form.ScoutInfoForm;
import org.eclipse.scout.rt.shared.TEXTS;

public class Desktop extends AbstractDesktop implements IDesktop{
  private static IScoutLogger logger = ScoutLogManager.getLogger(Desktop.class);

  public Desktop(){
  }

  @Override
  protected String getConfiguredTitle(){
    return TEXTS.get("ApplicationTitle");
  }

  @Override
  protected void execOpened() throws ProcessingException {
    // desktop form
    DesktopForm desktopForm = new DesktopForm();
    desktopForm.setIconId(Icons.EclipseScout);
    desktopForm.startView();
  }

  @Order(10.0)
  public class FileMenu extends AbstractMenu{

    @Override
    protected String getConfiguredText(){
      return TEXTS.get("FileMenu");
    }

    @Order(100.0)
    public class ExitMenu extends AbstractMenu{

      @Override
      protected String getConfiguredText(){
        return TEXTS.get("ExitMenu");
      }

      @Override
      public void execAction() throws ProcessingException{
        ClientSyncJob.getCurrentSession(ClientSession.class).stopSession();
      }
    }
  }

  @Order(20.0)
  public class ToolsMenu extends AbstractMenu{

    @Override
    protected String getConfiguredText(){
      return TEXTS.get("ToolsMenu");
    }
  }

  @Order(25)
  public class BookmarkMenu extends AbstractBookmarkMenu{
    public BookmarkMenu(){
      super(Desktop.this);
    }
  }

  @Order(30.0)
  public class HelpMenu extends AbstractMenu{

    @Override
    protected String getConfiguredText(){
      return TEXTS.get("HelpMenu");
    }

    @Order(10.0)
    public class AboutMenu extends AbstractMenu{

      @Override
      protected String getConfiguredText(){
        return TEXTS.get("AboutMenu");
      }

      @Override
      public void execAction() throws ProcessingException{
        ScoutInfoForm form=new ScoutInfoForm();
        form.startModify();
      }
    }
  }

  @Order(10.0)
  public class RefreshOutlineKeyStroke extends AbstractKeyStroke {

    @Override
    protected String getConfiguredKeyStroke() {
      return "f5";
    }

    @Override
    protected void execAction() throws ProcessingException {
      if (getOutline() != null) {
        IPage page = getOutline().getActivePage();
        if (page != null) {
          page.reloadPage();
        }
      }
    }
  }
}
