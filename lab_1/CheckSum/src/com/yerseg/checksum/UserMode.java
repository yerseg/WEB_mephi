package com.yerseg.checksum;

import org.apache.commons.codec.digest.DigestUtils;
import java.io.FileInputStream;
import java.io.IOException;

public interface UserMode
{
    public void start();
    public default String checkSumSHA256(String file)
    {
        String checksum = null;
        try
        {
            checksum = DigestUtils.sha256Hex(new FileInputStream(file));
        }
        catch (IOException ex)
        {
            System.out.println("IOException: " + ex.getMessage() + "Enter the correct file path:\n");
        }

        return checksum;
    }

    public default String checkSumMD5(String file)
    {
        String checksum = null;
        try
        {
            checksum = DigestUtils.md5Hex(new FileInputStream(file));
        }
        catch (IOException ex)
        {
            System.out.println("IOException: " + ex.getMessage() + "\n");
        }

        return checksum;
    }

}

