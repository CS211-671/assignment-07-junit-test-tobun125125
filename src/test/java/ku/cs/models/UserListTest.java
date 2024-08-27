package ku.cs.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserListTest {

    @Test
    @DisplayName("User should be found in UserList")
    public void testUserListFindUser() {
        UserList userList = new UserList();

        userList.addUser("Student1Test", "6xxxxxxxx");
        userList.addUser("Student2Test", "6xxxxxxxx");
        userList.addUser("Student3Test", "6xxxxxxxx");

        User user = userList.findUserByUsername("Student2Test");

        String expected = "Student2Test";
        String actual = user.getUsername();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("User can change password")
    public void testUserCanChangePassword() {
        UserList userList = new UserList();

        userList.addUser("Student1Test", "6xxxxxxxx");
        userList.addUser("Student2Test", "6xxxxxxxx");
        userList.addUser("Student3Test", "6xxxxxxxx");

        // Change password for Student3Test
        boolean actual = userList.changePassword("Student3Test", "6xxxxxxxx", "6610450081");

        // Assert that password change was successful
        assertTrue(actual);

        // Verify that the user can log in with the new password
        User user = userList.login("Student3Test", "6610450081");
        assertNotNull(user);
    }

    @Test
    @DisplayName("User with correct password can login")
    public void testUserListShouldReturnObjectIfUsernameAndPasswordIsCorrect() {
        UserList userList = new UserList();

        // Add 3 users to UserList
        userList.addUser("Student1Test", "6xxxxxxxx");
        userList.addUser("Student2Test", "6xxxxxxxx");
        userList.addUser("Student3Test", "6xxxxxxxx");

        // Call login() with correct username and password
        User user = userList.login("Student1Test", "6xxxxxxxx");

        // Assert that User object is found
        assertNotNull(user);
        assertEquals("Student1Test", user.getUsername());
    }

    @Test
    @DisplayName("User with incorrect password cannot login")
    public void testUserListShouldReturnNullIfUsernameAndPasswordIsIncorrect() {
        UserList userList = new UserList();

        // Add 3 users to UserList
        userList.addUser("Student1Test", "6xxxxxxxx");
        userList.addUser("Student2Test", "6xxxxxxxx");
        userList.addUser("Student3Test", "6xxxxxxxx");

        // Call login() with incorrect username or incorrect password
        User user = userList.login("Student1Test", "wrongpassword");

        // Assert that the method returns null
        assertNull(user);
    }
}
