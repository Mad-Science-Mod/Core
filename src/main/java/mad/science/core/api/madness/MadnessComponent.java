package mad.science.core.api.madness;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.ServerTickingComponent;
import mad.science.core.registry.ModComponents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.MathHelper;

// Madness can only be from -50 to 300
// Default is 1
public class MadnessComponent implements AutoSyncedComponent, ServerTickingComponent {
    private final PlayerEntity player;
    private long madness = 1;

    public MadnessComponent(PlayerEntity player) {
        this.player = player;
    }

    @Override
    public void serverTick() {
        // Madness from not sleeping
        if (!this.player.isSleeping() && this.player.getWorld().getTimeOfDay() == 5) {
            if (this.player instanceof ServerPlayerEntity serverPlayer) {
                long time = MathHelper.clamp(serverPlayer.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(Stats.TIME_SINCE_REST)), 1, 2147483647);
                if (time >= 24000) {
                    int xplevel = this.player.experienceLevel;
                    int days = (int) time % 24000;
                    this.madness += (days * 15) + (xplevel / 5);
                    ModComponents.MADNESS.sync(this.player);
                }
            }
        }

        if (this.player.getWorld().getTime() % 20 == 0) {
            // Saturation buff
            if (this.getMadness() >= 50) {
                if (this.player.getRandom().nextFloat() <= 0.01F) {
                    float saturation = this.player.getHungerManager().getSaturationLevel() + 0.4F;
                    this.player.getHungerManager().setSaturationLevel(saturation);
                }
            }

            // Permanent hunger
            if (this.getMadness() >= 200) {
                this.player.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 30, 2, true, false));
            }
        }
    }

    @Override
    public void readFromNbt(NbtCompound tag) {
        this.madness = MathHelper.clamp(tag.getLong("Madness"), -50, 300);
    }

    @Override
    public void writeToNbt(NbtCompound tag) {
        tag.putLong("Madness", this.madness);
    }

    public long getMadness() {
        return this.madness;
    }

    public void setMadness(long amt) {
        this.madness = MathHelper.clamp(amt, -50, 300);
    }
}
