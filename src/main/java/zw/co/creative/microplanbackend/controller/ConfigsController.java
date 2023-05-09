package zw.co.creative.microplanbackend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import zw.co.creative.microplanbackend.common.response.CommonResponse;
import zw.co.creative.microplanbackend.domain.LoanCreditConfigs;
import zw.co.creative.microplanbackend.domain.dto.LoanCreditConfigsDto;
import zw.co.creative.microplanbackend.domain.dto.LoanInterestConfigsDto;
import zw.co.creative.microplanbackend.service.LoanCreditConfigsService;
import zw.co.creative.microplanbackend.service.LoanInterestConfigsService;

import java.util.Objects;

@Controller
@Slf4j
@RequestMapping("/configs")
public class ConfigsController {

    @Autowired
    private LoanCreditConfigsService loanCreditConfigsService;

    @Autowired
    private LoanInterestConfigsService loanInterestConfigsService;

    @Autowired
    private AuthenticatedEmployee authenticatedEmployee;


    @RequestMapping("/add/credit")
    public String addCreditConfig(Model model) {
        model.addAttribute("name", authenticatedEmployee.getAuthenticatedUser().getFirstName() +
                " " + authenticatedEmployee.getAuthenticatedUser().getLastName());
        return "/pages/configurations/create_credit_configs";
    }

    @PostMapping("/create/credit")
    public String createCreditConfig(@Validated LoanCreditConfigsDto loanCreditConfigsDto, Model model) {
        log.info("Dto : {}", loanCreditConfigsDto);
        if (Objects.nonNull(loanCreditConfigsDto)) {
            loanCreditConfigsService.createLoanCreditConfigs(loanCreditConfigsDto);

            model.addAttribute("name", authenticatedEmployee.getAuthenticatedUser().getFirstName()
                    + " " + authenticatedEmployee.getAuthenticatedUser().getLastName());
            return "/pages/configurations/create_credit_configs";
        } else {
            model.addAttribute("name", authenticatedEmployee.getAuthenticatedUser().getFirstName()
                    + " " + authenticatedEmployee.getAuthenticatedUser().getLastName());
            return "/pages/configurations/create_credit_configs";
        }
    }

    @GetMapping("/view/credit")
    public String viewCreditConfigs(Model model){
        model.addAttribute("creditConfigs", loanCreditConfigsService.getLoanCreditConfigs().getResult());
        model.addAttribute("name",authenticatedEmployee.getAuthenticatedUser().getFirstName()+" "
                + ""+authenticatedEmployee.getAuthenticatedUser().getLastName());

        return "/pages/configurations/view_credit_configs";
    }

    @GetMapping("/update/credit/{id}")
    public void updateCreditConfigs(Model model, @PathVariable("id") Long id, Errors errors){
        LoanCreditConfigs loanCreditConfigs=loanCreditConfigsService.findLoanCreditConfigById(id);
        model.addAttribute("creditConfig", loanCreditConfigs);
    }

    @PostMapping("/edit/credit/{id}")
    public String editCreditConfigs(Model model, @PathVariable("id") Long id, @Validated LoanCreditConfigsDto
            loanCreditConfigsDto, Errors errors){

        log.info("Update LoanCreditConfigsDto-----------: {}", loanCreditConfigsDto);
        if(errors.hasErrors()){
            return "/pages/configurations/view_credit_configs";
        }
        CommonResponse updateProduct = loanCreditConfigsService.updateLoanCreditConfigs(id,loanCreditConfigsDto);
        log.info("Update Product response-----: {}",updateProduct.getResult());

        return "redirect:/configs/view/credit";
    }




    @RequestMapping("/add/interest")
    public String addInterestConfig(Model model) {
        model.addAttribute("name", authenticatedEmployee.getAuthenticatedUser().getFirstName() +
                " " + authenticatedEmployee.getAuthenticatedUser().getLastName());
        return "/pages/configurations/create_interest_configs";
    }

    @PostMapping("/create/interest")
    public String createInterestConfig(@Validated LoanInterestConfigsDto loanInterestConfigsDto, Model model) {
        log.info("Dto : {}", loanInterestConfigsDto);
        if (Objects.nonNull(loanInterestConfigsDto)) {
            loanInterestConfigsService.createLoanInterestConfigs(loanInterestConfigsDto);

            model.addAttribute("name", authenticatedEmployee.getAuthenticatedUser().getFirstName()
                    + " " + authenticatedEmployee.getAuthenticatedUser().getLastName());
            return "/pages/configurations/create_interest_configs";
        } else {
            model.addAttribute("name", authenticatedEmployee.getAuthenticatedUser().getFirstName()
                    + " " + authenticatedEmployee.getAuthenticatedUser().getLastName());
            return "/pages/configurations/create_interest_configs";
        }
    }

    @GetMapping("/view/interest")
    public String viewInterestConfigs(Model model){
        model.addAttribute("interestConfigs", loanInterestConfigsService.getLoanInterestConfigs().getResult());
        model.addAttribute("name",authenticatedEmployee.getAuthenticatedUser().getFirstName()+
                " "+authenticatedEmployee.getAuthenticatedUser().getLastName());

        return "/pages/configurations/view_interest_configs";
    }

    @PostMapping("/edit/interest/{id}")
    public String editInterestConfigs(Model model, @PathVariable("id") Long id, @Validated LoanInterestConfigsDto
            interestConfigsDto, Errors errors){

        log.info("Update LoanInterestConfigsDto-----------: {}", interestConfigsDto);
        if(errors.hasErrors()){
            return "/pages/configurations/view_interest_configs";
        }
        CommonResponse updateLoanInterestConfigs = loanInterestConfigsService.updateLoanInterestConfigs(id,interestConfigsDto);
        log.info("Update Product response-----: {}",updateLoanInterestConfigs.getResult());

        return "redirect:/configs/view/interest";
    }
}
