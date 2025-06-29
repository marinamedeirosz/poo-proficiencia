package com.marina.dao.interfaces;

import java.io.IOException;

public interface AuthDao {

    String login(String login, String password) throws IOException;
    String register(String login, String password) throws IOException;

}
