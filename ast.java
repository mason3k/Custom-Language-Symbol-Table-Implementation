/*  This defines AST classes for CSX
 *  Little, if any, of this needs to be changed
 * 
 */ 
// abstract superclass; only subclasses are actually created  
abstract class ASTNode {

	public  int 	linenum;
	public  int	colnum;
	
	ASTNode(){linenum=-1;colnum=-1;}
	ASTNode(int l,int c){linenum=l;colnum=c;}
	boolean   isNull(){return false;}; // Is this node null?

    	abstract void accept(Visitor v, int indent);// Will be defined in sub-classes    
};



// This node is used to root only CSXlite programs 
class csxLiteNode extends ASTNode {
	
   	public final fieldDeclsOption	progDecls;
	public final stmtsOption 	progStmts;
	
	csxLiteNode(fieldDeclsOption decls, stmtsOption stmts, int line, int col){  
		super(line,col);
		progDecls=decls;
		progStmts=stmts;
	}; 
	
	void accept(Visitor u, int indent){ u.visit(this,indent); }
	
};

// Root of all ASTs for CSX
class classNode extends ASTNode {

        public final identNode       className;
        public final memberDeclsNode members;

        classNode(identNode id, memberDeclsNode m, int line, int col){
                super(line,col);
                className=id;
                members=m;
        }

	void accept(Visitor u, int indent){u.visit(this,indent); }
		//System.out.println("In classnode accept\n");
		
};

class memberDeclsNode extends ASTNode {

        fieldDeclsOption 				fields;
        public final methodDeclsOption	methods;

        memberDeclsNode(fieldDeclsOption f, methodDeclsOption m,
                        int line, int col){
                super(line,col);
                fields=f;
                methods=m;
        }

	void accept(Visitor u, int indent){ u.visit(this,indent); }
};


abstract class fieldDeclsOption extends ASTNode{
	fieldDeclsOption(int line,int column){
		super(line,column);
	}
	fieldDeclsOption(){ super(); }

	static nullFieldDeclsNode NULL = new nullFieldDeclsNode();
};

class fieldDeclsNode extends fieldDeclsOption {

	public final declNode		thisField;
	public final fieldDeclsOption 	moreFields;
	
	fieldDeclsNode(declNode d, fieldDeclsOption f, int line, int col){
		super(line,col);
		thisField=d;
		moreFields=f;
	}
	
	void accept(Visitor u, int indent){ u.visit(this,indent);}

};

class nullFieldDeclsNode extends fieldDeclsOption {
	
	nullFieldDeclsNode(){};

	boolean   isNull(){return true;};

	void accept(Visitor u, int indent){ u.visit(this,indent);}

};

// abstract superclass; only subclasses are actually created
abstract class declNode extends ASTNode {
	declNode(){super();};
	declNode(int l,int c){super(l,c);};
};


class varDeclNode extends declNode { 
	
	public final	identNode	varName;
	public final	typeNode 	varType;
	public final	exprNodeOption 	initValue;
	
	varDeclNode(identNode id, typeNode t, exprNodeOption e,
			int line, int col){
		super(line,col);
		varName=id;
		varType=t;
		initValue=e;
	}
	
	void accept(Visitor u, int indent){ u.visit(this,indent);}
};

class constDeclNode extends declNode {

        public final identNode       constName;
        public final exprNode        constValue;

        constDeclNode(identNode id,  exprNode e, int line, int col){
                super(line,col);
                constName=id;
                constValue=e;
        }

	void accept(Visitor u, int indent) { u.visit(this,indent);}
};


class arrayDeclNode extends declNode {

        public final identNode       arrayName;
        public final typeNode        elementType;
        public final intLitNode      arraySize;

        arrayDeclNode(identNode id, typeNode t, intLitNode lit, int line, int col){
                super(line,col);
                arrayName=id;
                elementType=t;
                arraySize=lit;
        }

	void accept(Visitor u, int indent) { u.visit(this,indent);}
};


abstract class typeNodeOption extends ASTNode {
// abstract superclass; only subclasses are actually created
	typeNodeOption(){super();};
	typeNodeOption(int l,int c){super(l,c);};
	static nullTypeNode NULL = new nullTypeNode();
};

abstract class typeNode extends typeNodeOption {
// abstract superclass; only subclasses are actually created
	typeNode(){super();};
	typeNode(int l,int c){super(l,c);};
	//static nullTypeNode NULL = new nullTypeNode();
};

class nullTypeNode extends typeNodeOption {

	nullTypeNode(){};

	boolean   isNull(){return true;};

	void accept(Visitor u, int indent){ u.visit(this,indent); }
};


class intTypeNode extends typeNode {
	intTypeNode(int line, int col){
		super(line,col);
	}

	void accept(Visitor u, int indent){ u.visit(this,indent); }
};


class boolTypeNode extends typeNode {
	boolTypeNode(int line, int col){
		super(line,col);
	}

	void accept(Visitor u, int indent){ u.visit(this,indent); }
};

class charTypeNode extends typeNode {
        charTypeNode(int line, int col){
                super(line,col);
        }

	void accept(Visitor u, int indent) { u.visit(this,indent); }
};

class voidTypeNode extends typeNode {
        voidTypeNode(int line, int col){
                super(line,col);
        }

	void accept(Visitor u, int indent) { u.visit(this,indent); }
};

//abstract superclass; only subclasses are actually created
abstract class methodDeclsOption extends ASTNode {
	methodDeclsOption(){super();};
	methodDeclsOption(int l,int c){super(l,c);};

        static nullMethodDeclsNode NULL = new nullMethodDeclsNode();
};

class methodDeclsNode extends methodDeclsOption {

        public final methodDeclNode         thisDecl;
        public final methodDeclsOption      moreDecls;

        methodDeclsNode(methodDeclNode m, methodDeclsOption ms, int line, int col){
                super(line,col);
                thisDecl=m;
                moreDecls=ms;
        }

	void accept(Visitor u, int indent){ u.visit(this,indent); }
};

class nullMethodDeclsNode extends methodDeclsOption {

        nullMethodDeclsNode(){};

        boolean   isNull(){return true;};

	void accept(Visitor u, int indent) { u.visit(this,indent); }
};

class methodDeclNode extends ASTNode {
       
        public final identNode         name;
        public final argDeclsOption    args;
        public final typeNodeOption    returnType;
        public final fieldDeclsOption  decls;
        public final stmtsOption         stmts;
        
        methodDeclNode(identNode id, argDeclsOption a, typeNodeOption t,
                fieldDeclsOption f, stmtsOption s, int line, int col){
        	super(line,col);
        	name=id;
        	args=a;
        	returnType=t;
        	decls=f;
        	stmts=s;
		}

	void accept(Visitor u, int indent){ u.visit(this,indent); }
};


// abstract superclass; only subclasses are actually created
abstract class argDeclNode extends ASTNode {

        argDeclNode(){super();};
        argDeclNode(int l,int c){super(l,c);};
};


abstract class argDeclsOption extends ASTNode{

	argDeclsOption(int line,int column){
		super(line,column);
	}
	argDeclsOption(){ super(); }

        static nullArgDeclsNode NULL = new nullArgDeclsNode();
};


class argDeclsNode extends argDeclsOption {

        public final argDeclNode     thisDecl;
        public final argDeclsOption    moreDecls;

        argDeclsNode(argDeclNode arg, argDeclsOption args,
                        int line, int col){
                super(line,col);
                thisDecl=arg;
                moreDecls=args;
        }

	void accept(Visitor u, int indent) { u.visit(this,indent); }
};

class nullArgDeclsNode extends argDeclsOption {

        nullArgDeclsNode(){};

        boolean   isNull(){return true;};

	void accept(Visitor u, int indent) { u.visit(this,indent); }
};


class arrayArgDeclNode extends argDeclNode {

        public final identNode       argName;
        public final typeNode        elementType;

        arrayArgDeclNode(identNode id, typeNode t, int line, int col){
                super(line,col);
                argName=id;
                elementType=t;
        }

	void accept(Visitor u, int indent) { u.visit(this,indent); }
};


class valArgDeclNode extends argDeclNode {

        public final identNode       argName;
        public final typeNode        argType;

        valArgDeclNode(identNode id, typeNode t, int line, int col){
                super(line,col);
                argName=id;
                argType=t;
        }

	void accept(Visitor u, int indent) { u.visit(this,indent); }
};



//abstract superclass; only subclasses are actually created
abstract class stmtOption extends ASTNode {
	stmtOption(){super();};
	stmtOption(int l,int c){super(l,c);};
	static nullStmtNode NULL = new nullStmtNode();
};

// abstract superclass; only subclasses are actually created
abstract class stmtNode extends stmtOption {
	stmtNode(){super();};
	stmtNode(int l,int c){super(l,c);};
};

class nullStmtNode extends stmtOption {
	nullStmtNode(){};
	boolean   isNull(){return true;};
	void accept(Visitor u, int indent){ u.visit(this,indent);}
};

abstract class stmtsOption extends ASTNode{
	stmtsOption(int line,int column){
		super(line,column);
	}
	stmtsOption(){ super(); }

	static nullStmtsNode NULL = new nullStmtsNode();
};

class stmtsNode extends stmtsOption { 
	public final stmtNode	    	thisStmt;
	public final stmtsOption 	moreStmts;

	stmtsNode(stmtNode stmt, stmtsOption stmts, int line, int col){
		super(line,col);
		thisStmt=stmt;
		moreStmts=stmts;
	};
	
	void accept(Visitor u, int indent){ u.visit(this,indent);}

};


class nullStmtsNode extends stmtsOption {
	nullStmtsNode(){};

	boolean   isNull(){return true;};

	void accept(Visitor u, int indent){ u.visit(this,indent);}
};

class asgNode extends stmtNode {      

	public final nameNode	target;
	public final exprNode 	source;
	
	asgNode(nameNode n, exprNode e, int line, int col){       
		super(line,col);
		target=n;
		source=e;
	};
	
	void accept(Visitor u, int indent){ u.visit(this,indent);}
};

class incrementNode extends stmtNode {      

	public final nameNode	target;
	
	incrementNode(nameNode n, int line, int col){       
		super(line,col);
		target=n;
	};
	
	void accept(Visitor u, int indent){ u.visit(this,indent);}
};
class decrementNode extends stmtNode {      

	public final nameNode	target;
	
	decrementNode(nameNode n, int line, int col){       
		super(line,col);
		target=n;
	};
	
	void accept(Visitor u, int indent){ u.visit(this,indent);}
};


class ifThenNode extends stmtNode {
	
	public final exprNode 		condition;
	public final stmtNode 		thenPart;
	public final stmtOption 	elsePart;
	
	ifThenNode(exprNode e, stmtNode s1, stmtOption s2, int line, int col){
		super(line,col);
		condition=e;
		thenPart=s1;
		elsePart=s2;
	};

	ifThenNode(exprNode e, stmtNode s1, int line, int col){
		super(line,col);
		condition=e;
		thenPart=s1;
		elsePart=stmtNode.NULL;
	};
	
	
	void accept(Visitor u, int indent){ u.visit(this,indent);}
};

class whileNode extends stmtNode {

        public final exprNodeOption  label;
        public final exprNode        condition;
        public final stmtNode        loopBody;

        whileNode(exprNodeOption i, exprNode e, stmtNode s, int line, int col){
                super(line,col);
                label=i;
                condition=e;
                loopBody=s;
        }

	void accept(Visitor u, int indent) { u.visit(this,indent);}
};

abstract class readNodeOption extends stmtNode{
	readNodeOption(int line,int column){ super(line,column); }

	readNodeOption(){ super(); }

        static nullReadNode NULL = new nullReadNode();
};

class readNode extends readNodeOption {

        public final nameNode        targetVar;
        public final readNodeOption  moreReads;

        readNode(nameNode n, readNodeOption rn, int line, int col){
                super(line,col);
                 targetVar=n;
                 moreReads=rn;
        }

	void accept(Visitor u, int indent) { u.visit(this,indent);}
};


class nullReadNode extends readNodeOption {

        nullReadNode(){};

        boolean   isNull(){return true;};

	void accept(Visitor u, int indent){ u.visit(this,indent);}
};

abstract class printNodeOption extends stmtNode{
	printNodeOption(int line,int column){ super(line,column); }

	printNodeOption(){ super(); }

        static nullPrintNode NULL = new nullPrintNode();
};

class printNode extends printNodeOption {

        public final exprNode       	outputValue;
        public final printNodeOption	morePrints;

        printNode(exprNode val, printNodeOption pn, int line, int col){
                super(line,col);
                outputValue=val;
                morePrints=pn;
        }

	void accept(Visitor u, int indent) { u.visit(this,indent);}
};


class nullPrintNode extends printNodeOption {
        nullPrintNode(){};

        boolean   isNull(){return true;};

	void accept(Visitor u, int indent){ u.visit(this,indent);}
};


class callNode extends stmtNode {

        public final identNode       methodName;
        public final argsNodeOption  args;

        callNode(identNode id, argsNodeOption a, int line, int col){
                super(line,col);
                methodName=id;
                args=a;
        }

	void accept(Visitor u, int indent) { u.visit(this,indent);}
};

class returnNode extends stmtNode {

        public final exprNodeOption	returnVal;

        returnNode(exprNodeOption e, int line, int col){
                super(line,col);
                returnVal=e;
        }
	
	 void accept(Visitor u, int indent) { u.visit(this,indent);}

};


class blockNode extends stmtNode {
	
	public final fieldDeclsOption 	decls;  
	public final stmtsOption 	stmts;
	
	blockNode(fieldDeclsOption f, stmtsOption s, int line, int col){
		super(line,col);
		decls=f;
		stmts=s;
	}
	
	 void accept(Visitor u, int indent){ u.visit(this,indent);}

};

class breakNode extends stmtNode {

        public final identNode       label;

        breakNode(identNode i, int line, int col){
                super(line,col);
                label=i;
        }
	
	 void accept(Visitor u, int indent) { u.visit(this,indent);}
};

class continueNode extends stmtNode {

        public final identNode       label;

        continueNode(identNode i, int line, int col){
                super(line,col);
                label=i;
        }
	
	 void accept(Visitor u, int indent) { u.visit(this,indent);}
};


//abstract superclass; only subclasses are actually created
abstract class argsNodeOption extends ASTNode {
	argsNodeOption(){super();};
	argsNodeOption(int l,int c){super(l,c);};

        static nullArgsNode NULL = new nullArgsNode();
};

class argsNode extends argsNodeOption {

        public final exprNode        	argVal;
        public final argsNodeOption     moreArgs;

        argsNode(exprNode e, argsNodeOption a, int line, int col){
                super(line,col);
                argVal=e;
                moreArgs=a;
        }

	void accept(Visitor u, int indent){ u.visit(this,indent);}
};


class nullArgsNode extends argsNodeOption {

        nullArgsNode(){};

        boolean   isNull(){return true;};

	void accept(Visitor u, int indent){ u.visit(this,indent);}
};

class strLitNode extends exprNode {

        public final String  strval;

        strLitNode(String stringval, int line, int col){
                super(line,col);
                strval=stringval;
        }

	void accept(Visitor u, int indent)  { u.visit(this,indent);}
};


//abstract superclass; only subclasses are actually created
abstract class exprNodeOption extends ASTNode {
	exprNodeOption(){super();};
	exprNodeOption(int l,int c){super(l,c);};

	static nullExprNode NULL = new nullExprNode();
};

// abstract superclass; only subclasses are actually created
abstract class exprNode extends exprNodeOption {
	exprNode(){super();};
	exprNode(int l,int c){super(l,c);};
};

class nullExprNode extends exprNodeOption {
	nullExprNode(){super();};

	boolean   isNull(){return true;};

	void accept(Visitor u, int indent){}
};

class binaryOpNode extends exprNode {
	
	public final exprNode 	leftOperand;
	public final exprNode 	rightOperand;
	public final int	operatorCode; // Token code of the operator
	
	binaryOpNode(exprNode e1, int op, exprNode e2, int line, int col){
		super(line,col);
		operatorCode=op;
		leftOperand=e1;
		rightOperand=e2;
	};

	void accept(Visitor u, int indent){ u.visit(this,indent);}

};


class unaryOpNode extends exprNode {

        public final exprNode        operand;
        public final int             operatorCode; // Token code of the operator
        unaryOpNode(int op, exprNode e, int line, int col){
                super(line,col);
                operand=e;
                operatorCode=op;
        }

	void accept(Visitor u, int indent) { u.visit(this,indent);}
};

class castNode extends exprNode {

        public final exprNode        operand;
        public final typeNode        resultType;

        castNode(typeNode t, exprNode e, int line, int col){
                super(line,col);
                operand=e;
                resultType=t;
        }

	void accept(Visitor u, int indent) { u.visit(this,indent);}
};

class fctCallNode extends exprNode {

        public final identNode       methodName;
        public final argsNodeOption  methodArgs;

        fctCallNode(identNode id, argsNodeOption a, int line, int col){
                super(line,col);
                methodName=id;
                methodArgs=a;
        }

	void accept(Visitor u, int indent) { u.visit(this,indent);}
};


class identNode extends exprNode {
	
	public final String 	idname;
	
	identNode(String identname, int line, int col){
		super(line,col);
		idname   = identname;
	};

	void accept(Visitor u, int indent){ u.visit(this,indent);}
};

class nameNode extends exprNode {

        public final identNode    	  		varName;
        public final exprNodeOption       	subscriptVal;

        nameNode(identNode id, exprNodeOption expr, int line, int col){
                super(line,col);
                varName=id;
                subscriptVal=expr;
        };

        nameNode(identNode id, int line, int col){
                super(line,col);
                varName=id;
                subscriptVal=exprNode.NULL;
        };

	void accept(Visitor u, int indent){ u.visit(this,indent);}

};




class intLitNode extends exprNode {
	public final int 	intval;
	intLitNode(int val, int line, int col){
		super(line,col);
		intval=val;
	}

	void accept(Visitor u, int indent){ u.visit(this,indent);}
};

class bitStringNode extends exprNode {
	public final int 	intValue;
	public final String 	bitString;
	bitStringNode(int val, String bitStr, int line, int col){
		super(line,col);
		intValue=val;
		bitString=bitStr;
	}

	void accept(Visitor u, int indent){ u.visit(this,indent);}
};

class charLitNode extends exprNode {

        public final char    charval;

        charLitNode(char val, int line, int col){
                super(line,col);
                 charval=val;
        }

	void accept(Visitor u, int indent) { u.visit(this,indent);}
};


class trueNode extends exprNode {
        trueNode(int line, int col){
                super(line,col);
        }

	void accept(Visitor u, int indent) { u.visit(this,indent);}
};


class falseNode extends exprNode {
        falseNode(int line, int col){
                super(line,col);
        }

	void accept(Visitor u, int indent) { u.visit(this,indent);}
};

