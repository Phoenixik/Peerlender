package domain.exception;

public class LoanApplicationNotFoundException extends RuntimeException
{
    public LoanApplicationNotFoundException(Long loanApplicationId) {
        super("Loan Application with id: "+ loanApplicationId + "not found");
    }
}
