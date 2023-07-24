package cse_machine;

/**
 * 
 * @author navindu-ds
 */

/*
 * Class to generate Exceptions caused during CSE evaluation phase
 */
public class EvaluationException extends RuntimeException {
    
    public EvaluationException(String message) {
        super(message);
    }
}
