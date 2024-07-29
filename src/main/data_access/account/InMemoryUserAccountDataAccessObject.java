package data_access.account;

import data_access.authentication.UserSignupDataAccessInterface;
import entity.account.UserAccount;

import java.util.*;

/**
 * In-memory data access object (DAO) for user account operations.
 * <p>
 * This class implements the {@link UserSignupDataAccessInterface} interface and provides an in-memory
 * implementation for managing user accounts. It is intended for testing purposes and does not persist data
 * beyond the lifetime of the application.
 * </p>
 *
 * @author Eric
 * @author Jessica
 */

// in memory DAO for test purposes
public class InMemoryUserAccountDataAccessObject implements UserSignupDataAccessInterface{
    private final Map<String, UserAccount> users = new HashMap<>();

    /**
     * Checks if a user exists with the given identification.
     *
     * @param identifier the unique identifier for the user account
     * @return {@code true} if a user account exists with the specified identification; {@code false} otherwise
     */
    @Override
    public boolean existById(String identifier) {
        return users.containsKey(identifier);
    }

    /**
     * Saves a new user account.
     *
     * @param newUser the {@link UserAccount} to be saved
     */
    public void save(UserAccount newUser) {
        users.put(newUser.getIdentification(), newUser);
    }

    /**
     * Deletes a user account by its unique identification.
     *
     * @param identifier the unique identifier for the user account to be deleted
     */
    public void deleteById(String identifier) {
        users.remove(identifier);
    }

    /**
     * Retrieves a user account by its unique identification.
     *
     * @param identifier the unique identifier for the user account
     * @return the {@link UserAccount} associated with the specified identification, or {@code null} if not found
     */
    public UserAccount getById(String identifier) {
        return users.get(identifier);
    }

    /**
     * Retrieves all user accounts.
     *
     * @return a {@link Map} of all user accounts where the key is the user's identification and the value is the {@link UserAccount}
     */
    public Map<String, UserAccount> getAllUserAcc() {
        return users;
    }
}