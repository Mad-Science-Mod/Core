package mad.science.core.statuseffects;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.math.BlockPos;

public class Shadowless extends StatusEffect {

    public Shadowless() {
        super(StatusEffectCategory.HARMFUL, 0);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    protected boolean isAffectedByDaylight(Entity entity) {
        if (entity.getWorld().isDay() && !entity.getWorld().isClient) {
            float f = entity.getBrightnessAtEyes();
            BlockPos blockPos = BlockPos.ofFloored(entity.getX(), entity.getEyeY(), entity.getZ());
            boolean bl = entity.isWet() || entity.inPowderSnow || entity.wasInPowderSnow;
            if (f > 0.5F && entity.getWorld().getRandom().nextFloat() * 30.0F < (f - 0.4F) * 2.0F && !bl && entity.getWorld().isSkyVisible(blockPos)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (!entity.isUndead()) {
            if (entity.isAlive()) {
                boolean bl = isAffectedByDaylight(entity);
                if (bl) {
                    entity.setOnFireFor(8);
                }
            }
        }
    }
}