package domain.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class User {

    @Id
    private String username;
    private  String firstname;
    private  String lastname;
    private  int age;
    private  String occupation;
    @OneToOne(cascade = CascadeType.ALL)
    private Balance balance;

    public User() {
    }

    public User(String username, String firstname, String lastname, int age, String occupation, Balance balance) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.occupation = occupation;
        this.balance= balance;
    }

    public Balance getBalance() {
        return balance;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public int getAge() {
        return age;
    }

    public String getOccupation() {
        return occupation;
    }

    public void topUp(Money money ) {
        balance.topUp(money);
    }
    public void withdraw(Money money) {
        balance.withdraw(money);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age && Objects.equals(username, user.username) && Objects.equals(firstname, user.firstname) && Objects.equals(lastname, user.lastname) && Objects.equals(occupation, user.occupation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, firstname, lastname, age, occupation);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", age=" + age +
                ", occupation='" + occupation + '\'' +
                ", balance=" + balance +
                '}';
    }
}
