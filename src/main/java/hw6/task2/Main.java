package hw6.task2;

import java.util.List;

public class Main {

    static void main() {
        UserHelper uh = new UserHelper();

        List<User> userList = uh.getUsers(7);
        User newUser = new User();
        newUser.setName("1");

        for (User user : userList) {
            uh.updateUser(user.getId(), newUser);
        }

        System.out.println(uh.getUsers());

    }

}
