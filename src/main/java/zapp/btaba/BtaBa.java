package zapp.btaba;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.data.tag.Tag;
import net.minecraft.core.item.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.ItemHelper;
import zapp.btaba.Backpack.BackpackItem;


public class BtaBa implements ModInitializer {
    public static final String MOD_ID = "btaba";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final Item BACKPACK = registerBackpack(0, "backpack");

    private static Item registerBackpack(int id, String name) {
        return ItemHelper.createItem(MOD_ID, new BackpackItem(19000+id).setMaxStackSize(1), name, name + ".png");
    }

    @Override
    public void onInitialize() {
        LOGGER.info("BtaBa initialized.");
    }
}
