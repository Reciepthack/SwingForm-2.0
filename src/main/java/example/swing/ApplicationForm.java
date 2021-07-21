package main.java.example.swing;



import main.java.example.swing.form.UserForm;
import main.java.example.swing.service.FileUserInfoRepository;
import main.java.example.swing.service.UserInfoRepository;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ApplicationForm {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("X:\\FormSwing\\src\\main\\resources\\UserInfo\\Info.dat");
        UserInfoRepository writer = new FileUserInfoRepository(path);
        new UserForm(writer);
    }
}
