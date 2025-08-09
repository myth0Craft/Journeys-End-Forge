package net.je.worldgen;

import net.je.JourneysEnd;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModStructurePieceTypes {

	public static final DeferredRegister<StructurePieceType> STRUCTURE_PIECE_TYPES =
			DeferredRegister.create(Registries.STRUCTURE_PIECE, JourneysEnd.MODID);

	public static final RegistryObject<StructurePieceType> SHADOW_TOWER_PIECE =
			STRUCTURE_PIECE_TYPES.register("shadow_tower_piece", () -> (StructurePieceType) (nbt, manager) ->
					new ShadowTowerPiece(ModStructurePieceTypes.SHADOW_TOWER_PIECE.get(), manager, nbt.structureTemplateManager()));

					/*(StructurePieceSerializationContext, compoundTag) ->
					new ShadowTowerPiece(compoundTag, StructurePieceSerializationContext.structureTemplateManager()));*/

	public static void register(IEventBus eventBus) {
		STRUCTURE_PIECE_TYPES.register(eventBus);
	}

}
