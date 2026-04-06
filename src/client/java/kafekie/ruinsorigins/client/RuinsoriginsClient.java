package kafekie.ruinsorigins.client;

import kafekie.ruinsorigins.Ruinaorigins;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class RuinsoriginsClient implements ClientModInitializer {
    private static KeyBinding OPEN_AUGMENTATIONS;
    @Override
    public void onInitializeClient() {
        HandledScreens.register(
                Ruinaorigins.AUGMENTATIONS_SCREEN_HANDLER,
                AugmentationsScreen::new
        );
        OPEN_AUGMENTATIONS = KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                        "key.ruinsorigins.open_augmentations",
                        InputUtil.Type.KEYSYM,
                        GLFW.GLFW_KEY_G,
                        "category.ruinsorigins"
                )
        );


    }
    }
