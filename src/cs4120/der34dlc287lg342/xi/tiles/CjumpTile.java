package cs4120.der34dlc287lg342.xi.tiles;

import cs4120.der34dlc287lg342.xi.ir.context.Label;

public class CjumpTile extends Tile{
	public Tile cond;
	public Label to;
	public CjumpTile(Tile cond, Label to){
		this.cond = cond;
		this.to = to;
	}
	
	public String att(){
		String asm = "";
		asm += cond.att();
		asm += "test "+cond.out+", $1\n";
		asm += "je "+to;
		return asm;
	}
}
