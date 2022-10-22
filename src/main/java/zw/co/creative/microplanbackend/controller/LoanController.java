package zw.co.creative.microplanbackend.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zw.co.creative.microplanbackend.domain.Loan;
import zw.co.creative.microplanbackend.domain.dto.LoanDto;
import zw.co.creative.microplanbackend.persistance.LoanRepository;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("api/loan")
public class LoanController {
    @Autowired
    LoanRepository loanRepository;

    @PostMapping("/create")
    public Loan addLoan(@RequestBody LoanDto loanDto) {
        log.info("Loan Dto------------:{}", loanDto);
        Loan savedLoan = Loan.builder()
                .name(loanDto.getName())
                .surname(loanDto.getSurname())
                .build();
        Loan saved = loanRepository.save(savedLoan);
        return saved;

    }

    @GetMapping("/getAll")
    public List<Loan> getAll() {
        List<Loan> all = loanRepository.findAll();
        log.info("All Loans-----------{}",all);
        return all;
    }
}
