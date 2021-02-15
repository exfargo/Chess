package game;

import utils.Pair;

import java.util.Scanner;

public class TestovaciSunka {
    public static void main(String[] args) {
        GameController g = new GameController();
        boolean isOn = true;
        Scanner sc = new Scanner(System.in);
        g.printBoard();

        int arg1;
        int arg2;
        int arg3;
        int arg4;



        while (true){
            System.out.println("Source :");
            arg1 = sc.nextInt();
            arg2 = sc.nextInt();
            System.out.println("Target :");
            arg3 = sc.nextInt();
            arg4 = sc.nextInt();
            g.makeMove(new Pair<>(arg1, arg2), new Pair<>(arg3, arg4), (byte) 1);
            g.printBoard();
        }
    }
}
