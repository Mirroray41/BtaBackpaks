package zapp.btaba.Backpack;

import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.IInventory;
import net.minecraft.core.world.World;

public class BackpackInventory implements IInventory {
    
    private ItemStack[] storedItems;
    private World world;
    private ItemStack backpackItemStack;
    
    public BackpackInventory(ItemStack[] storedItems, World world, ItemStack backpackItemStack) {
        super();
        this.storedItems = storedItems;
        this.world = world;
        this.backpackItemStack = backpackItemStack;
    }



    @Override
    public int getSizeInventory() {
        return 27;
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return this.storedItems[i];
    }

    @Override
    public ItemStack decrStackSize(int i, int j) {
        if (this.storedItems[i] != null) {
            ItemStack itemstack1;
            if (this.storedItems[i].stackSize <= j) {
                itemstack1 = this.storedItems[i];
                this.storedItems[i] = null;
            } else {
                itemstack1 = this.storedItems[i].splitStack(j);
                if (this.storedItems[i].stackSize <= 0) {
                    this.storedItems[i] = null;
                }

            }
            return itemstack1;
        } else {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemStack) {
        this.storedItems[i] = itemStack;
        if (itemStack != null && itemStack.stackSize > this.getInventoryStackLimit()) {
            itemStack.stackSize = this.getInventoryStackLimit();
        }
    }

    @Override
    public String getInvName() {
        return "Backpack";
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public void onInventoryChanged() {
        for (int i = 0; i < 27; i++) {
            if (this.storedItems[i] != null) {
                BackpackItem.getOrCreateSavedData(backpackItemStack, world, storedItems).id[i] = (short) this.storedItems[i].itemID;
                BackpackItem.getOrCreateSavedData(backpackItemStack, world, storedItems).count[i] = (byte) this.storedItems[i].stackSize;
                BackpackItem.getOrCreateSavedData(backpackItemStack, world, storedItems).damage[i] = (short) this.storedItems[i].getItemDamageForDisplay();
                BackpackItem.getOrCreateSavedData(backpackItemStack, world, storedItems).metadata[i] = (byte) this.storedItems[i].getMetadata();
            } else {
                BackpackItem.getOrCreateSavedData(backpackItemStack, world, storedItems).id[i] = 0;
                BackpackItem.getOrCreateSavedData(backpackItemStack, world, storedItems).count[i] = 0;
                BackpackItem.getOrCreateSavedData(backpackItemStack, world, storedItems).damage[i] = 0;
                BackpackItem.getOrCreateSavedData(backpackItemStack, world, storedItems).metadata[i] = 0;
            }
        }
        BackpackItem.getOrCreateSavedData(backpackItemStack, world, storedItems).save(backpackItemStack.getData());
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return true;
    }
}
