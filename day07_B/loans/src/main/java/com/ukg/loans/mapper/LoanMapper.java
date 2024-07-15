package com.ukg.loans.mapper;

import com.ukg.loans.dto.LoansDto;
import com.ukg.loans.entity.Loans;

public class LoanMapper {

    public static Loans mapToLoans(LoansDto loansDto, Loans loans){
        loans.setLoanNumber(loansDto.getLoanNumber());
        loans.setLoanType(loansDto.getLoanType());
        loans.setTotalLoan(loansDto.getTotalLoan());
        loans.setAmountPaid(loansDto.getAmountPaid());
        loans.setMobileNumber(loansDto.getMobileNumber());
        loans.setOutstandingAmount(loansDto.getOutstandingAmount());
        return loans;
    }

    public static LoansDto mapToLoansDto(Loans loan, LoansDto loansDto){
        loansDto.setLoanNumber(loan.getLoanNumber());
        loansDto.setLoanType(loan.getLoanType());
        loansDto.setTotalLoan(loan.getTotalLoan());
        loansDto.setAmountPaid(loan.getAmountPaid());
        loansDto.setMobileNumber(loan.getMobileNumber());
        loansDto.setOutstandingAmount(loan.getOutstandingAmount());
        return loansDto;
    }
}
