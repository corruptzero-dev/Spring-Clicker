package com.game.clicker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.game.clicker.entity.User;
import com.game.clicker.messages.Messages;
import com.game.clicker.repository.UserRepository;


@Service
public class UserService {

    private final UserRepository repository;

    @Autowired                                      //Внедрение зависимостей через конструктор лучше чем через поля
    public UserService(UserRepository repository){
        this.repository=repository;
    }

    public String addUser(User user){
        if(user.getUsername().isBlank()||user.getPassword().isBlank()){         //Проверка на пустой логин или пароль
            return Messages.BLANK_CREDENTIALS;
        }
        if (repository.existsByUsernameAndPassword(user.getUsername(), user.getPassword())){    //Все ОК
            return Messages.SUCCESSFUL_LOGIN;
        } else if (repository.existsByUsername(user.getUsername())){                            //Неверный пароль
            return Messages.WRONG_PASSWORD;
        } else {
            repository.save(user);
            return Messages.SUCCESSFULLY_REGISTERED;
        }
    }
    public Page<User> getUsers(Pageable pageable){return repository.findAll(pageable);}

}
