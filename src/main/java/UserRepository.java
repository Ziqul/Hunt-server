
package main.java;
/**
 * Created by Marina on 12.02.2017.
 */
public interface UserRepository {
    User get(String login, String password);
    User create(User user);
    User update(User user);
    User removeUser(Integer id);
    User getAll();
}
