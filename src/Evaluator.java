import java.util.LinkedList;

/**
 * Helper class for evaluating expressions
 */
public final class Evaluator {
    // no instantiation allowed
    private Evaluator(){
    }

    /**
     * Evaluates a math expression given in the form of a linked list
     * @param list
     * @return the evaluated expression as a double
     */
    public static double eval(LinkedList<Object> list){
        if(list.size()==1) {
            return (Double) list.get(0);
        }

        double result = (Double) list.get(0);
        for(int i = 1; i < list.size(); i+=2){
            if(list.get(i).equals(Operand.ADD)){
                result += (Double) list.get(i+1);
            }
            else if(list.get(i).equals(Operand.SUB)){
                result -= (Double) list.get(i+1);
            }
            else if (list.get(i).equals(Operand.MUL)){
                result *= (Double) list.get(i+1);
            }
            else if (list.get(i).equals(Operand.DIV)){
                result /= (Double) list.get(i+1);
            }
        }
        return result;
    }
}
