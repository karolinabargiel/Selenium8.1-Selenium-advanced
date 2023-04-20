package models;

public class AddressFactory {
    public static Address getAddressForUser() {
        String address= System.getProperty("address");
        String city= System.getProperty("city");
        String zipCode= System.getProperty("zipCode");
        return new Address.Builder().setAddress(address)
                .setCity(city)
                .setZipCode(zipCode)
                .build();

    }
}
