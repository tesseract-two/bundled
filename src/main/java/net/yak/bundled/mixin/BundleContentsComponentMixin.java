package net.yak.bundled.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.mojang.serialization.DataResult;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.BundleItem;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BundleContents;
import net.yak.bundled.Bundled;
import org.apache.commons.lang3.math.Fraction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BundleContents.class)
public class BundleContentsComponentMixin {

//    @ModifyReturnValue(method = "getWeight(Lnet/minecraft/world/item/ItemStack;)Lorg/apache/commons/lang3/math/Fraction;", at = @At("RETURN"))
//    private static Fraction bundled$unstackablesLesserWeight(Fraction original, ItemStack stack) {
//        if (stack.get(DataComponents.BUNDLE_CONTENTS) == null && stack.get(DataComponents.BEES) == null) {
//            if (stack.is(Bundled.MAX_WEIGHT)) { // max weight tag
//                return Fraction.getFraction(1, 1);
//            }
//            else if (stack.is(Bundled.HALF_WEIGHT)) { // half weight tag
//                return Fraction.getFraction(1, 2);
//            }
//            else if (stack.is(Bundled.QUARTER_WEIGHT)) { // quarter weight tag and default for unstackables
//                return Fraction.getFraction(1, 4);
//            }
//            else if (stack.is(Bundled.EIGHTH_WEIGHT) || (stack.getMaxStackSize() == 1 && (!stack.is(Bundled.SIXTEENTH_WEIGHT)) && !stack.is(Bundled.THIRTYSECOND_WEIGHT) && !stack.is(Bundled.SIXTYFOURTH_WEIGHT))) { // eighth weight tag
//                return Fraction.getFraction(1, 8);
//            }
//            else if (stack.is(Bundled.SIXTEENTH_WEIGHT)) { // sixteenth weight tag
//                return Fraction.getFraction(1, 16);
//            }
//            else if (stack.is(Bundled.THIRTYSECOND_WEIGHT)) { // thirtysecond weight tag
//                return Fraction.getFraction(1, 32);
//            }
//            else if (stack.is(Bundled.SIXTYFOURTH_WEIGHT)) { // sixtyfourth weight tag
//                return Fraction.getFraction(1, 64);
//            }
//        }
//        return original;
//    }

    @Inject(method = "getWeight", at = @At("RETURN"), cancellable = true)
    private static void bundled$unstackablesChangeWeight(ItemInstance item, CallbackInfoReturnable<DataResult<Fraction>> cir) {
        if (item.get(DataComponents.BUNDLE_CONTENTS) == null && item.get(DataComponents.BEES) == null) {
            if (item.is(Bundled.MAX_WEIGHT)) { // max weight tag
                cir.setReturnValue(DataResult.success(Fraction.getFraction(1, 1)));
            }
            else if (item.is(Bundled.HALF_WEIGHT)) { // half weight tag
                cir.setReturnValue(DataResult.success(Fraction.getFraction(1, 2)));
            }
            else if (item.is(Bundled.QUARTER_WEIGHT)) { // quarter weight tag and default for unstackables
                cir.setReturnValue(DataResult.success(Fraction.getFraction(1, 4)));
            }
            else if (item.is(Bundled.EIGHTH_WEIGHT) || (item.getMaxStackSize() == 1 && (!item.is(Bundled.SIXTEENTH_WEIGHT)) && !item.is(Bundled.THIRTYSECOND_WEIGHT) && !item.is(Bundled.SIXTYFOURTH_WEIGHT))) { // eighth weight tag
                cir.setReturnValue(DataResult.success(Fraction.getFraction(1, 8)));
            }
            else if (item.is(Bundled.SIXTEENTH_WEIGHT)) { // sixteenth weight tag
                cir.setReturnValue(DataResult.success(Fraction.getFraction(1, 16)));
            }
            else if (item.is(Bundled.THIRTYSECOND_WEIGHT)) { // thirtysecond weight tag
                cir.setReturnValue(DataResult.success(Fraction.getFraction(1, 32)));
            }
            else if (item.is(Bundled.SIXTYFOURTH_WEIGHT)) { // sixtyfourth weight tag
                cir.setReturnValue(DataResult.success(Fraction.getFraction(1, 64)));
            }
        }
    }

    @ModifyReturnValue(method = "canItemBeInBundle", at = @At("RETURN"))
    private static boolean bundled$canBundle(boolean original, ItemStack stack) {
        return original && !stack.is(Bundled.CANNOT_NEST) && BundleItem.getFullnessDisplay(stack) == 0f;
    }

}
