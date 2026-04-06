package kafekie.ruinsorigins.power;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.resource.ResourceManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.Identifier;

public class AugmentationsChecker {
    private static String command;
    private static final Identifier FOLDER = new Identifier("ruina", "augments");

    public AugmentationsChecker(String command) {
        this.command = command;
    }


    public static boolean matchesDatapackAugment(PlayerEntity player, ItemStack stack) {
        if (!stack.hasNbt()) return false;
        NbtCompound nbt = stack.getNbt();

        if (!nbt.contains("power")) return false;

        String power = nbt.getString("power");

        MinecraftServer server = player.getServer();
        if (server == null) return false;

        ResourceManager manager = server.getResourceManager();

        Identifier fileId = new Identifier(FOLDER.getNamespace(),
                FOLDER.getPath() + "/" + power + ".json");

        return manager.getResource(fileId).isPresent();
    }

    public static void onChanged(PlayerEntity player, ItemStack stack) {
        if (stack.isEmpty()) {
            return;
            //слот очищен
        }
        NbtCompound nbt = stack.getNbt();
    }

    public static void onPut(PlayerEntity player, ItemStack stack, int slot) {
        if (stack.isEmpty()) return;
        ServerCommandSource source = player.getCommandSource();

        if (player.getServer() != null) {
            player.getServer().getCommandManager().executeWithPrefix(source, command);
        }
    }

    public static void onRemove(PlayerEntity player, ItemStack stack, int slot) {
        if (stack.isEmpty()) return;
        System.out.println("Removed item from" + slot + ": " + stack);
    }
}