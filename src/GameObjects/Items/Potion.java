package GameObjects.Items;

import GameObjects.Entites.PlayerCharacter;

public class Potion extends Item implements Consumable {

    public boolean healingPotion;
    public int potionEffect = 0;

    public Potion(String name, String desc, int potionEffect, boolean healingPotion) {
        super(name, desc);
        this.potionEffect = potionEffect;
        this.healingPotion = healingPotion;
    }

    @Override
    public void consume(PlayerCharacter player) {
        System.out.println();
        if (healingPotion) {
            if (!player.isFullHP()) {
                player.changeHealth(potionEffect);
                System.out.println("You consumed " + getName());
                System.out.println("Your health is now " + player.getHealth());
                player.getInventory().removeItem(this);
            } else {
                System.out.println("You don't need to consume " + getName() + " right now");
            }

        } else {
            int damage = -potionEffect;
            player.changeHealth(damage);
            System.out.println("You consumed an ooga booga potion and took " + damage + " damage.");
            System.out.println("Your health is now " + player.getHealth());
            player.getInventory().removeItem(this);
        }

        if (player.isFullHP()) {
            player.setHealth(player.getMaxHealth());
        }
    }

}
