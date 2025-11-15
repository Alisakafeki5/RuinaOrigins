package kafekie.ruinsorigins.power.condition;//



import io.github.apace100.apoli.data.ApoliDataTypes;
import io.github.apace100.apoli.power.factory.condition.ConditionFactory;
import io.github.apace100.apoli.util.Comparison;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataTypes;
import kafekie.ruinsorigins.augmentations.AugmentationsComponent;
import kafekie.ruinsorigins.augmentations.AugmentationsHolder;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import java.util.function.Predicate;

public class AugmentationsInventoryCondition {

    public static boolean condition(SerializableData.Instance data, Entity entity) {

        if (!(entity instanceof AugmentationsHolder holder))
            return false;

        AugmentationsComponent aug = holder.getAugmentations();
        SimpleInventory inv = aug.getInventory();

        Predicate<ItemStack> itemPredicate = data.get("item_condition");
        Comparison comparison = data.get("comparison");
        Integer slot = data.get("slot");
        int compareTo = data.get("compare_to");

        int matches = 0;

        if (slot != null) {
            int slotIndex = slot - 1;

            if (slotIndex < 0 || slotIndex >= inv.size())
                return false;

            ItemStack stack = inv.getStack(slotIndex);
            if (!stack.isEmpty() && (itemPredicate == null || itemPredicate.test(stack)))
                matches = 1;
        } else {
            for (int i = 0; i < inv.size(); i++) {
                ItemStack stack = inv.getStack(i);
                if (!stack.isEmpty() && (itemPredicate == null || itemPredicate.test(stack)))
                    matches++;
            }
        }

        return comparison.compare(matches, compareTo);
    }


    public static ConditionFactory<Entity> getFactory() {
        return new ConditionFactory<>(
                new Identifier("ruins-origins", "augmentations_inventory"),
                new SerializableData()
                        .add("slot", SerializableDataTypes.INT, null)
                        .add("item_condition", ApoliDataTypes.ITEM_CONDITION, null)
                        .add("comparison", ApoliDataTypes.COMPARISON, Comparison.GREATER_THAN)
                        .add("compare_to", SerializableDataTypes.INT, 0),
                AugmentationsInventoryCondition::condition
        );
    }
}
