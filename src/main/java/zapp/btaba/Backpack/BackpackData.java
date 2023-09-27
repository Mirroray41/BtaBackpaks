package zapp.btaba.Backpack;

import com.mojang.nbt.CompoundTag;
import net.minecraft.core.world.saveddata.SavedData;

public class BackpackData extends SavedData {

    public Short[] id = new Short[27];
    public Byte[] count = new Byte[27];
    public Short[] damage = new Short[27];
    public Byte[] metadata = new Byte[27];

    public BackpackData(String s) {
        super(s);
    }

    @Override
    public void load(CompoundTag compoundTag) {
        for (int i = 0; i < 27; i++) {
            this.id[i] = compoundTag.getShort("id_"+i);
            this.count[i] = compoundTag.getByte("count_"+i);
            this.damage[i] =  compoundTag.getShort("damage_"+i);
            this.metadata[i] = compoundTag.getByte("metadata_"+i);
        }
    }

    @Override
    public void save(CompoundTag compoundTag) {
        for (int i = 0; i < 27; i++) {
            compoundTag.putShort("id_"+i, (this.id[i] != null) ? this.id[i] : 0);
            compoundTag.putByte("count_"+i, (this.count[i] != null) ? this.count[i] : 0);
            compoundTag.putShort("damage_"+i, (this.damage[i] != null) ? this.damage[i] : 0);
            compoundTag.putByte("metadata_"+i, (this.metadata[i] != null) ? this.metadata[i] : 0);
        }
    }
}
