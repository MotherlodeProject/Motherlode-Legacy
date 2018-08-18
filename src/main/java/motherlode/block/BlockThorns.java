package motherlode.block;

import mcp.MethodsReturnNonnullByDefault;
import motherlode.client.model.MotherlodeModels;
import motherlode.util.ModelUtil;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Locale;
import java.util.Random;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class BlockThorns extends MotherlodeConnectedPillar implements MotherlodeModels.ModelRegisterCallback {
    public static final IProperty<ThornVariant> VARIANT = PropertyEnum.create("variant", ThornVariant.class);

    private static final float THORN_DAMAGE = 4.0F;
    protected String name;

    BlockThorns(String name) {
        super(Material.WOOD, 3, 13);
        this.name = name;
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setHardness(2.0F);
        this.setResistance(2.0F);
        this.setSoundType(SoundType.WOOD);

        if (hasVariant())
            this.setDefaultState(this.getDefaultState().withProperty(VARIANT, ThornVariant.BROWN));
    }

    @Override
    protected IProperty[] getAdditionalProperties() {
        return new IProperty[]{VARIANT};
    }

    protected boolean hasVariant() {
        return true;
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return hasVariant() ? super.getMetaFromState(state) | state.getValue(VARIANT).ordinal() : super.getMetaFromState(state);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return hasVariant() ? super.getStateFromMeta(meta).withProperty(VARIANT, ThornVariant.values()[meta & 0b11]) : super.getStateFromMeta(meta);
    }

    @Override
    protected boolean canConnectTo(IBlockState state, IBlockState otherState, IBlockAccess world, BlockPos pos, EnumFacing connectTo) {
        return (otherState.getBlock() instanceof BlockThorns
                || otherState.getBlock() == MotherlodeBlocks.THORNS
                || otherState.getMaterial() == Material.GRASS
                || otherState.getMaterial() == Material.GROUND)
                || super.canConnectTo(state, otherState, world, pos, connectTo);
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(MotherlodeBlocks.THORNS, 1, state.getValue(VARIANT).ordinal());
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    @Deprecated
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    public void onEntityCollision(World world, BlockPos pos, IBlockState state, Entity entity) {
        entity.attackEntityFrom(DamageSource.CACTUS, THORN_DAMAGE);
    }

    @Override
    public void onEntityWalk(World world, BlockPos pos, Entity entity) {
        IBlockState state = world.getBlockState(pos);

        if (state.getBlock() instanceof BlockThorns && state.getValue(AXIS) == EnumFacing.Axis.Y)
            onEntityCollision(world, pos, state, entity);

        super.onEntityWalk(world, pos, entity);
    }

    @Deprecated
    public EnumPushReaction getPushReaction(IBlockState state) {
        return EnumPushReaction.BLOCK;
    }

    @Override
    public int quantityDropped(Random random) {
        return 0;
    }

    @Override
    public void getSubBlocks(CreativeTabs creativeTab, NonNullList<ItemStack> list) {
        for (int i = 0; i < (hasVariant() ? ThornVariant.values().length : 1); i++) {
            list.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing face) {
        return face.getAxis() != state.getValue(AXIS) ? BlockFaceShape.MIDDLE_POLE_THICK : BlockFaceShape.CENTER_BIG;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }


    @SideOnly(Side.CLIENT)
    @Override
    public void registerModel() {
        ModelUtil.registerToStateSingleVariant(this, VARIANT);
    }

    @SideOnly(Side.CLIENT)
    @Override
    @Deprecated
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        return (blockAccess.getBlockState(pos.offset(side)).getBlock() instanceof BlockThorns || super.shouldSideBeRendered(blockState, blockAccess, pos, side));
    }

    public enum ThornVariant implements IStringSerializable {
        BROWN;

        @Override
        public String getName() {
            return name().toLowerCase(Locale.ROOT);
        }
    }
}
