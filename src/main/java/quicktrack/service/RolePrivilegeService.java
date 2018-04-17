package quicktrack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quicktrack.entity.Fleet;
import quicktrack.entity.Privilege;
import quicktrack.entity.Role;
import quicktrack.entity.Vehicle;
import quicktrack.repository.FleetRepository;
import quicktrack.repository.PrivilegeRepository;
import quicktrack.repository.RoleRepository;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class RolePrivilegeService {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PrivilegeRepository privilegeRepository;

    public String saveRole(Role role){
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String date = formatter.format(new Date());
        role.setId(null);
        role.setCreatedDate(date);
        role.setModifiedDate(date);
        roleRepository.save(role);
        return "success";
    }

    public String savePrivilege(Privilege privilege){
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String date = formatter.format(new Date());
        privilege.setId(null);
        privilege.setCreatedDate(date);
        privilege.setModifiedDate(date);
        privilegeRepository.save(privilege);
        return "success";
    }




}
