package kafekie.ruinsorigins.augmentations;

import kafekie.ruinsorigins.Ruinaorigins;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class AugmentationsScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    private final boolean editable;
    private final AugmentationsTypes type;
    public AugmentationsScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(6), false, AugmentationsTypes.ALL);
    }

    public AugmentationsScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, boolean editable) {
        this(syncId, playerInventory, inventory, editable, AugmentationsTypes.ALL);
    }


    public AugmentationsScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, boolean editable, AugmentationsTypes type) {
        super(Ruinaorigins.AUGMENTATIONS_SCREEN_HANDLER, syncId);
        checkSize(inventory, 6);
        this.inventory = inventory;
        this.editable = editable;
        this.type = type;

        inventory.onOpen(playerInventory.player);

        if (inventory instanceof SimpleInventory simpleInv) {
            simpleInv.addListener(sender -> this.sendContentUpdates());
        }

        this.addSlot(new FilteredSlot(inventory, 0, 71, 13, this.editable, this.type));
        this.addSlot(new FilteredSlot(inventory, 1, 100, 14, this.editable, this.type));
        this.addSlot(new FilteredSlot(inventory, 2, 59, 38, this.editable, this.type));
        this.addSlot(new FilteredSlot(inventory, 3, 80, 38, this.editable, this.type));
        this.addSlot(new FilteredSlot(inventory, 4, 101, 38, this.editable, this.type));
        this.addSlot(new FilteredSlot(inventory, 5, 74, 76, this.editable, this.type));

        for (int m = 0; m < 3; ++m) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + m * 9 + 9, 8 + l * 18, 116 + m * 18));
            }
        }

        for (int m = 0; m < 9; ++m) {
            this.addSlot(new Slot(playerInventory, m, 8 + m * 18, 174));
        }
    }



    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        if (!editable) return ItemStack.EMPTY;

        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);

        if (slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();

            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    public Inventory getInventory() {
        return this.inventory;
    }


    private static class FilteredSlot extends Slot {
        private final boolean editable;
        private final AugmentationsTypes type;

        public FilteredSlot(Inventory inventory, int index, int x, int y, boolean editable, AugmentationsTypes type) {
            super(inventory, index, x, y);
            this.editable = editable;
            this.type = type;
        }

        @Override
        public boolean canInsert(ItemStack stack) {
            if (!editable) return false;
            if (stack.isEmpty()) return false;

            switch (type) {
                case MECHA:
                    return stack.isIn(AugmentationsTags.MECHA_ITEMS);
                case TATTOO:
                    return stack.isIn(AugmentationsTags.TATTOO_ITEMS);
                case INJECTION:
                    return stack.isIn(AugmentationsTags.INJECTION_ITEMS);
                default:
                    return false;
            }
        }

        @Override
        public boolean canTakeItems(PlayerEntity player) {
            if (!editable) return false;

            switch (type) {
                case MECHA:
                    return true;
                case INJECTION:
                    return false;
                case TATTOO:
                    return false;
                default:
                    return false;
            }
        }
    }


}
