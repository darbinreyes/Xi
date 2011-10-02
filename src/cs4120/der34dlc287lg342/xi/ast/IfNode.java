package cs4120.der34dlc287lg342.xi.ast;

import java.util.ArrayList;

import cs4120.der34dlc287lg342.xi.typechecker.ContextList;
import cs4120.der34dlc287lg342.xi.typechecker.InvalidXiTypeException;
import cs4120.der34dlc287lg342.xi.typechecker.XiPrimitiveType;
import cs4120.der34dlc287lg342.xi.typechecker.XiType;
import cs4120.der34dlc287lg342.xi.typechecker.XiTypeContext;

import edu.cornell.cs.cs4120.util.VisualizableTreeNode;
import edu.cornell.cs.cs4120.xi.AbstractSyntaxNode;
import edu.cornell.cs.cs4120.xi.CompilationException;
import edu.cornell.cs.cs4120.xi.Position;

public class IfNode extends AbstractSyntaxTree {

	protected Position position;
	protected AbstractSyntaxNode condition, s1, s2;
	protected ArrayList<VisualizableTreeNode> children;
	public IfNode(AbstractSyntaxNode condition, AbstractSyntaxNode s1, AbstractSyntaxNode s2, Position position){
		this.condition = condition;
		this.s1 = s1;
		this.s2 = s2;
		this.position = position;
		children = new ArrayList<VisualizableTreeNode>();
		children.add(condition);
		children.add(s1);
		if (s2 != null)
			children.add(s2);
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
		return "IF" + (s2 == null ? "" : "-ELSE");
	}
	
	@Override
	public XiType typecheck(ContextList stack) throws CompilationException {
		XiType condType = ((AbstractSyntaxTree)condition).typecheck(stack);
		XiTypeContext context = new XiTypeContext(false);
		stack.push(context);
		XiType s1Type = ((AbstractSyntaxTree)s1).typecheck(stack);
		try {
			stack.pop();
		} catch (InvalidXiTypeException e) {
			throw new CompilationException(e.getMessage(), position());
		}
		XiType s2Type = null;
		if(s2 != null) {
			context = new XiTypeContext(false);
			stack.push(context);
			s2Type = ((AbstractSyntaxTree)s2).typecheck(stack);
			try {
				stack.pop();
			} catch (InvalidXiTypeException e) {
				throw new CompilationException(e.getMessage(), position());
			}
		}
		
		if(condType.equals(XiPrimitiveType.BOOL) && s1Type.equals(XiPrimitiveType.UNIT))
			if( s2Type != null)
				if(s2Type.equals(XiPrimitiveType.UNIT)){
					type = XiPrimitiveType.UNIT;
					return type;	
				}
		
		throw new CompilationException("Invalid boolean expression", position);
			
	}
}
