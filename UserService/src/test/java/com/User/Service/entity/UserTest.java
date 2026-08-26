package com.User.Service.entity;

import com.User.Service.repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserTest {
    @Autowired
    private UserRepo userRepo;


    @Test
    void shouldSaveAndRetrieveUser() {
        User user = new User();
        Address address = new Address();

        address.setAddress("2-2-647-235/18A");
        address.setCity("Hyderabad");
        address.setState("Telangana");
        address.setZipCode("500013");

        user.setUsername("Ilyaz@2000");
        user.setFirstName("Ilyaz");
        user.setLastName("Mohammed");
        user.setEmail("ilyaz8099231@gmail.com");
        user.setPassword("qwertyuiop");
        user.setRole(Role.USER);

        user.setAddress(address);

        User currUser = userRepo.save(user);
        User retrieveUser = userRepo.findById(currUser.getId()).orElseThrow();

        assertAll(
                ()->assertNotNull(retrieveUser.getId()),
                ()->assertEquals("Ilyaz@2000",retrieveUser.getUsername()),
                ()->assertEquals("Ilyaz",retrieveUser.getFirstName()),
                ()->assertEquals("Mohammed",retrieveUser.getLastName()),
                ()->assertEquals("ilyaz8099231@gmail.com",retrieveUser.getEmail()),
                ()->assertEquals("qwertyuiop",retrieveUser.getPassword()),
                ()->assertEquals(Role.USER,retrieveUser.getRole()),
                ()->assertEquals("2-2-647-235/18A",retrieveUser.getAddress().getAddress()),
                ()->assertEquals("Hyderabad",retrieveUser.getAddress().getCity()),
                ()->assertEquals("Telangana",retrieveUser.getAddress().getState()),
                ()->assertEquals("500013",retrieveUser.getAddress().getZipCode())


                );
    }

    @Test
    void testGetAllUsers(){
        List<User> userList=userRepo.findAll();
        assertEquals(3, userList.size());
    }

    @Test
    void updateAndRetrieveUserDetails(){
        User updateUserDetails=userRepo.findUserByUsernameIgnoreCase("jane_smith").orElse(null);
        assertNotNull(updateUserDetails);
        updateUserDetails.setPassword("ertyuiokjhgfdfcvb");
        User retriveUpdatedUser =userRepo.saveAndFlush(updateUserDetails);
        assertAll(
                ()-> assertNotNull(retriveUpdatedUser),
                ()->assertEquals("ertyuiokjhgfdfcvb",retriveUpdatedUser.getPassword())
        );
    }
    @Test
    void shouldDeleteUser() {

        User user = userRepo
                .findUserByUsernameIgnoreCase("jane_smith")
                .orElseThrow();

        userRepo.delete(user);
        userRepo.flush();

        assertTrue(
                userRepo.findUserByUsernameIgnoreCase("jane_smith").isEmpty()
        );
    }
}
