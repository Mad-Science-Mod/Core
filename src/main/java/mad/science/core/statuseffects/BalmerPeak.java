package mad.science.core.statuseffects;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class BalmerPeak extends StatusEffect {
    public BalmerPeak() {
        super(StatusEffectCategory.BENEFICIAL, 0);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
