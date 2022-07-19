package com.example.thymeleafcrud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepo;

    @Autowired
    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public User getUser (Long id) throws ChangeSetPersister.NotFoundException {
       return userRepo.findByIdAndActive(id, true).orElseThrow(ChangeSetPersister.NotFoundException::new);

    }

    public User createUser (User user){
        return userRepo.save(user);
    }

    public List<User> getUsers(){
        return userRepo.findAllByActiveOrderByActiveDesc(true);
    }

    public User updateUser (Long id, User update) throws ChangeSetPersister.NotFoundException {
        User fromDB = getUser(id);
        fromDB.setNome(update.getNome());
        fromDB.setCognome(update.getCognome());
        fromDB.setActive(update.isActive());
        fromDB.setAggiornatoil(LocalDateTime.now());
        return userRepo.save(fromDB);
    }

    public User findUser(Long id) throws ChangeSetPersister.NotFoundException {
        return userRepo.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);

    }


}
