package mad.science.core.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.LongArgumentType;
import mad.science.core.registry.ModComponents;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class MadnessCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("madness")
                .requires(source -> source.hasPermissionLevel(2))

                .then(CommandManager.literal("get")
                        .then(CommandManager.argument("player", EntityArgumentType.player())
                                .executes(context -> getMadness(context.getSource(), EntityArgumentType.getPlayer(context, "player")))))

                .then(CommandManager.literal("set")
                    .then(CommandManager.argument("player", EntityArgumentType.player())
                        .then(CommandManager.argument("amount", LongArgumentType.longArg(-50, 300))
                                .executes(context -> setMadness(context.getSource(), EntityArgumentType.getPlayer(context, "player"), LongArgumentType.getLong(context, "amount"))))))

        );
    }

    public static int setMadness(ServerCommandSource source, ServerPlayerEntity player, long amount) {
        ModComponents.MADNESS.get(player).setMadness(amount);
        ModComponents.MADNESS.sync(player);

        source.sendFeedback(() -> Text.translatable("command.mad-science.set_madness", player.getDisplayName(), amount), true);
        return 1;
    }

    public static int getMadness(ServerCommandSource source, ServerPlayerEntity player) {
        long amount = ModComponents.MADNESS.get(player).getMadness();
        source.sendFeedback(() -> Text.translatable("command.mad-science.get_madness", player.getDisplayName(), amount), false);
        return 1;
    }
}
