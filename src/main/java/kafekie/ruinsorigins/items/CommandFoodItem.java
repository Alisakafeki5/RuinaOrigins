package kafekie.ruinsorigins.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class CommandFoodItem extends Item {

    private final String command;

    public CommandFoodItem(Settings settings, String command) {
        super(settings.food(new FoodComponent.Builder()
                .hunger(4)
                .saturationModifier(0.3F)
                .alwaysEdible()
                .build()));
        this.command = command;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        ItemStack result = super.finishUsing(stack, world, user);

        if (!world.isClient && user instanceof ServerPlayerEntity player) {
            ServerCommandSource source = player.getCommandSource();

            player.getServer().getCommandManager().executeWithPrefix(source, command);
        }

        return result;
    }
}
