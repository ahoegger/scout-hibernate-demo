package org.eclipse.scout.example.hibernate.shared.security;

import java.security.BasicPermission;

public class CreatePersonPermission extends BasicPermission {

  private static final long serialVersionUID = 0L;

  public CreatePersonPermission() {
    super("CreatePerson");
  }
}
