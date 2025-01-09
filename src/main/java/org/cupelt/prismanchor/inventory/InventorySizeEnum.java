package org.cupelt.prismanchor.inventory;

public enum InventorySizeEnum {
    XS(9), S(18), SM(27), LM(36), L(45), XL(54);

    final int size;

    InventorySizeEnum(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
