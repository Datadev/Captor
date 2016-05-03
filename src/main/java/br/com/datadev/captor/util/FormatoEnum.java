package br.com.datadev.captor.util;

/**
 *
 * @author fcsilva
 */
public enum FormatoEnum implements Rotulado {

    gif("GIF"),
    jpg("JPG"),
    png("PNG");

    private final String label;

    FormatoEnum(String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    public static FormatoEnum getByLabel(String valor) {
        for (FormatoEnum e : FormatoEnum.values()) {
            if (e.getLabel().equals(valor)) {
                return e;
            }
        }
        return null;
    }
}
