package kafekie.ruinsorigins.power.condition;

import io.github.apace100.apoli.power.factory.condition.ConditionFactory;
import io.github.apace100.apoli.registry.ApoliRegistries;
import net.minecraft.entity.Entity;
import net.minecraft.registry.Registry;

public class AugmentationConditionRegistration {
    public static void register() {
        ConditionFactory<Entity> factory = AugmentationsInventoryCondition.getFactory();
        Registry.register(ApoliRegistries.ENTITY_CONDITION, factory.getSerializerId(), factory);
    }
}
