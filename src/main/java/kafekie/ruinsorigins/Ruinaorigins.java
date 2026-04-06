package kafekie.ruinsorigins;

import kafekie.ruinsorigins.augmentations.*;
import kafekie.ruinsorigins.items.AugmentationsItems;
import kafekie.ruinsorigins.items.Bolus;
import kafekie.ruinsorigins.power.condition.AugmentationConditionRegistration;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.item.FoodComponents;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.ResourceType;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Ruinaorigins implements ModInitializer {

    public static final ScreenHandlerType<AugmentationsScreenHandler> AUGMENTATIONS_SCREEN_HANDLER =
            Registry.register(
                    Registries.SCREEN_HANDLER,
                    new Identifier("ruins-origins", "augmentations"),
                    new ScreenHandlerType<>(
                            AugmentationsScreenHandler::new,
                            FeatureSet.empty()
                    )
            );



    public static final Logger LOGGER = LoggerFactory.getLogger("ruins-origins");

    public static final Item HEMOBAR = new Item(new Item.Settings().food(FoodComponents.ROTTEN_FLESH));

    public static final Item CINQLOGO = new Item(new Item.Settings().food(FoodComponents.DRIED_KELP));
    public static final Item CINQLOGOWEST = new Item(new Item.Settings().food(FoodComponents.DRIED_KELP));
    public static final Item DEVYATLOGO = new Item(new Item.Settings().food(FoodComponents.DRIED_KELP));
    public static final Item DIECILOGO = new Item(new Item.Settings().food(FoodComponents.DRIED_KELP));
    public static final Item HANALOGO = new Item(new Item.Settings().food(FoodComponents.DRIED_KELP));
    public static final Item LIULOGO = new Item(new Item.Settings().food(FoodComponents.DRIED_KELP));
    public static final Item OUFILOGO = new Item(new Item.Settings().food(FoodComponents.DRIED_KELP));
    public static final Item SEVENLOGO = new Item(new Item.Settings().food(FoodComponents.DRIED_KELP));
    public static final Item SHILOGO = new Item(new Item.Settings().food(FoodComponents.DRIED_KELP));
    public static final Item ZWEILOGO = new Item(new Item.Settings().food(FoodComponents.DRIED_KELP));
    public static final Item ZWEILOGOWEST = new Item(new Item.Settings().food(FoodComponents.DRIED_KELP));
    public static final Item HCORP = new Item(new Item.Settings().food(FoodComponents.DRIED_KELP));
    public static final Item KCORP = new Item(new Item.Settings().food(FoodComponents.DRIED_KELP));
    public static final Item LIMBUS = new Item(new Item.Settings().food(FoodComponents.DRIED_KELP));
    public static final Item LCORP = new Item(new Item.Settings().food(FoodComponents.DRIED_KELP));
    public static final Item LOR = new Item(new Item.Settings().food(FoodComponents.DRIED_KELP));
    public static final Item NCORP = new Item(new Item.Settings().food(FoodComponents.DRIED_KELP));
    public static final Item TCORP = new Item(new Item.Settings().food(FoodComponents.DRIED_KELP));
    public static final Item WCORP = new Item(new Item.Settings().food(FoodComponents.DRIED_KELP));
    public static final Item BL = new Item(new Item.Settings().food(FoodComponents.DRIED_KELP));
    public static final Item BLUEREV = new Item(new Item.Settings().food(FoodComponents.DRIED_KELP));
    public static final Item CARNIVAL = new Item(new Item.Settings().food(FoodComponents.DRIED_KELP));
    public static final Item INDEX = new Item(new Item.Settings().food(FoodComponents.DRIED_KELP));
    public static final Item KUROKUMO = new Item(new Item.Settings().food(FoodComponents.DRIED_KELP));
    public static final Item MIDDLE = new Item(new Item.Settings().food(FoodComponents.DRIED_KELP));
    public static final Item RING = new Item(new Item.Settings().food(FoodComponents.DRIED_KELP));
    public static final Item SWEEPER = new Item(new Item.Settings().food(FoodComponents.DRIED_KELP));
    public static final Item THUMB = new Item(new Item.Settings().food(FoodComponents.DRIED_KELP));


    @Override
    public void onInitialize() {
        AugmentationsPackets.registerServer();
        ResourceManagerHelper.get(ResourceType.SERVER_DATA)
                .registerReloadListener(new AugmentationsInsert.WorkshopLoader());

        AugmentationConditionRegistration.register();

        Registry.register(Registries.ITEM, new Identifier("ruins-origins", "hemobar"), HEMOBAR);
        Registry.register(Registries.ITEM, new Identifier("ruins-origins", "cinq_logo"), CINQLOGO);
        Registry.register(Registries.ITEM, new Identifier("ruins-origins", "cinq_logo_west"), CINQLOGOWEST);
        Registry.register(Registries.ITEM, new Identifier("ruins-origins", "devyat_logo"), DEVYATLOGO);
        Registry.register(Registries.ITEM, new Identifier("ruins-origins", "dieci_logo"), DIECILOGO);
        Registry.register(Registries.ITEM, new Identifier("ruins-origins", "hana_logo"), HANALOGO);
        Registry.register(Registries.ITEM, new Identifier("ruins-origins", "liu_logo"), LIULOGO);
        Registry.register(Registries.ITEM, new Identifier("ruins-origins", "oufi_logo"), OUFILOGO);
        Registry.register(Registries.ITEM, new Identifier("ruins-origins", "seven_logo"), SEVENLOGO);
        Registry.register(Registries.ITEM, new Identifier("ruins-origins", "shi_logo"), SHILOGO);
        Registry.register(Registries.ITEM, new Identifier("ruins-origins", "zwei_logo"), ZWEILOGO);
        Registry.register(Registries.ITEM, new Identifier("ruins-origins", "zwei_logo_west"), ZWEILOGOWEST);
        Registry.register(Registries.ITEM, new Identifier("ruins-origins", "h_corp_logo"), HCORP);
        Registry.register(Registries.ITEM, new Identifier("ruins-origins", "k_corp_logo"), KCORP);
        Registry.register(Registries.ITEM, new Identifier("ruins-origins", "limbus_logo"), LIMBUS);
        Registry.register(Registries.ITEM, new Identifier("ruins-origins", "l_corp_logo"), LCORP);
        Registry.register(Registries.ITEM, new Identifier("ruins-origins", "lor_logo"), LOR);
        Registry.register(Registries.ITEM, new Identifier("ruins-origins", "n_corp_logo"), NCORP);
        Registry.register(Registries.ITEM, new Identifier("ruins-origins", "t_corp_logo"), TCORP);
        Registry.register(Registries.ITEM, new Identifier("ruins-origins", "w_corp_logo"), WCORP);
        Registry.register(Registries.ITEM, new Identifier("ruins-origins", "bl_logo"), BL);
        Registry.register(Registries.ITEM, new Identifier("ruins-origins", "blue_rev_logo"), BLUEREV);
        Registry.register(Registries.ITEM, new Identifier("ruins-origins", "carnival_logo"), CARNIVAL);
        Registry.register(Registries.ITEM, new Identifier("ruins-origins", "index_logo"), INDEX);
        Registry.register(Registries.ITEM, new Identifier("ruins-origins", "kurokumo_logo"), KUROKUMO);
        Registry.register(Registries.ITEM, new Identifier("ruins-origins", "middle_logo"), MIDDLE);
        Registry.register(Registries.ITEM, new Identifier("ruins-origins", "ring_logo"), RING);
        Registry.register(Registries.ITEM, new Identifier("ruins-origins", "sweeper_logo"), SWEEPER);
        Registry.register(Registries.ITEM, new Identifier("ruins-origins", "thumb_logo"), THUMB);
        Bolus.registerModItems();
        AugmentationsItems.registerModItems();
        CommandRegistrationCallback.EVENT.register(
                (dispatcher, registryAccess, environment) -> {
                    AugmentationsCommand.register(dispatcher);
                }
        );

        ServerPlayerEvents.COPY_FROM.register((oldPlayer, newPlayer, alive) -> {
            if (!alive) {
                var oldAug = ((AugmentationsHolder) oldPlayer).getAugmentations();
                var newAug = ((AugmentationsHolder) newPlayer).getAugmentations();
                newAug.copyFrom(oldAug);
            }
        });
        LOGGER.info("Face the sin. Save the E.G.O");
    }

}