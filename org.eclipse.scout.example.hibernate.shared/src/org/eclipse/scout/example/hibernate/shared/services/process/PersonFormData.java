package org.eclipse.scout.example.hibernate.shared.services.process;

import org.eclipse.scout.rt.shared.data.form.ValidationRule;
import org.eclipse.scout.rt.shared.data.form.fields.AbstractValueFieldData;
import org.eclipse.scout.rt.shared.data.form.AbstractFormData;
import org.eclipse.scout.rt.shared.data.form.properties.AbstractPropertyData;

public class PersonFormData extends AbstractFormData {
  private static final long serialVersionUID = 1L;

  public PersonFormData() {
  }

  public PersonNrProperty getPersonNrProperty() {
    return getPropertyByClass(PersonNrProperty.class);
  }

  /**
   * access method for property PersonNr.
   */
  public Long getPersonNr() {
    return getPersonNrProperty().getValue();
  }

  /**
   * access method for property PersonNr.
   */
  public void setPersonNr(Long personNr) {
    getPersonNrProperty().setValue(personNr);
  }

  public Name getName() {
    return getFieldByClass(Name.class);
  }

  public Prename getPrename() {
    return getFieldByClass(Prename.class);
  }

  public ThrowException getThrowException() {
    return getFieldByClass(ThrowException.class);
  }

  public class PersonNrProperty extends AbstractPropertyData<Long> {
    private static final long serialVersionUID = 1L;

    public PersonNrProperty() {
    }
  }

  public static class Name extends AbstractValueFieldData<String> {
    private static final long serialVersionUID = 1L;

    public Name() {
    }

    /**
     * list of derived validation rules.
     */
    @Override
    protected void initValidationRules(java.util.Map<String, Object> ruleMap) {
      super.initValidationRules(ruleMap);
      ruleMap.put(ValidationRule.MAX_LENGTH, 4000);
    }
  }

  public static class Prename extends AbstractValueFieldData<String> {
    private static final long serialVersionUID = 1L;

    public Prename() {
    }

    /**
     * list of derived validation rules.
     */
    @Override
    protected void initValidationRules(java.util.Map<String, Object> ruleMap) {
      super.initValidationRules(ruleMap);
      ruleMap.put(ValidationRule.MAX_LENGTH, 4000);
    }
  }

  public static class ThrowException extends AbstractValueFieldData<Boolean> {
    private static final long serialVersionUID = 1L;

    public ThrowException() {
    }
  }
}
