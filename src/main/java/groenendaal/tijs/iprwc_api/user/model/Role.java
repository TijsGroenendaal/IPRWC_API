package groenendaal.tijs.iprwc_api.user.model;

public enum Role {
    ADMIN("admin"),
    CUSTOMER("customer");

    public final String value;

    Role(String value) {
        this.value = value;
    }
}
