package parameterized;

public enum ParameterizedNames {
    FIRST_VALUE("Andrey", "AndreyZhmaka@gmail.com"),
    SECOND_VALUE("Andrey321313213", "Andrey312321321321@gmail.com");

    private final String name;
    private final String address;

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    ParameterizedNames(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
