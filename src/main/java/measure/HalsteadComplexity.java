package measure;

/**
 * Created by Marco on 14/09/16.
 *
 * This class return the measures of the Halstead complexity.
 * For further indormation visit: https://en.wikipedia.org/wiki/Halstead_complexity_measures
 *
 *
 */
public class HalsteadComplexity
{
    private Integer distinctOperands;
    private Integer distinctOperators;
    private Integer totalOperands;
    private Integer totalOperators;


    public HalsteadComplexity(Integer numDistinctOp, Integer numDistinctOpr, Integer totalOperands, Integer totalOperators) {
        this.distinctOperands = numDistinctOp;
        this.distinctOperators = numDistinctOpr;
        this.totalOperands = totalOperands;
        this.totalOperators = totalOperators;
    }


    public Double getVocabulary() {
        return (double) distinctOperands + distinctOperators;
    }

    public Double getLength() {
        return (double) totalOperands + totalOperators;
    }

    public Double getCalculatedLength() {
        return (double) distinctOperands * (Math.log(distinctOperands) / Math.log(2))
                + distinctOperators * (Math.log(distinctOperators) / Math.log(2));
    }

    public Double getVolume() {
        return getLength() * (Math.log(getVocabulary() / Math.log(2)));
    }

    public Double getDifficult() {
        return (double) distinctOperands / 2 * totalOperators / distinctOperators;
    }

    public Double getEffort() {
        return getVolume() * getDifficult();
    }

    public Double timeRequired()
    {
        return (double) getEffort()/18;
    }

    public Double deliveredBugs()
    {
        return (double) getVolume()/3000;
    }


    public Integer getDistinctOperands() {
        return distinctOperands;
    }

    public void setDistinctOperands(Integer distinctOperands) {
        this.distinctOperands = distinctOperands;
    }

    public Integer getDistinctOperators() {
        return distinctOperators;
    }

    public void setDistinctOperators(Integer distinctOperators) {
        this.distinctOperators = distinctOperators;
    }

    public Integer getTotalOperands() {
        return totalOperands;
    }

    public void setTotalOperands(Integer totalOperands) {
        this.totalOperands = totalOperands;
    }

    public Integer getTotalOperators() {
        return totalOperators;
    }

    public void setTotalOperators(Integer totalOperators) {
        this.totalOperators = totalOperators;
    }
}
