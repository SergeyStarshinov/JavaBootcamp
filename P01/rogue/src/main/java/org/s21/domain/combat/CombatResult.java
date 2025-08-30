package org.s21.domain.combat;

public class CombatResult {
    private final int damage;
    private boolean dodge;
    private final boolean isCritical;


    public CombatResult(int damage, boolean dodge, boolean isCritical) {
        this.damage = damage;
        this.dodge = dodge;
        this.isCritical = isCritical;
    }

    public int getDamage() {
        return damage;
    }

    public boolean isNotDodge() {
        return !dodge;
    }


    public void setDodge(boolean dodge) {
        this.dodge = dodge;
    }

    public String toString() {
        if (dodge) {
            return "miss";
        }
        String result = "";
        if(isCritical) {
            result += "critical ";
        }
        return result + damage;
    }
}