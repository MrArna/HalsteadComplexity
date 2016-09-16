/**
 * Created by Marco on 13/09/16.
 */
import java.io.File;
import java.io.IOException;

import static java.lang.System.exit;


public class Main
{

    public static void main(String[] args) throws IOException
    {

        if(args.length != 2)
        {
            System.out.println("Wrong usage. \n \n" + args[0] + " path/to/project/root/");
            exit(0);
        }

        HalsteadVisitor visitor = new HalsteadVisitor(); //instantiation of the visitor
        //File dirs = new File(".");
        //String dirPath = dirs.getCanonicalPath() + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator;

        String dirPath = args[1];

        visitor.parseFilesInDir(dirPath); //parsing recursively the files in the given dir

        //the number of distinct operands [n1] || operators [n2] is the number of respective keys in the map
        System.out.println(visitor.getNames().toString());

        Integer n1 = visitor.getNames().keySet().size();
        Integer n2 = visitor.getOperators().keySet().size();
        Integer N1 = 0; //total operands
        Integer N2 = 0; //total operators

        //summing up the total of operands and operators
        for(String key : visitor.getNames().keySet())
        {
            N1 += visitor.getNames().get(key);
        }

        for(String key : visitor.getOperators().keySet())
        {
            N2 += visitor.getOperators().get(key);
        }

        //instantiate the class in charge to calculate the complexity measures
        HalsteadComplexity hc = new HalsteadComplexity(n1,n2,N1,N2);


        //print out the results
        System.out.println(
                "******* HALSTEAD COMPLEXITY MEASURES *******\n\n"
                +"Distinct operands = " + n1 + "\n"
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
                + "Bugs delivered = " + hc.deliveredBugs() + "\n"
                + "*********************************************"
        );
    }


}

