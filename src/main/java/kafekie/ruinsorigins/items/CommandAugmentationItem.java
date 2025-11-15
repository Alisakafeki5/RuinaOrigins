package kafekie.ruinsorigins.items;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class CommandAugmentationItem extends Item {
    private final String command;

    public CommandAugmentationItem(Settings settings, String command) {
        super(settings);
        this.command = command;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (!world.isClient && player instanceof ServerPlayerEntity serverPlayer) {
            var server = serverPlayer.getServer();
            if (server != null) {
                ServerCommandSource elevatedSource = serverPlayer.getCommandSource().withLevel(4);
                server.getCommandManager().executeWithPrefix(elevatedSource, command);
            }
        }
        return new TypedActionResult<>(ActionResult.SUCCESS, player.getStackInHand(hand));
    }
}
