package net.je.block.entity;

import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import it.unimi.dsi.fastutil.objects.Object2IntMap.Entry;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.je.block.ModBlocks;
import net.je.item.ModItems;
import net.je.recipe.EndStoneFurnaceRecipe;
import net.je.recipe.ModRecipeSerializers;
import net.je.screen.EndStoneFurnaceMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeCraftingHolder;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;

public class EndStoneFurnaceBlockEntity extends BaseContainerBlockEntity implements WorldlyContainer, RecipeCraftingHolder, StackedContentsCompatible {
    protected static final int SLOT_INPUT = 0;
    protected static final int SLOT_FUEL = 1;
    protected static final int SLOT_RESULT = 2;
    public static final int DATA_LIT_TIME = 0;
    private static final int[] SLOTS_FOR_UP = new int[]{0};
    private static final int[] SLOTS_FOR_DOWN = new int[]{2, 1};
    private static final int[] SLOTS_FOR_SIDES = new int[]{1};
    public static final int DATA_LIT_DURATION = 1;
    public static final int DATA_COOKING_PROGRESS = 2;
    public static final int DATA_COOKING_TOTAL_TIME = 3;
    public static final int NUM_DATA_VALUES = 4;
    public static final int BURN_TIME_STANDARD = 200;
    public static final int BURN_COOL_SPEED = 2;
    //private final RecipeType<? extends AbstractCookingRecipe> recipeType;
    protected NonNullList<ItemStack> items = NonNullList.withSize(3, ItemStack.EMPTY);
    int litTime;
    int litDuration;
    int cookingProgress;
    int cookingTotalTime;
    @Nullable
    private static volatile Map<Item, Integer> fuelCache;
    protected final ContainerData dataAccess = new ContainerData() {
        @Override
        public int get(int p_58431_) {
            switch (p_58431_) {
                case 0:
                    return EndStoneFurnaceBlockEntity.this.litTime;
                case 1:
                    return EndStoneFurnaceBlockEntity.this.litDuration;
                case 2:
                    return EndStoneFurnaceBlockEntity.this.cookingProgress;
                case 3:
                    return EndStoneFurnaceBlockEntity.this.cookingTotalTime;
                default:
                    return 0;
            }
        }

        @Override
        public void set(int p_58433_, int p_58434_) {
            switch (p_58433_) {
                case 0:
                    EndStoneFurnaceBlockEntity.this.litTime = p_58434_;
                    break;
                case 1:
                    EndStoneFurnaceBlockEntity.this.litDuration = p_58434_;
                    break;
                case 2:
                    EndStoneFurnaceBlockEntity.this.cookingProgress = p_58434_;
                    break;
                case 3:
                    EndStoneFurnaceBlockEntity.this.cookingTotalTime = p_58434_;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    };
    private final Object2IntOpenHashMap<ResourceLocation> recipesUsed = new Object2IntOpenHashMap<>();
    private final RecipeManager.CachedCheck<SingleRecipeInput, ? extends AbstractCookingRecipe> quickCheck;

    @SuppressWarnings("unchecked")
	public EndStoneFurnaceBlockEntity(
        BlockPos pPos, BlockState pBlockState
    ) {
        super(ModBlockEntities.END_STONE_FURNACE_BE.get(), pPos, pBlockState);
        //this.quickCheck = RecipeManager.createCheck((RecipeType)EndStoneFurnaceRecipe.TYPE1);
        //this.recipeType = EndStoneFurnaceRecipe.TYPE1;
        this.quickCheck = RecipeManager.createCheck((RecipeType<EndStoneFurnaceRecipe>) ModRecipeSerializers.END_STONE_FURNACE_RECIPE_TYPE.get());
        //this.recipeType = ModRecipeSerializers.END_STONE_FURNACE_RECIPE_TYPE.get();
    }


    public static Map<Item, Integer> getFuel() {
        Map<Item, Integer> map = fuelCache;
        if (map != null) {
            return map;
        } else {
            Map<Item, Integer> map1 = Maps.newLinkedHashMap();
            add(map1, ModItems.VOIDBLIGHT_BUCKET.get(), 100);
            add(map1, ModBlocks.VOIDMASS.get().asItem(), 10);
            add(map1, ModBlocks.VOIDBLOOM.get().asItem(), 10);
            fuelCache = map1;
            return map1;
        }
    }


    private static void add(Map<Item, Integer> pMap, ItemLike pItem, int pBurnTime) {
        Item item = pItem.asItem();
        pMap.put(item, pBurnTime);
    }

    public static void invalidateCache() {
        fuelCache = null;
    }

    private boolean isLit() {
        return this.litTime > 0;
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(pTag, this.items, pRegistries);
        this.litTime = pTag.getInt("BurnTime");
        this.cookingProgress = pTag.getInt("CookTime");
        this.cookingTotalTime = pTag.getInt("CookTimeTotal");
        this.litDuration = this.getBurnDuration(this.items.get(1));
        CompoundTag compoundtag = pTag.getCompound("RecipesUsed");

        for (String s : compoundtag.getAllKeys()) {
            this.recipesUsed.put(ResourceLocation.parse(s), compoundtag.getInt(s));
        }
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.saveAdditional(pTag, pRegistries);
        pTag.putInt("BurnTime", this.litTime);
        pTag.putInt("CookTime", this.cookingProgress);
        pTag.putInt("CookTimeTotal", this.cookingTotalTime);
        ContainerHelper.saveAllItems(pTag, this.items, pRegistries);
        CompoundTag compoundtag = new CompoundTag();
        this.recipesUsed.forEach((p_187449_, p_187450_) -> compoundtag.putInt(p_187449_.toString(), p_187450_));
        pTag.put("RecipesUsed", compoundtag);
    }

    public static void serverTick(Level pLevel, BlockPos pPos, BlockState pState, EndStoneFurnaceBlockEntity pBlockEntity) {
        boolean flag = pBlockEntity.isLit();
        boolean flag1 = false;
        if (pBlockEntity.isLit()) {
            pBlockEntity.litTime--;
        }

        ItemStack itemstack = pBlockEntity.items.get(1);
        ItemStack itemstack1 = pBlockEntity.items.get(0);
        boolean flag2 = !itemstack1.isEmpty();
        boolean flag3 = !itemstack.isEmpty();
        if (pBlockEntity.isLit() || flag3 && flag2) {
            RecipeHolder<?> recipeholder;
            if (flag2) {
                recipeholder = pBlockEntity.quickCheck.getRecipeFor(new SingleRecipeInput(itemstack1), pLevel).orElse(null);
            } else {
                recipeholder = null;
            }

            int i = pBlockEntity.getMaxStackSize();
            if (!pBlockEntity.isLit() && pBlockEntity.canBurn(pLevel.registryAccess(), recipeholder, pBlockEntity.items, i)) {
                pBlockEntity.litTime = pBlockEntity.getBurnDuration(itemstack);
                pBlockEntity.litDuration = pBlockEntity.litTime;
                if (pBlockEntity.isLit()) {
                    flag1 = true;
                    if (itemstack.hasCraftingRemainingItem()) {
                        pBlockEntity.items.set(1, itemstack.getCraftingRemainingItem());
                    } else
                    if (flag3) {
                        //Item item = itemstack.getItem();
                        itemstack.shrink(1);
                        if (itemstack.isEmpty()) {
                            pBlockEntity.items.set(1, itemstack.getCraftingRemainingItem());
                        }
                    }
                }
            }

            if (pBlockEntity.isLit() && pBlockEntity.canBurn(pLevel.registryAccess(), recipeholder, pBlockEntity.items, i)) {
                pBlockEntity.cookingProgress++;
                if (pBlockEntity.cookingProgress == pBlockEntity.cookingTotalTime) {
                    pBlockEntity.cookingProgress = 0;
                    pBlockEntity.cookingTotalTime = getTotalCookTime(pLevel, pBlockEntity);
                    if (pBlockEntity.burn(pLevel.registryAccess(), recipeholder, pBlockEntity.items, i)) {
                        pBlockEntity.setRecipeUsed(recipeholder);
                    }

                    flag1 = true;
                }
            } else {
                pBlockEntity.cookingProgress = 0;
            }
        } else if (!pBlockEntity.isLit() && pBlockEntity.cookingProgress > 0) {
            pBlockEntity.cookingProgress = Mth.clamp(pBlockEntity.cookingProgress - 2, 0, pBlockEntity.cookingTotalTime);
        }

        if (flag != pBlockEntity.isLit()) {
            flag1 = true;
            pState = pState.setValue(AbstractFurnaceBlock.LIT, Boolean.valueOf(pBlockEntity.isLit()));
            pLevel.setBlock(pPos, pState, 3);
        }

        if (flag1) {
            setChanged(pLevel, pPos, pState);
        }
    }

    @SuppressWarnings("unchecked")
	private boolean canBurn(RegistryAccess pRegistryAccess, @Nullable RecipeHolder<?> pRecipe, NonNullList<ItemStack> pInventory, int pMaxStackSize) {
        if (!pInventory.get(0).isEmpty() && pRecipe != null) {
            ItemStack itemstack = ((RecipeHolder<net.minecraft.world.item.crafting.Recipe<SingleRecipeInput>>)pRecipe).value().assemble(new SingleRecipeInput(this.getItem(0)), pRegistryAccess);
            if (itemstack.isEmpty()) {
                return false;
            } else {
                ItemStack itemstack1 = pInventory.get(2);
                if (itemstack1.isEmpty()) {
                    return true;
                } else if (!ItemStack.isSameItemSameComponents(itemstack1, itemstack)) {
                    return false;
                } else {
                    return (itemstack1.getCount() + itemstack.getCount() <= pMaxStackSize && itemstack1.getCount() + itemstack.getCount() <= itemstack1.getMaxStackSize()) // Forge fix: make furnace respect stack sizes in furnace recipes
                        ? true
                        : itemstack1.getCount() + itemstack.getCount() <= itemstack.getMaxStackSize(); // Forge fix: make furnace respect stack sizes in furnace recipes
                }
            }
        } else {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
	private boolean burn(RegistryAccess pRegistryAccess, @Nullable RecipeHolder<?> pRecipe, NonNullList<ItemStack> pInventory, int pMaxStackSize) {
        if (pRecipe != null && canBurn(pRegistryAccess, pRecipe, pInventory, pMaxStackSize)) {
            ItemStack itemstack = pInventory.get(0);
            ItemStack itemstack1 = ((RecipeHolder<net.minecraft.world.item.crafting.Recipe<SingleRecipeInput>>)pRecipe).value().assemble(new SingleRecipeInput(this.getItem(0)), pRegistryAccess);
            ItemStack itemstack2 = pInventory.get(2);
            if (itemstack2.isEmpty()) {
                pInventory.set(2, itemstack1.copy());
            } else if (ItemStack.isSameItemSameComponents(itemstack2, itemstack1)) {
                itemstack2.grow(itemstack1.getCount());
            }

            itemstack.shrink(1);
            return true;
        } else {
            return false;
        }
    }

    protected int getBurnDuration(ItemStack pFuel) {
        if (pFuel.is(ModItems.VOIDBLIGHT_BUCKET.get())) {
        	return 100;
        } else if (pFuel.is(ModBlocks.VOIDBLOOM.get().asItem()) || pFuel.is(ModBlocks.VOIDMASS.get().asItem())) {
        	return 10;
        } else {
        	return 0;
        }
    }

    private static int getTotalCookTime(Level pLevel, EndStoneFurnaceBlockEntity pBlockEntity) {
        SingleRecipeInput singlerecipeinput = new SingleRecipeInput(pBlockEntity.getItem(0));
        return pBlockEntity.quickCheck.getRecipeFor(singlerecipeinput, pLevel).map(p_296950_ -> p_296950_.value().getCookingTime()).orElse(200);
    }

    public static boolean isFuel(ItemStack pStack) {
        return pStack.is(ModItems.VOIDBLIGHT_BUCKET.get()) || pStack.is(ModBlocks.VOIDBLOOM.get().asItem()) || pStack.is(ModBlocks.VOIDMASS.get().asItem());
    }

    @Override
    public int[] getSlotsForFace(Direction pSide) {
        if (pSide == Direction.DOWN) {
            return SLOTS_FOR_DOWN;
        } else {
            return pSide == Direction.UP ? SLOTS_FOR_UP : SLOTS_FOR_SIDES;
        }
    }

    @Override
    public boolean canPlaceItemThroughFace(int pIndex, ItemStack pItemStack, @Nullable Direction pDirection) {
        return this.canPlaceItem(pIndex, pItemStack);
    }

    @Override
    public boolean canTakeItemThroughFace(int pIndex, ItemStack pStack, Direction pDirection) {
        return pDirection == Direction.DOWN && pIndex == 1 ? pStack.is(Items.WATER_BUCKET) || pStack.is(Items.BUCKET) : true;
    }

    @Override
    public int getContainerSize() {
        return this.items.size();
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> pItems) {
        this.items = pItems;
    }

    @Override
    public void setItem(int pIndex, ItemStack pStack) {
        ItemStack itemstack = this.items.get(pIndex);
        boolean flag = !pStack.isEmpty() && ItemStack.isSameItemSameComponents(itemstack, pStack);
        this.items.set(pIndex, pStack);
        pStack.limitSize(this.getMaxStackSize(pStack));
        if (pIndex == 0 && !flag) {
            this.cookingTotalTime = getTotalCookTime(this.level, this);
            this.cookingProgress = 0;
            this.setChanged();
        }
    }

    @Override
    public boolean canPlaceItem(int pIndex, ItemStack pStack) {
        if (pIndex == 2) {
            return false;
        } else if (pIndex != 1) {
            return true;
        } else {
            ItemStack itemstack = this.items.get(1);
            return pStack.is(ModItems.VOIDBLIGHT_BUCKET.get())
            		|| pStack.is(ModBlocks.VOIDBLOOM.get().asItem())
            		|| pStack.is(ModBlocks.VOIDMASS.get().asItem())
            		|| pStack.is(Items.BUCKET) && !itemstack.is(Items.BUCKET);
        }
    }

    @Override
    public void setRecipeUsed(@Nullable RecipeHolder<?> pRecipe) {
        if (pRecipe != null) {
            ResourceLocation resourcelocation = pRecipe.id();
            this.recipesUsed.addTo(resourcelocation, 1);
        }
    }

    @Nullable
    @Override
    public RecipeHolder<?> getRecipeUsed() {
        return null;
    }

    @Override
    public void awardUsedRecipes(Player pPlayer, List<ItemStack> pItems) {
    }

    public void awardUsedRecipesAndPopExperience(ServerPlayer pPlayer) {
        List<RecipeHolder<?>> list = this.getRecipesToAwardAndPopExperience(pPlayer.serverLevel(), pPlayer.position());
        pPlayer.awardRecipes(list);

        for (RecipeHolder<?> recipeholder : list) {
            if (recipeholder != null) {
                pPlayer.triggerRecipeCrafted(recipeholder, this.items);
            }
        }

        this.recipesUsed.clear();
    }

    public List<RecipeHolder<?>> getRecipesToAwardAndPopExperience(ServerLevel pLevel, Vec3 pPopVec) {
        List<RecipeHolder<?>> list = Lists.newArrayList();

        for (Entry<ResourceLocation> entry : this.recipesUsed.object2IntEntrySet()) {
            pLevel.getRecipeManager().byKey(entry.getKey()).ifPresent(p_296949_ -> {
                list.add(p_296949_);
                createExperience(pLevel, pPopVec, entry.getIntValue(), ((AbstractCookingRecipe)p_296949_.value()).getExperience());
            });
        }

        return list;
    }

    private static void createExperience(ServerLevel pLevel, Vec3 pPopVec, int pRecipeIndex, float pExperience) {
        int i = Mth.floor(pRecipeIndex * pExperience);
        float f = Mth.frac(pRecipeIndex * pExperience);
        if (f != 0.0F && Math.random() < f) {
            i++;
        }

        ExperienceOrb.award(pLevel, pPopVec, i);
    }

    @Override
    public void fillStackedContents(StackedContents pHelper) {
        for (ItemStack itemstack : this.items) {
            pHelper.accountStack(itemstack);
        }
    }

    net.minecraftforge.common.util.LazyOptional<? extends net.minecraftforge.items.IItemHandler>[] handlers =
        net.minecraftforge.items.wrapper.SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);

    @Override
    public <T> net.minecraftforge.common.util.LazyOptional<T> getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, @Nullable Direction facing) {
        if (capability == net.minecraftforge.common.capabilities.ForgeCapabilities.ITEM_HANDLER && facing != null && !this.remove) {
            return switch (facing) {
                case UP -> handlers[0].cast();
                case DOWN -> handlers[1].cast();
                default -> handlers[2].cast();
            };
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        for (LazyOptional<? extends IItemHandler> handler : handlers) {
            handler.invalidate();
        }
    }

    @Override
    public void reviveCaps() {
        super.reviveCaps();
        this.handlers = net.minecraftforge.items.wrapper.SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);
    }

	@Override
	protected Component getDefaultName() {
		return Component.translatable("container.je.end_stone_furnace");
	}

	@Override
	protected AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory) {
		return new EndStoneFurnaceMenu(pContainerId, pInventory, this, this.dataAccess);
	}
}
