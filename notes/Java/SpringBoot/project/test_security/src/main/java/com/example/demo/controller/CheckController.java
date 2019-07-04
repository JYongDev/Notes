package com.example.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/check")
@RestController
public class CheckController {

    @RequestMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String checkAdmin() {
        return "check admin Success";
    }

    @RequestMapping("/user")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String checkUser() {
        return "check user Success";
    }

    @RequestMapping("/visitor")
    @PreAuthorize("hasRole('ROLE_VISITOR')")
    public String checkVisitor() {
        return "check visitor Success";
    }

    @RequestMapping("/pvisit")
    @PreAuthorize("hasPermission('','visit')")
    public String checkPVisit() {
        return "check permission visit success";
    }

    @RequestMapping("/pread")
    @PreAuthorize("hasPermission('','read')")
    public String checkPRead() {
        return "check permission read success";
    }

    @RequestMapping("/pwrite")
    @PreAuthorize("hasPermission('','write')")
    public String checkPWrite() {
        return "check permission write success";
    }

    @RequestMapping("/pexecute")
    @PreAuthorize("hasPermission('','execute')")
    public String checkPExecute() {
        return "check permission execute success";
    }

    @RequestMapping("/porder")
    @PreAuthorize("hasPermission('','order')")
    public String checkPOrder() {
        return "check permission order success";
    }
}
