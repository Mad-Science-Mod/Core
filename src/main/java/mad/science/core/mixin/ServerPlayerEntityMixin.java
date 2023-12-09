package mad.science.core.mixin;

import mad.science.core.api.madness.MadnessComponent;
import mad.science.core.registry.ModComponents;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin {

    @Inject(method = "sleep", at = @At("HEAD"))
    private void decreaseMadness(BlockPos pos, CallbackInfo ci) {
        ServerPlayerEntity player = (ServerPlayerEntity) (Object) this;
        MadnessComponent component = ModComponents.MADNESS.get(player);
        component.setMadness(component.getMadness() - (long)(component.getMadness() * 0.1));
        ModComponents.MADNESS.sync(player);
    }
}
