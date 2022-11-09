package zw.co.creative.microplanbackend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import zw.co.creative.microplanbackend.common.response.ProductResponse;
import zw.co.creative.microplanbackend.domain.DocumentUpload;
import zw.co.creative.microplanbackend.domain.LoanApplication;
import zw.co.creative.microplanbackend.domain.SSBApplication;
import zw.co.creative.microplanbackend.enums.CreationStatus;
import zw.co.creative.microplanbackend.persistance.DocumentsUploadRepository;
import zw.co.creative.microplanbackend.persistance.LoanApplicationRepository;
import zw.co.creative.microplanbackend.service.CreativeUserService;
import zw.co.creative.microplanbackend.service.ProductService;
import zw.co.creative.microplanbackend.service.impl.SSBApplicationServiceImpl;

import java.util.List;

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
    public String home(Model model){


        List<ProductResponse>  result = (List<ProductResponse>)productService.getAllProducts().getResult();
        model.addAttribute("totalApplications",ssbApplicationService.findAll().size());
        model.addAttribute("totalAgents",creativeUserService.getAllCreativeUsersByStatusAndRole(CreationStatus.ACTIVE,"agent").size());
        model.addAttribute("totalProducts",result.size());
         model.addAttribute("name",authenticatedEmployee.getAuthenticatedUser().getFirstName()+" "+authenticatedEmployee.getAuthenticatedUser().getLastName());
        return "pages/home/dashboard";
    }


    @GetMapping(value = "/viewApplication")
    public String APPLICATION(Model model){
        List<LoanApplication> applications= loanApplicationRepository.findAll();
        log.info("Application------: {}",applications);

        model.addAttribute("applications",applications);
        model.addAttribute("space", " ");
        model.addAttribute("name",authenticatedEmployee.getAuthenticatedUser().getFirstName()+" "+authenticatedEmployee.getAuthenticatedUser().getLastName());
        return "pages/applications/view";
    }

    @GetMapping(value = "/viewApplicationForm")
    public String viewAPPLICATION(@RequestParam(value = "id",required = false) long id, Model model){
        LoanApplication ssbApplication=loanApplicationRepository.findById(id).get();
        model.addAttribute("SSBApplication", ssbApplication);
        String ref= ssbApplication.getUniqueRef();


        String employeeCodeNumbers=ssbApplication.getEmployeeNumber().substring(0,7);
        log.info("employeeCodeNumbers-----------: {}",employeeCodeNumbers);
        String employeeCodeCheckDidgit=ssbApplication.getEmployeeNumber().substring(7);
        log.info("employeeCodeCheckDidgit----------: {}",employeeCodeCheckDidgit);
        DocumentUpload documentUpload=documentsUploadRepository.findByLoanUniqueRef(ref);
        model.addAttribute("documentUpload",documentUpload);
        model.addAttribute("space"," ");
        model.addAttribute("employeeCodeNumbers",employeeCodeNumbers);
        model.addAttribute("employeeCodeCheckDidgit",employeeCodeCheckDidgit);
        model.addAttribute("name",authenticatedEmployee.getAuthenticatedUser().getFirstName()+" "+authenticatedEmployee.getAuthenticatedUser().getLastName());
        return "pages/applications/applicationForm";
    }
}



