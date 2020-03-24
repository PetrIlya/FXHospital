package view;

import javafx.scene.control.Dialog;
import javafx.scene.control.TextInputDialog;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServerAddressProcessor {
    public static final String DEFAULT_SERVER_ADDRESS = "";
    private Dialog<String> container;
    private String address;

    public ServerAddressProcessor() {
        this.container = new TextInputDialog();
        this.container.showAndWait().
                ifPresentOrElse(
                        this::setAddress,
                        () -> this.address = DEFAULT_SERVER_ADDRESS);
    }
}
