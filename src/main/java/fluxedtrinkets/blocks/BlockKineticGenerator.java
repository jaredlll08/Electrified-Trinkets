package fluxedtrinkets.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import fluxedtrinkets.network.MessageEnergyUpdate;
import fluxedtrinkets.network.PacketHandler;
import fluxedtrinkets.tileEntity.TileEntityKineticGenerator;

public class BlockKineticGenerator extends Block implements ITileEntityProvider {

	protected BlockKineticGenerator() {
		super(Material.iron);
	}

	public void onEntityWalking(World world, int x, int y, int z, Entity entity) {
		TileEntityKineticGenerator tile = (TileEntityKineticGenerator) world.getTileEntity(x, y, z);
		if (entity instanceof EntityPlayer) {
			if (!world.isRemote) {
				int energy = new Random().nextInt(50);
				tile.generateEnergy(energy);
			}

		}

	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityKineticGenerator();
	}
}