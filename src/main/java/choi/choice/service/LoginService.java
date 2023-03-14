package choi.choice.service;

import choi.choice.domain.Mbr;

import java.security.NoSuchAlgorithmException;

public interface LoginService {

    boolean login(Mbr mbr) throws NoSuchAlgorithmException;
}
