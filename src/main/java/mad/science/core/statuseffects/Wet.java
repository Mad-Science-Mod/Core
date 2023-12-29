package mad.science.core.statuseffects;

import mad.science.core.registry.ModStatusEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;

public class Wet extends StatusEffect {
    public Wet() {super(StatusEffectCategory.NEUTRAL, 0x20C620);}

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (!entity.getBlockStateAtPos().getFluidState().isIn(FluidTags.LAVA)) {
            Vec3d vec3d = entity.getVelocity();
            entity.extinguish();
            entity.getEntityWorld().addParticle(ParticleTypes.DRIPPING_WATER, entity.getX() + entity.getRandom().nextFloat() - entity.getRandom().nextFloat(), entity.getY() + entity.getRandom().nextFloat() + entity.getRandom().nextFloat() - entity.getRandom().nextFloat(), entity.getZ() + entity.getRandom().nextFloat() - entity.getRandom().nextFloat(), vec3d.x, vec3d.y, vec3d.z);
            if (entity.getBlockStateAtPos().isIn(BlockTags.FIRE)) {
                entity.getEntityWorld().playSound(null, entity.getBlockPos(), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.PLAYERS, 1.0f, 1.0f);
                entity.getEntityWorld().removeBlock(entity.getBlockPos(), false);
            }
        } else {
            entity.removeStatusEffect(ModStatusEffects.WET);
            entity.getEntityWorld().playSound(null, entity.getBlockPos(), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.PLAYERS, 1.0f, 1.0f);
        }
    }
}
