package domain;

public enum Role {

    CLIENT("Client"),
    ADMINISTRATOR("Admin");

    private String name;

    private Role(String name) {
        this.name = name;
    }

    public Long getId() {
        return (long) ordinal();
    }

    @Override
    public String toString() {
        return name;
    }
}
