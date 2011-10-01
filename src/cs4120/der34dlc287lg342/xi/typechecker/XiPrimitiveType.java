package cs4120.der34dlc287lg342.xi.typechecker;

import java.util.ArrayList;

import edu.cornell.cs.cs4120.util.VisualizableTreeNode;
import edu.cornell.cs.cs4120.xi.AbstractSyntaxNode;

public class XiPrimitiveType implements XiType {
	public String type;
	ArrayList<VisualizableTreeNode> dimension;
	
	ArrayList<Integer> static_dimension;
	
	public XiPrimitiveType(String type, ArrayList<VisualizableTreeNode> dimension){
		this.type = type;
		this.dimension = dimension;
		this.static_dimension = null;
	}
	
	public XiPrimitiveType(String type){
		this(type, new ArrayList<VisualizableTreeNode>());
	}
	
//	public ArrayList<Integer> staticDimension(){
//		// check if dimension is static
//		if (static_dimension != null)
//			return static_dimension;
//		
//		// typecheck 
//		
//		return static_dimension;
//	}
	
	@Override
	public String toString(){
		String t = type;
		for (VisualizableTreeNode i : dimension){
			AbstractSyntaxNode node = (AbstractSyntaxNode) i;
			t += "["+(node != null?node.label():"")+"]";
		}
		return t;
	}
	
	public static XiType UNIT = new XiPrimitiveType("unit");
}
