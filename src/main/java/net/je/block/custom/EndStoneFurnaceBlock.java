package net.je.block.custom;

import javax.annotation.Nullable;

import com.mojang.serialization.MapCodec;

import net.je.block.entity.EndStoneFurnaceBlockEntity;
import net.je.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class EndStoneFurnaceBlock extends AbstractFurnaceBlock {
	public static final MapCodec<EndStoneFurnaceBlock> CODEC = simpleCodec(EndStoneFurnaceBlock::new);

    @Override
    public MapCodec<EndStoneFurnaceBlock> codec() {
        return CODEC;
    }

    public EndStoneFurnaceBlock(BlockBehaviour.Properties p_49773_) {
        super(p_49773_);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new EndStoneFurnaceBlockEntity(pPos, pState);
    }

    @Override
    public int getLightEmission(BlockState state, BlockGetter world, BlockPos pos) {
        return state.getValue(BlockStateProperties.LIT) ? 14 : 0;
    }

    @Nullable
    protected static <T extends BlockEntity> BlockEntityTicker<T> createEndStoneFurnaceTicker(
        Level pLevel, BlockEntityType<T> pServerType, BlockEntityType<EndStoneFurnaceBlockEntity> pClientType
    ) {
        return pLevel.isClientSide ? null : createTickerHelper(pServerType, pClientType, EndStoneFurnaceBlockEntity::serverTick);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return createEndStoneFurnaceTicker(pLevel, pBlockEntityType, ModBlockEntities.END_STONE_FURNACE_BE.get());
    }

    @Override
    protected void openContainer(Level pLevel, BlockPos pPos, Player pPlayer) {
        BlockEntity blockentity = pLevel.getBlockEntity(pPos);
        if (blockentity instanceof EndStoneFurnaceBlockEntity) {
            pPlayer.openMenu((MenuProvider)blockentity);
        }
    }

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pState.getValue(LIT)) {
            double d0 = pPos.getX() + 0.5;
            double d1 = pPos.getY();
            double d2 = pPos.getZ() + 0.5;
            if (pRandom.nextDouble() < 0.2) {
                pLevel.playLocalSound(d0, d1, d2, SoundEvents.REDSTONE_TORCH_BURNOUT, SoundSource.BLOCKS, 0.1F, 0.01F, false);
            }

            Direction direction = pState.getValue(FACING);
            Direction.Axis direction$axis = direction.getAxis();
            double d4 = pRandom.nextDouble() * 0.6 - 0.3;
            double d5 = direction$axis == Direction.Axis.X ? direction.getStepX() * 0.52 : d4;
            double d6 = pRandom.nextDouble() * 9.0 / 16.0;
            double d7 = direction$axis == Direction.Axis.Z ? direction.getStepZ() * 0.52 : d4;
            pLevel.addParticle(ParticleTypes.SMOKE, d0 + d5, d1 + d6, d2 + d7, 0.0, 0.0, 0.0);
        }
    }
}