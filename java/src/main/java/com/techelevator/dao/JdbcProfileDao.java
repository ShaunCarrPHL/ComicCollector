package com.techelevator.dao;


import com.techelevator.model.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class JdbcProfileDao implements ProfileDao {
    @Override
    public List<Profile> listAllProfiles() {
        return null;
    }

    @Override
    public Profile getProfileByUserId(int userid) {
        return null;
    }
}
