package kafekie.ruinsorigins.power;

import kafekie.ruinsorigins.augmentations.AugmentationsBodyParts;
import kafekie.ruinsorigins.augmentations.AugmentationsScreenHandler;
import kafekie.ruinsorigins.augmentations.AugmentationsTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;

import java.util.function.Consumer;

public class AugmentationsDetector extends AugmentationsScreenHandler.FilteredSlot {
    private final PlayerEntity player;
    private final Consumer<ItemStack> onChanged;

    private final AugmentationsScreenHandler handler;

    public AugmentationsDetector(
            AugmentationsScreenHandler handler,
            Inventory inventory,
            int index,
            int x,
            int y,
            boolean editable,
            AugmentationsTypes type,
            AugmentationsBodyParts bodyPart,
            PlayerEntity player,
            Consumer<ItemStack> onChanged
    ) {
        super(handler, inventory, index, x, y, editable, type, AugmentationsBodyParts.ANY);
        this.handler = handler;
        this.player = player;
        this.onChanged = onChanged;
    }

    @Override
    public void setStack(ItemStack stack) {
        super.setStack(stack);
        AugmentationsChecker.onPut(player, stack, getIndex());
        if (onChanged != null) {
            onChanged.accept(stack);
        }
    }

    @Override
    public ItemStack takeStack(int amount) {
        ItemStack removed = super.takeStack(amount);
        AugmentationsChecker.onRemove(player, removed, getIndex());
        return removed;
    }

    @Override
    public void onTakeItem(PlayerEntity player, ItemStack stack) {
        super.onTakeItem(player, stack);
        AugmentationsChecker.onRemove(player, stack, getIndex());
    }
}