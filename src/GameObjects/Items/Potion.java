package GameObjects.Items;

import java.util.Optional;

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
            if (player.getHealth() < player.getMaxHealth()) {
                player.setHealth(player.getHealth() + potionEffect);
                System.out.println("You consumed the potion");
                player.getInventory().removeItem(this);
            } else {
                System.out.println("You don't need to consume a potion right now");
            }

        } else {
            int damage = -potionEffect;
            player.setHealth(player.getHealth() - damage);
            player.getInventory().removeItem(this);
            System.out.println("You consumed an ooga booga potion and took " + damage + " damage.");
        }

        if (player.getHealth() > player.getMaxHealth()) {
            player.setHealth(player.getMaxHealth());
        }
    }

}
