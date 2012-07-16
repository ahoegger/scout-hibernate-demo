package org.eclipse.scout.example.hibernate.server.services.process;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.example.hibernate.entity.Person;
import org.eclipse.scout.example.hibernate.server.dao.IPersonAccessService;
import org.eclipse.scout.example.hibernate.shared.services.process.DesktopFormData;
import org.eclipse.scout.example.hibernate.shared.services.process.IDesktopProcessService;
import org.eclipse.scout.service.AbstractService;
import org.eclipse.scout.service.SERVICES;

public class DesktopProcessService extends AbstractService implements IDesktopProcessService {

  @Override
  public DesktopFormData load(DesktopFormData formData) throws ProcessingException {
    formData.getPersonTable().clearRows();
    IPersonAccessService service = SERVICES.getService(IPersonAccessService.class);

    for (Person p : service.getAllPersons()) {
      formData.getPersonTable().addRow(new Object[]{p.getId(), p.getName(), p.getPrename()});
      System.out.println("ID:'" + p.getId() + "' Name:'" + p.getName() + "'");
    }
    return formData;

  }
}
