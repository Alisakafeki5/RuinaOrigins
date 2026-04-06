    package kafekie.ruinsorigins.augmentations;

    import kafekie.ruinsorigins.Ruinaorigins;
    import net.minecraft.entity.player.PlayerEntity;
    import net.minecraft.entity.player.PlayerInventory;
    import net.minecraft.inventory.Inventory;
    import net.minecraft.inventory.SimpleInventory;
    import net.minecraft.item.ItemStack;
    import net.minecraft.nbt.NbtCompound;
    import net.minecraft.nbt.NbtElement;
    import net.minecraft.nbt.NbtList;
    import net.minecraft.screen.ScreenHandler;
    import net.minecraft.screen.slot.Slot;
    import net.minecraft.server.MinecraftServer;
    import net.minecraft.server.command.ServerCommandSource;
    import net.minecraft.server.network.ServerPlayerEntity;
    import net.minecraft.text.Text;

    import java.util.ArrayList;
    import java.util.List;

    public class AugmentationsScreenHandler extends ScreenHandler {

        private final Inventory inventory;
        private final boolean editable;
        private final PlayerEntity player;
        private final AugmentationsTypes type;




        public AugmentationsScreenHandler(
                int syncId,
                PlayerInventory playerInventory,
                Inventory inventory,
                boolean editable,
                PlayerEntity player,
                AugmentationsTypes type
        ) {

            super(Ruinaorigins.AUGMENTATIONS_SCREEN_HANDLER, syncId);

            this.inventory = inventory;
            this.editable = editable;
            this.player = player;
            this.type = type;

            checkSize(inventory, 6);
            inventory.onOpen(playerInventory.player);

            if (inventory instanceof SimpleInventory simpleInv) {
                simpleInv.addListener(sender -> sendContentUpdates());
            }

            addSlot(new FilteredSlot(this, inventory, 0, 109, 19, editable, type,  AugmentationsBodyParts.HEAD));
            addSlot(new FilteredSlot(this, inventory, 1, 40, 20, editable, type, AugmentationsBodyParts.EYE));
            addSlot(new FilteredSlot(this, inventory, 2, 40, 83, editable, type, AugmentationsBodyParts.ARM));
            addSlot(new FilteredSlot(this, inventory, 3, 35, 50, editable, type, AugmentationsBodyParts.BODY));
            addSlot(new FilteredSlot(this, inventory, 4, 114, 49, editable, type, AugmentationsBodyParts.HEART));
            addSlot(new FilteredSlot(this, inventory, 5, 109, 82, editable, type, AugmentationsBodyParts.LEG));

            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 9; col++) {
                    addSlot(new Slot(
                            playerInventory,
                            col + row * 9 + 9,
                            8 + col * 18,
                            123 + row * 18
                    ));
                }
            }

            for (int i = 0; i < 9; i++) {
                addSlot(new Slot(playerInventory, i, 8 + i * 18, 181));
            }
        }
        public AugmentationsScreenHandler(int syncId, PlayerInventory playerInventory) {
            this(
                    syncId,
                    playerInventory,
                    new SimpleInventory(6),
                    true,
                    playerInventory.player,
                    AugmentationsTypes.MECHA
            );
        }


        @Override
        public ItemStack quickMove(PlayerEntity player, int invSlot) {
            return ItemStack.EMPTY;
        }

        @Override
        public boolean canUse(PlayerEntity player) {
            return inventory.canPlayerUse(player);
        }


        public void onItemInserted(int slotIndex, ItemStack stack) {
            if (!(player instanceof ServerPlayerEntity serverPlayer)) return;

            List<String> lore = FilteredSlot.getLoreWorkshop(stack);
            if (lore.isEmpty()) return;

            handleWorkshop(serverPlayer, lore);
        }

        public void onItemRemoved(int slotIndex, ItemStack stack) {
            if (!(player instanceof ServerPlayerEntity serverPlayer)) return;

            List<String> lore = FilteredSlot.getLoreWorkshop(stack);
            if (lore.isEmpty()) return;

            handleWorkshopUnequip(serverPlayer, lore);
        }


        private void handleWorkshop(ServerPlayerEntity player, List<String> lore) {
            for (var entry : AugmentationsInsert.WorkshopLoader.WORKSHOPS.values()) {
                for (AugmentationsInsert.Workshops workshop : entry) {
                    if (matchesLoreLines(workshop.contains(), lore)) {
                        runCommand(player, workshop.command());
                        return;
                    }
                }
            }
        }

        private void handleWorkshopUnequip(ServerPlayerEntity player, List<String> lore) {
            for (var entry : AugmentationsInsert.WorkshopLoader.WORKSHOPS.values()) {
                for (AugmentationsInsert.Workshops workshop : entry) {
                    if (matchesLoreLines(workshop.contains(), lore)) {
                        if (!workshop.unequip_command().isBlank()) {
                            runCommand(player, workshop.unequip_command());
                        }
                        return;
                    }
                }
            }
        }


        private boolean matchesLore(List<String> equals, List<String> lore) {
            for (String key : equals) {
                for (String line : lore) {
                    if (line.equals(key)) {
                        return true;
                    }
                }
            }
            return false;
        }
        private boolean matchesLoreLines(List<String> keys, List<String> lore) {
            if (lore.size() != keys.size()) return false;

            for (int i = 0; i < keys.size(); i++) {
                String key = keys.get(i).toLowerCase();
                String line = lore.get(i).toLowerCase();

                if (!line.contains(key)) {
                    return false;
                }
            }
            return true;
        }


        private void runCommand(ServerPlayerEntity player, String command) {
            MinecraftServer server = player.getServer();
            if (server == null) return;

            ServerCommandSource source = server.getCommandSource()
                    .withEntity(player)
                    .withPosition(player.getPos())
                    .withLevel(2);



            server.getCommandManager().executeWithPrefix(source, command);
        }




        public static class FilteredSlot extends Slot {

            private final AugmentationsScreenHandler handler;
            private final boolean editable;
            private final AugmentationsTypes type;
            private final AugmentationsBodyParts bodyPart;

            public FilteredSlot(
                    AugmentationsScreenHandler handler,
                    Inventory inventory,
                    int index,
                    int x,
                    int y,
                    boolean editable,
                    AugmentationsTypes type, AugmentationsBodyParts bodyPart
            ) {
                super(inventory, index, x, y);
                this.handler = handler;
                this.editable = editable;
                this.type = type;
                this.bodyPart = bodyPart;
            }

            @Override
            public boolean canInsert(ItemStack stack) {
                if (!editable || stack.isEmpty()) return false;

                boolean typeAllowed = switch (type) {
                    case MECHA -> stack.isIn(AugmentationsTags.MECHA_ITEMS);
                    case TATTOO -> stack.isIn(AugmentationsTags.TATTOO_ITEMS);
                    case INJECTION -> stack.isIn(AugmentationsTags.INJECTION_ITEMS);
                    default -> false;
                };

                if (!typeAllowed) return false;

                AugmentationsBodyParts itemPart = getBodyPartFromLore(stack);
                return itemPart != null && (itemPart == AugmentationsBodyParts.ANY || itemPart == bodyPart);
            }

            @Override
            public boolean canTakeItems(PlayerEntity player) {
                return editable && type == AugmentationsTypes.MECHA;
            }

            @Override
            public void setStack(ItemStack stack) {
                ItemStack old = this.getStack().copy();
                super.setStack(stack);

                if (old.isEmpty() && !stack.isEmpty()) {
                    handler.onItemInserted(getIndex(), stack.copy());
                }
            }
            @Override
            public void onTakeItem(PlayerEntity player, ItemStack stack) {
                super.onTakeItem(player, stack);

                if (!(player instanceof ServerPlayerEntity serverPlayer)) return;

                handler.onItemRemoved(getIndex(), stack.copy());
            }




            public static List<String> getLoreWorkshop(ItemStack stack) {
                List<String> lines = new ArrayList<>();
                if (!stack.hasNbt()) return lines;

                NbtCompound display = stack.getSubNbt("display");
                if (display == null) return lines;

                NbtList lore = display.getList("Lore", NbtElement.STRING_TYPE);
                if (lore.isEmpty()) return lines;

                for (int i = 0; i < lore.size(); i++) {

                    Text text = Text.Serializer.fromJson(lore.getString(i));
                    if (text != null) {
                        lines.add(text.getString());
                    }
                }

                return lines;
            }


            public static AugmentationsBodyParts getBodyPartFromLore(ItemStack stack) {
                if (!stack.hasNbt()) return null;

                NbtCompound display = stack.getSubNbt("display");
                if (display == null) return null;

                NbtList lore = display.getList("Lore", NbtElement.STRING_TYPE);
                if (lore.size() <= 1) return null;

                Text text = Text.Serializer.fromJson(lore.getString(1));
                if (text == null) return null;

                return AugmentationsBodyParts.fromLoreLine(text.getString());


        }
    }}
