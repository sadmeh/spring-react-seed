package com.evo.authserver;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class BcryptTest {
    public static void main(String[] args) {
        String plain_password = "interstellar";
        String hashpw = BCrypt.hashpw(plain_password, BCrypt.gensalt());
        System.out.println(hashpw);

    }
}
