package com.yerseg.checksum;

public class CheckSum
{
    public static void main(String[] args)
    {
        try
        {
            switch(args[0])
            {
                case "-i":
                    InteractiveMode iModeObj = new InteractiveMode(args);
                    iModeObj.start();
                    break;

                default:
                    SimpleMode sModeObj = new SimpleMode(args);
                    sModeObj.start();
                    break;
            }
        }
        catch(ArrayIndexOutOfBoundsException ex)
        {
            System.out.println("No arguments were entered!\n");
        }
    }
}
