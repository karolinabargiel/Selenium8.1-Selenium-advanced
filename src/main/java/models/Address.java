package models;

import lombok.Data;

@Data
public class Address {
    private String address;
    private String city;
    private String zipCode;


    public Address(Builder builder) {
        this.address = builder.address;
        this.city = builder.city;
        this.zipCode = builder.zipCode;
    }

    public static class Builder {
        private String address;
        private String city;
        private String zipCode;

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setCity(String city) {
            this.city = city;
            return this;
        }

        public Builder setZipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }
        public Address build() {
            return new Address(this);
        }
    }
}
