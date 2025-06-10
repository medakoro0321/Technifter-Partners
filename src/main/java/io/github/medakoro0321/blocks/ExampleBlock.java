package io.github.medakoro0321.blocks;

import io.github.medakoro0321.TechnifterPartners;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ExampleBlock {

    // "technifter_partners"の名前空間で登録されるブロックを保持するための遅延登録を作成します
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(TechnifterPartners.MODID);
    // "technifter_partners"の名前空間で登録されるアイテムを保持するための遅延登録を作成します
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TechnifterPartners.MODID);
    // 名前空間とパスを組み合わせて、ID "technifter_partners:example_block"を持つ新しいブロックを作成します
    public static final DeferredBlock<Block> EXAMPLE_BLOCK = BLOCKS.registerSimpleBlock("example_block", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    // 名前空間とパスを組み合わせて、ID "technifter_partners:example_block"を持つ新しいBlockItemを作成します
    public static final DeferredItem<BlockItem> EXAMPLE_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("example_block", EXAMPLE_BLOCK);
}
