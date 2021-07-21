package main.java.example.swing.service;





import main.java.example.swing.model.UserInfo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;


public class FileUserInfoRepository implements UserInfoRepository {
    private static final String INFO_SEPARATOR = ";";
    private final Path path;


    public FileUserInfoRepository(Path path) {
        this.path = path;
        System.out.println();
    }

    public void write(UserInfo info) throws IOException {
        Files.write(path, toWritebleInfo(info),
                StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    @Override
    public List<UserInfo> findAll() throws IOException {
        if (!Files.exists(path)){
            return  Collections.emptyList();
        } else {
            return Arrays.stream(
                    Files.readString(path).split("\n"))
                    .map(this::toInfo).filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toList()
                    );
        }
    }

    private Optional <UserInfo> toInfo(String line) {
        var array = line.split(INFO_SEPARATOR);
        if (array.length !=3){
            return Optional.empty();
        }
        return Optional.of(new UserInfo(array[1], array[2], array[0]));
    }

    private byte[] toWritebleInfo(UserInfo info) {
        return (info.getEmail() + INFO_SEPARATOR + info.getFirstName() +  INFO_SEPARATOR + info.getLastName()+"\n").getBytes(StandardCharsets.UTF_8);
    }
}
