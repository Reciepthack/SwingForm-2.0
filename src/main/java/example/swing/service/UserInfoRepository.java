package main.java.example.swing.service;





import main.java.example.swing.model.UserInfo;

import java.io.IOException;
import java.util.List;

public interface UserInfoRepository {

    void write(UserInfo info) throws IOException;

    List<UserInfo> findAll() throws IOException;

}
