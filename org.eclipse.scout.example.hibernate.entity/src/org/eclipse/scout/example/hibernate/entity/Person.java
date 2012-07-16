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
@SequenceGenerator(name = "ExampleSequence", sequenceName = "EXAMPLE_SQ")
public class Person {
  private Long id;
  private String m_name;
  private String m_prename;

  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ExampleSequence")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Column(name = "NAME")
  public String getName() {
    return m_name;
  }

  public void setName(String name) {
    m_name = name;
  }

  @Column(name = "PRENAME")
  public String getPrename() {
    return m_prename;
  }

  public void setPrename(String prename) {
    m_prename = prename;
  }

}
