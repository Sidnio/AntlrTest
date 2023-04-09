package org.example;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class Main {

    public static void main(String[] args) {
        String code = "class C { " +
                "int a;\n" +
                "Module module2\n; " +
                "Module module\n; }";
        CharStream input = CharStreams.fromString(code);
        org.example.JavaLexer lexer = new org.example.JavaLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        org.example.JavaParser parser = new org.example.JavaParser(tokens);
        ParseTree tree = parser.compilationUnit();
        JL jl = new JL();
        ParseTreeWalker.DEFAULT.walk(jl, tree);
    }
}

class JL extends org.example.JavaParserBaseListener {


    @Override
    public void enterVariableDeclaratorId(org.example.JavaParser.VariableDeclaratorIdContext ctx) {
//        Token token = ctx.identifier().IDENTIFIER().getSymbol();
//        System.out.println(token.getStartIndex());
//        System.out.println(token.getStopIndex());

        super.enterVariableDeclaratorId(ctx);
    }

    @Override
    public void enterFieldDeclaration(org.example.JavaParser.FieldDeclarationContext ctx) {

        Token token = ctx.variableDeclarators().variableDeclarator(0).variableDeclaratorId().identifier().IDENTIFIER().getSymbol();
        System.out.println(token.getText());
        System.out.println(token.getStartIndex());
        System.out.println(token.getStopIndex());
        //我这里是 要获取 module   token 的所有参数 而不单是文本数据



     /*   String text = ctx.variableDeclarators().variableDeclarator(0).variableDeclaratorId().getChild(0).getText();
        System.out.println(text);*/
    }
}
