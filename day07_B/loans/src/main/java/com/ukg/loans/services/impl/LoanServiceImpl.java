package com.ukg.loans.services.impl;

import com.ukg.loans.LoansApplication;
import com.ukg.loans.dto.LoansDto;
import com.ukg.loans.entity.Loans;
import com.ukg.loans.exceptions.LoanAlreadyExistsException;
import com.ukg.loans.mapper.LoanMapper;
import com.ukg.loans.repository.LoanRepository;
import com.ukg.loans.services.ILoanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@AllArgsConstructor
@Service
public class LoanServiceImpl implements ILoanService {

    private final LoanRepository loanRepository;

    /**
     * @param mobileNumber - String
     */
    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loans> loanExists = loanRepository.findByMobileNumber(mobileNumber);
        if(loanExists.isPresent()){
            throw new LoanAlreadyExistsException("Mobile number already exists for loan");
        }

        LoansDto loansDto = createNewLoan(mobileNumber);
        Loans loan = LoanMapper.mapToLoans(loansDto, new Loans());
        loanRepository.save(loan);
    }

    private LoansDto createNewLoan(String mobileNumber) {
        long randomLoanNumber = 10000000000L + new Random().nextInt(800000000);
        LoansDto loansDto = new LoansDto();
        loansDto.setLoanNumber(String.valueOf(randomLoanNumber));
        loansDto.setTotalLoan(10000);
        loansDto.setLoanType("Home Loan");
        loansDto.setAmountPaid(0);
        loansDto.setOutstandingAmount(10000);
        loansDto.setMobileNumber(mobileNumber);
        return loansDto;

    }
}
