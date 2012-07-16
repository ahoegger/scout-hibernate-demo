package org.eclipse.scout.example.hibernate.shared.services.process;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.service.IService;

public interface IPersonProcessService extends IService {

  PersonFormData prepareCreate(PersonFormData formData) throws ProcessingException;

  PersonFormData create(PersonFormData formData) throws ProcessingException;

  PersonFormData load(PersonFormData formData) throws ProcessingException;

  PersonFormData store(PersonFormData formData) throws ProcessingException;
}
