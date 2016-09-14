/**
 * Created by Marco on 13/09/16.
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.eclipse.core.internal.localstore.Bucket;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;


public class Main
{

    public static void main(String[] args) throws IOException
    {
        HalsteadVisitor visitor = new HalsteadVisitor();
        visitor.ParseFilesInDir();
        //System.out.println(((HalsteadVisitor)visitor).getOperators().toString());
        //System.out.println(((HalsteadVisitor)visitor).getNames().toString());
        Integer n1 = visitor.getNames().keySet().size();
        Integer n2 = visitor.getOperators().keySet().size();
        Integer N1 = 0;
        Integer N2 = 0;

        for(String key : visitor.getNames().keySet())
        {
            N1 += visitor.getNames().get(key);
        }

        for(String key : visitor.getOperators().keySet())
        {
            N2 += visitor.getOperators().get(key);
        }
        HalsteadComplexity hc = new HalsteadComplexity(n1,n2,N1,N2);



        System.out.println(
                "Distinct operands = " + n1 + "\n"
                + "Distinct operators = " + n2 + "\n"
                + "Total operands = " + N1 + "\n"
                + "Total Operators = " + N2 + "\n"
                + "Program Vocabulary = " + hc.getVocabulary() + "\n"
                + "Program Length = " + hc.getLength() + "\n"
                + "Calculated program Length = " + hc.getCalculatedLength() + "\n"
                + "Volume = " + hc.getVolume() + "\n"
                + "Difficult = " + hc.getDifficult() + "\n"
                + "Effort = " + hc.getEffort() + "\n"
                + "Time required = " + hc.timeRequired() + " seconds\n"
                + "Bugs delivered = " + hc.deliveredBugs()
        );
    }


}

