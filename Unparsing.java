// The following methods unparse  AST nodes used in CSX Lite
//  You will need to complete the methods to unparse all
//    of CSX
//  Be sure to extend method printOp to handle all CSX operators

public class Unparsing extends Visitor {
	
	static void genIndent(int indent){
		for (int i=1;i<=indent;i++)
//			System.out.print("\t");
		    System.out.print("   ");
	}
// Extend this to handle all CSX binary operators
	static void printOp(int op) {
		switch (op) {
			case sym.PLUS:
				System.out.print(" + ");
				break;
			case sym.MINUS:
				System.out.print(" - ");
				break;
			case sym.EQ:
				System.out.print(" == ");
				break;
			case sym.NOTEQ:
				System.out.print(" != ");
				break;
			case sym.ASG:
				System.out.print(" = ");
				break;
			case sym.CAND:
				System.out.print(" && ");
				break;
			case sym.AND:
				System.out.print(" & ");
				break;
			case sym.COR:
				System.out.print(" || ");
				break;
			case sym.GEQ:
				System.out.print(" >= ");
				break;
			case sym.GT:
				System.out.print(" > ");
				break;
			case sym.LT:
				System.out.print(" < ");
				break;
			case sym.LEQ:
				System.out.print(" <= ");
				break;
			case sym.NOT:
				System.out.print("!");
				break;
			case sym.OR:
				System.out.print(" | ");
				break;
			case sym.TIMES:
				System.out.print(" * ");
				break;
			case sym.SLASH:
				System.out.print(" / ");
				break;
			default:
				throw new Error();
		}
	}

	
	 void visit(csxLiteNode n,int indent){
		//System.out.println ("\n\nStart 2nd unparsing:\n"); 
		System.out.println(n.linenum + ":\t" + " {");
		this.visit(n.progDecls,1);
		this.visit(n.progStmts,1);
		System.out.println(n.linenum + ":\t" + " } EOF");
	}
	
	void visit(fieldDeclsNode n,int indent){
			this.visit(n.thisField,indent);
			this.visit(n.moreFields,indent);
	}
	void visit(nullFieldDeclsNode n,int indent){}

	void visit(stmtsNode n,int indent){
		  //System.out.println ("In stmtsNode\n");
		//System.out.print(n.linenum + ":\t");
		//genIndent(indent);
		  this.visit(n.thisStmt,indent);
		  //System.out.println("");
		  this.visit(n.moreStmts,indent);

	}
	void visit(nullStmtsNode n,int indent){}

// Extend varDeclNode's method to handle initialization
	void visit(varDeclNode n,int indent){
		System.out.print(n.linenum + ":\t");
		genIndent(indent);
	    	this.visit(n.varType,0);
		System.out.print(" ");
		this.visit(n.varName,0);
		if (!(n.initValue.isNull())) {
			System.out.print(" = ");
			this.visit(n.initValue, 0);
		}
		System.out.println(";");
	};
	
	void visit(nullTypeNode n,int ident){}
	
	void visit(intTypeNode n,int ident){
		System.out.print("int");
	}
	void visit(boolTypeNode n,int ident){
		System.out.print("bool");
	}
	void visit(identNode n,int indent){
		System.out.print(n.idname);
	}
// Extend nameNode's method to handle subscripts
	void visit(nameNode n,int indent){
		System.out.print(n.varName.idname);
		if (!(n.subscriptVal.isNull())) {
			System.out.print("[");
			this.visit(n.subscriptVal, indent);
			System.out.print("]");
		}
	}
	void visit(asgNode n,int indent){
		System.out.print(n.linenum + ":\t");
		genIndent(indent);
		this.visit(n.target,0);
		System.out.print(" = ");
		this.visit(n.source,0);
		System.out.println(";");
	} 
	 void visit(incrementNode n,int indent){
		 System.out.print(n.linenum + ":\t");
			genIndent(indent);
		 this.visit(n.target, indent);
			System.out.println("++;");
		  }
		  
	 void visit(decrementNode n,int indent){
		 System.out.print(n.linenum + ":\t");
			genIndent(indent);
		 this.visit(n.target, indent);
			System.out.println("--;");
		  }
	
// Extend ifThenNode's method to handle else parts
	void visit(ifThenNode n,int indent){
		  System.out.print(n.linenum + ":\t");
		  genIndent(indent);
		  System.out.print("if (");
		  this.visit(n.condition,0);
		  System.out.println(")");
		  this.visit(n.thenPart,indent+1);
		  if (!(n.elsePart.isNull())) {
			  System.out.print(n.linenum + ":\t");
				genIndent(indent);
			  System.out.print("else");
			  System.out.println("");
			  this.visit((stmtNode) n.elsePart, indent+1);
		  }
	}
	  
	  void visit(blockNode n,int indent){
		System.out.print(n.linenum + ":\t");
		genIndent(indent);
		System.out.println("{");
		this.visit(n.decls,indent+1);
		this.visit(n.stmts,indent+1);
		System.out.print(n.linenum + ":\t");
		genIndent(indent);
		System.out.println("}");
	  }

	
	  void visit(binaryOpNode n,int indent){
		  
		  System.out.print("(");
			this.visit(n.leftOperand,0);
			printOp(n.operatorCode);
			this.visit(n.rightOperand,0);
			System.out.print(")");
	  }

	
	
	void visit(intLitNode n,int indent){
		if (n.intval>=0)
			System.out.print(n.intval);
		else	System.out.print("~"+-n.intval);
	}


 
// Extend these unparsing methods to correctly unparse CSX AST nodes
	 
	 void visit(classNode n,int indent){
			System.out.print(n.linenum + ": class ");
			this.visit(n.className,1);
			System.out.println("{");
			this.visit(n.members,1);
			System.out.println(n.linenum + ":\t" + " } EOF");
		}

	 void  visit(memberDeclsNode n,int indent){
		 this.visit(n.fields, indent);
		 this.visit(n.methods, indent);
	 }
	 
	 void  visit(methodDeclsNode n,int indent){
		this.visit(n.thisDecl, indent);
		this.visit(n.moreDecls, indent);
		 }
	 
	 void visit(nullStmtNode n,int indent){}
	 
	 void visit(nullReadNode n,int indent){}

	 void visit(nullPrintNode n,int indent){}

	 void visit(nullExprNode n,int indent){}

	 void visit(nullMethodDeclsNode n,int indent){}

	 void visit(methodDeclNode n,int indent){
		 System.out.print(n.linenum + ":\t");
		 genIndent(indent);
		this.visit(n.returnType,indent);
		System.out.print(" ");
		this.visit(n.name,indent);
		System.out.print(" (");
		this.visit(n.args, indent);
		System.out.println(") {");
		this.visit(n.decls, indent+1);
		this.visit(n.stmts, indent+1);
		System.out.print(n.linenum + ":\t");
		genIndent(indent);
		System.out.println("}");
	 }

	 
	void visit(argDeclsNode n,int indent){
		this.visit(n.thisDecl, indent);
		if (!(n.moreDecls.isNull())){
			System.out.print(", ");
			this.visit(n.moreDecls, indent);
		}
	}

	void visit(nullArgDeclsNode n,int indent){}

	
	void visit(valArgDeclNode n,int indent){
		this.visit(n.argType, indent);
		System.out.print(" ");
		this.visit(n.argName, indent);
	}
	
	void visit(arrayArgDeclNode n,int indent){
		this.visit(n.elementType, indent);
		System.out.print(" ");
		this.visit(n.argName, indent);
		System.out.print(" [ ]");
	}
	
	void visit(constDeclNode n,int indent){
		System.out.print(n.linenum + ":\t");
		 genIndent(indent);
		 System.out.print("const ");
		 this.visit(n.constName, indent);
		 System.out.print(" = ");
		 this.visit(n.constValue, indent);
		 System.out.println(";");
	 }
	 
	 void visit(arrayDeclNode n,int indent){
		 System.out.print(n.linenum + ":\t");
		 genIndent(indent);
		 this.visit(n.elementType, indent);
		 System.out.print(" ");
		 this.visit(n.arrayName, indent);
		 System.out.print(" [ ");
		 this.visit(n.arraySize, indent);
		 System.out.println(" ];");
	 }
	
	void visit(charTypeNode n,int indent){
		System.out.print("char");
	}
	void visit(voidTypeNode n,int indent){
		System.out.print("void");
	}

	void visit(whileNode n,int indent){
		System.out.print(n.linenum + ":\t");
		genIndent(indent);
		if (!(n.label.isNull())) {
			this.visit(n.label, indent);
			System.out.print(": ");
		}
		System.out.print(" while (");
		this.visit(n.condition, indent);
		System.out.println(")");
		this.visit(n.loopBody, indent+1);
	  }

	void visit(breakNode n,int indent){
		System.out.print(n.linenum + ":\t");
		genIndent(indent);
		System.out.print("break ");
		this.visit(n.label, indent);
		System.out.println(";");
	}
	void visit(continueNode n,int indent){
		System.out.print(n.linenum + ":\t");
		genIndent(indent);
		System.out.print("continue ");
		this.visit(n.label, indent);
		System.out.println(";");
	}
	  
	void visit(callNode n,int indent){
		System.out.print(n.linenum + ":\t");
		genIndent(indent);
		this.visit(n.methodName, indent);
		System.out.print("(");
		this.visit(n.args, indent);
		System.out.println(");");
	}

	  
	 void visit(printNode n,int indent){
		 if (indent>0) {
			 System.out.print(n.linenum + ":\t");
				genIndent(indent);
			 System.out.print("print");
			 System.out.print("(");
		 }
		 this.visit(n.outputValue, indent);
		if (!(n.morePrints.isNull())) {
			System.out.print(", ");
			this.visit(n.morePrints, -1);
		}
		if (indent>0) {
			System.out.println(");");
		}
	  }
	  
	  void visit(readNode n,int indent){
		  if (indent>0) {
			  System.out.print(n.linenum + ":\t");
				genIndent(indent);
				 System.out.print("read");
				 System.out.print("(");
			 }
			 this.visit(n.targetVar, indent);
			if (!(n.moreReads.isNull())) {
				System.out.print(", ");
				this.visit(n.moreReads, -1);
			}
			if (indent>0) {
				System.out.println(");");
			}
	  }
	  

	  void visit(returnNode n,int indent){
		  System.out.print(n.linenum + ":\t");
			genIndent(indent);
		System.out.print("return");
		this.visit(n.returnVal, indent);
		System.out.println(";");
	  }

	  
	  void visit(argsNode n,int indent){
		  this.visit(n.argVal, indent);
		  if (!(n.moreArgs.isNull())) {
			  System.out.print(", ");
			  this.visit(n.moreArgs, indent);
		  }
	  }
	  	
	  void visit(nullArgsNode n,int indent){}
		
	  void visit(castNode n,int indent){
		  System.out.print("(");
		  this.visit(n.resultType, indent);
		  System.out.print(") ");
		  this.visit(n.operand, indent);
	  }

	  void visit(fctCallNode n,int indent){
		  this.visit(n.methodName, indent);
		  System.out.print("(");
		  this.visit(n.methodArgs, indent);
		  System.out.print(")");
	  }

	  void visit(unaryOpNode n,int indent){
		  System.out.print("(");
		  printOp(n.operatorCode);
		  System.out.print(" ");
		  this.visit(n.operand, indent);
		  System.out.print(")");
	  }

	
	void visit(charLitNode n,int indent){
		char charVal = n.charval;
		String stringChar = "";
		  if (charVal=='\t') {
			  stringChar = "\\t";
		  }
		  else if (charVal=='\n') {
			  stringChar = "\\n";
		  }
		  else if (charVal=='\\'){
				  stringChar = "\\\\";
		  }
		  else if (charVal=='\'') {
			  stringChar = "\\\'";
		  }
		  else {
			  stringChar = String.valueOf(charVal);
		  }
		System.out.print("'" + stringChar + "'");
	}
	  
	void visit(strLitNode n,int indent){
		System.out.print(n.strval);
	}
	
	void visit(bitStringNode n,int indent){
		System.out.print(n.bitString);
		System.out.print("B");
    }
	
	void visit(trueNode n,int indent){
		System.out.print("true");
	}

	void visit(falseNode n,int indent){
		System.out.print("false");
	}


}
