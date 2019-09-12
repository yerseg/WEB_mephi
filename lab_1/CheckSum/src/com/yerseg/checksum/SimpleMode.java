package com.yerseg.checksum;

import java.io.FileInputStream;
import java.io.IOException;

public class SimpleMode implements UserMode
{
    private String args[];

    public SimpleMode(String args[])
    {
        this.args = args;
        if (this.args.length >= 4)
        {
            for (int i = 3; i < this.args.length; i++)
            {
                this.args[2] += (" " + this.args[i]);
            }
        }
    }

    public void start()
    {
        try
        {
            switch (args[0])
            {
                case "-md5":
                    if (args[1].equals("-f"))
                    {
                        if (checkSumMD5(args[2]) == null)
                        {
                            break;
                        }
                        else
                        {
                            System.out.println(checkSumMD5(args[2]));
                        }
                    }
                    else
                    {
                        System.out.println("Bad arguments");
                    }
                    break;

                case "-sha256":
                    if (args[1].equals("-f"))
                    {
                        if (checkSumSHA256(args[2]) == null)
                        {
                            break;
                        }
                        else
                        {
                            System.out.println(checkSumSHA256(args[2]));
                        }
                    }
                    else
                    {
                        System.out.println("Bad arguments");
                    }
                    break;
                default:
                    System.out.println("Bad arguments");
                    break;
            }
        }
        catch (IndexOutOfBoundsException ex)
        {
            System.out.println("No arguments were entered!\n");
            ex.getMessage();
        }
    }
}
