package kafekie.ruinsorigins.augmentations;

import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;

public class AugmentationsComponent {
    private final SimpleInventory inventory = new SimpleInventory(6);

    public SimpleInventory getInventory() {
        return inventory;
    }

    public NbtCompound writeToNbt() {
        NbtCompound nbt = new NbtCompound();
        NbtList items = new NbtList();
        for (int i = 0; i < inventory.size(); i++) {
            ItemStack stack = inventory.getStack(i);
            if (!stack.isEmpty()) {
                NbtCompound stackNbt = new NbtCompound();
                stackNbt.putByte("Slot", (byte) i);
                stack.writeNbt(stackNbt);
                items.add(stackNbt);
            }
        }
        nbt.put("Items", items);
        return nbt;
    }

    public void readFromNbt(NbtCompound nbt) {
        NbtList items = nbt.getList("Items", 10);
        for (int i = 0; i < items.size(); ++i) {
            NbtCompound stackNbt = items.getCompound(i);
            int slot = stackNbt.getByte("Slot") & 255;
            if (slot >= 0 && slot < inventory.size()) {
                inventory.setStack(slot, ItemStack.fromNbt(stackNbt));
            }
        }
    }

    public void copyFrom(AugmentationsComponent original) {
        for (int i = 0; i < inventory.size(); i++) {
            inventory.setStack(i, original.inventory.getStack(i).copy());
        }
    }
}
