import org.eclipse.jdt.core.dom.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Marco on 14/09/16.
 */
public class HalsteadVisitor extends ASTVisitor
{

    private final String operands = "=   >   <   !   ~   ?   :   -> " +
            "==  >=  <=  !=  &&  ||  ++  -- " +
            "+   -   *   /   &   |   ^   %   <<   >>   >>> " +
            "+=  -=  *=  /=  &=  |=  ^=  %=  <<=  >>=  >>>=";


    private Map<String, Integer> names = new HashMap<String, Integer>();
    private Map<String, Integer> operators = new HashMap<String, Integer>();

    private CompilationUnit cu;

    HalsteadVisitor(CompilationUnit cu)
    {
        super();
        this.cu = cu;
    }
    HalsteadVisitor()
    {
        super();
    }

    private void updateMap(Map<String,Integer> map, String key)
    {
        if(map.containsKey(key))
        {
            map.put(key, map.get(key)+1);
        }
        else
        {
            map.put(key, 1);
        }
    }


    public boolean visit(VariableDeclarationFragment node)
    {
        SimpleName name = node.getName();
        updateMap(names,name.getIdentifier());
        return true;
    }

    public boolean visit(MethodDeclaration node)
    {
        updateMap(names, node.getName().getIdentifier());
        return true;
    }

    public boolean visit(SimpleName node)
    {
        if (names.containsKey(node.getIdentifier()))
            updateMap(names,node.getIdentifier());
        return true;
    }

    public boolean visit(InfixExpression node)
    {
        //System.out.println("Infix -> " + node.toString() + " Lop: " );
        updateMap(operators,node.getOperator().toString());
        return true;
    }

    public boolean visit(PostfixExpression node)
    {
        //System.out.println("Postfix -> " + node.toString() + " Lop: " );
        updateMap(operators,node.getOperator().toString());
        return true;
    }

    public boolean visit(PrefixExpression node)
    {
        //System.out.println("Prefix -> " + node.toString() + " Lop: " );
        updateMap(operators,node.getOperator().toString());
        return true;
    }

    public boolean visit(Assignment node)
    {
        //System.out.println("Assign -> " + node.getOperator().toString() + " Lop: " );
        updateMap(operators,node.getOperator().toString());
        return true;
    }

    //use ASTParse to parse string
    public void parse(String str)
    {
        ASTParser parser = ASTParser.newParser(AST.JLS3);
        parser.setSource(str.toCharArray());
        parser.setKind(ASTParser.K_COMPILATION_UNIT);

        final CompilationUnit cu = (CompilationUnit) parser.createAST(null);

        cu.accept(this);

    }

    //read file content into a string
    public String readFileToString(String filePath) throws IOException
    {
        StringBuilder fileData = new StringBuilder(1000);
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        char[] buf = new char[10];
        int numRead = 0;
        while ((numRead = reader.read(buf)) != -1) {
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
            buf = new char[1024];
        }

        reader.close();

        return  fileData.toString();
    }

    //loop directory to get file list
    public void ParseFilesInDir() throws IOException
    {
        File dirs = new File(".");
        String dirPath = dirs.getCanonicalPath() + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator;

        File root = new File(dirPath);
        File[] files = root.listFiles();
        String filePath = null;

        for (File f : files) {
            filePath = f.getAbsolutePath();
            if (f.isFile()) {
                parse(readFileToString(filePath));
            }
        }
    }

    public Map<String, Integer> getNames()
    {
        return names;
    }

    public Map<String, Integer> getOperators() {
        return operators;
    }

}
