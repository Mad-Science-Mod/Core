package mad.science.core.registry;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import mad.science.core.MadScience;
import mad.science.core.api.madness.MadnessComponent;
import net.minecraft.util.Identifier;

public class ModComponents implements EntityComponentInitializer {
    public static final ComponentKey<MadnessComponent> MADNESS = ComponentRegistry.getOrCreate(new Identifier(MadScience.MODID, "madness"), MadnessComponent.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(MADNESS, MadnessComponent::new, RespawnCopyStrategy.NEVER_COPY);
    }
}
