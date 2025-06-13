package io.github.medakoro0321;

//アイテム、ブロック追加のImports

import io.github.medakoro0321.blocks.TechnifterWorkbench;
import io.github.medakoro0321.items.sword_beast;
import io.github.medakoro0321.blocks.ExampleBlock;

//以下自動生成
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import static io.github.medakoro0321.blocks.ExampleBlock.*;
import static io.github.medakoro0321.items.TestModItems.ITEMS;

// この値はMETA-INF/neoforge.mods.tomlファイルのエントリと一致する必要があります
@Mod(TechnifterPartners.MODID)
public class TechnifterPartners
{
    // すべてが参照する共通の場所にmod IDを定義します
    public static final String MODID = "technifter_partners";
    // slf4jロガーを直接参照します
    private static final Logger LOGGER = LogUtils.getLogger();
    // "technifter_partners"の名前空間で登録されるCreativeModeTabsを保持するための遅延登録を作成します
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);



    // ID "technifter_partners:example_id"、栄養1、満腹度2の新しい食べ物アイテムを作成します
    public static final DeferredItem<Item> EXAMPLE_ITEM = ITEMS.registerSimpleItem("example_item", new Item.Properties().food(new FoodProperties.Builder()
            .alwaysEdible().nutrition(1).saturationModifier(2f).build()));

    // コンバットタブの後に配置される、サンプルアイテム用のID "technifter_partners:example_tab"を持つクリエイティブタブを作成します
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("example_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.technifter_partners")) //CreativeModeTabのタイトルの言語キー
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> EXAMPLE_ITEM.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(EXAMPLE_ITEM.get()); // サンプルアイテムをタブに追加します。独自のタブの場合、このメソッドはイベントよりも推奨されます
            }).build());

    // modクラスのコンストラクタは、modがロードされたときに最初に実行されるコードです。
    // FMLはIEventBusやModContainerのような一部のパラメータタイプを認識し、自動的にそれらを渡します。
    public TechnifterPartners(IEventBus modEventBus, ModContainer modContainer)
    {
        // modloadingのcommonSetupメソッドを登録します
        modEventBus.addListener(this::commonSetup);

        //todo:**できるなら**ItemsとかにEventBusをまとめておきたい
        //Modイベントバスに登録


        //ExampleBlock登録
        {
            ExampleBlock.ITEMS.register(modEventBus);
            ExampleBlock.BLOCKS.register(modEventBus);
        }
        //TechnifterWorkbench登録
        {
            TechnifterWorkbench.BLOCKS.register(modEventBus);
            TechnifterWorkbench.ITEMS.register(modEventBus);
        }
        //todo:ITEMS.registerを削除してファイルを移動
        ITEMS.register(modEventBus);
        // タブが登録されるように、遅延登録をmodイベントバスに登録します
        CREATIVE_MODE_TABS.register(modEventBus);

        // 興味のあるサーバーイベントやその他のゲームイベントに自身を登録します。
        // これは、このクラス (TechnifterPartners) がイベントに直接応答したい場合にのみ必要であることに注意してください。
        // 下記のonServerStarting()のように、このクラスに@SubscribeEventアノテーションが付いた関数がない場合は、この行を追加しないでください。
        NeoForge.EVENT_BUS.register(this);

        // アイテムをクリエイティブタブに登録します
        modEventBus.addListener(this::addCreative);

        // FMLがconfigファイルを作成およびロードできるように、modのModConfigSpecを登録します
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // いくつかの共通セットアップコード
        LOGGER.info("HELLO FROM COMMON SETUP");

        if (Config.logDirtBlock)
            LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));

        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    }

    // ビルディングブロックタブにサンプルブロックアイテムを追加します
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS)
            event.accept(EXAMPLE_BLOCK_ITEM);
    }

    // SubscribeEventを使用して、イベントバスが呼び出すメソッドを自動的に検出させることができます
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // サーバーが起動したときに何かをします
        LOGGER.info("HELLO from server starting");
    }

    // EventBusSubscriberを使用して、@SubscribeEventでアノテーションされたクラス内のすべての静的メソッドを自動的に登録できます
    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // いくつかのクライアントセットアップコード
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}