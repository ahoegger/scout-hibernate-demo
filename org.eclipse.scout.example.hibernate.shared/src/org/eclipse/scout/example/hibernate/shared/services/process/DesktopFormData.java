package org.eclipse.scout.example.hibernate.shared.services.process;

import org.eclipse.scout.rt.shared.data.form.AbstractFormData;
import org.eclipse.scout.rt.shared.data.form.fields.tablefield.AbstractTableFieldData;

public class DesktopFormData extends AbstractFormData {
  private static final long serialVersionUID = 1L;

  public DesktopFormData() {
  }

  public PersonTable getPersonTable() {
    return getFieldByClass(PersonTable.class);
  }

  public static class PersonTable extends AbstractTableFieldData {
    private static final long serialVersionUID = 1L;

    public PersonTable() {
    }

    public static final int ID_COLUMN_ID = 0;
    public static final int NAME_COLUMN_ID = 1;
    public static final int PRENAME_COLUMN_ID = 2;

    public void setId(int row, Long id) {
      setValueInternal(row, ID_COLUMN_ID, id);
    }

    public Long getId(int row) {
      return (Long) getValueInternal(row, ID_COLUMN_ID);
    }

    public void setName(int row, String name) {
      setValueInternal(row, NAME_COLUMN_ID, name);
    }

    public String getName(int row) {
      return (String) getValueInternal(row, NAME_COLUMN_ID);
    }

    public void setPrename(int row, String prename) {
      setValueInternal(row, PRENAME_COLUMN_ID, prename);
    }

    public String getPrename(int row) {
      return (String) getValueInternal(row, PRENAME_COLUMN_ID);
    }

    @Override
    public int getColumnCount() {
      return 3;
    }

    @Override
    public Object getValueAt(int row, int column) {
      switch (column) {
        case ID_COLUMN_ID:
          return getId(row);
        case NAME_COLUMN_ID:
          return getName(row);
        case PRENAME_COLUMN_ID:
          return getPrename(row);
        default:
          return null;
      }
    }

    @Override
    public void setValueAt(int row, int column, Object value) {
      switch (column) {
        case ID_COLUMN_ID:
          setId(row, (Long) value);
          break;
        case NAME_COLUMN_ID:
          setName(row, (String) value);
          break;
        case PRENAME_COLUMN_ID:
          setPrename(row, (String) value);
          break;
      }
    }
  }
}
