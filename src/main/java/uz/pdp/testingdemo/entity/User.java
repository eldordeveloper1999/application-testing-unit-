package uz.pdp.testingdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.PackagePrivate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.UniqueConstraint;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
@PackagePrivate
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String username;

    public User(String name, String username) {
        this.name = name;
        this.username = username;
    }
}
