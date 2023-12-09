package mad.science.core.mixin;

import mad.science.core.util.CoreUtil;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Shadow protected abstract boolean shouldAlwaysDropXp();

    @Shadow protected int playerHitTimer;

    @Inject(method = "drop", at = @At("TAIL"))
    private void dropMadnessXp(DamageSource source, CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;
        if (source.getAttacker() instanceof PlayerEntity player) {
            if (CoreUtil.getMadness(player) >= 100 && CoreUtil.chance(0.1)) {
                if (entity.getWorld() instanceof ServerWorld && !entity.isExperienceDroppingDisabled() && (this.shouldAlwaysDropXp() || this.playerHitTimer > 0 && entity.shouldDropXp() && entity.getWorld().getGameRules().getBoolean(GameRules.DO_MOB_LOOT))) {
                    ExperienceOrbEntity.spawn((ServerWorld)entity.getWorld(), entity.getPos(), entity.getXpToDrop() / 2);
                }
            }
        }
    }
}
