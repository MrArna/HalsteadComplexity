import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Marco on 14/09/16.
 */
public class HalsteadComplexityTest {

    private HalsteadComplexity hc;

    @Before
    public void  init()
    {
        hc = new HalsteadComplexity(8,5,32,12);
    }

    @Test
    public void getVocabulary() throws Exception
    {
        assertEquals(hc.getVocabulary(),13.0,1);
    }

    @Test
    public void getLength() throws Exception
    {
        assertEquals(hc.getLength(),44.0,1);
    }

    @Test
    public void getCalculatedLength() throws Exception
    {
        assertEquals(hc.getCalculatedLength(),35.60964047443681,1);
    }

    @Test
    public void getVolume() throws Exception
    {
        assertEquals(hc.getVolume(),128.98434023390084,1);
    }

    @Test
    public void getDifficult() throws Exception
    {
        assertEquals(hc.getDifficult(),9.6,1);
    }

    @Test
    public void getEffort() throws Exception
    {
        assertEquals(hc.getEffort(),1238.249666245448,1);

    }

    @Test
    public void timeRequired() throws Exception
    {
        assertEquals(hc.timeRequired(),68.79164812474711,1);

    }

    @Test
    public void deliveredBugs() throws Exception
    {
        assertEquals(hc.deliveredBugs(),0.04299478007796695,1);

    }

}