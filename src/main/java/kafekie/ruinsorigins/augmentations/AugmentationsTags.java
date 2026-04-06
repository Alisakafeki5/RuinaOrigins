package kafekie.ruinsorigins.augmentations;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class AugmentationsTags {
    public static final TagKey<Item> MECHA_ITEMS = TagKey.of(RegistryKeys.ITEM, new Identifier("ruins-origins", "mecha"));
    public static final TagKey<Item> INJECTION_ITEMS = TagKey.of(RegistryKeys.ITEM, new Identifier("ruins-origins", "injections"));
    public static final TagKey<Item> TATTOO_ITEMS = TagKey.of(RegistryKeys.ITEM, new Identifier("ruins-origins", "ink"));
    public static final TagKey<Item> IMPLANT_ITEMS = TagKey.of(RegistryKeys.ITEM, new Identifier("ruins-origins", "implants"));

}

