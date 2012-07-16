package org.eclipse.scout.example.hibernate.server.services.process;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.exception.VetoException;
import org.eclipse.scout.example.hibernate.entity.Person;
import org.eclipse.scout.example.hibernate.server.Activator;
import org.eclipse.scout.example.hibernate.server.dao.IPersonAccessService;
import org.eclipse.scout.example.hibernate.shared.security.CreatePersonPermission;
import org.eclipse.scout.example.hibernate.shared.security.ReadPersonPermission;
import org.eclipse.scout.example.hibernate.shared.security.UpdatePersonPermission;
import org.eclipse.scout.example.hibernate.shared.services.process.IPersonProcessService;
import org.eclipse.scout.example.hibernate.shared.services.process.PersonFormData;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.security.ACCESS;
import org.eclipse.scout.service.AbstractService;
import org.eclipse.scout.service.SERVICES;

public class PersonProcessService extends AbstractService implements IPersonProcessService {

  @Override
  public PersonFormData prepareCreate(PersonFormData formData) throws ProcessingException {
    if (!ACCESS.check(new CreatePersonPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    //TODO [aho] business logic here
    return formData;
  }

  @Override
  public PersonFormData create(PersonFormData formData) throws ProcessingException {
    if (!ACCESS.check(new CreatePersonPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    Person person = new Person();
    person.setName(formData.getName().getValue());
    person.setPrename(formData.getPrename().getValue());
    IPersonAccessService personAccessService = SERVICES.getService(IPersonAccessService.class);
    personAccessService.createPerson(person);
    if (formData.getThrowException().getValue()) {
      throw new ProcessingException("Transaction breaking exception - due to selection in PersonForm.");
    }
    return formData;
  }

  @Override
  public PersonFormData load(PersonFormData formData) throws ProcessingException {
    if (!ACCESS.check(new ReadPersonPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    IPersonAccessService personAccessService = SERVICES.getService(IPersonAccessService.class);
    Person person = personAccessService.getPersonById(formData.getPersonNr());
    if (person == null) {
      throw new ProcessingException(new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Could not load person with id '" + formData.getPersonNr() + "'."));
    }
    formData.getName().setValue(person.getName());
    formData.getPrename().setValue(person.getPrename());
    return formData;
  }

  @Override
  public PersonFormData store(PersonFormData formData) throws ProcessingException {
    if (!ACCESS.check(new UpdatePersonPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    Person person = new Person();
    person.setId(formData.getPersonNr());
    person.setName(formData.getName().getValue());
    person.setPrename(formData.getPrename().getValue());
    IPersonAccessService personAccessService = SERVICES.getService(IPersonAccessService.class);
    personAccessService.updatePerson(person);
    if (formData.getThrowException().getValue()) {
      throw new ProcessingException("Transaction breaking exception - due to selection in PersonForm.");
    }
    return formData;
  }
}
