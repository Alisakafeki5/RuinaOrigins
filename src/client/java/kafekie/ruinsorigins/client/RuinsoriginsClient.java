package kafekie.ruinsorigins.client;

import kafekie.ruinsorigins.Ruinaorigins;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

@Environment(EnvType.CLIENT)
public class RuinsoriginsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandledScreens.register(Ruinaorigins.AUGMENTATIONS_SCREEN_HANDLER, AugmentationsScreen::new);
    }
}