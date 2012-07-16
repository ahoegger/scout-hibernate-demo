package org.eclipse.scout.example.hibernate.shared.security;

import java.security.BasicPermission;

public class ReadPersonPermission extends BasicPermission {

  private static final long serialVersionUID = 0L;

  public ReadPersonPermission() {
    super("ReadPerson");
  }
}
