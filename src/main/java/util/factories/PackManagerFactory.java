package util.factories;

import view.network.PackManagerForm;

import java.util.List;

public class PackManagerFactory {
    public static PackManagerForm generatePackManagerForm(List<String> names) {
        return new PackManagerForm(names);
    }
}
