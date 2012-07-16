package org.eclipse.scout.example.hibernate.client.ui.forms;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.FormData.SdkCommand;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.example.hibernate.client.ui.forms.DesktopForm.MainBox.PersonTableField;
import org.eclipse.scout.example.hibernate.shared.services.process.DesktopFormData;
import org.eclipse.scout.example.hibernate.shared.services.process.IDesktopProcessService;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractLongColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.tablefield.AbstractTableField;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.service.SERVICES;

@FormData(value = DesktopFormData.class, sdkCommand = SdkCommand.CREATE)
public class DesktopForm extends AbstractForm {

  public DesktopForm() throws ProcessingException {
    super();
  }

  @Override
  protected boolean getConfiguredAskIfNeedSave() {
    return false;
  }

  @Override
  protected int getConfiguredDisplayHint() {
    return DISPLAY_HINT_VIEW;
  }

  @Override
  protected String getConfiguredDisplayViewId() {
    return VIEW_ID_CENTER;
  }

  @Override
  protected String getConfiguredIconId() {
    return org.eclipse.scout.example.hibernate.shared.Icons.EclipseScout;
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  public PersonTableField getPersonTableField() {
    return getFieldByClass(PersonTableField.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Order(10.0)
    public class PersonTableField extends AbstractTableField<PersonTableField.Table> {

      @Override
      protected boolean getConfiguredLabelVisible() {
        return false;
      }

      @Order(10.0)
      public class Table extends AbstractTable {

        public NameColumn getNameColumn() {
          return getColumnSet().getColumnByClass(NameColumn.class);
        }

        public PrenameColumn getPrenameColumn() {
          return getColumnSet().getColumnByClass(PrenameColumn.class);
        }

        @Override
        protected String getConfiguredDefaultIconId() {
          return org.eclipse.scout.example.hibernate.shared.Icons.Eye;
        }

        public IdColumn getIdColumn() {
          return getColumnSet().getColumnByClass(IdColumn.class);
        }

        @Order(10.0)
        public class IdColumn extends AbstractLongColumn {

          @Override
          protected boolean getConfiguredDisplayable() {
            return false;
          }

          @Override
          protected boolean getConfiguredVisible() {
            return false;
          }
        }

        @Order(20.0)
        public class NameColumn extends AbstractStringColumn {

          @Override
          protected String getConfiguredHeaderText() {
            return TEXTS.get("Name");
          }

          @Override
          protected int getConfiguredWidth() {
            return 200;
          }
        }

        @Order(30.0)
        public class PrenameColumn extends AbstractStringColumn {

          @Override
          protected String getConfiguredHeaderText() {
            return TEXTS.get("Prename");
          }

          @Override
          protected int getConfiguredWidth() {
            return 200;
          }
        }

        @Order(10.0)
        public class EditMenu extends AbstractMenu {

          @Override
          protected String getConfiguredText() {
            return TEXTS.get("EditFilterMenu");
          }

          @Override
          protected void execAction() throws ProcessingException {
            PersonForm form = new PersonForm();
            Long personId = getIdColumn().getSelectedValue();
            if (personId != null) {
              form.setPersonNr(personId);
              form.startModify();
              form.waitFor();
              if (form.isFormStored()) {
                reloadPersonTable();
              }
            }

          }

        }

        @Order(20.0)
        public class NewMenu extends AbstractMenu {

          @Override
          protected String getConfiguredText() {
            return TEXTS.get("New");
          }

          @Override
          protected boolean getConfiguredEmptySpaceAction() {
            return true;
          }

          @Override
          protected void execAction() throws ProcessingException {
            PersonForm form = new PersonForm();
            form.startNew();
            form.waitFor();
            if (form.isFormStored()) {
              reloadPersonTable();
            }
          }
        }
      }
    }

  }

  /**
   * @throws ProcessingException
   */
  private void reloadPersonTable() throws ProcessingException {
    IDesktopProcessService service = SERVICES.getService(IDesktopProcessService.class);
    DesktopFormData formData = new DesktopFormData();
    exportFormData(formData);
    formData = service.load(formData);
    importFormData(formData);
  }

  public class ViewHandler extends AbstractFormHandler {

    @Override
    protected void execLoad() throws ProcessingException {
      reloadPersonTable();

    }
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Persons");
  }

  public void startView() throws ProcessingException {
    startInternal(new ViewHandler());
  }
}
