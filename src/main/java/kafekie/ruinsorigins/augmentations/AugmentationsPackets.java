package kafekie.ruinsorigins.augmentations;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class AugmentationsPackets {

    public static final Identifier OPEN_VIEW =
            new Identifier("ruins-origins", "open_augmentations_view");

    public static void registerServer() {
        ServerPlayNetworking.registerGlobalReceiver(
                OPEN_VIEW,
                (server, player, handler, buf, responseSender) ->
                        server.execute(() -> open(player))
        );
    }

    private static void open(ServerPlayerEntity player) {
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
    }
}
