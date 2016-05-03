package br.com.datadev.captor.util;

/**
 *
 * @author fcsilva
 */
public enum CursorEnum implements Rotulado {

    azul("CursorAzul.gif"),
    vermelho("CursorVermelho.gif");

    private final String label;

    CursorEnum(String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return this.label;
    }
}
