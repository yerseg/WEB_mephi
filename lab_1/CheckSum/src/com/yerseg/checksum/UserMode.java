package com.yerseg.checksum;

import org.apache.commons.codec.digest.DigestUtils;
import java.io.FileInputStream;
import java.io.IOException;

public abstract class UserMode {
    public boolean IOError = false;

    public abstract void start();

    public String checkSumSHA256(String file) {
        String checksum = null;
        try {
            checksum = DigestUtils.sha256Hex(new FileInputStream(file));
        } catch (IOException ex) {
            IOError = true;
            System.out.println(ex.getMessage() + "Enter the correct file path:\n");
        }

        return checksum;
    }

    public String checkSumMD5(String file) {
        String checksum = null;
        try {
            checksum = DigestUtils.md5Hex(new FileInputStream(file));
        } catch (IOException ex) {
            IOError = true;
            System.out.println(ex.getMessage() + "\n");
        }

        return checksum;
    }

}

