package io.github.medakoro0321.blocks.Entities;

import com.mojang.serialization.MapCodec;
import io.github.medakoro0321.blocks.TechnifterWorkbench;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

//extendsでBaseEntityBlockを継承
public class TechnifterWorkbenchBlock extends BaseEntityBlock {
    public static Block Block;
    //当たり判定を追加(SHAPE)
    public static final VoxelShape SHAPE = Block.box(2, 0, 2, 14, 13, 14);
    //コーデック
    public static final MapCodec<TechnifterWorkbenchBlock> CODEC = simpleCodec(TechnifterWorkbenchBlock::new);


    public TechnifterWorkbenchBlock(Properties properties) {
        super(properties);
    }

    //上書きしてそのShapeを返す
    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;

    }

    //モデルのレンダリングシェイプを返す必要がある
    //行わないとブロック自体が見えない
    @Override
    protected RenderShape getRenderShape(BlockState state) {
        //todo:RenderShape.MODEL;に変更 なんでか分からんけどエラーが出る:woozy_face:
        return super.getRenderShape(state);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return null;
    }

    //todo:ここのメソッドたちを理解する
    //todo:https://www.youtube.com/watch?v=sQzHmHba92o&list=PLKGarocXCE1G6CQOoiYdMVx-E1d9F_itF&index=55&t=319
    //ON?削除メソッド
    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        //この特定のブロックが破壊された際にその内容物もすべてワールドに流出させるようにする
        super.onRemove(state, level, pos, newState, movedByPiston);
    }

    //use item on?削除メソッド

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }
}
