package kafekie.ruinsorigins.items;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class Bolus {

    public static final Item MAO_BOLUS = registerItem("mao_bolus",
            new CommandFoodItem(new Item.Settings().maxCount(16),
                    "origin set @s origins:heishou ruinsorigins:heishou/mao"));

    public static final Item SI_BOLUS = registerItem("si_bolus",
            new CommandFoodItem(new Item.Settings().maxCount(16),
                    "origin set @s origins:heishou ruinsorigins:heishou/si"));

    public static final Item WU_BOLUS = registerItem("wu_bolus",
            new CommandFoodItem(new Item.Settings().maxCount(16),
                    "origin set @s origins:heishou ruinsorigins:heishou/wu"));

    public static final Item ZI_BOLUS = registerItem("zi_bolus",
            new CommandFoodItem(new Item.Settings().maxCount(16),
                    "origin set @s origins:heishou ruinsorigins:heishou/zi"));

    public static final Item CHOU_BOLUS = registerItem("chou_bolus",
            new CommandFoodItem(new Item.Settings().maxCount(16),
                    "origin set @s origins:heishou ruinsorigins:heishou/chou"));

    public static final Item YIN_BOLUS = registerItem("yin_bolus",
            new CommandFoodItem(new Item.Settings().maxCount(16),
                    "origin set @s origins:heishou ruinsorigins:heishou/yin"));

    public static final Item CHEN_BOLUS = registerItem("chen_bolus",
            new CommandFoodItem(new Item.Settings().maxCount(16),
                    "origin set @s origins:heishou ruinsorigins:heishou/chen"));
    public static final Item WEI_BOLUS = registerItem("wei_bolus",
            new CommandFoodItem(new Item.Settings().maxCount(16),
                    "origin set @s origins:heishou ruinsorigins:heishou/wei"));

    public static final Item SHEN_BOLUS = registerItem("shen_bolus",
            new CommandFoodItem(new Item.Settings().maxCount(16),
                    "origin set @s origins:heishou ruinsorigins:heishou/shen"));

    public static final Item YOU_BOLUS = registerItem("you_bolus",
            new CommandFoodItem(new Item.Settings().maxCount(16),
                    "origin set @s origins:heishou ruinsorigins:heishou/you"));

    public static final Item XU_BOLUS = registerItem("xu_bolus",
            new CommandFoodItem(new Item.Settings().maxCount(16),
                    "origin set @s origins:heishou ruinsorigins:heishou/xu"));

    public static final Item HAI_BOLUS = registerItem("hai_bolus",
            new CommandFoodItem(new Item.Settings().maxCount(16),
                    "origin set @s origins:heishou ruinsorigins:heishou/hai"));

    public static final Item SUPPRESSION_BOLUS = registerItem("suppression_bolus",
            new CommandFoodItem(new Item.Settings().maxCount(16),
                    "origin set @s origins:heishou ruinsorigins:heishou/not"));

    static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier("ruins-origins", name), item);
    }

    public static void registerModItems() {
    }
}
