package org.softuni.productshop.service;

import org.softuni.productshop.domain.models.service.RoleServiceModel;
import org.softuni.productshop.domain.models.service.UserServiceModel;

import java.util.Set;

public interface RoleService {

    void seedRolesInDb();

//    void assignUserRoles(UserServiceModel userServiceModel, long numberOfUsers);

    Set<RoleServiceModel> findAllRoles();

    RoleServiceModel findByAuthority(String authority);
}
