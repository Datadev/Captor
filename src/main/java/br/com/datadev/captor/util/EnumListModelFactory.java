package br.com.datadev.captor.util;

import javax.swing.DefaultListModel;

/**
 *
 * @author fcsilva
 */
public class EnumListModelFactory<T extends Enum<T> & Rotulado> {

    private final Class<T> enumType;

    public EnumListModelFactory(Class<T> enumType) {
        this.enumType = enumType;
    }

    public DefaultListModel getListModel() {
        DefaultListModel listModel = new DefaultListModel();
        for (T c : enumType.getEnumConstants()) {
            listModel.addElement(c.getLabel());
        }
        return listModel;
    }
}
