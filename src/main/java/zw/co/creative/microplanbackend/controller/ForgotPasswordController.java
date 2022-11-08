package zw.co.creative.microplanbackend.controller;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import zw.co.creative.microplanbackend.common.Utility;
import zw.co.creative.microplanbackend.domain.CreativeUser;
import zw.co.creative.microplanbackend.domain.dto.CreativeUserDto;
import zw.co.creative.microplanbackend.service.CreativeUserService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Controller
@Slf4j
public class ForgotPasswordController {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private CreativeUserService creativeUserService;

    @GetMapping("/forgot_password")
    public String showForgotPasswordForm() {
        return "pages/access/forgot_password";
    }

    @PostMapping("/forgot_password")
    public String processForgotPassword(HttpServletRequest request, Model model) {
        String email = request.getParameter("email");
        log.info("Email-----: {}", email);
        String token = RandomString.make(30);

        try {
            creativeUserService.updateUserResetPasswordToken(token, email);
            String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;
            log.info("resetPasswordLink-----: {}", resetPasswordLink);
            sendEmail(email, resetPasswordLink);
            model.addAttribute("message", "We have sent a reset password link to your email. Please check.");

        } catch (UnsupportedEncodingException | MessagingException e) {
            model.addAttribute("error", "Error while sending email");
        } catch (Exception ex) {
            log.info("Error---: {}", ex);
            model.addAttribute("error", ex.getMessage());
        }
        return "pages/access/forgot_password";
    }

    public void sendEmail(String recipientEmail, String link) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");

        helper.setFrom("pridemoreviriri@gmail.com");
        helper.setTo(recipientEmail);

        String subject = "Here's the link to reset your password";

        String content = "<p>Hello,</p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><a href=\"" + link + "\">Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";

        helper.setSubject(subject);

        helper.setText(content, true);

        mailSender.send(message);
    }


    @GetMapping("/reset_password")
    public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
        log.info("Token----: {}",token);
        CreativeUser creativeUser = creativeUserService.getByResetPasswordToken(token);
        log.info("User with password reset-----: {}", creativeUser);
        model.addAttribute("token", token);

        if (creativeUser == null) {
            model.addAttribute("message", "Invalid Token");
            return "pages/access/login";
        }

        return "pages/access/reset_password";
    }

    @PostMapping("/reset_password")
    public String processResetPassword(HttpServletRequest request, Model model) {
        String token = request.getParameter("token");
        String password = request.getParameter("password");
        log.info("Token-----: {}",token);

        CreativeUser creativeUser = creativeUserService.getByResetPasswordToken(token);
        model.addAttribute("title", "Reset your password");

        if (creativeUser == null) {
            model.addAttribute("message", "Invalid Token");
            return "pages/access/login";
        } else {
            creativeUser.setPassword(password);
//            CreativeUserDto updateCreativeUserDto = CreativeUserDto.builder()
//                    .firstName(creativeUser.getFirstName())
//                    .lastName(creativeUser.getLastName())
//                    .email(creativeUser.getEmail())
//                    .cellNumber(creativeUser.getCellNumber())
//                    .gender(creativeUser.getGender())
//                    .date_of_birth(creativeUser.getDate_of_birth().toString())
//                    .houseAddress(creativeUser.getHouseAddress())
//                    .password()
//                    .role(creativeUser.getRole())
//                    .build();


            creativeUserService.resetPassword(creativeUser,password );

            model.addAttribute("message", "You have successfully changed your password.");
        }

        return "pages/access/login";
    }
}
