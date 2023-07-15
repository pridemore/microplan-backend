package zw.co.creative.microplanbackend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

/*
Created by alfred on 22 Apr 2020
*/
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/resources")
public class ResourceController {

    @Value("${images.path.resources}")
    private String resources = "/opt/creative/media/";
    @GetMapping("/{fileName:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable("fileName") String fileName, Model model) {
        log.info("fileName : {}",fileName);
        return getResourceFromFileSystem(fileName);
    }

    private ResponseEntity<Resource> getResourceFromFileSystem(String fileName) {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-type", "image/*");
        //String resources = "/opt/creative/media/";

        final String fullPath = String.format("%s/%s", resources, fileName);
        Resource resourceFile = loadAsResource(Paths.get(fullPath));
        return new ResponseEntity<>(resourceFile, httpHeaders, HttpStatus.OK);
    }


    private Resource loadAsResource(Path path) {
        if (path == null) {
            throw new IllegalArgumentException("Could not read file from null path");
        }
        //String currentTenant = TenantContext.getCurrentTenant().getCode();
        final String tenantPathString = path.toString();//.replace(TENANT_IDENTIFIER_PREFIX, currentTenant);
        Path tenantAwarePath = Paths.get(tenantPathString);
        try {
            Resource resource = new UrlResource(tenantAwarePath.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new IllegalArgumentException(
                        "Could not read file: " + path);

            }
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Could not read file: " + path, e);
        }
    }
}