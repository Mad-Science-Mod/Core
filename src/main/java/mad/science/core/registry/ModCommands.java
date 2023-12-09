package mad.science.core.registry;

import mad.science.core.command.MadnessCommand;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class ModCommands {

    public static void init() {
        CommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess, environment) -> {
            MadnessCommand.register(dispatcher);
        }));
    }
}
