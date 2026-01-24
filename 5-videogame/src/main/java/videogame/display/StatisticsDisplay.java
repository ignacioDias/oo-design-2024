package videogame.display;

import videogame.Fight;
import videogame.Observer;
import videogame.character.Character;
import videogame.weapon.Weapon;

import java.util.HashMap;
import java.util.Map;

public class StatisticsDisplay implements Observer, DisplayElement {
    Fight fight;
    String currentOutput;
    Map<String, Integer> versusHistory = new HashMap<>();
    Map<String, Integer> weaponsWinsPerCharacterHistory = new HashMap<>();
    public StatisticsDisplay(Fight fight) {
        this.fight = fight;
    }
    @Override
    public void updateStartingFight() {
        currentOutput = "Fight starts!";
        display();
    }

    @Override
    public void updateAttack(Character attacker, Character defender, int damage) {
        currentOutput = "Attack";
        display();
    }

    @Override
    public void updateWinnerAndLoser(Character winner, Character loser) {
        addWinToHistory(winner, loser);
        addWinToCharacterAndWeapon(winner);
        display();
    }
    private void addWinToHistory(Character winner, Character loser) {
        String key = winner.getClass().toString() + loser.getClass().toString();
        int wins = versusHistory.getOrDefault(key, 0); // Returns 0 if key not found
        versusHistory.put(key, ++wins);
        generateOutputVersus(winner, loser, wins);
    }
    private void generateOutputVersus(Character winner, Character loser, int wins) {
        this.currentOutput = "Wins of " + winner + " against " + loser + ": " + wins;
    }
    private void addWinToCharacterAndWeapon(Character winner) {
        Weapon weapon = winner.getWeapon();
        String key = winner.getClass().toString() + weapon.getClass().toString();
        int wins = weaponsWinsPerCharacterHistory.getOrDefault(key, 0); // Returns 0 if key not found
        weaponsWinsPerCharacterHistory.put(key, ++wins);
        generateOutputWeapon(winner, weapon);
    }
    private void generateOutputWeapon(Character winner, Weapon weapon) {
        this.currentOutput = winner + " wins using: " + weapon;
    }
    @Override
    public void display() {
        System.out.println(currentOutput);
    }
}
