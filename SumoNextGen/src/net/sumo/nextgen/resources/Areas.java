package net.sumo.nextgen.resources;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;

public class Areas {

	public static Area COOK_AREA1_CHEF_ROOM = new Area(3205, 3217, 3212, 3212);
	public static Area COOK_AREA2_LUMBRIDGE_BASEMENT = new Area(3207, 9614, 3220, 9628);
	public static Area COOK_AREA3_LUMBRIDGE_BASEMENT_BUCKET = new Area(3217, 9628, 3212, 9623);
	public static Area COOK_AREA4_LUMBRIDGE_BASEMENT_LADDER = new Area(3207, 9614, 3210, 9618);
	public static Area COOK_AREA5_WEED_AREA = new Area(new Position[] { new Position(3158, 3297, 0),
			new Position(3162, 3299, 0), new Position(3163, 3290, 0), new Position(3158, 3293, 0) });
	public static Area COOK_AREA6_HOPPER_FLOOR_AREA = new Area(new Position(3163, 3303, 2),
			new Position(3169, 3310, 2));
	public static Area COOK_AREA7_FLOUR_BIN_AREA = new Area(3163, 3310, 3169, 3304);
	public static Area COOK_AREA8_EGG_AREA = new Area(3226, 3298, 3225, 3301);
	public static Area COOK_AREA9_COW_AREA = new Area(3253, 3270, 3255, 3275);

	public static Area SHEEP_AREA1_SHEEP_AREA = new Area(3193, 3275, 3210, 3257);
	public static Area SHEEP_AREA2_FARMER_AREA = new Area(3184, 3270, 3192, 3275);
	public static Area SHEEP_AREA3_SPIN_AREA = new Area(new Position(3208, 3212, 1), new Position(3212, 3217, 1));
	public static Area SHEEP_AREA4_FARMER_FAIL_AREA = new Area(3184, 3270, 3187, 3275);

	public static Area GHOST_AREA_1_FATHER_AREA = new Area(
			new Position[] { new Position(3240, 3215, 0), new Position(3240, 3211, 0), new Position(3238, 3211, 0),
					new Position(3238, 3209, 0), new Position(3240, 3209, 0), new Position(3240, 3204, 0),
					new Position(3247, 3204, 0), new Position(3247, 3215, 0) });

	public static Area GHOST_AREA_2_FATHER_URNHEY = new Area(3151, 3173, 3144, 3177);
	public static Area GHOST_AREA_3_GHOST_HOUSE = new Area(3252, 3195, 3247, 3190);
	public static Area GHOST_AREA_4_SKULL_AREA = new Area(3121, 9569, 3118, 9564);

	public static Area RUNE_AREA_1_DUKE_AREA = new Area(new Position(3208, 3218, 1), new Position(3213, 3225, 1));
	public static Area RUNE_AREA_2_FAIL_DUKE_AREA = new Area(new Position(3205, 3216, 1), new Position(3207, 3227, 1));
	public static Area RUNE_AREA_3_HEAD_WIZARD_AREA = new Area(3107, 9574, 3096, 9566);
	public static Area RUNE_AREA_4_FAIL_HEAD_WIZARD_AREA = new Area(3108, 9577, 3110, 9564);
	public static Area RUNE_AREA_5_AUBURY_AREA = new Area(3252, 3404, 3257, 3399);
	public static Area RUNE_AREA_6_FAIL_AUBURY_AREA = new Area(3248, 3398, 3258, 3396);

	public static Area ROMEO_AREA_1_ROMEO_AREA = new Area(3206, 3437, 3221, 3421);
	public static Area ROMEO_AREA_2_JULIET_AREA = new Area(new Position(3155, 3426, 1), new Position(3161, 3425, 1));
	public static Area ROMEO_AREA_3_FAIL_JULIET_AREA = new Area(new Position(3153, 3430, 1),
			new Position(3164, 3427, 1));
	public static Area ROMEO_AREA_4_FATHER_AREA = new Area(3252, 3488, 3259, 3471);
	public static Area ROMEO_AREA_5_APOTHECARY_AREA = new Area(3198, 3406, 3192, 3402);
	public static Area ROMEO_AREA_6_CADAVA_AREA = new Area(3271, 3375, 3279, 3371);

	public static Area GRAND_EXCHANGE_AREA = new Area(3159,3494,3170,3484);
	
	
	
	
	public static Area NORMAL_TREE_LUMBRIDGE = new Area(new Position[] { new Position(3196, 3209, 0),
			new Position(3179, 3214, 0), new Position(3153, 3213, 0), new Position(3152, 3238, 0),
			new Position(3170, 3263, 0), new Position(3185, 3266, 0), new Position(3199, 3252, 0) });

	public static Area WILLOW_TREE_LUMBRIDGE_AREA = new Area(3182, 3268, 3175, 3277);
	public static Area TREE_AREA_LUMBRIDGE = new Area(
			new Position[] { new Position(3201, 3196, 0), new Position(3200, 3234, 0), new Position(3213, 3241, 0),
					new Position(3216, 3253, 0), new Position(3197, 3257, 0), new Position(3191, 3258, 0),
					new Position(3190, 3263, 0), new Position(3182, 3270, 0), new Position(3164, 3261, 0),
					new Position(3136, 3263, 0), new Position(3132, 3214, 0) });

	public static Area OAK_TREE_LUMBRIDGE_AREA = new Area(new Position[] { new Position(3209, 3237, 0),
			new Position(3207, 3238, 0), new Position(3203, 3236, 0), new Position(3201, 3234, 0),
			new Position(3196, 3240, 0), new Position(3193, 3249, 0), new Position(3200, 3252, 0),
			new Position(3205, 3253, 0), new Position(3206, 3249, 0), new Position(3209, 3242, 0) });

	// mining
	public static Area TIN_AREA_RIMMINGTON = new Area(2982, 3238, 2986, 3235);

	public static Area COPPER_AREA_RIMMINGTON = new Area(2976, 3244, 2978, 3246);

	public static Area IRON_AREA_RIMMINGTON = new Area(2979, 3231, 2983, 3236);

	public static Area GOLD_AREA_RIMMINGTON = new Area(2973, 3232, 2979, 3235);

	// banks
	public static Area DEPOSIT_BOX_PORT_SARIM = new Area(new Position[] { new Position(3042, 3238, 0),
			new Position(3051, 3238, 0), new Position(3052, 3233, 0), new Position(3043, 3233, 0) });

	public static Area DEPOSIT_BOX_PORT_SARIM_WALKABLE = new Area(new Position[] { new Position(3046, 3237, 0),
			new Position(3048, 3237, 0), new Position(3048, 3235, 0), new Position(3046, 3236, 0) });

	public static Area BANK_AREA_DRAYNOR = new Area(new Position[] { new Position(3096, 3241, 0),
			new Position(3097, 3246, 0), new Position(3089, 3246, 0), new Position(3089, 3241, 0) });

	public static Area BANK_AREA_WALKABLE_DRAYNOR = new Area(3095, 3241, 3092, 3245);

	public static Area SEAGULL_AREA = new Area(
			new Position[] { new Position(3039, 3234, 0), new Position(3039, 3238, 0), new Position(3031, 3238, 0),
					new Position(3030, 3243, 0), new Position(3024, 3244, 0), new Position(3025, 3242, 0),
					new Position(3025, 3216, 0), new Position(3030, 3217, 0), new Position(3030, 3234, 0) });

	public static Area COW_AREA_LUMBRIDGE = new Area(
			new Position[] { new Position(3265, 3254, 0), new Position(3265, 3295, 0), new Position(3261, 3299, 0),
					new Position(3243, 3297, 0), new Position(3242, 3284, 0), new Position(3251, 3277, 0),
					new Position(3253, 3272, 0), new Position(3253, 3255, 0) });

	public static Area BANK_AREA_ALKHARID_WALKABLE = new Area(new Position[] { new Position(3273, 3160, 0),
			new Position(3269, 3160, 0), new Position(3269, 3172, 0), new Position(3272, 3174, 0) });

	public static Area SCHRIMP_AREA_DRAYNOR = new Area(3079, 3238, 3088, 3222);

}
