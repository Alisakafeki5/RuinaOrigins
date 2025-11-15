package kafekie.ruinsorigins.augmentations;

import com.mojang.brigadier.CommandDispatcher;
import kafekie.ruinsorigins.mixin.PlayerEntityMixin;
import net.minecraft.server.command.CommandManager;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import kafekie.ruinsorigins.augmentations.AugmentationsScreenHandler;
import net.minecraft.inventory.SimpleInventory;

public class AugmentationsCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("augmentations")
                .then(CommandManager.literal("view")
                        .executes(context -> {
                            ServerPlayerEntity player = context.getSource().getPlayer();
                            if (player != null) {
                                player.openHandledScreen(new SimpleNamedScreenHandlerFactory(
                                        (syncId, playerInventory, playerEntity) -> {
                                            var augmentations = ((AugmentationsHolder) playerEntity).getAugmentations();
                                            return new AugmentationsScreenHandler(syncId, playerInventory, augmentations.getInventory(), false);
                                        },
                                        Text.of("Augmentations")
                                ));
                            }
                            return 1;
                        })
                )
                .then(CommandManager.literal("edit")
                        .requires(source -> source.hasPermissionLevel(2))
                        .then(CommandManager.argument("type", StringArgumentType.word())
                                .suggests((ctx, builder) -> {
                                    builder.suggest("all");
                                    builder.suggest("mecha");
                                    builder.suggest("injection");
                                    builder.suggest("tattoo");
                                    builder.suggest("implant");
                                    return builder.buildFuture();
                                })
                                .executes(context -> {
                                    ServerPlayerEntity player = context.getSource().getPlayer();
                                    String typeStr = StringArgumentType.getString(context, "type");
                                    AugmentationsTypes type = AugmentationsTypes.valueOf(typeStr.toUpperCase());

                                    if (player != null) {
                                        player.openHandledScreen(new SimpleNamedScreenHandlerFactory(
                                                (syncId, playerInventory, playerEntity) -> {
                                                    var augmentations = ((AugmentationsHolder) playerEntity).getAugmentations();
                                                    return new AugmentationsScreenHandler(syncId, playerInventory, augmentations.getInventory(), true, type);
                                                },
                                                Text.of("Augmentations")
                                        ));
                                    }
                                    return 1;
                                })
                        )
                )

        );
    }
}