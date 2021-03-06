/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Jan 24, 2015, 4:43:16 PM (GMT)]
 */
package vazkii.botania.common.item.lens;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import vazkii.botania.api.internal.IManaBurst;

public class LensWeight extends Lens {

	@Override
	public boolean collideBurst(IManaBurst burst, EntityThrowable entity, MovingObjectPosition pos, boolean isManaBlock, boolean dead, ItemStack stack) {
		if(!burst.isFake()) {
			int x = pos.blockX;
			int y = pos.blockY;
			int z = pos.blockZ;
			Block block = entity.worldObj.getBlock(x, y, z);
			Block blockBelow = entity.worldObj.getBlock(x, y - 1, z);
			if(blockBelow.isAir(entity.worldObj, x, y - 1, z) && block.getBlockHardness(entity.worldObj, x, y, z) != -1 && entity.worldObj.getTileEntity(x, y, z) == null) {
				EntityFallingBlock falling = new EntityFallingBlock(entity.worldObj, x + 0.5, y + 0.5, z + 0.5, block, entity.worldObj.getBlockMetadata(x, y, z));
				if(!entity.worldObj.isRemote)
					entity.worldObj.spawnEntityInWorld(falling);
			}
		}

		return dead;
	}

}
