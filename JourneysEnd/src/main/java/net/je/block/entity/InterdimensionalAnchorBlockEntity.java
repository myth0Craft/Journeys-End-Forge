package net.je.block.entity;

//import net.je.block.custom.InterdimensionalAnchorBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.chat.LoggedChatMessage.Player;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.players.PlayerList;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.dimension.DimensionType;

/*
 * public class InterdimensionalAnchorBlockEntity extends BlockEntity {
 * 
 * public InterdimensionalAnchorBlockEntity(BlockPos pPos, BlockState
 * pBlockState) { super(ModBlockEntities.INTERDIMENSIONAL_ANCHOR_BE.get(), pPos,
 * pBlockState); //pBlockState =
 * pBlockState.setValue(InterdimensionalAnchorBlock.IS_IN_END,
 * Boolean.valueOf(this.getLevel() == "overworld"));
 * 
 * Level level = this.getLevel(); String dim = level.dimension().toString();
 * //this.level.setBlock(pPos, pBlockState, 3);
 * //PlayerList.broadcastChatMessage("", null, null);
 * Minecraft.getInstance().player.sendSystemMessage(Component.literal(dim)); }
 * 
 * }
 */