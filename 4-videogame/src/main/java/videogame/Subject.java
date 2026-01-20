package videogame;

import videogame.character.Character;

public interface Subject {
    public void addObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyObserversAboutFightStarting();
    public void notifyObserversAboutAttack(Character attacker, Character defender, int damage);
    public void notifyObserversAboutWinnerAndLoser(Character winner, Character loser);
}
