package kafekie.ruinsorigins.items;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.security.Permission;

import static kafekie.ruinsorigins.items.Bolus.registerItem;

public class AugmentationsItems {
    private static Item registerItem;
    public static final Item IMPLANT_1 = registerItem("implant_1", new Item(new Item.Settings().maxCount(1)));
    public static final Item IMPLANT_2 = registerItem("implant_2", new Item(new Item.Settings().maxCount(1)));
    public static final Item INK_BLACK = registerItem("ink_black", new Item(new Item.Settings().maxCount(1)));
    public static final Item INK_GREEN = registerItem("ink_green", new Item(new Item.Settings().maxCount(1)));
    public static final Item INK_RED = registerItem("ink_red", new Item(new Item.Settings().maxCount(1)));
    public static final Item INK_PINK = registerItem("ink_pink", new Item(new Item.Settings().maxCount(1)));
    public static final Item MECHANICAL_BLUE = registerItem("mechanical_blue", new Item(new Item.Settings().maxCount(1)));
    public static final Item MECHANICAL_GREEN = registerItem("mechanical_green", new Item(new Item.Settings().maxCount(1)));
    public static final Item MECHANICAL_PINK = registerItem("mechanical_pink", new Item(new Item.Settings().maxCount(1)));
    public static final Item MECHANICAL_RED = registerItem("mechanical_red", new Item(new Item.Settings().maxCount(1)));
    public static final Item SYRINGE = registerItem("syringe", new Item(new Item.Settings()));
    public static final Item SCALPEL = registerItem("scalpel",
            new CommandAugmentationItem(new Item.Settings().maxCount(1),
                    "augmentations edit mecha"));
    public static final Item TATTOO_MACHINE = registerItem("tattoo_machine",
            new CommandAugmentationItem(new Item.Settings().maxCount(1),
                    "augmentations edit tattoo"));
    public static final Item SYRINGE_BLUE = registerItem("syringe_blue",
            new CommandAugmentationItem(new Item.Settings().maxCount(16),
                    "augmentations edit injection"));
    public static final Item SYRINGE_GREEN = registerItem("syringe_green",
            new CommandAugmentationItem(new Item.Settings().maxCount(16),
                    "augmentations edit injection"));
    public static final Item SYRINGE_PINK = registerItem("syringe_pink",
            new CommandAugmentationItem(new Item.Settings().maxCount(16),
                    "augmentations edit injection"));
    public static final Item SYRINGE_RED = registerItem("syringe_red",
            new CommandAugmentationItem(new Item.Settings().maxCount(16),
                    "augmentations edit injection"));
    static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier("ruins-origins", name), item);
    }
    public static void registerModItems() {
    }
}
