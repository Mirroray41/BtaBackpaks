package zapp.btaba.Backpack;

import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import org.lwjgl.Sys;

public class BackpackItem extends Item {
    public BackpackItem(int id) {
        super(id);
    }

    ItemStack[] itemStack = new ItemStack[27];

    ItemStack current;

    @Override
    public boolean onItemUse(ItemStack useditemstack, EntityPlayer entityplayer, World world, int blockX, int blockY, int blockZ, Side side, double xPlaced, double yPlaced) {
        itemStack = new ItemStack[27];
        this.current = useditemstack;
        BackpackData backpackdata = getOrCreateSavedData(useditemstack, world, itemStack);

        for(int i = 0; i < 27; i++) {
            if(backpackdata.id[i].intValue() != 0) {
                itemStack[i] = new ItemStack(backpackdata.id[i].intValue(), backpackdata.count[i].intValue(), backpackdata.metadata[i].intValue());
            }
        }
        entityplayer.displayGUIChest(new BackpackInventory(itemStack, world, useditemstack));
        return false;
    }

    public static BackpackData getOrCreateSavedData(ItemStack stack, World world, ItemStack[] items) {
        if (stack.getMetadata() == 0) {
            stack.setMetadata(world.getUniqueDataId("backpack"));
        }

        (new StringBuilder()).append("backpack_").append(stack.getMetadata()).toString();
        BackpackData backpackdata = (BackpackData)world.getSavedData(BackpackData.class, "backpack_" + stack.getMetadata());
        if (backpackdata == null) {
            String s1 = "backpack_" + stack.getMetadata();
            backpackdata = new BackpackData(s1);
            for (int i = 0; i < 27; i++) {
                if(items[i] != null) {
                    backpackdata.id[i] = (short) items[i].itemID;
                    backpackdata.count[i] = (byte) items[i].stackSize;
                    backpackdata.damage[i] = (short) items[i].getItemDamageForDisplay();
                    backpackdata.metadata[i] = (byte) items[i].getMetadata();
                } else {
                    backpackdata.id[i] = (short) 0;
                    backpackdata.count[i] = (short) 0;
                    backpackdata.damage[i] = (short) 0;
                    backpackdata.metadata[i] = (short) 0;
                }

            }
            backpackdata.setDirty();
            world.setSavedData(s1, backpackdata);
        }

        return backpackdata;
    }
}
