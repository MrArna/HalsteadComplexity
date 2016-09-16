import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by Marco on 14/09/16.
 */
public class HalsteadVisitorTest {

    private HalsteadVisitor visitor;
    private File dirs;
    private String dirPath;

    @Before
    public void init() throws IOException
    {
        visitor = new HalsteadVisitor();
        dirs = new File(".");
        dirPath = dirs.getCanonicalPath() + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator;

    }

    @Test
    public void readFileToString() throws Exception
    {
        assertEquals(visitor.readFileToString(dirPath + "/test.java"),"import java.util.ArrayList;\n" +
                " \n" +
                "public class Apple {\n" +
                "\tprivate  int prova (int c){\n" +
                "\t\ta = b + c;\n" +
                "\t}\n" +
                "\tpublic static void main(String[] args) {\n" +
                "\t\tArrayList<String> al = null;\n" +
                "\t\tint a,b = 0;\n" +
                "\t\tint j =0;\n" +
                "\t\tint f = 2;\n" +
                "\t\tint g = j + f;\n" +
                "\t\tg++;\n" +
                "\t\tg = g + prova();\n" +
                "\t\tg = j + f + (al - g + p);\n" +
                "\t\tif(g <= prova(a))\n" +
                "\t\tSystem.out.println(j + j);\n" +
                "\t\tSystem.out.println(al);\n" +
                "\t}\n" +
                "}");
    }

    @Test
    public void parseFilesInDir() throws Exception
    {
        assertEquals(visitor.parseFilesInDir(dirPath),2);
    }

    @Test
    public void parse() throws Exception
    {
        visitor.parseFilesInDir(dirPath);
        assertEquals(visitor.getNames().keySet().size(),10);
        assertEquals(visitor.getOperators().keySet().size(),5);
    }

}