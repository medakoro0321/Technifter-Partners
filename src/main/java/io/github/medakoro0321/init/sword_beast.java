package io.github.medakoro0321.init;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;
import net.neoforged.neoforge.common.Tags;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.registries.DeferredRegister;
import java.util.function.Supplier;

import static io.github.medakoro0321.TechnifterPartners.MODID;

public class sword_beast {
    // DeferredRegisterを追加
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(net.minecraft.core.registries.Registries.ITEM, MODID);

    public static final Tier BEAST_TIER = new SimpleTier(
            // このツールが破壊できないブロックを決定するタグ。詳細は以下を参照。
            BlockTags.INCORRECT_FOR_WOODEN_TOOL,
            // 階層の耐久性を決定する。
            // 石は131、鉄は250。
            200,
            // 階層の採掘速度を決定する。剣では未使用。
            // 石は4、鉄は6を使用。
            5f,
            // 攻撃ダメージボーナスを決定する。道具によって使い方が異なる。例えば、剣は(getAttackDamageBonus() + 4)のダメージを与える。
            // 石は1、鉄は2を使用し、それぞれ剣の5と6の攻撃ダメージに対応する。
            20000000,
            // 階層のエンチャント可能性を決定する。これはこの道具のエンチャントがどの程度優れているかを表す。
            // 金は22で、銅はそれより少し低い。
            20,
            // ティアの修理成分を決定する。初期化しやすいようにサプライヤーを使用する。
            () -> Ingredient.of(Tags.Items.INGOTS_COPPER)
    );

    public static final Supplier<SwordItem> BEAST_SWORD = ITEMS.register("beast_sword", () -> new SwordItem(
            //使用するティア。
            BEAST_TIER,
            //  アイテムのプロパティです。TieredItemが処理してくれるので、ここで耐久性を設定する必要はありません。
            new Item.Properties().attributes(
                    // 各DiggerItemのクラスまたはサブクラスには、`createAttributes`メソッドがある。
                    SwordItem.createAttributes(
                            //使用するティア。
                            BEAST_TIER,
                            //  タイプ別の攻撃ダメージボーナス。剣は3、シャベルは1.5、ツルハシは1、斧と鍬は変化する。
                            3,
                            //  タイプ固有の攻撃速度修正値。プレーヤーのデフォルトの攻撃速度は4なので、希望する
                            // 1.6fにするために、-2.4fを使用する。剣は-2.4f、シャベルは-3f、つるはしは-2.8f、斧と鍬は変化する。
                            -2.4f
                    )
            )
    ));
}