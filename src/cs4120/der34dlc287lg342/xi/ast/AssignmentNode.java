package cs4120.der34dlc287lg342.xi.ast;

import java.util.ArrayList;

import edu.cornell.cs.cs4120.util.VisualizableTreeNode;
import edu.cornell.cs.cs4120.xi.AbstractSyntaxNode;
import edu.cornell.cs.cs4120.xi.Position;

public class AssignmentNode extends AbstractSyntaxTree {

	protected Position position;
	protected AbstractSyntaxNode id, expr;
	protected ArrayList<VisualizableTreeNode> children;
	public AssignmentNode(IdNode id, AbstractSyntaxNode expr, Position position){
		this.id = id;
		this.expr = expr;
		this.position = position;
		children = new ArrayList<VisualizableTreeNode>();
		children.add(id);
		children.add(expr);
	}
	
	@Override
	public Position position() {
		return position;
	}

	@Override
	public Iterable<VisualizableTreeNode> children() {
		return children;
	}

	@Override
	public String label() {
		return "ASSIGNMENT";
	}

}
