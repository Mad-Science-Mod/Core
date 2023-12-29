package mad.science.core.statuseffects;

import mad.science.core.registry.ModStatusEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.AttributeModifierCreator;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTracker;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

import java.util.Iterator;
import java.util.Map;

public class ImplacableInebriation extends StatusEffect {
    public ImplacableInebriation() {
        super(StatusEffectCategory.NEUTRAL, 0);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        entity.removeStatusEffect(StatusEffects.SLOWNESS);
        entity.removeStatusEffect(StatusEffects.MINING_FATIGUE);
        entity.removeStatusEffect(StatusEffects.NAUSEA);
    }

}
