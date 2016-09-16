import org.eclipse.jdt.core.dom.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Marco on 14/09/16.
 */
public class HalsteadVisitor extends ASTVisitor
{

    private final String operands = "=   >   <   !   ~   ?   :   -> " +
            "==  >=  <=  !=  &&  ||  ++  -- " +
            "+   -   *   /   &   |   ^   %   <<   >>   >>> " +
            "+=  -=  *=  /=  &=  |=  ^=  %=  <<=  >>=  >>>=";


    //map for the names
    private Map<String, Integer> names = new HashMap<String, Integer>();
    //map for the operators
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


    /**
     * @param map: the map to be updated
     * @param key: the value into the map to be updated
     *
     * The method update the value of a given key in a given map.
     * If the key exists its value is incremented by 1,
     * if NOT a new pair (key,1) is added to the given map.
     *
     */
    private void updateMap(Map<String,Integer> map, String key)
    {
        if(map.containsKey(key))
        {
            map.put(key, map.get(key)+1);
        }
        else
        {
            if(map.equals(names))
            {
                map.put(key, 0);
            }
            else
            {
                map.put(key,1);
            }
        }
    }


    //List of useful implementation of the visit method, used by the AST traversor.
    //Called only when the node of the the declared type is reached
    //In order to add more operator options, add more visit methods
    public boolean visit(VariableDeclarationFragment node)
    {
        SimpleName name = node.getName();
        updateMap(names,name.getIdentifier());
        return true;
    }

    public boolean visit(MethodDeclaration node)
    {
        updateMap(names, node.getName().getIdentifier());
        for(Object param : node.parameters())
        {
            updateMap(names, param.toString().split(" ")[1]);
        }
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


    /**
     * @param str: String to be parsed
     *
     * Exploits the ASTParser in order to parse a String, via compilation
     */
    public void parse(String str)
    {
        ASTParser parser = ASTParser.newParser(AST.JLS8);
        parser.setSource(str.toCharArray());
        parser.setKind(ASTParser.K_COMPILATION_UNIT);

        final CompilationUnit cu = (CompilationUnit) parser.createAST(null);

        cu.accept(this);

    }


    /**
     * @param filePath String representing the path of the file to be put into a string
     * @return String, the file into String
     * @throws IOException thrown when the file is not found
     *
     * The method reads a file and put it into a String.
     */
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

    /**
     * @param dirPath String, representing the URI of the dir
     * @return the number of file in the dir
     * @throws IOException Thrown if the path is incorrect
     *
     * Recursively parse the file in a given dir and its sub dirs.
     */
    public int parseFilesInDir(String dirPath) throws IOException
    {

        File root = new File(dirPath);
        File[] files = root.listFiles();
        String filePath = null;
        int numFile = 0;

        for (File f : files) {
            filePath = f.getAbsolutePath();
            //Recursive visit if f is a dir
            if (f.isFile())
            {
                parse(readFileToString(filePath));
                numFile++;
            }
            if(f.isDirectory())
            {
                numFile += parseFilesInDir(f.getPath());
            }
        }
        return numFile;
    }

    public Map<String, Integer> getNames()
    {
        Map<String,Integer> copy = new HashMap<String, Integer>();
        for(String key : names.keySet())
        {
            copy.put(key, names.get(key)-1);
        }
        return names;
    }

    public Map<String, Integer> getOperators() {
        return operators;
    }

}
