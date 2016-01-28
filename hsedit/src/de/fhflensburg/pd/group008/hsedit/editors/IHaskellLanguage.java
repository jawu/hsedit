package de.fhflensburg.pd.group008.hsedit.editors;

/**
 * In this interface Haskell data types, keywords and operators are listed to be discovered in source files
 *
 */
public interface IHaskellLanguage {
	
	// haskell data types
	String[] types = {
			"Bool",
			"Char",
			"String",
			"[]",
			"Integer",
			"Int",
			"Int8",
			"Int16",
			"Int32",
			"Int64",
			"Float",
			"Double",
			"RealFloat",
			"Ratio"
	};
	
	// haskell operators
	String[] operators = {
			".",
			"..",
			"=",
			"==",
			"/=",
			"<",
			">",
			"<=",
			">=",
			"+",
			"-",
			"*",
			"/",
			"**",
			"^",
			"^^",
			"->",
			"<-",
			"|",
			"::",
			"!!",
			"||",
			"&&",
			"mod",
			"div",			
	};
	
	// haskell keywords
	String[] keywords = {
			"as",
			"case",
			"class",
			"data",
			"default",
			"deriving",
			"do",
			"else",
			"hiding",
			"forall",
			"foreign",
			"if",
		    "import",
		    "in",
		    "infix",
		    "infixl",
		    "infixr",
		    "instance",
		    "interruptible",
		    "let",
		    "module",
		    "newtype",
		    "of",
		    "qualified",
		    "then",
		    "type",
		    "where",
		    "ccall",	    
		    "safe",
		    "stdcallconv",
		    "ccallconv",
		    "capiconv",
		    "primcallconv",
		    "javascript",
		    "mdo",
		    "family",
		    "role",
		    "group",
		    "by",
		    "using",
		    "pattern",
		    "static",
		    "unsafe",
		    "export",
		    "label",
		    "dynamic",
		    "catch",
		    "throw"		    
		};
}