package mad.science.core.registry;

import mad.science.core.MadScience;
import mad.science.core.statuseffects.*;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModStatusEffects {
    public static final StatusEffect SHADOWLESS = new Shadowless();
    public static final StatusEffect WET = new Wet();
    public static final StatusEffect INCURABLE = new Incurable();
    public static final StatusEffect BALMER_PEAK = new BalmerPeak();
    public static final StatusEffect IMPLACABLE_INEBRIATION = new ImplacableInebriation();

    public static void init() {
        Registry.register(Registries.STATUS_EFFECT, new Identifier(MadScience.MODID, "shadowless"), SHADOWLESS);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(MadScience.MODID, "wet"), WET);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(MadScience.MODID, "incurable"), INCURABLE);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(MadScience.MODID, "balmer_peak"), BALMER_PEAK);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(MadScience.MODID, "implacable_inebriation"), IMPLACABLE_INEBRIATION);
    }
}