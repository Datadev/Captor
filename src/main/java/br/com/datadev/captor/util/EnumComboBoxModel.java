package br.com.datadev.captor.util;

import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author fcsilva
 */
public class EnumComboBoxModel<T extends Enum<T> & Rotulado> implements ComboBoxModel {

    private final Class<T> enumType;
    private String selecionado;

    public EnumComboBoxModel(Class<T> enumType) {
        this.enumType = enumType;
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selecionado = String.valueOf(anItem);
    }

    @Override
    public Object getSelectedItem() {
        return selecionado;
    }

    @Override
    public int getSize() {
        return enumType.getEnumConstants().length;
    }

    @Override
    public Object getElementAt(int index) {
        ArrayList<T> elementos = new ArrayList<>(Arrays.asList(enumType.getEnumConstants()));
        return ((T)elementos.get(index)).getLabel();
    }

    @Override
    public void addListDataListener(ListDataListener l) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

}
