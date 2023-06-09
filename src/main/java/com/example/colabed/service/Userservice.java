package com.example.colabed.service;

import com.example.colabed.api.model.User;
import com.example.colabed.api.model.Userrepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Userservice
{
    private List<User> list;
    private final Userrepository repository;

    public Userservice(Userrepository repository) {

        this.repository = repository;
    }

    //    public Userservice()
//    {
//        list=new ArrayList<>();
//        User user1=new User(1,"hello");
//        User user2=new User(2,"world");
//        User user3=new User(3,"hi");
//        User user4=new User(4,"bye");
//
//        list.addAll(Arrays.asList(user1,user2,user3,user4));
//    }
//    public Optional<User> getUser(Integer id)
//    {
//        Optional optional= Optional.empty();
//        for (User user:list)
//        {
//            if(id==user.getPassword())
//            {
//                optional=Optional.of(user);
//                return optional;
//            }
//        }
//        return optional;
//    }
    public Optional<User> getUser(String id)
    {


         return repository.findById(id);

    }
    public User newUser(User user)
    {
        User u=repository.findUserByEmailId(user.getEmail());
        if(u!=null) {
            u.setAccessToken(user.getAccessToken());
            u.setSocketId(user.getSocketId());
            return repository.save(u);
        }
        else {

            return repository.save(user);
        }
    }
    public User updateUser(User user)
    {

        return repository.save(user);
    }
    public Optional<User> getUserByToken(String token)
    {

        Optional<User> user =Optional.ofNullable(repository.findUserByToken(token));


        return user;
        // return repository.findUserByToken(token);
    }



    public Optional<User> updateSocketId(String token, String sId) {
        Optional<User> user = getUserByToken(token);
        if (user.isPresent()){
            User u = user.get();
            u.setSocketId(sId);
            updateUser(u);
        }
        return user;
    }

}
