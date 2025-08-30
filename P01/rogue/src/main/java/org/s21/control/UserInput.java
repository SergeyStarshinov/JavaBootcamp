package org.s21.control;

import jcurses.system.InputChar;
import jcurses.system.Toolkit;
import org.s21.domain.model.items.ItemType;
import org.s21.domain.model.utils.Direction;
import org.s21.ui.actions.BackpackUserAction;
import org.s21.ui.actions.HelpUserAction;
import org.s21.ui.actions.MoveUserAction;
import org.s21.ui.actions.UserAction;


public class UserInput {

    public static InputChar getUserInput() {
        return Toolkit.readCharacter();
    }

    public static UserAction selectUserAction(int inputCode) {
        UserAction result = null;
        if (inputCode == InputChar.KEY_UP || inputCode == 'W' || inputCode == 'w') {
            result = new MoveUserAction(Direction.UP);
        } else if (inputCode == InputChar.KEY_DOWN || inputCode == 'S' || inputCode == 's') {
            result = new MoveUserAction(Direction.DOWN);
        } else if (inputCode == InputChar.KEY_LEFT || inputCode == 'A' || inputCode == 'a') {
            result = new MoveUserAction(Direction.LEFT);
        } else if (inputCode == InputChar.KEY_RIGHT || inputCode == 'D' || inputCode == 'd') {
            result = new MoveUserAction(Direction.RIGHT);
        } else if (inputCode == 'I' || inputCode == 'i') {
            result = new HelpUserAction();
        } else if (inputCode == 'H' || inputCode == 'h') {
            result = new BackpackUserAction(ItemType.WEAPON);
        } else if (inputCode == 'J' || inputCode == 'j') {
            result = new BackpackUserAction(ItemType.FOOD);
        } else if (inputCode == 'K' || inputCode == 'k') {
            result = new BackpackUserAction(ItemType.ELIXIR);
        } else if (inputCode == 'E' || inputCode == 'e') {
            result = new BackpackUserAction(ItemType.SCROLL);
        }
        return result;
    }

}
