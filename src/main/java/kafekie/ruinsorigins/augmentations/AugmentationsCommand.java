package kafekie.ruinsorigins.augmentations;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class AugmentationsCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {

        dispatcher.register(CommandManager.literal("augmentations")

                // /augmentations view
                .then(CommandManager.literal("view")
                        .executes(context -> {
                            ServerPlayerEntity player = context.getSource().getPlayerOrThrow();

                            player.openHandledScreen(new SimpleNamedScreenHandlerFactory(
                                    (syncId, playerInventory, playerEntity) -> {
                                        var augmentations =
                                                ((AugmentationsHolder) playerEntity).getAugmentations();

                                        return new AugmentationsScreenHandler(
                                                syncId,
                                                playerInventory,
                                                augmentations.getInventory(),
                                                false,
                                                playerEntity,
                                                AugmentationsTypes.MECHA
                                        );
                                    },
                                    Text.of("Augmentations")
                            ));

                            return 1;
                        })
                )

                // /augmentations edit <type|view>
                .then(CommandManager.literal("edit")
                        .requires(source -> source.hasPermissionLevel(2))
                        .then(CommandManager.argument("type", StringArgumentType.word())
                                .suggests((ctx, builder) -> {
                                    builder.suggest("mecha");
                                    builder.suggest("tattoo");
                                    builder.suggest("injection");
                                    builder.suggest("view");
                                    return builder.buildFuture();
                                })
                                .executes(context -> {
                                    ServerPlayerEntity player = context.getSource().getPlayerOrThrow();
                                    String typeStr = StringArgumentType.getString(context, "type");

                                    boolean editable;
                                    AugmentationsTypes type = AugmentationsTypes.MECHA;

                                    if (typeStr.equalsIgnoreCase("view")) {
                                        editable = false;
                                    } else {
                                        editable = true;
                                        try {
                                            type = AugmentationsTypes.valueOf(typeStr.toUpperCase());
                                        } catch (IllegalArgumentException e) {
                                            context.getSource().sendError(
                                                    Text.of("Unknown augmentation type: " + typeStr)
                                            );
                                            return 0;
                                        }
                                    }

                                    AugmentationsTypes finalType = type;
                                    player.openHandledScreen(new SimpleNamedScreenHandlerFactory(
                                            (syncId, playerInventory, playerEntity) -> {
                                                var augmentations =
                                                        ((AugmentationsHolder) playerEntity).getAugmentations();

                                                return new AugmentationsScreenHandler(
                                                        syncId,
                                                        playerInventory,
                                                        augmentations.getInventory(),
                                                        editable,
                                                        playerEntity,
                                                        finalType
                                                );
                                            },
                                            Text.of("Augmentations")
                                    ));

                                    return 1;
                                })
                        )
                )
        );
    }
}
