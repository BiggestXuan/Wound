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
    public static ForgeConfigSpec.DoubleValue woundResistancePeaceful;
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
        builder.comment("General Settings").push("general");
        maxWound = builder.comment("Players can take maximum wound").defineInRange("wound",16,0,Double.MAX_VALUE);
        woundRate = builder.comment("How much of the player's damage turns into wound").defineInRange("rate",0.25,0,1);
        woundNaturalHeal = builder.comment("How much of the player heal wound in every tick").defineInRange("heal",0.00003,0,Double.MAX_VALUE);
        defaultShield = builder.comment("How much damage can the shield block by default").defineInRange("shield_protect",20,0,Double.MAX_VALUE);
        builder.pop();
        builder.comment("Difficulty Settings").push("Difficulty");
        builder.comment("How much global wound resistance can players gain under different difficulties");
        woundResistancePeaceful = builder.defineInRange("peaceful",1,0,1d);
        woundResistanceEasy = builder.define("easy",0.9d);
        woundResistanceNormal = builder.define("normal",0.5d);
        woundResistanceHard = builder.define("hard",0.2d);
        woundResistanceHardCore = builder.define("hardcore",0d);
        builder.pop();
        builder.comment("Source Settings").push("source");
        builder.comment("Which sources will cause wound");
        playerSource = builder.define("player",false);
        mobSource = builder.define("mob",true);
        fireSource = builder.define("fire",true);
        explosionSource = builder.define("explosion",true);
        fallSource = builder.define("fall",false);
        magicSource = builder.define("magic",true);
        drownSource = builder.define("drown",false);
        otherSource = builder.define("other",true);
        builder.pop();
        CONFIG = builder.build();
    }
}
