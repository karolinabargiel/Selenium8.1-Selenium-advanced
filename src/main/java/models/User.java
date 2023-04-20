package models;

import lombok.Data;

@Data
public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String birthDate;


    public User(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.password = builder.password;
        this.birthDate = builder.birthDate;

    }

    public static class Builder {
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private String birthDate;

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setBirthDate(String birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }


}
