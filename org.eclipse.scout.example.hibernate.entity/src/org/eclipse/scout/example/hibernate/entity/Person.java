package org.eclipse.scout.example.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PERSON")
public class Person {
  @Id
  @SequenceGenerator(name = "ExampleSequence", sequenceName = "EXAMPLE_SQ", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ExampleSequence")
  @Column(name = "ID")
  private Long id;

  @Column(name = "NAME")
  private String m_name;
  @Column(name = "PRENAME")
  private String m_prename;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return m_name;
  }

  public void setName(String name) {
    m_name = name;
  }

  public String getPrename() {
    return m_prename;
  }

  public void setPrename(String prename) {
    m_prename = prename;
  }

}
