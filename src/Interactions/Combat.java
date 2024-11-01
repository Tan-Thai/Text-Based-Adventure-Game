package Interaction;
import GameObjects.Entities.*;
import java.util.Random;
import java.util.Scanner;
public class Combat
{
    boolean isCombatInProgress = true;
    Random random =new Random();

    int amountOfSides=6;
    private int amountOfHits=0;
    private int enemieAmountOfHits=0;
    int successValue =4;
    int critValue=6;

;
    String RESET = "\u001B[0m";
    String RED = "\u001B[31m";
    String GREEN = "\u001B[32m";

    private
    private Entity  enemy;


    private Combat() {}


    public void entireCombat()
    {
        while(isCombatInProgress ==true)
        {
            printHeroHP();
            printFoeHP();
            System.out.println("to attack press 1, to end combat press 2");
            anybutton=scan.nextInt();
            System.out.println(anybutton);
            if(anybutton==1)
            {
                playerStrike();
                foeStrike();
                printPlayerHits();
                printFoeHits();
                calcHeroHP();
                calcFoeHP();
                calcVictory();
            }
            else
            {
                isCombatInProgress =false;
            }

        }
    }

    public void playerStrike()
    {
        for(int i=0; i<entityOneStr; i++)
        {
            currentValue=random.nextInt(amountOfSides)+1;
            System.out.print(GREEN +"["+currentValue+"]"+RESET);
            if(currentValue>=successValue)
            {
                if(currentValue==critValue)
                {
                    i--;
                }
                amountOfHits++;
            }
        }
        System.out.println();
    }
    public void foeStrike()
    {
        for(int i=0; i<entitytwoStr; i++)
        {
            currentValue=random.nextInt(amountOfSides)+1;
            System.out.print(RED+"["+currentValue+"]"+RESET);
            if(currentValue>=successValue)
            {
                if(currentValue==critValue)
                {
                    i--;
                }
                enemieAmountOfHits++;
            }
        }
        System.out.println();
    }
    public void printPlayerHits()
    {
        System.out.println("You got "+amountOfHits+" hits!");
    }

    public  void printFoeHits()
    {
        System.out.println("Your enemie got "+enemieAmountOfHits+" hits!");
    }
    public void calcFoeHP()
    {
        foeHp=foeHp-amountOfHits;
        amountOfHits=0;

    }
    public void calcHeroHP()
    {

        heroHp=heroHp-enemieAmountOfHits;
        player.setHealth(heroHp);
        enemieAmountOfHits=0;
    }

    public int getAmountOfHits() {
        return amountOfHits;
    }

    public void setAmountOfHits(int amountOfHits) {
        this.amountOfHits = amountOfHits;
    }

    public int getEnemieAmountOfHits() {
        return enemieAmountOfHits;
    }

    public void setEnemieAmountOfHits(int enemieAmountOfHits) {
        this.enemieAmountOfHits = enemieAmountOfHits;
    }
    public void printHeroHP()
    {
        player.PrintColorHealthbar(1);
    }
    public void printFoeHP()
    {
        enemy.PrintColorHealthbar(2);
    }
    public void calcVictory()
    {
        if(foeHp<=0&&heroHp<=0)
        {
            System.out.println("Both of you perish");
            isCombatInProgress =false;
        }
        if(foeHp<=0)
        {
            player.PrintColorHealthbar(1);
            System.out.println("You have vanquished your foe!");
            playerExp=+5;
            System.out.println("You gain 5 experiance points and a rusty longsword");
            System.out.println("your current experiance is "+playerExp);
            isCombatInProgress =false;
        }
        if(heroHp<=0)
        {
            System.out.println("You have been slayed");
            isCombatInProgress =false;
        }
    }
}

