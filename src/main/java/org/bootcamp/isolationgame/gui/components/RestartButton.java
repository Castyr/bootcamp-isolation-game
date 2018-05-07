package org.bootcamp.isolationgame.gui.components;

import javafx.scene.control.Button;
import org.bootcamp.isolationgame.core.CommandFacade;

public class RestartButton extends Button {

    public RestartButton(CommandFacade commandFacade) {
        setText("Restart Game");
        setTranslateX(10);
        setTranslateY(50);
        setOnAction(e -> commandFacade.reset());
    }
}
