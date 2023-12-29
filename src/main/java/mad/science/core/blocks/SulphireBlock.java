package mad.science.core.blocks;

import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SulphireBlock extends AbstractFireBlock {
    public final float damage = 2.0f;
    public SulphireBlock(Settings settings) {
        super(settings, 2.0f);
    }

    @Override
    protected boolean isFlammable(BlockState state) {return true;}

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (!entity.isFireImmune()) {
            entity.setFireTicks(entity.getFireTicks() + 1);
            if (entity.getFireTicks() == 0) {
                entity.setOnFireFor(8);
            }
        }

        entity.damage(world.getDamageSources().inWall(), this.damage);
        super.onEntityCollision(state, world, pos, entity);
    }
}
