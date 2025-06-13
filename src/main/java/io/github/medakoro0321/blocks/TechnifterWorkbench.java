package io.github.medakoro0321.blocks;

import io.github.medakoro0321.TechnifterPartners;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class TechnifterWorkbench {

    // "technifter_partners"の名前空間で登録されるブロックを保持するための遅延登録を作成します
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(TechnifterPartners.MODID);
    // "technifter_partners"の名前空間で登録されるアイテムを保持するための遅延登録を作成します
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TechnifterPartners.MODID);

    // 名前空間とパスを組み合わせて、ID "technifter_partners:technifter_workbench"を持つ新しいブロックを作成します
    public static final DeferredBlock<Block> TECHNIFTER_WORKBENCH = BLOCKS.register("technifter_workbench",
            () -> new TechnifterWorkbenchBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .strength(2.5F)
                    .sound(net.minecraft.world.level.block.SoundType.WOOD)));

    // 名前空間とパスを組み合わせて、ID "technifter_partners:technifter_workbench"を持つ新しいBlockItemを作成します
    public static final DeferredItem<BlockItem> TECHNIFTER_WORKBENCH_ITEM = ITEMS.registerSimpleBlockItem("technifter_workbench", TECHNIFTER_WORKBENCH);

    // 作業台ブロックの実装クラス
    public static class TechnifterWorkbenchBlock extends Block {

        private static final Component CONTAINER_TITLE = Component.translatable("container.technifter_workbench");

        public TechnifterWorkbenchBlock(BlockBehaviour.Properties properties) {
            super(properties);
        }

        public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
            if (level.isClientSide) {
                return InteractionResult.SUCCESS;
            } else {
                // プレイヤーにクラフティングメニューを開く
                player.openMenu(state.getMenuProvider(level, pos));
                return InteractionResult.CONSUME;
            }
        }

        //@Override
        public MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
            return new SimpleMenuProvider((id, inventory, player) -> {
                return new CraftingMenu(id, inventory, ContainerLevelAccess.create(level, pos));
            }, CONTAINER_TITLE);
        }
    }
}