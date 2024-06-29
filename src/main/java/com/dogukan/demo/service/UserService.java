package com.dogukan.demo.service;

import com.dogukan.demo.entity.User;
import com.dogukan.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void saveUserWithException(User user) { /* After saving, we expect a rollback when an error occurs.*/
        userRepository.save(user);
        throw new RuntimeException("Simulated exception");
    }

    @Transactional(noRollbackFor = RuntimeException.class)
    public void saveUserWithoutRollback(User user) {/* We expect it not to rollback when it receives an error after saving.*/
        userRepository.save(user);
        throw new RuntimeException("Simulated exception");
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public User findUserById(Long id) {
        return userRepository.getReferenceById(id);
    }

}
