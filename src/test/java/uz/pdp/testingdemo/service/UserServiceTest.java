package uz.pdp.testingdemo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpEntity;
import uz.pdp.testingdemo.entity.User;
import uz.pdp.testingdemo.repository.UserRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

//@DataJpaTest
////@TestPropertySource(locations = "classpath:application.properties")
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    UserService userService;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository);
    }

    @Test
    void getAllUsers() {

        userService.getAllUsers();

        verify(userRepository).findAll();
    }

    @Test
    void canAddNewUser() {

        User user = new User("Aziz", "aziz");

        userService.save(user);

        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);

        verify(userRepository).save(userArgumentCaptor.capture());

        assertEquals(user, userArgumentCaptor.getValue());
    }

    @Test
    void willThrowWhenUsernameExist() {
        User user = new User("Aziz", "aziz");


        given(userRepository.existsByUsername(anyString())).willReturn(true);
        //userService.save(user);

        assertThatThrownBy(() -> userService.save(user))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(user.getName()+ "is already taken");

        verify(userRepository, never()).save(any());
    }

    @Test
    void canUpdateUser() {
//        User user = new User("Aziz", "aziz");
//
//        userService.save(user);

        User forUpdate = new User(1, "Aziz", "azizUpdate");

        userService.save(forUpdate);

        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);

        verify(userRepository).save(userArgumentCaptor.capture());

        assertEquals(forUpdate, userArgumentCaptor.getValue());
    }

    @Test
    void canDeleteUser() {
        Integer id = 1;

       // given(userRepository.existsById(id)).willReturn(true);

        userService.deleteUser(id);

        verify(userRepository).deleteById(id);
    }
}