package mad.science.core.registry;

import mad.science.core.MadScience;
import mad.science.core.statuseffects.Shadowless;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModStatusEffects {
    public static final StatusEffect SHADOWLESS = new Shadowless();

    public static void init() {
        Registry.register(Registries.STATUS_EFFECT, new Identifier(MadScience.MODID, "shadowless"), SHADOWLESS);
    }
}