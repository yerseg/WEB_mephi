package com.yerseg.checksum;

import java.io.FileInputStream;
import java.io.IOException;

public class SimpleMode extends UserMode {
    private String args[];

    public SimpleMode(String args[]) {
        this.args = args;
    }

    public void start() {
        try {
            switch (args[0]) {
                case "-md5":
                    if (args[1].equals("-f")) {
                        int k = 1;
                        for (int i = 2; i < args.length; i++) {
                            if (args[i].equals("-f")) {

                            } else if (checkSumMD5(args[i]) == null) {
                                System.out.println("File " + k + ": does not exist\n");
                                k++;
                            } else if (!(args[i].equals("-f"))) {
                                System.out.println("File " + k + ": " + checkSumMD5(args[i]));
                                k++;
                            }
                        }
                    } else {
                        System.out.println("Bad arguments: specify the file");
                    }
                    break;

                case "-sha256":
                    if (args[1].equals("-f")) {
                        int k = 1;
                        for (int i = 2; i < args.length; i++) {
                            if (args[i].equals("-f")) {

                            } else if (checkSumSHA256(args[i]) == null) {
                                System.out.println("File " + k + ": does not exist\n");
                                k++;
                            } else if (!(args[i].equals("-f"))) {
                                System.out.println("File " + k + ": " + checkSumSHA256(args[i]));
                                k++;
                            }
                        }
                    } else {
                        System.out.println("Bad arguments: specify the file");
                    }
                    break;
                default:
                    System.out.println("Bad arguments: specify the algo");
                    break;
            }
        } catch (
                IndexOutOfBoundsException ex) {
            System.out.println("No arguments were entered!\n");
            ex.getMessage();
        }
    }
}
