package zw.co.creative.microplanbackend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.allegro.finance.tradukisto.MoneyConverters;
import zw.co.creative.microplanbackend.common.Utility;
import zw.co.creative.microplanbackend.common.response.ProductResponse;
import zw.co.creative.microplanbackend.domain.CreativeUser;
import zw.co.creative.microplanbackend.domain.DocumentUpload;
import zw.co.creative.microplanbackend.domain.LoanApplication;
import zw.co.creative.microplanbackend.domain.SSBApplication;
import zw.co.creative.microplanbackend.domain.dto.LoanApplicationDTO;
import zw.co.creative.microplanbackend.enums.CreationStatus;
import zw.co.creative.microplanbackend.persistance.DocumentsUploadRepository;
import zw.co.creative.microplanbackend.persistance.LoanApplicationRepository;
import zw.co.creative.microplanbackend.service.CreativeUserService;
import zw.co.creative.microplanbackend.service.ProductService;
import zw.co.creative.microplanbackend.service.impl.SSBApplicationServiceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@Slf4j
public class HomeController {

    @Autowired
    private SSBApplicationServiceImpl ssbApplicationService;

    @Autowired
    private LoanApplicationRepository loanApplicationRepository;

    @Autowired
    private DocumentsUploadRepository documentsUploadRepository;

    @Autowired
    private AuthenticatedEmployee authenticatedEmployee;

    @Autowired
    private CreativeUserService creativeUserService;

    @Autowired
    private ProductService productService;

    private SSBApplication ssbApplication;

    @GetMapping(value = "/dashboard")
    public String home(Model model) {


        List<ProductResponse> result = (List<ProductResponse>) productService.getAllProducts().getResult();
        model.addAttribute("totalApplications", ssbApplicationService.findAll().size());
        model.addAttribute("totalAgents", creativeUserService.getAllCreativeUsersByStatusAndRole(CreationStatus.ACTIVE, "agent").size());
        model.addAttribute("totalProducts", result.size());
        model.addAttribute("name", authenticatedEmployee.getAuthenticatedUser().getFirstName() + " " + authenticatedEmployee.getAuthenticatedUser().getLastName());
        return "pages/home/dashboard";
    }


    @GetMapping(value = "/viewApplication")
    public String APPLICATION(Model model) {
        List<LoanApplicationDTO> applicationsDTOsList = new ArrayList<>();
        List<LoanApplication> applications = loanApplicationRepository.getAllLoans();
        log.info("Application------: fetched");
        //applications.stream().forEachOrdered(a -> processDTOs(a, applicationsDTOsList));

        for(LoanApplication application:applications){
            processDTOs(application, applicationsDTOsList);
        }
        model.addAttribute("applications", applicationsDTOsList);
        model.addAttribute("space", " ");
        model.addAttribute("name", authenticatedEmployee.getAuthenticatedUser().getFirstName() + " " + authenticatedEmployee.getAuthenticatedUser().getLastName());
        return "pages/applications/view";
    }

    private void processDTOs(LoanApplication application, List<LoanApplicationDTO> applicationsDTOsList) {
        CreativeUser applicationAgent = creativeUserService.getUserById(application.getAgentId());
        if (Objects.nonNull(applicationAgent)) {
            LoanApplicationDTO loanApplicationDTO = LoanApplicationDTO.builder()
                    .id(application.getId())
                    .uniqueRef(application.getUniqueRef())
                    .applicationTitle(application.getApplicationTitle())
                    .agentId(applicationAgent.getId())
                    .agentFirstName(applicationAgent.getFirstName())
                    .agentLastName(applicationAgent.getLastName())
                    .loanPurpose(application.getLoanPurpose())
                    .firstName(application.getFirstName())
                    .lastName(application.getLastName())
                    .employeeNumber(application.getEmployeeNumber())
                    .status(application.getStatus())
                    .dateCreated(application.getDateCreated())
                    .lastUpdated(application.getLastUpdated())
                    .build();
            applicationsDTOsList.add(loanApplicationDTO);
        }
    }


    @GetMapping(value = "/viewApplicationForm")
    public String viewAPPLICATION(@RequestParam(value = "id", required = false) long id, Model model) {
        LoanApplication ssbApplication = loanApplicationRepository.findById(id).get();
        model.addAttribute("SSBApplication", ssbApplication);
        String interestRate = ssbApplication.getInterestRate().substring(2);
        model.addAttribute("interestRate", interestRate);
        String ref = ssbApplication.getUniqueRef();
        log.info("Money Value-----------: {}", ssbApplication.getApprovedLoanAmount());
        log.info("Money in words-----------: {}", Utility.convertMoneyToNumberMain(ssbApplication.getApprovedLoanAmount()));

        String employeeCodeNumbers = ssbApplication.getEmployeeNumber().substring(0, 7);
        log.info("employeeCodeNumbers-----------: {}", employeeCodeNumbers);
        String employeeCodeCheckDidgit = ssbApplication.getEmployeeNumber().substring(7);
        log.info("employeeCodeCheckDidgit----------: {}", employeeCodeCheckDidgit);
        CreativeUser agent = creativeUserService.getUserById(ssbApplication.getAgentId());
        DocumentUpload documentUpload = null;
        try {
            documentUpload = documentsUploadRepository.findByLoanUniqueRef(ref);
        } catch (Exception ex) {
            documentUpload = documentsUploadRepository.findAllByLoanUniqueRef(ref)
                    .stream().findFirst().get();
        }
        model.addAttribute("documentUpload", documentUpload);
        model.addAttribute("space", " ");
        model.addAttribute("employeeCodeNumbers", employeeCodeNumbers);
        model.addAttribute("employeeCodeCheckDidgit", employeeCodeCheckDidgit);
        model.addAttribute("agentName", agent.getFirstName() + " " + agent.getLastName());
        model.addAttribute("loanPriceInWords", Utility.convertMoneyToNumberMain(ssbApplication.getApprovedLoanAmount()));
        model.addAttribute("name", authenticatedEmployee.getAuthenticatedUser().getFirstName() + " " + authenticatedEmployee.getAuthenticatedUser().getLastName());
        return "pages/applications/applicationForm";
    }

}



