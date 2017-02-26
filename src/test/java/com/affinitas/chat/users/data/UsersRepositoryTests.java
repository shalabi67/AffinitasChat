package com.affinitas.chat.users.data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.Assert;

/**
 * Created by mohammad on 2/25/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class UsersRepositoryTests {
    @Autowired
    UsersRepository usersRepository;

    @Test
    public void testAddUser() {
        User user = new User();
        user = usersRepository.addUser(user);
        int id = user.getId();
        usersRepository.addUser(new User());
        User someUser = usersRepository.addUser(new User());
        User lastUser = usersRepository.addUser(new User());
        Assert.isTrue(usersRepository.getUserById(id).getId() == id);
        Assert.isTrue(usersRepository.getUserById(id).getId() == id);
        Assert.isTrue(usersRepository.getUserById(someUser.getId()).getId() == someUser.getId());
    }
}
