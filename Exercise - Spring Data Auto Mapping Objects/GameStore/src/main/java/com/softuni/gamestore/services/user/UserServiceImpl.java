package com.softuni.gamestore.services.user;

import com.softuni.gamestore.domain.dtos.UserLoginDTO;
import com.softuni.gamestore.domain.dtos.UserRegisterDTO;
import com.softuni.gamestore.domain.entities.User;
import com.softuni.gamestore.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.softuni.gamestore.constants.Validations.PASSWORD_NO_VALID_MESSAGE;

@Service
public class UserServiceImpl implements UserService{

    private User loggedInUser;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String registerUser(String[] args) throws IllegalAccessException {
        final String email = args[1];
        final String password = args[2];
        final String confirmPassword = args[3];
        final String fullName = args[4];

        final UserRegisterDTO userRegisterDTO = new UserRegisterDTO(email, password, confirmPassword, fullName);

        final User user = modelMapper.map(userRegisterDTO, User.class);

        if (userRepository.count() == 0) {
            user.setAdmin(true);
        }

        boolean isUserFound = userRepository.findByEmail(userRegisterDTO.getEmail()).isPresent();

        if (isUserFound) {
            throw new IllegalArgumentException("Email already exists");
        }

        userRepository.save(user);

        return userRegisterDTO.successfulRegisterFormat();
    }

    @Override
    public String loginUser(String[] args) {
        final String email = args[1];
        final String password = args[2];

        final UserLoginDTO userLoginDTO = new UserLoginDTO(email, password);

        Optional<User> user = this.userRepository.findByEmail(userLoginDTO.getEmail());

        if (user.isPresent() && this.loggedInUser == null && user.get().getPassword().equals(userLoginDTO.getPassword())) {
            this.loggedInUser = this.userRepository.findByEmail(userLoginDTO.getEmail()).get();
            return "Successful logged in " + this.loggedInUser.getFullName();
        }
        return PASSWORD_NO_VALID_MESSAGE;
    }

    @Override
    public String logoutUser() {
        if (this.loggedInUser == null) {
            return "Cannot log out. No user was logged in";
        }

        String output = "User " + this.loggedInUser.getFullName() + " successfully logged out";

        this.loggedInUser = null;

        return output;
    }

    @Override
    public User getLoggedInUser() {
        return loggedInUser;
    }
}
