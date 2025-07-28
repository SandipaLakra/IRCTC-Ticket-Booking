package ticket.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.entities.User;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


public class  UserBookingService {

    private User user;
    private List<User> userList;
    private static final String USERS_PATH = "app/src/main/java/ticket.booking/localDb/users.json";
    private ObjectMapper objectMapper = new ObjectMapper();

    public UserBookingService(User user1) throws IOException {

        this.user = user1;

        File users = new File(USERS_PATH);

        //deserialize user json file into java
        userList = objectMapper.readValue(users, new TypeReference<List<User>>() {});
    }

    //login method
    public Boolean logInUser() {
        Optional<User> foundUser = userList.stream().filter(user1 -> {
            user.getName().equals(user1.getName()) && UserServiceUtil.checkPassword(user.getPassword(), user1.getPassword());
        }).findFirst();
        return foundUser.isPresent();
    }

}
