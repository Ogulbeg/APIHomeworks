package com.automation.homework1;
/**
 * {
 *         "name": "Zoe",
 *         "surname": "Vogel",
 *         "gender": "female",
 *         "region": "Germany"
 *     }
 */
import com.google.gson.annotations.SerializedName;
public class User {
    private String name;
    private String surname;
    private String gender;
    private String region;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getGender() {
        return gender;
    }

    public String getRegion() {
        return region;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", gender='" + gender + '\'' +
                ", region='" + region + '\'' +
                '}';
    }
}
