package biggestxuan.wound.config;

import net.minecraftforge.common.ForgeConfigSpec;

/**
 *  @Author Biggest_Xuan
 *  2023/01/14
 */

public class Config {
    public static ForgeConfigSpec CONFIG;
    public static ForgeConfigSpec.DoubleValue maxWound;
    public static ForgeConfigSpec.DoubleValue woundRate;
    public static ForgeConfigSpec.DoubleValue woundNaturalHeal;
    public static ForgeConfigSpec.DoubleValue defaultShield;
    public static ForgeConfigSpec.ConfigValue<Double> woundResistancePeaceful;
    public static ForgeConfigSpec.ConfigValue<Double> woundResistanceEasy;
    public static ForgeConfigSpec.ConfigValue<Double> woundResistanceNormal;
    public static ForgeConfigSpec.ConfigValue<Double> woundResistanceHard;
    public static ForgeConfigSpec.ConfigValue<Double> woundResistanceHardCore;

    public static ForgeConfigSpec.BooleanValue playerSource;
    public static ForgeConfigSpec.BooleanValue mobSource;
    public static ForgeConfigSpec.BooleanValue fireSource;
    public static ForgeConfigSpec.BooleanValue explosionSource;
    public static ForgeConfigSpec.BooleanValue fallSource;
    public static ForgeConfigSpec.BooleanValue magicSource;
    public static ForgeConfigSpec.BooleanValue drownSource;
    public static ForgeConfigSpec.BooleanValue otherSource;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.push("general");
        maxWound = builder.comment("Players can take maximum wound").defineInRange("MaxWound",16,0,Double.MAX_VALUE);
        woundRate = builder.comment("How much of the player's damage turns into wound").defineInRange("WoundRate",0.25,0,1);
        woundNaturalHeal = builder.comment("How much of the player heal wound in every tick").defineInRange("NatureHeal",0.00003,0,Double.MAX_VALUE);
        defaultShield = builder.comment("How much damage can the shield block by default").defineInRange("ShieldProtect",20,0,Double.MAX_VALUE);
        builder.pop();
        builder.comment("Wound resistance in different difficulties.\n Range: 0.0 ~ 1.0").push("difficulty");
        woundResistancePeaceful = builder.define("Peaceful",1d);
        woundResistanceEasy = builder.define("Easy",0.9d);
        woundResistanceNormal = builder.define("Normal",0.5d);
        woundResistanceHard = builder.define("Hard",0.2d);
        woundResistanceHardCore = builder.define("HardcoreMode",0d);
        builder.pop();
        builder.comment("Whether the following sources of injury will cause wound.").push("source");
        playerSource = builder.define("Player",false);
        mobSource = builder.define("Mob",true);
        fireSource = builder.define("Fire",true);
        explosionSource = builder.define("Explosion",true);
        fallSource = builder.define("Fall",false);
        magicSource = builder.define("Magic",true);
        drownSource = builder.define("Drown",false);
        otherSource = builder.define("Other",true);
        builder.pop();
        CONFIG = builder.build();
    }
}
