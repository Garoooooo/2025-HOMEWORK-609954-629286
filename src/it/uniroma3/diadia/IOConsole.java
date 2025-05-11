package it.uniroma3.diadia;

import java.util.Scanner;

public class IOConsole implements IO {

    private Scanner scannerDiLinee;

    public IOConsole() {
        this.scannerDiLinee = new Scanner(System.in);
    }

    @Override
    public String leggiRiga() {
        if (scannerDiLinee.hasNextLine())
            return scannerDiLinee.nextLine();
        else
            return null; 
    }

    @Override
    public void mostraMessaggio(String msg) {
        System.out.println(msg);
    }
}
