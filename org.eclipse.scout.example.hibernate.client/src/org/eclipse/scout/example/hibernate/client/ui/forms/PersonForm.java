package org.eclipse.scout.example.hibernate.client.ui.forms;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.FormData.SdkCommand;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.example.hibernate.client.ui.forms.PersonForm.MainBox.CancelButton;
import org.eclipse.scout.example.hibernate.client.ui.forms.PersonForm.MainBox.DebugBox;
import org.eclipse.scout.example.hibernate.client.ui.forms.PersonForm.MainBox.DebugBox.ThrowExceptionField;
import org.eclipse.scout.example.hibernate.client.ui.forms.PersonForm.MainBox.NameField;
import org.eclipse.scout.example.hibernate.client.ui.forms.PersonForm.MainBox.OkButton;
import org.eclipse.scout.example.hibernate.client.ui.forms.PersonForm.MainBox.PrenameField;
import org.eclipse.scout.example.hibernate.shared.security.UpdatePersonPermission;
import org.eclipse.scout.example.hibernate.shared.services.process.IPersonProcessService;
import org.eclipse.scout.example.hibernate.shared.services.process.PersonFormData;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.booleanfield.AbstractBooleanField;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.service.SERVICES;

@FormData(value = PersonFormData.class, sdkCommand = SdkCommand.CREATE)
public class PersonForm extends AbstractForm {

  private Long personNr;

  public PersonForm() throws ProcessingException {
    super();
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Person");
  }

  public CancelButton getCancelButton() {
    return getFieldByClass(CancelButton.class);
  }

  @FormData
  public Long getPersonNr() {
    return personNr;
  }

  @FormData
  public void setPersonNr(Long personNr) {
    this.personNr = personNr;
  }

  public void startModify() throws ProcessingException {
    startInternal(new ModifyHandler());
  }

  public void startNew() throws ProcessingException {
    startInternal(new NewHandler());
  }

  public DebugBox getDebugBox() {
    return getFieldByClass(DebugBox.class);
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  public NameField getNameField() {
    return getFieldByClass(NameField.class);
  }

  public OkButton getOkButton() {
    return getFieldByClass(OkButton.class);
  }

  public PrenameField getPrenameField() {
    return getFieldByClass(PrenameField.class);
  }

  public ThrowExceptionField getThrowExceptionField() {
    return getFieldByClass(ThrowExceptionField.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Order(10.0)
    public class NameField extends AbstractStringField {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Name");
      }
    }

    @Order(20.0)
    public class PrenameField extends AbstractStringField {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Prename");
      }
    }

    @Order(40.0)
    public class DebugBox extends AbstractGroupBox {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Debug");
      }

      @Order(10.0)
      public class ThrowExceptionField extends AbstractBooleanField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("ThrowException");
        }
      }
    }

    @Order(50.0)
    public class OkButton extends AbstractOkButton {
    }

    @Order(60.0)
    public class CancelButton extends AbstractCancelButton {
    }
  }

  public class ModifyHandler extends AbstractFormHandler {

    @Override
    public void execLoad() throws ProcessingException {
      IPersonProcessService service = SERVICES.getService(IPersonProcessService.class);
      PersonFormData formData = new PersonFormData();
      exportFormData(formData);
      formData = service.load(formData);
      importFormData(formData);
      setEnabledPermission(new UpdatePersonPermission());
    }

    @Override
    public void execStore() throws ProcessingException {
      IPersonProcessService service = SERVICES.getService(IPersonProcessService.class);
      PersonFormData formData = new PersonFormData();
      exportFormData(formData);
      formData = service.store(formData);
    }
  }

  public class NewHandler extends AbstractFormHandler {

    @Override
    public void execLoad() throws ProcessingException {
      IPersonProcessService service = SERVICES.getService(IPersonProcessService.class);
      PersonFormData formData = new PersonFormData();
      exportFormData(formData);
      formData = service.prepareCreate(formData);
      importFormData(formData);
    }

    @Override
    public void execStore() throws ProcessingException {
      IPersonProcessService service = SERVICES.getService(IPersonProcessService.class);
      PersonFormData formData = new PersonFormData();
      exportFormData(formData);
      formData = service.create(formData);
    }
  }
}
