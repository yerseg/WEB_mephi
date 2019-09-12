package com.yerseg.checksum;

import java.io.FileInputStream;
import java.io.IOException;

public class InteractiveMode implements UserMode
{
    private String args[];
    private boolean stop;

    public InteractiveMode(String args[])
    {
        this.args = args;
        this.stop = false;
        if (this.args.length >= 4)
        {
            for(int i = 3; i < this.args.length; i++)
            {
                this.args[2] += this.args[i];
            }
        }
    }

    public void start()
    {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        while(!stop)
        {
            String fileName = "";
            String stopperFlag = "";

            System.out.println("Enter the file path: ");
            do
            {
                if(sc.hasNextLine())
                {
                    fileName = sc.nextLine();
                }
                else
                {
                    System.out.println("Enter the correct file path: ");
                }
            }
            while (fileName.equals(""));

            System.out.println("MD5: " + checkSumMD5(fileName) + "\n" +
                    "SHA256: " + checkSumSHA256(fileName) + "\n");

            do
            {
                System.out.println("Continue? (Y/N)\n");
                stopperFlag = sc.nextLine();
                switch(stopperFlag)
                {
                    case "N":
                        stop = true;
                        break;
                }
            }
            while (!stopperFlag.equals("Y") && !stopperFlag.equals("N"));
        }
    }
}

