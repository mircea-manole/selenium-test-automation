package org.automation.constants;


public enum Products {

    FIRST_PRODUCT("Hoodie"),
    SECOND_PRODUCT("Tank"),
    THIRD_PRODUCT("Jeans"),
    FOURTH_PRODUCT("Dress"),
    FIVE_PRODUCT("Shirt"),
    SIX_PRODUCT("Pants");

    public final String product;

    Products(String product) {
        this.product = product;
    }

    public String getProduct() {
        return product;
    }
}

