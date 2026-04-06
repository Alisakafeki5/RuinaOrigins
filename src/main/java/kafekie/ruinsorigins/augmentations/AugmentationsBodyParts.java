package kafekie.ruinsorigins.augmentations;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.text.Text;

public enum AugmentationsBodyParts {
    HEAD(1),
    EYE(5),
    HEART(6),
    BODY(2),
    LEG(4),
    ARM(3), ANY(0);
    private final int id;
    AugmentationsBodyParts(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public static AugmentationsBodyParts fromLoreLine(String line) {
        String l = line.toLowerCase();
        if (l.contains("any")) return ANY;
        if (l.contains("head")) return HEAD;
        if (l.contains("body")) return BODY;
        if (l.contains("arm"))  return ARM;
        if (l.contains("leg"))  return LEG;
        if (l.contains("eye"))  return EYE;
        if (l.contains("heart")) return HEART;
        return null;
    }

}

         //       if (line.contains("head")) return 1;
      //  if (line.contains("body")) return 2;
      //  if (line.contains("arm"))  return 3;
     //   if (line.contains("leg"))  return 4;
     //   if (line.contains("eye"))  return 5;
      //  if (line.contains("heart"))  return 6;