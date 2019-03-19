// No need to change anything in this class!
// Generic visit
abstract class Visitor {
	public void visit(ASTNode n,int indent){ 
		//System.out.println ("In  ASTNode visit\t"+n);

		n.accept(this, indent);
	}
	
	abstract  void visit(csxLiteNode n,int indent);
	abstract  void visit(classNode n,int indent);
	abstract  void visit(memberDeclsNode n,int indent);
	abstract  void visit(fieldDeclsNode n,int indent);
	abstract  void visit(valArgDeclNode n,int indent);
	abstract  void visit(arrayArgDeclNode n,int indent);
	abstract  void visit(argDeclsNode n,int indent);
	abstract  void visit(nullArgDeclsNode n,int indent);
	abstract  void visit(methodDeclsNode n,int indent);
	abstract  void visit(nullMethodDeclsNode n,int indent);
	abstract  void visit(methodDeclNode n,int indent);
	abstract  void visit(trueNode n,int indent);
	abstract  void visit(falseNode n,int indent);
	abstract  void visit(nullFieldDeclsNode n,int indent);
	abstract  void visit(stmtsNode n,int indent);
	abstract  void visit(varDeclNode n,int indent);
	abstract  void visit(constDeclNode n,int indent);
	abstract  void visit(arrayDeclNode n,int indent);
	abstract  void visit(nullTypeNode n,int indent);
	abstract  void visit(intTypeNode n,int indent);
	abstract  void visit(boolTypeNode n,int indent);
	abstract  void visit(charTypeNode n,int indent);
	abstract  void visit(voidTypeNode n,int indent);
	abstract  void visit(identNode n,int indent);
	abstract  void visit(nameNode n,int indent);
	abstract  void visit(asgNode n,int indent);
	abstract  void visit(incrementNode n,int indent);
	abstract  void visit(decrementNode n,int indent);
	abstract  void visit(printNode n,int indent);
	abstract  void visit(nullPrintNode n,int indent);
	abstract  void visit(readNode n,int indent);
	abstract  void visit(nullReadNode n,int indent);
	abstract  void visit(intLitNode n,int indent);
	abstract  void visit(bitStringNode n,int indent);
	abstract  void visit(charLitNode n,int indent);
	abstract  void visit(strLitNode n,int indent);
	abstract  void visit(argsNode n,int indent);
	abstract  void visit(nullArgsNode n,int indent);
	abstract  void visit(binaryOpNode n,int indent);
	abstract  void visit(unaryOpNode n,int indent);
	abstract  void visit(nullStmtsNode n,int indent);
	abstract  void visit(nullStmtNode n,int indent);
	abstract  void visit(nullExprNode n,int indent);
	abstract  void visit(ifThenNode n,int indent);
	abstract  void visit(whileNode n,int indent);
	abstract  void visit(blockNode n,int indent);
	abstract  void visit(callNode n,int indent);
	abstract  void visit(fctCallNode n,int indent);
	abstract  void visit(returnNode n,int indent);
	abstract  void visit(breakNode n,int indent);
	abstract  void visit(continueNode n,int indent);
	abstract  void visit(castNode n,int indent);












}  
