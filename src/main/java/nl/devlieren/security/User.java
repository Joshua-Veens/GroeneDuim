package nl.devlieren.security;


import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

public class User implements Principal {
    private String name;
    private String password;
    private String role;
    private static List<User> users = new ArrayList<>();

    public User(String name, String password)
    {
        this.name = name;
        this.password = password;
        this.role = "admin";
    }

    static {
        users.add(new User("joshua", "wachtwoord"));
        users.add(new User("test", "test"));
    }

    public static String validateLogin(String username, String password) {
        for (User user : users) {
            if (user.getName().equals(username) && user.getPassword().equals(password)) {
                return user.getRole();
            }
        }
        throw new IllegalArgumentException("Invalid login");
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role){
        this.role = role;
    }

    public static boolean addUser(User user) {
        if (users.contains(user)) return false;
        users.add(user);
        return true;
    }

    public static User getUserByName(String user) {
        return users.stream().filter(u -> u.getName().equals(user)).findFirst().orElse(null);
    }

    @Override
    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

}
