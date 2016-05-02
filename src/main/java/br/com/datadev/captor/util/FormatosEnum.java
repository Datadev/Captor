package br.com.datadev.captor.util;

/**
 *
 * @author fcsilva
 */
public enum FormatosEnum implements Rotulado {

    gif("GIF"),
    jpg("JPG"),
    png("PNG");

    private final String label;

    FormatosEnum(String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    public static Enum getByLabel(String valor) {
        for (FormatosEnum e : FormatosEnum.values()) {
            if (e.getLabel().equals(valor)) {
                return e;
            }
        }
        return null;
    }
}
