package com.varijon.tinies.IVEVCommand;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.storage.PlayerPartyStorage;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class EVCommand implements ICommand {

	private List aliases;
	public EVCommand()
	{
	   this.aliases = new ArrayList();
	   this.aliases.add("ev");
	   this.aliases.add("evs");
	}
	
	@Override
	public int compareTo(ICommand arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "/evs";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		// TODO Auto-generated method stub
		return "/evs [slot]";
	}

	@Override
	public List<String> getAliases() {
		// TODO Auto-generated method stub
		return this.aliases;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException 
	{
		if(sender.canUseCommand(4, "IVEVCommand.EVs"))
		{
			if(args.length == 0)
			{
				sender.sendMessage(new TextComponentString(TextFormatting.RED + "Usage: /evs [#]"));						
				return;
			}
			if(sender instanceof EntityPlayerMP)
			{
				EntityPlayerMP player = (EntityPlayerMP) sender;
				try
				{
					PlayerPartyStorage playerStorage =  Pixelmon.storageManager.getParty(player);

					if(!NumberUtils.isNumber(args[0]))
					{
						sender.sendMessage(new TextComponentString(TextFormatting.RED + "Invalid slot!"));			
						return;
					}

					int partyNumber = Integer.parseInt(args[0]) - 1;
					
					if(partyNumber < 0 || partyNumber > 5)
					{
						sender.sendMessage(new TextComponentString(TextFormatting.RED + "Invalid slot!"));	
						return;
					}
					
					Pokemon pixelmon = playerStorage.get(partyNumber);
					
					if (pixelmon != null)
					{
						int pixelmonEVHP = pixelmon.getStats().evs.hp;
						int pixelmonEVAttack = pixelmon.getStats().evs.attack;
						int pixelmonEVDefense = pixelmon.getStats().evs.defence;
						int pixelmonEVSpAtt = pixelmon.getStats().evs.specialAttack;
						int pixelmonEVSpDef = pixelmon.getStats().evs.specialDefence;
						int pixelmonEVSpeed = pixelmon.getStats().evs.speed;
						String pixelmonName = pixelmon.getBaseStats().pixelmonName;
						
						String testString = TextFormatting.GREEN + "HP:" + "`" + TextFormatting.GREEN + pixelmonEVHP
								+ "\n" + TextFormatting.RED + "Attack:"   + "`"  + TextFormatting.RED + pixelmonEVAttack
								+ "\n" + TextFormatting.GOLD + "Defence:" + "`" + TextFormatting.GOLD + pixelmonEVDefense
								+ "\n" + TextFormatting.LIGHT_PURPLE + "Sp.Attack:" + "`" + TextFormatting.LIGHT_PURPLE + pixelmonEVSpAtt
								+ "\n" + TextFormatting.YELLOW + "Sp.Defence:" + "`" + TextFormatting.YELLOW + pixelmonEVSpDef
								+ "\n" + TextFormatting.AQUA + "Speed:" + "`" + TextFormatting.AQUA + pixelmonEVSpeed;
						
						sender.sendMessage(new TextComponentString(TextFormatting.AQUA + pixelmonName + (pixelmonName.charAt(pixelmonName.length()-1) == 's' ? "'" : "'s") + " " + TextFormatting.WHITE + "EVs:"));
						TabText tt = new TabText(testString);
						tt.setTabs(12);

						sender.sendMessage(new TextComponentString(tt.getPage(0, false)));
						
//						sender.sendMessage(new TextComponentString(TextFormatting.GREEN + "HP:            " + pixelmonEVHP));
//						sender.sendMessage(new TextComponentString(TextFormatting.RED + "Attack:       "  + pixelmonEVAttack));
//						sender.sendMessage(new TextComponentString(TextFormatting.GOLD + "Defence:     " + pixelmonEVDefense));
//						sender.sendMessage(new TextComponentString(TextFormatting.LIGHT_PURPLE + "Sp.Attack:    " + pixelmonEVSpAtt));
//						sender.sendMessage(new TextComponentString(TextFormatting.YELLOW + "Sp.Defence:  " + pixelmonEVSpDef));
//						sender.sendMessage(new TextComponentString(TextFormatting.AQUA + "Speed:        " + pixelmonEVSpeed));
						
						int combinedEV = pixelmonEVHP + pixelmonEVAttack + pixelmonEVDefense + pixelmonEVSpAtt + pixelmonEVSpDef + pixelmonEVSpeed;
						
						sender.sendMessage(new TextComponentString(TextFormatting.GOLD + "Total: " + TextFormatting.YELLOW + combinedEV + "/510 or " + Math.round(((float)combinedEV / 510.0 * 100.0)) + "%"));
					}
					else
					{
						sender.sendMessage(new TextComponentString(TextFormatting.RED + "Party slot is empty!"));						
					}
				}
				catch(Exception ex)
				{
					//System.out.println(ex.getStackTrace());
				}
			}
			return;
		}
		else
		{
			sender.sendMessage(new TextComponentString(TextFormatting.RED + "You don't have permission to use this command"));
			return;
		}

	}

	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) 
	{
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos pos) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		// TODO Auto-generated method stub
		return false;
	}

}
