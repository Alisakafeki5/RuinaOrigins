package kafekie.ruinsorigins.mixin;
import kafekie.ruinsorigins.augmentations.AugmentationsComponent;
import kafekie.ruinsorigins.augmentations.AugmentationsHolder;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin implements AugmentationsHolder {
    @Unique
    private final AugmentationsComponent augmentations = new AugmentationsComponent();

    @Override
    public AugmentationsComponent getAugmentations() {
        return augmentations;
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    private void saveAugmentations(NbtCompound nbt, CallbackInfo ci) {
        nbt.put("Augmentations", augmentations.writeToNbt());
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    private void loadAugmentations(NbtCompound nbt, CallbackInfo ci) {
        if (nbt.contains("Augmentations")) {
            augmentations.readFromNbt(nbt.getCompound("Augmentations"));
        }
    }
}