package quicktrack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import quicktrack.entity.Fleet;
import quicktrack.entity.Privilege;
import quicktrack.entity.Role;
import quicktrack.entity.Vehicle;
import quicktrack.service.FleetService;
import quicktrack.service.RolePrivilegeService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/rolePrivilege")
public class RolePrivilegeController {

    @Autowired
    RolePrivilegeService rolePrivilegeService;

    @RequestMapping(value = "/saveRole", method = RequestMethod.POST)
    public String saveRole(HttpServletRequest request,
                           @RequestBody Role role) {
        rolePrivilegeService.saveRole(role);
        return "success";
    }

    @RequestMapping(value = "/savePrivilege", method = RequestMethod.POST)
    public String savePrivilege(HttpServletRequest request,
                           @RequestBody Privilege privilege) {
        rolePrivilegeService.savePrivilege(privilege);
        return "success";
    }


}
